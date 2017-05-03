package com.example.administrator.mengshuying1502s20170424;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.ListView;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity{
    private PullToRefreshListView mList_view;
    private int channelId=0;
    private int startNum=0;
    private List<MyBean.DataBean> list=new ArrayList<MyBean.DataBean>();
    public static List<Integer> list1=new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList_view = (PullToRefreshListView) findViewById(R.id.plistview);
        mList_view.setMode(PullToRefreshBase.Mode.BOTH);
        mytext();
        wang();
     }
    //判断有没有网？
    public  boolean isConnected() {
        NetworkInfo info = getActiveNetworkInfo();
        return info != null && info.isConnected();
    }
    public void wang(){
        if(isConnected()){
            shu();
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("请选择网络");
            String[] arr=new String[]{"wifi","4g","取消"};
            builder.setItems(arr, new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which){
                    switch(which){
                        case 0:
                            WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                            wifiManager.setWifiEnabled(true);
                            shu();
                            break;
                        case 1:
                            TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                            Method setMobileDataEnabledMethod = null;
                            try {
                                setMobileDataEnabledMethod = tm.getClass().getDeclaredMethod("setDataEnabled", boolean.class);
                                setMobileDataEnabledMethod.invoke(tm, true);
                                shu();
                              }catch (Exception e){
                                e.printStackTrace();
                             }
                            break;
                        case 2:
                            MySqilte sq=new MySqilte(MainActivity.this);
                            SQLiteDatabase db = sq.getReadableDatabase();
                            Cursor cursor = db.query   ("user",null,null,null,null,null,null);
                            while(cursor.moveToNext()){
                                    String imageurl=cursor.getString(cursor.getColumnIndex("imageurl"));
                                    String title=cursor.getString(cursor.getColumnIndex("title"));
                                    String fromname=cursor.getString(cursor.getColumnIndex("fromname"));
                                    String showtime=cursor.getString(cursor.getColumnIndex("showtime"));
                                MyBean.DataBean bean=new MyBean.DataBean();
                                bean.setIMAGEURL(imageurl);
                                bean.setTITLE(title);
                                bean.setFROMNAME(fromname);
                                bean.setSHOWTIME(showtime);
                                list.add(bean);
                             }
                           if(list!=null&list.size()>0){
                               MyAdapter adapter=new MyAdapter(MainActivity.this,list);
                               mList_view.setAdapter(adapter);
                           }

                            break;
                    }
                    dialog.dismiss();
                }
            });

            builder.show();

        }
    }
    /**
     * 获取活动网络信息
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @return NetworkInfo
     */
    private NetworkInfo getActiveNetworkInfo(){
        return ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
    }
    private void shu(){
        MyHttp http = new MyHttp(this, mList_view);
        http.get(channelId,startNum);
        mList_view.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            //下拉刷新
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView){
                MyHttp http = new MyHttp(MainActivity.this, mList_view);
                startNum=0;
                http.get(channelId,startNum);
                mList_view.onRefreshComplete();
            }
            //上拉加载
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView){
                MyHttp http = new MyHttp(MainActivity.this, mList_view);
                startNum++;
                http.get(channelId,startNum);
                Log.i("fff","Main"+startNum);
                mList_view.onRefreshComplete();
            }
        });
    }
    private void mytext(){
        //设置刷新时显示的文本
        ILoadingLayout startLayout = mList_view.getLoadingLayoutProxy(true,false);
        startLayout.setPullLabel("正在下拉刷新...");
        startLayout.setRefreshingLabel("正在玩命加载中...");
        startLayout.setReleaseLabel("放开以刷新");
        ILoadingLayout endLayout = mList_view.getLoadingLayoutProxy(false,true);
        endLayout.setPullLabel("正在上拉刷新...");
        endLayout.setRefreshingLabel("正在玩命加载中...");
        endLayout.setReleaseLabel("放开以刷新");
       }
  }
