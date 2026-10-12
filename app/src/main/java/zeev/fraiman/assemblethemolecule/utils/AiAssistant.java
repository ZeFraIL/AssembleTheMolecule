package zeev.fraiman.assemblethemolecule.utils;

import android.graphics.Bitmap;

import zeev.fraiman.assemblethemolecule.BuildConfig;
import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.common.util.concurrent.ListenableFuture;

public class AiAssistant {

    private final GenerativeModelFutures model;

    public AiAssistant() {
        GenerativeModel gm = new GenerativeModel("gemini-3.6-flash", BuildConfig.GEMINI_API_KEY);
        this.model = GenerativeModelFutures.from(gm);
    }

    public interface AiCallback {
        void onResult(String feedback, boolean isCorrect);
        void onError(String error);
    }

    public void checkStructure(Bitmap sketch, String substanceName, String formula, AiCallback callback) {
        if (BuildConfig.GEMINI_API_KEY.isEmpty()) {
            callback.onError("Gemini API key is missing. Please add GEMINI_API_KEY to local.properties.");
            return;
        }

        String prompt = "Analyze this hand-drawn chemical structure for " + substanceName + " (" + formula + "). " +
                "1. Check if symbols and number of atoms are correct. " +
                "2. Check if chemical bonds (lines) are present and correct based on valence rules. " +
                "3. Ignore angles and drawing quality. " +
                "IMPORTANT: Respond in plain text ONLY. Do NOT use LaTeX, stars, or special formatting. " +
                "Start with 'YES' if it is correct or 'NO' if there are errors, followed by a 1-2 sentence explanation.";

        Content content = new Content.Builder()
                .addText(prompt)
                .addImage(sketch)
                .build();

        ListenableFuture<GenerateContentResponse> response = model.generateContent(content);
        
        response.addListener(() -> {
            try {
                GenerateContentResponse result = response.get();
                String text = result.getText();
                boolean isCorrect = text != null && text.trim().toUpperCase().startsWith("YES");
                String feedback = text != null ? text.replaceFirst("(?i)YES", "").replaceFirst("(?i)NO", "").trim() : "No response from AI";
                callback.onResult(feedback, isCorrect);
            } catch (Exception e) {
                callback.onError(e.getMessage());
            }
        }, Runnable::run);
    }
}
