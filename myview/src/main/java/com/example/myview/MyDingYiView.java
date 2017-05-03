package com.example.myview;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
/**
 * date:${DATA}
 * author:孟淑英
 * function:
 */
public class MyDingYiView extends View{
    private Paint mPaint;
    private Paint mPaint1;
    public static int rastu;
    public static final int heng=200;
    public MyDingYiView(Context context){
        super(context);
        mPaint = newPaint();
        mPaint1 = newPaint1();
    }
    public MyDingYiView(Context context, AttributeSet attrs){
         super(context, attrs);
          mPaint = newPaint();
          mPaint1 = newPaint1();
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyDingYiView);
//        rastu = typedArray.getInteger(R.styleable.MyDingYiView_rastu, rastu);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyDingYiView);
        typedArray.getInteger(R.styleable.MyDingYiView_heng,heng);

    }
  //决定控件在手机中的大小
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        //画一个圆
        canvas.drawCircle(heng,200,100,mPaint);
        //canvas.drawCircle(200,200,3,mPaint1);
        //画一条直线
        //canvas.drawLine(100,100,200,50,mPaint);
        //canvas.drawText("我爱你小臭臭",100,300,mPaint);
     //  *****画三角形******   ！连得线和移动的点的坐标相同
//        Path path=new Path();
//        path.moveTo(60,60);
//        path.moveTo(120,60);
//        path.moveTo(100,200);
//        path.lineTo(60,60);
//        path.lineTo(120,60);
//        path.lineTo(100,200);
//        path.close();
//        canvas.drawPath(path,mPaint);
    }
    //设置画笔的属性
    public Paint newPaint(){
        //画笔
        Paint paint = new Paint();
        //设置抗锯齿
        paint.setAntiAlias(true);
        //设置画笔的颜色
        paint.setColor(Color.BLACK);
        //设置画笔的样式 Paint.Style.STROKE：空心，Paint.Style.FILL实心，Style.FILL_AND_STROKE:空心与实心的结合(事实上和实心的效果是一样的)
        paint.setStyle(Paint.Style.STROKE);
        //设置画笔绘制的文本字体的大小
        paint.setTextSize(30);
       return paint;
       }
    //设置画笔的属性
    public Paint newPaint1(){
        //画笔
        Paint paint = new Paint();
        //设置抗锯齿
        paint.setAntiAlias(true);
        //设置画笔的颜色
        paint.setColor(Color.BLACK);
        //设置画笔的样式 Paint.Style.STROKE：空心，Paint.Style.FILL实心，Style.FILL_AND_STROKE:空心与实心的结合(事实上和实心的效果是一样的)
        paint.setStyle(Paint.Style.FILL);
        //设置画笔绘制的文本字体的大小
        paint.setTextSize(30);
        return paint;
    }
}
