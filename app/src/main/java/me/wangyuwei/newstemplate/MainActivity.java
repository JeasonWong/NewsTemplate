package me.wangyuwei.newstemplate;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity implements NewsPresenter.INewsView {

    private WebView mWeb;
    private NewsPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWeb = (WebView) findViewById(R.id.web);
        mWeb.getSettings().setJavaScriptEnabled(true);
        mWeb.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                addImgClickListener();
            }
        });
        mWeb.addJavascriptInterface(this, "bazhang");

        mPresenter = new NewsPresenter(this);
        mPresenter.loadNewsData();

    }

    @Override
    public void loadSuccess() {
        mWeb.loadUrl("file:///android_asset/newscont.html");
    }

    /**
     * 增加图片点击事件
     */
    private void addImgClickListener() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mWeb.evaluateJavascript("clickImg();", null);
        } else {
            mWeb.loadUrl("javascript:clickImg();");
        }
    }

    @JavascriptInterface
    public String getNewsData() {
        return mPresenter.getNewsData();
    }

    @JavascriptInterface
    public void openImg(final String imgUrl, final int index) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "第" + index + "张图片\n" + imgUrl, LENGTH_SHORT).show();
            }
        });
    }

}
