package me.wangyuwei.newstemplate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity implements NewsPresenter.INewsView {

    private WebView mWeb;
    private NewsPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWeb = (WebView) findViewById(R.id.web);
        mWeb.getSettings().setJavaScriptEnabled(true);
        mWeb.addJavascriptInterface(this, "bazhang");

        mPresenter = new NewsPresenter(this);
        mPresenter.loadNewsData();

    }

    @Override
    public void loadSuccess() {
        mWeb.loadUrl("file:///android_asset/newscont.html");
    }

    @JavascriptInterface
    public String getNewsData() {
        return mPresenter.getNewsData();
    }

}
