package io.seamoss.urbino.views.active_board;

import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.widget.LinearLayout;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.inject.Inject;

import butterknife.BindView;
import io.seamoss.urbino.BuildConfig;
import io.seamoss.urbino.R;
import io.seamoss.urbino.Urbino;
import io.seamoss.urbino.base.nav.BaseNavActivity;
import io.seamoss.urbino.data.models.Node;
import timber.log.Timber;

/**
 * Created by Alexander Melton on 3/5/2017.
 */

public class ActiveBoardActivity extends BaseNavActivity implements ActiveBoardView{

    @Inject
    ActiveBoardPresenter activeBoardPresenter;

    @BindView(R.id.webview_container)
    WebView webView;

    ProgressDialog progressDialog;

    private float initialZoomX;
    private float initialZoomY;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Urbino.instance().getAppGraph().inject(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading board");
        progressDialog.setMessage("This will only take a moment!");
        progressDialog.setCancelable(false);
        progressDialog.show();

        webView.setWebViewClient(new WebViewClient(){
            public void onPageFinished(WebView view, String url){
                endLoading();
            }
        });

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

        webView.addJavascriptInterface(activeBoardPresenter, "UrbinoAndroid");
        webView.clearCache(true);
        webView.loadUrl(BuildConfig.API_URL_WEBVIEW + "/index.html");
        webView.setPadding(0,0,0,0);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        initialZoomX = webView.getScaleX();
        initialZoomY = webView.getScaleY();

        Timber.d(BuildConfig.API_URL_WEBVIEW);
    }

    @Override()
    public void moveCamera(float px, float py) {
        Timber.d(webView.getScaleX() + "," + webView.getScaleY());
        int scrollX = (int) (px * getResources().getDisplayMetrics().density) - (webView.getWidth()/2);
        int scrollY = (int) (py * getResources().getDisplayMetrics().density) - (webView.getHeight()/2);
        ObjectAnimator objectAnimatorY = ObjectAnimator.ofInt(webView, "scrollY", webView.getScrollY(), scrollY);
        ObjectAnimator objectAnimatorX = ObjectAnimator.ofInt(webView, "scrollX", webView.getScrollX(), scrollX);
        objectAnimatorY.setDuration(500).start();
        objectAnimatorX.setDuration(500).start();
        //webView.scrollTo(scrollX,scrollY);
        webView.computeScroll();
    }

    @Override
    public void selectNode(Node node) {
        NodeSelectDialog nodeSelectDialog = NodeSelectDialog.newInstance(node);
        nodeSelectDialog.show(getFragmentManager(), null);
    }

    @Override
    public void openNode(Node node) {
        Timber.d("Opening Node");
    }

    @Override
    public void endLoading() {
        progressDialog.dismiss();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.activeBoardPresenter.attachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.activeBoardPresenter.detachView();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_active_board;
    }
}
