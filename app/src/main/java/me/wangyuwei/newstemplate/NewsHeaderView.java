package me.wangyuwei.newstemplate;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.wangyuwei.newstemplate.manager.ImageLoader;
import me.wangyuwei.newstemplate.model.NewsEntity;

/**
 * 作者： 巴掌 on 16/7/8 22:30
 */
public class NewsHeaderView extends FrameLayout {

    @BindView(R.id.img_news_header)
    ImageView mImgNewsHeader;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_img_sourse)
    TextView mTvImgSourse;

    public NewsHeaderView(Context context) {
        this(context, null);
    }

    public NewsHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NewsHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_news_header, this);
        ButterKnife.bind(this);
    }

    public void setHeaderData(NewsEntity newsEntity) {
        ImageLoader.load(newsEntity.getImage(), mImgNewsHeader);
        mTvTitle.setText(newsEntity.getTitle());
        mTvImgSourse.setText(newsEntity.getImage_source());
    }
}
