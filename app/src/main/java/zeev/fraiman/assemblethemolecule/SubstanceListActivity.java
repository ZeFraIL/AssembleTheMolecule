package zeev.fraiman.assemblethemolecule;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import zeev.fraiman.assemblethemolecule.adapter.SubstanceAdapter;
import zeev.fraiman.assemblethemolecule.database.HelperDB;
import zeev.fraiman.assemblethemolecule.model.Substance;

public class SubstanceListActivity extends AppCompatActivity {

    private RecyclerView rvSubstances;
    private HelperDB helperDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_substance_list);

        rvSubstances = findViewById(R.id.rvSubstances);
        rvSubstances.setLayoutManager(new LinearLayoutManager(this));
        
        helperDB = new HelperDB(this);
        loadSubstances();

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void loadSubstances() {
        List<Substance> substances = new ArrayList<>();
        SQLiteDatabase db = helperDB.getReadableDatabase();
        Cursor cursor = db.query("substances", null, null, null, null, null, "name");
        while (cursor.moveToNext()) {
            Substance s = new Substance();
            s.setFormula(cursor.getString(cursor.getColumnIndexOrThrow("formula")));
            s.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
            s.setClassName(cursor.getString(cursor.getColumnIndexOrThrow("class_name")));
            substances.add(s);
        }
        cursor.close();

        SubstanceAdapter adapter = new SubstanceAdapter(substances);
        rvSubstances.setAdapter(adapter);
    }
}
