package zeev.fraiman.assemblethemolecule;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import zeev.fraiman.assemblethemolecule.adapter.ElementAdapter;
import zeev.fraiman.assemblethemolecule.database.HelperDB;
import zeev.fraiman.assemblethemolecule.model.Element;
import zeev.fraiman.assemblethemolecule.model.Substance;
import zeev.fraiman.assemblethemolecule.utils.FormulaUtils;

public class GameActivity extends AppCompatActivity {

    private TextView tvTask, tvSubstanceName, tvFormulaDisplay;
    private RecyclerView rvElements;
    private HelperDB helperDB;
    private List<Element> allElements;
    private final List<SelectedAtom> currentFormulaAtoms = new ArrayList<>();
    private String mode;
    private Substance targetSubstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.game_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mode = getIntent().getStringExtra("mode");
        helperDB = new HelperDB(this);

        initViews();
        loadElements();
        setupGame();
    }

    private void initViews() {
        tvTask = findViewById(R.id.tvTask);
        tvSubstanceName = findViewById(R.id.tvSubstanceName);
        tvFormulaDisplay = findViewById(R.id.tvFormulaDisplay);
        rvElements = findViewById(R.id.rvElements);

        rvElements.setLayoutManager(new GridLayoutManager(this, 5));

        findViewById(R.id.btnIndexPlus).setOnClickListener(v -> changeIndex(1));
        findViewById(R.id.btnIndexMinus).setOnClickListener(v -> changeIndex(-1));
        findViewById(R.id.btnBracketOpen).setOnClickListener(v -> {
            currentFormulaAtoms.add(new SelectedAtom("(", 1));
            updateDisplay();
        });
        findViewById(R.id.btnBracketClose).setOnClickListener(v -> {
            currentFormulaAtoms.add(new SelectedAtom(")", 1));
            updateDisplay();
        });
        findViewById(R.id.btnBackspace).setOnClickListener(v -> removeLast());
        findViewById(R.id.btnClear).setOnClickListener(v -> clearFormula());
        findViewById(R.id.btnCheck).setOnClickListener(v -> checkFormula());
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void loadElements() {
        allElements = new ArrayList<>();
        SQLiteDatabase db = helperDB.getReadableDatabase();
        Cursor cursor = db.query("elements", null, null, null, null, null, "atomic_number");
        while (cursor.moveToNext()) {
            Element element = new Element();
            element.setSymbol(cursor.getString(cursor.getColumnIndexOrThrow("symbol")));
            element.setAtomicNumber(cursor.getInt(cursor.getColumnIndexOrThrow("atomic_number")));
            allElements.add(element);
        }
        cursor.close();

        ElementAdapter adapter = new ElementAdapter(allElements, element -> {
            currentFormulaAtoms.add(new SelectedAtom(element.getSymbol(), 1));
            updateDisplay();
        });
        rvElements.setAdapter(adapter);
    }

    private void setupGame() {
        if ("game".equals(mode)) {
            tvTask.setText(getString(R.string.task_prefix) + " " + getString(R.string.task_assemble));
            loadRandomSubstance();
        } else {
            tvTask.setText(getString(R.string.mode_lab));
            tvSubstanceName.setText(getString(R.string.lab_instruction));
        }
    }

    private void loadRandomSubstance() {
        SQLiteDatabase db = helperDB.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM substances ORDER BY RANDOM() LIMIT 1", null);
        if (cursor.moveToFirst()) {
            targetSubstance = new Substance();
            targetSubstance.setFormula(cursor.getString(cursor.getColumnIndexOrThrow("formula")));
            targetSubstance.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
            tvSubstanceName.setText(targetSubstance.getName());
        }
        cursor.close();
    }

    private void changeIndex(int delta) {
        if (currentFormulaAtoms.isEmpty()) return;
        SelectedAtom last = currentFormulaAtoms.get(currentFormulaAtoms.size() - 1);
        int newIndex = last.index + delta;
        if (newIndex >= 1 && newIndex <= 99) {
            last.index = newIndex;
            updateDisplay();
        }
    }

    private void clearFormula() {
        currentFormulaAtoms.clear();
        updateDisplay();
    }

    private void removeLast() {
        if (!currentFormulaAtoms.isEmpty()) {
            currentFormulaAtoms.remove(currentFormulaAtoms.size() - 1);
            updateDisplay();
        }
    }

    private void updateDisplay() {
        StringBuilder sb = new StringBuilder();
        for (SelectedAtom atom : currentFormulaAtoms) {
            sb.append(atom.symbol);
            if (atom.index > 1) {
                sb.append(atom.index);
            }
        }
        tvFormulaDisplay.setText(FormulaUtils.formatForDisplay(sb.toString()));
    }

    private void checkFormula() {
        String currentFormula = buildFormulaString();
        String normalized = FormulaUtils.normalize(currentFormula);

        if ("game".equals(mode)) {
            String targetFormula = FormulaUtils.normalize(targetSubstance.getFormula());
            if (normalized.equalsIgnoreCase(targetFormula)) {
                Toast.makeText(this, R.string.correct, Toast.LENGTH_SHORT).show();
                loadRandomSubstance();
                clearFormula();
            } else {
                String errorMsg = getString(R.string.error_assembled, 
                        FormulaUtils.formatForDisplay(normalized), 
                        FormulaUtils.formatForDisplay(targetFormula));
                Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show();
            }
        } else {
            SQLiteDatabase db = helperDB.getReadableDatabase();
            Cursor cursor = db.query("substances", null, "formula LIKE ?", new String[]{normalized}, null, null, null);
            if (cursor.moveToFirst()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                Toast.makeText(this, getString(R.string.found_prefix) + name, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.not_found, FormulaUtils.formatForDisplay(normalized)), Toast.LENGTH_LONG).show();
            }
            cursor.close();
        }
    }

    private String buildFormulaString() {
        StringBuilder sb = new StringBuilder();
        for (SelectedAtom atom : currentFormulaAtoms) {
            sb.append(atom.symbol);
            if (atom.index > 1) {
                sb.append(atom.index);
            }
        }
        return sb.toString();
    }

    private static class SelectedAtom {
        String symbol;
        int index;

        SelectedAtom(String symbol, int index) {
            this.symbol = symbol;
            this.index = index;
        }
    }
}
