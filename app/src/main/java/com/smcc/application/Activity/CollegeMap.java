package com.smcc.application.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by admin on 8/19/2017.
 */

public class CollegeMap extends Activity {
//    String latitude= "17.541723";
//    String longitude = "78.474583";
   // String url = "http://maps.google.com/maps/api/staticmap?center=" + latitude + "," + longitude+ "&zoom=10&size=400x400&sensor=false";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_mou);
        WebView webView=new WebView(CollegeMap.this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);

        //---you need this to prevent the webview from
        // launching another browser when a url
        // redirection occurs---
        webView.setWebViewClient(new CollegeMap.Callback());
        webView.loadUrl("http://maps.google.com/maps?q=17.541723,78.474583");
        //webView.loadUrl("http://maps.google.com/maps/api/staticmap?center=\" + 17.541723 + \",\" + 78.474583 + \"&zoom=10&size=400x400&sensor=true");
//        webView.loadUrl(
//                "http://docs.google.com/gview?embedded=true&url=" + pdfURL);

        setContentView(webView);
    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(
                WebView view, String url) {
            return(false);
        }
    }

}
