package com.example.administrator.mengshuying1502s20170424;
import com.google.gson.Gson;
import java.util.List;

/**
 * date:${DATA}
 * author:孟淑英
 * function:
 */
public class MyGson {
    public List<MyBean.DataBean> jiexi(String stt){
        Gson gson=new Gson();
        MyBean myBean = gson.fromJson(stt, MyBean.class);
        List<MyBean.DataBean> list = myBean.getData();
        return list;
    }
}
