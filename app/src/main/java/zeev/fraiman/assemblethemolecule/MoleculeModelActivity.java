package zeev.fraiman.assemblethemolecule;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Map;

import zeev.fraiman.assemblethemolecule.utils.FormulaUtils;

public class MoleculeModelActivity extends AppCompatActivity {

    private String formula, name;
    private WebView wvMolecule3D;
    private LinearLayout loadingLayout;
    private TextView tvLoadingStatus;

    private static final Map<String, String> MODEL_IDS = new HashMap<>();
    static {
        MODEL_IDS.put("H2O", "962");
        MODEL_IDS.put("NH3", "222");
        MODEL_IDS.put("CO2", "280");
        MODEL_IDS.put("NaCl", "5234");
        MODEL_IDS.put("HCl", "313");
        MODEL_IDS.put("H2SO4", "1118");
        MODEL_IDS.put("NaOH", "14798");
        MODEL_IDS.put("CaCO3", "10112");
        MODEL_IDS.put("Ca(OH)2", "60932");
        MODEL_IDS.put("Al(OH)3", "101760");
        MODEL_IDS.put("Fe(OH)3", "73964");
        MODEL_IDS.put("Al2O3", "14769");
        MODEL_IDS.put("Fe2O3", "14822");
        MODEL_IDS.put("Na2SO4", "24436");
        MODEL_IDS.put("K2CO3", "11430");
        MODEL_IDS.put("Fe2(SO4)3", "24826");
        MODEL_IDS.put("Ca3(PO4)2", "24219");
        MODEL_IDS.put("HNO3", "944");
        MODEL_IDS.put("CuSO4", "24462");
        MODEL_IDS.put("SiO2", "24261");

        // Covalent-bonding substance list (added for chemistry-teacher review)
        MODEL_IDS.put("H2", "783");
        MODEL_IDS.put("O2", "977");
        MODEL_IDS.put("N2", "947");
        MODEL_IDS.put("Cl2", "24526");
        MODEL_IDS.put("H2S", "402");
        MODEL_IDS.put("CH4", "297");
        MODEL_IDS.put("PH3", "24404");
        MODEL_IDS.put("SiH4", "23960");
        MODEL_IDS.put("P2H4", "139283");
        MODEL_IDS.put("N2H4", "9321");
        MODEL_IDS.put("H2O2", "784");
        MODEL_IDS.put("HCN", "768");
        MODEL_IDS.put("CS2", "6348");
        MODEL_IDS.put("ClNO", "17601");
        MODEL_IDS.put("OF2", "24547");
        MODEL_IDS.put("CCl4", "5943");
        MODEL_IDS.put("PCl3", "24387");
        MODEL_IDS.put("PF3", "62665");
        MODEL_IDS.put("BF3", "6356");
        MODEL_IDS.put("BeF2", "24589");
        MODEL_IDS.put("BeCl2", "24588");
        MODEL_IDS.put("SF2", "139605");
        MODEL_IDS.put("SCl2", "25353");
        MODEL_IDS.put("CH3Cl", "6327");
        MODEL_IDS.put("CH2Cl2", "6344");
        MODEL_IDS.put("CH2F2", "6853");
        MODEL_IDS.put("C2Cl4", "31374");
        MODEL_IDS.put("COCl2", "6371");
        MODEL_IDS.put("HOCl", "24341");
        MODEL_IDS.put("HNO2", "24529");
        MODEL_IDS.put("HCOOH", "284");
        MODEL_IDS.put("CH3COOH", "176");
        MODEL_IDS.put("CH4O", "887");
        MODEL_IDS.put("C2H6O", "702");
        MODEL_IDS.put("C3H8O", "1031");
        MODEL_IDS.put("CH3NH2", "6329");
        MODEL_IDS.put("CH2O", "712");
        MODEL_IDS.put("C2H4", "6325");
        MODEL_IDS.put("C2H6", "6324");
        MODEL_IDS.put("C3H4", "6335");
        MODEL_IDS.put("C2H5Cl", "6337");
        MODEL_IDS.put("C2H3Cl", "6338");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_molecule_model);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.model_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        formula = getIntent().getStringExtra("formula");
        name = getIntent().getStringExtra("name");

        TextView tvTitle = findViewById(R.id.tvSubstanceName);
        tvTitle.setText(name);

        TextView tvFormula = findViewById(R.id.tvFormula);
        tvFormula.setText(FormulaUtils.formatForDisplay(formula));

        wvMolecule3D = findViewById(R.id.wvMolecule3D);
        loadingLayout = findViewById(R.id.loadingLayout);
        tvLoadingStatus = (TextView) loadingLayout.getChildAt(1);

        findViewById(R.id.btnCloseModel).setOnClickListener(v -> finish());

        setupWebView();
        loadMolViewModel();
    }

    private void setupWebView() {
        WebSettings settings = wvMolecule3D.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setAllowFileAccess(true);

        wvMolecule3D.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                // Aggressive CSS injection to hide all MolView menus/headers
                view.loadUrl("javascript:(function() { " +
                        "var hideList = ['header', 'search-wrapper', 'footer', 'sidebar', 'toolbar', 'dialog-overlay', 'dialog-container', 'molview-toolbar']; " +
                        "for (var i = 0; i < hideList.length; i++) { " +
                        "  var el = document.getElementById(hideList[i]) || document.querySelector('.'+hideList[i]); " +
                        "  if (el) el.style.display='none'; " +
                        "} " +
                        "document.body.style.backgroundColor = 'white'; " +
                        "document.querySelector('canvas').style.width = '100%'; " +
                        "document.querySelector('canvas').style.height = '100%'; " +
                        "})()");

                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    loadingLayout.setVisibility(View.GONE);
                    wvMolecule3D.setVisibility(View.VISIBLE);
                }, 3500);
            }
        });

        wvMolecule3D.setWebChromeClient(new WebChromeClient());
    }

    private void loadMolViewModel() {
        String id = MODEL_IDS.get(formula);
        if (id == null) {
            // No verified PubChem CID for this substance yet: don't send the raw
            // formula to molview.org, since it isn't a valid id and just produces
            // a "not found" error on their site. Fail gracefully instead.
            tvLoadingStatus.setText("3D model not available for this substance yet");
            return;
        }

        tvLoadingStatus.setText(R.string.rendering_status);

        // Use standard stable URL instead of /v1/ to avoid 404
        String url = "https://molview.org/?cid=" + id;
        wvMolecule3D.loadUrl(url);
    }
}