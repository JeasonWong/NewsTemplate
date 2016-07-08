package me.wangyuwei.newstemplate;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ObservableWebView;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.nineoldandroids.view.ViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.wangyuwei.newstemplate.model.NewsEntity;

import static android.widget.Toast.LENGTH_SHORT;

public class NewsActivity extends AppCompatActivity implements NewsPresenter.INewsView {

    @BindView(R.id.scr_news)
    ObservableScrollView mScrollViewNews;
    @BindView(R.id.web_news)
    ObservableWebView mWebNews;
    @BindView(R.id.view_header)
    NewsHeaderView mViewHeader;

    private NewsPresenter mPresenter;
    private boolean mDragging;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mScrollViewNews.setScrollViewCallbacks(mScrollViewScrollCallbacks);
        mWebNews.setScrollViewCallbacks(mWebViewScrollCallbacks);

        mWebNews.getSettings().setJavaScriptEnabled(true);
        mWebNews.addJavascriptInterface(this, "bazhang");
        mWebNews.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                addImgClickListener();
            }
        });

        mPresenter = new NewsPresenter(this);
        mPresenter.loadNewsData();
    }

    @Override
    public void loadSuccess(NewsEntity newsEntity) {
        mWebNews.loadUrl("file:///android_asset/newscont.html");
        mViewHeader.setHeaderData(newsEntity);
    }

    /**
     * 增加图片点击事件
     */
    private void addImgClickListener() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mWebNews.evaluateJavascript("clickImg();", null);
        } else {
            mWebNews.loadUrl("javascript:clickImg();");
        }
    }

    private ObservableScrollViewCallbacks mScrollViewScrollCallbacks = new ObservableScrollViewCallbacks() {
        @Override
        public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
            if (mDragging) {
                ViewHelper.setTranslationY(mViewHeader, scrollY / 2);
            }
        }

        @Override
        public void onDownMotionEvent() {
        }

        @Override
        public void onUpOrCancelMotionEvent(ScrollState scrollState) {
            if (scrollState == ScrollState.STOP) {
                mDragging = false;
            }
        }
    };

    private ObservableScrollViewCallbacks mWebViewScrollCallbacks = new ObservableScrollViewCallbacks() {
        @Override
        public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        }

        @Override
        public void onDownMotionEvent() {
            mDragging = true;
        }

        @Override
        public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        }
    };

    @JavascriptInterface
    public String getNewsData() {
        return mPresenter.getNewsData();
    }

    @JavascriptInterface
    public void openImg(final String imgUrl, final int index) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(NewsActivity.this, "第" + index + "张图片\n" + imgUrl, LENGTH_SHORT).show();
            }
        });
    }

}
