package com.example.mvp.model;

import static com.example.mvp.page.RecycleViewAdapter.hasMore;

import android.util.Log;

import com.example.mvp.util.NetCallbackListener;
import com.example.mvp.util.NetUtil;
import com.example.mvp.bean.QuestionData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author WhiteNight123 (Guo Xiaoqiang)
 * @email 1448375249@qq.com
 * @data 2022/2/20
 */
public class QuestionModelImpl implements QuestionModel {
    private static final String TAG = "HAHA";
    private DataLoadListener dataLoadListener;


    @Override
    public void load2(int page) {

        NetUtil.sendHttpRequest("https://wanandroid.com/wenda/list/"+page+"/json", "GET", null, new NetCallbackListener() {
            @Override
            public void onFinish(String response) {
                Log.e(TAG, "onFinish: " + response);
                dataLoadListener.success(jsonDecode(response));
            }

            @Override
            public void onError(Exception e) {
                dataLoadListener.failure(e);
            }
        });

    }

    public interface DataLoadListener {
        void failure(Exception e);

        void success(ArrayList<QuestionData> dataArrayList);
    }

    public void setListener(DataLoadListener listener) {
        this.dataLoadListener = listener;
    }

    private ArrayList<QuestionData> jsonDecode(String str) {
        ArrayList<QuestionData> articleData = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(str);
            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            hasMore=!jsonObject1.getBoolean("over");
            JSONArray jsonArray = jsonObject1.getJSONArray("datas");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject data = jsonArray.getJSONObject(i);
                String author = data.getString("author");
                String time = data.getString("niceDate");
                if (time.length() > 10) {
                    time = time.substring(0, 10);
                }
                String title = data.getString("title");
                String content = data.getString("desc");
                String htmlRegex = "<[^>]+>";
                String spaceRegex = "\\s*|\t|\r|\n";
                content = content.replaceAll(htmlRegex, ""); //去除html标签
                content = content.replaceAll(spaceRegex, ""); //过滤空格
                String chapterName = data.getString("superChapterName");
                String url = data.getString("link");
                QuestionData questionData = new QuestionData();
                questionData.setAuthor(author);
                questionData.setTitle(title);
                questionData.setTime(time);
                questionData.setUrl(url);
                questionData.setContent(content);
                questionData.setChapterName(chapterName);
                articleData.add(questionData);
            }
            Log.d(TAG, "jsonDeco2" + articleData.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleData;
    }

}
