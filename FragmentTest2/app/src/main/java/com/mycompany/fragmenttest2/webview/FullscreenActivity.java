package com.mycompany.fragmenttest2.webview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mycompany.fragmenttest2.R;

public class FullscreenActivity extends AppCompatActivity {

    private static final String URL = "https://romivalladares.github.io/";
    private WebView webview;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        webview = findViewById(R.id.webview);

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webview.loadUrl(URL);
        webview.addJavascriptInterface(new WebAppInterface(this), "Android");

        webview.setWebViewClient(new MyWebViewClient());
        webview.getSettings().setDomStorageEnabled(true);


        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.d("webviewresult", consoleMessage.message() + " -- From line "
                        + consoleMessage.lineNumber() + " of "
                        + consoleMessage.sourceId());
                return super.onConsoleMessage(consoleMessage);
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                webview.evaluateJavascript(
                        "showDialog('Msj desde app');",
                        new ValueCallback<String>() {
                            @Override
                            public void onReceiveValue(String s) {
                                Log.d("webviewresult",s);
                            }
                        });
            }
        }, 6000);

    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals("mathematica.stackexchange.com")) {
                return false;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
       return super.onKeyDown(keyCode, event);
    }

}