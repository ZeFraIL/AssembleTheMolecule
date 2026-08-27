package zeev.fraiman.assemblethemolecule.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class HelperDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "chemistry.db";
    private static final int DATABASE_VERSION = 3;
    private final Context context;

    public HelperDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
        seedData(db);
    }

    private void createTables(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS elements (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "symbol TEXT, " +
                "name TEXT, " +
                "atomic_number INTEGER, " +
                "atomic_mass REAL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS substances (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "formula TEXT, " +
                "name TEXT, " +
                "class_name TEXT, " +
                "description TEXT, " +
                "interesting_fact TEXT, " +
                "difficulty INTEGER)");

        db.execSQL("CREATE TABLE IF NOT EXISTS progress (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "substance_id INTEGER, " +
                "solved_count INTEGER DEFAULT 0, " +
                "last_solved LONG)");
    }

    private void seedData(SQLiteDatabase db) {
        seedElements(db);
        seedSubstances(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS elements");
        db.execSQL("DROP TABLE IF EXISTS substances");
        db.execSQL("DROP TABLE IF EXISTS progress");
        onCreate(db);
    }

    private void seedElements(SQLiteDatabase db) {
        try {
            String json = loadJSONFromAsset("data/elements.json");
            if (json == null) return;
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                ContentValues values = new ContentValues();
                values.put("symbol", obj.getString("symbol"));
                values.put("name", obj.getString("name"));
                values.put("atomic_number", obj.getInt("atomic_number"));
                values.put("atomic_mass", obj.getDouble("atomic_mass"));
                db.insert("elements", null, values);
            }
        } catch (Exception e) {
            Log.e("HelperDB", "Error seeding elements", e);
        }
    }

    private void seedSubstances(SQLiteDatabase db) {
        try {
            String json = loadJSONFromAsset("data/substances.json");
            if (json != null) {
                JSONArray array = new JSONArray(json);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    ContentValues values = new ContentValues();
                    values.put("formula", obj.getString("formula"));
                    values.put("name", obj.getString("name"));
                    values.put("class_name", obj.optString("class_name", ""));
                    values.put("description", obj.optString("description", ""));
                    values.put("interesting_fact", obj.optString("interesting_fact", ""));
                    values.put("difficulty", obj.optInt("difficulty", 1));
                    db.insert("substances", null, values);
                }
            }
        } catch (Exception e) {
            Log.e("HelperDB", "Error seeding substances", e);
        }
    }

    private String loadJSONFromAsset(String fileName) {
        try (InputStream is = context.getAssets().open(fileName)) {
            byte[] buffer = new byte[is.available()];
            int read = is.read(buffer);
            return new String(buffer, 0, read, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            Log.e("HelperDB", "Error reading asset: " + fileName, ex);
            return null;
        }
    }
}
