package com.example.administrator.mengshuying1502s20170424;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.List;
/**
 * date:${DATA}
 * author:孟淑英
 * function:
 */
public class MyAdapter extends BaseAdapter{
   private final Context mContext;
       private final List<MyBean.DataBean> mComicsBeen;
       public MyAdapter(Context context, List<MyBean.DataBean> list){
            mContext =context;
            mComicsBeen =list;
        }
       @Override
       public int getCount(){
           return mComicsBeen.size();
       }
       @Override
       public Object getItem(int position){
           return mComicsBeen.get(position);
       }
       @Override
       public long getItemId(int position){
           return position;
       }
       @Override
       public View getView(int position, View convertView, ViewGroup parent){
           ViewHolder viewHolder;
           if(convertView==null){
               convertView=View.inflate(mContext, R.layout.xiao_layout,null);
               viewHolder=new ViewHolder();
               viewHolder.imageview= (ImageView) convertView.findViewById(R.id.imageview);
               viewHolder.textview1= (TextView) convertView.findViewById(R.id.textview1);
               viewHolder.textview2= (TextView) convertView.findViewById(R.id.textview2);
               viewHolder.textview3= (TextView) convertView.findViewById(R.id.textview3);

               convertView.setTag(viewHolder);
           }else{
               viewHolder= (ViewHolder) convertView.getTag();
           }
           ImageLoader.getInstance().displayImage((String) mComicsBeen.get(position).getIMAGEURL(),viewHolder.imageview);
           viewHolder.textview1.setText(mComicsBeen.get(position).getTITLE());
           viewHolder.textview2.setText(mComicsBeen.get(position).getFROMNAME());
           viewHolder.textview3.setText(mComicsBeen.get(position).getSHOWTIME());


           return convertView;
       }
       public class ViewHolder{
           ImageView imageview;
           TextView textview1;
           TextView textview2;
           TextView textview3;
       }
}
