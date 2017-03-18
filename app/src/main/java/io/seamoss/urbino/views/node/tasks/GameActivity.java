package io.seamoss.urbino.views.node.tasks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import io.seamoss.urbino.BuildConfig;
import io.seamoss.urbino.R;
import io.seamoss.urbino.base.BaseActivity;

/**
 * Created by Alexander Melton on 3/17/2017.
 */

public class GameActivity extends BaseActivity {

    @BindView(R.id.webview_container)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webView.setWebChromeClient(new WebChromeClient() {
            public boolean onConsoleMessage(ConsoleMessage cm) {
                Log.d("MyApplication", cm.message() + " -- From line "
                        + cm.lineNumber() + " of "
                        + cm.sourceId() );
                return true;
            }
        });

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDisplayZoomControls(false);

        webView.clearCache(true);
        webView.loadUrl(getIntent().getExtras().getString("url"));
        webView.setPadding(0,0,0,0);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_game;
    }
}
