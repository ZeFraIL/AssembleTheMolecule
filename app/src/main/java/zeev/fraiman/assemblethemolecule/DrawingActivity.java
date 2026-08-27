package zeev.fraiman.assemblethemolecule;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import zeev.fraiman.assemblethemolecule.adapter.ElementAdapter;
import zeev.fraiman.assemblethemolecule.database.HelperDB;
import zeev.fraiman.assemblethemolecule.model.Element;
import zeev.fraiman.assemblethemolecule.model.Substance;
import zeev.fraiman.assemblethemolecule.utils.AiAssistant;
import zeev.fraiman.assemblethemolecule.utils.DrawingView;
import zeev.fraiman.assemblethemolecule.utils.FormulaUtils;

public class DrawingActivity extends AppCompatActivity {

    private DrawingView drawingView;
    private TextView tvDrawTaskName, tvDrawTaskFormula, tvAiFeedback;
    private RecyclerView rvElementPalette;
    private HelperDB helperDB;
    private Substance currentTaskSubstance;
    private AiAssistant aiAssistant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_drawing);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawing_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        drawingView = findViewById(R.id.drawingCanvas);
        tvDrawTaskName = findViewById(R.id.tvDrawTaskName);
        tvDrawTaskFormula = findViewById(R.id.tvDrawTaskFormula);
        tvAiFeedback = findViewById(R.id.tvAiFeedback);
        rvElementPalette = findViewById(R.id.rvElementPalette);
        helperDB = new HelperDB(this);
        aiAssistant = new AiAssistant();

        setupPalette();
        loadRandomTask();

        findViewById(R.id.btnClearCanvas).setOnClickListener(v -> drawingView.clearCanvas());
        findViewById(R.id.btnAnalyzeAi).setOnClickListener(v -> analyzeWithAi());
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void setupPalette() {
        rvElementPalette.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        List<Element> elements = new ArrayList<>();
        SQLiteDatabase db = helperDB.getReadableDatabase();
        Cursor cursor = db.query("elements", null, null, null, null, null, "atomic_number");
        while (cursor.moveToNext()) {
            Element e = new Element();
            e.setSymbol(cursor.getString(cursor.getColumnIndexOrThrow("symbol")));
            elements.add(e);
        }
        cursor.close();

        ElementAdapter adapter = new ElementAdapter(elements, element -> {
            drawingView.setCurrentElement(element.getSymbol());
            Toast.makeText(this, "Tap on canvas to place " + element.getSymbol(), Toast.LENGTH_SHORT).show();
        });
        rvElementPalette.setAdapter(adapter);
    }

    private void loadRandomTask() {
        SQLiteDatabase db = helperDB.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM substances ORDER BY RANDOM() LIMIT 1", null);
        if (cursor.moveToFirst()) {
            currentTaskSubstance = new Substance();
            currentTaskSubstance.setFormula(cursor.getString(cursor.getColumnIndexOrThrow("formula")));
            currentTaskSubstance.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
            
            tvDrawTaskName.setText(currentTaskSubstance.getName());
            tvDrawTaskFormula.setText(FormulaUtils.formatForDisplay(currentTaskSubstance.getFormula()));
        }
        cursor.close();
    }

    private void analyzeWithAi() {
        Bitmap sketch = drawingView.getBitmap();
        tvAiFeedback.setVisibility(View.VISIBLE);
        tvAiFeedback.setText("AI is analyzing your sketch...");
        tvAiFeedback.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));

        aiAssistant.checkStructure(
                sketch, 
                currentTaskSubstance.getName(), 
                currentTaskSubstance.getFormula(), 
                new AiAssistant.AiCallback() {
                    @Override
                    public void onResult(String feedback, boolean isCorrect) {
                        runOnUiThread(() -> {
                            tvAiFeedback.setText(isCorrect ? "Correct! " + feedback : "Incorrect. " + feedback);
                            tvAiFeedback.setTextColor(getResources().getColor(
                                    isCorrect ? android.R.color.holo_green_dark : android.R.color.holo_red_dark));
                        });
                    }

                    @Override
                    public void onError(String error) {
                        runOnUiThread(() -> {
                            tvAiFeedback.setText("AI Error: " + error);
                            tvAiFeedback.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                        });
                    }
                }
        );
    }
}
