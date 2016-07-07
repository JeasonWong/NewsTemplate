package me.wangyuwei.newstemplate;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 作者： 巴掌 on 16/7/6 21:23
 */
public class NewsPresenter {

    private INewsView mView;
    private String mNewsData;

    public NewsPresenter(INewsView view) {
        this.mView = view;
    }

    public void loadNewsData() {
        Observable.just(readAssets())
                .map(new Func1<String, NewsEntity>() {
                    @Override
                    public NewsEntity call(String s) {
                        return JSON.parseObject(s, NewsEntity.class);
                    }
                })
                .map(new Func1<NewsEntity, String>() {
                    @Override
                    public String call(NewsEntity newsEntity) {
                        return createDataJson(newsEntity);
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        mNewsData = s;
                        mView.loadSuccess();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

    public String getNewsData() {
        return TextUtils.isEmpty(mNewsData) ? "{}" : mNewsData;
    }

    private String createDataJson(NewsEntity newsEntity) {

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("body", newsEntity.getBody());
            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
    }

    private String readAssets() {
        String json = "";
        try {
            InputStream inputStream = AppContext.getContext().getAssets().open("data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public interface INewsView {

        void loadSuccess();

    }

}
