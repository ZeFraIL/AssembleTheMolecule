package zeev.fraiman.assemblethemolecule;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnPlay = findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            intent.putExtra("mode", "game");
            startActivity(intent);
        });

        Button btnFreeLab = findViewById(R.id.btnFreeLab);
        btnFreeLab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            intent.putExtra("mode", "lab");
            startActivity(intent);
        });

        Button btnDictionary = findViewById(R.id.btnDictionary);
        btnDictionary.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SubstanceListActivity.class);
            startActivity(intent);
        });

        Button btnDraw = findViewById(R.id.btnDraw);
        btnDraw.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DrawingActivity.class);
            startActivity(intent);
        });

        Button btnAchievements = findViewById(R.id.btnAchievements);
        btnAchievements.setOnClickListener(v -> {
            // Future implementation
        });
    }
}
