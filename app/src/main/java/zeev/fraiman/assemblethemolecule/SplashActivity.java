package zeev.fraiman.assemblethemolecule;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Hide Action Bar if it exists
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        
        setContentView(R.layout.activity_splash);

        ImageView ivSplashIcon = findViewById(R.id.ivSplashIcon);

        // Scaling animation: from 0.5x to 1.2x and back to 1.0x or just simple increase?
        // User asked for "плавно увеличиваться в размерах" (smoothly increase in size)
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                0.0f, 1.0f, // Start and end values for the X axis scaling
                0.0f, 1.0f, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
        scaleAnimation.setDuration(3000); // 3 seconds for animation
        scaleAnimation.setFillAfter(true); // Keep the result after animation ends

        ivSplashIcon.startAnimation(scaleAnimation);

        // Total delay 4.5 seconds
        new Handler(android.os.Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Close SplashActivity
        }, 4500);
    }
}
