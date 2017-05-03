package com.example.administrator.mengshuying1502s20170424;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;
import java.util.List;

import static com.example.administrator.mengshuying1502s20170424.MainActivity.list1;

/**
 * date:${DATA}
 * author:孟淑英
 * function:
 */
public class MyHttp{
    private final Context mContext;
    private final PullToRefreshListView mListView;
    MyAdapter adapter;
    boolean flag=true;
    public MyHttp(Context context,PullToRefreshListView listview){
         mContext =context;
        mListView =listview;
     }
    /**
     *
     */
    public void get(int channelId, final int startNum){
        RequestParams params = new RequestParams(MyUrl.url);
        params.addQueryStringParameter("channelId",channelId+"");
        params.addQueryStringParameter("startNum", startNum+"");
        Log.i("fff","http"+startNum);
        x.http().get(params, new Callback.CommonCallback<String>() {
            private boolean hasError = false;
            private String result = null;
            @Override
            public void onSuccess(String result){
                MyGson myGson=new MyGson();
                List<MyBean.DataBean> list = myGson.jiexi(result);
                adapter=new MyAdapter(mContext,list);
                mListView.setAdapter(adapter);
                for(Integer xx:list1){
                    if(xx==startNum){
                      flag=false;

                    }
                }
                if(flag){
                        MySqilte mySqilte=new MySqilte(mContext);
                        SQLiteDatabase readableDatabase = mySqilte.getReadableDatabase();
                    for(MyBean.DataBean xx:list){
                        ContentValues values=new ContentValues();
                        values.put("imageurl", (String) xx.getIMAGEURL());
                        values.put("title",xx.getTITLE());
                        values.put("fromname",xx.getFROMNAME());
                        values.put("showtime",xx.getSHOWTIME());
                        readableDatabase.insert("user",null,values);
                    }
                list1.add(startNum);
                }

            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback){
            }
            @Override
            public void onCancelled(CancelledException cex){
            }
            @Override
            public void onFinished(){
            }
        });
    }
}
