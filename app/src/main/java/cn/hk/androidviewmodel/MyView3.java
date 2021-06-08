package cn.hk.androidviewmodel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView3 extends View {
    private Paint myPaint;
    private Path myPath;

    public MyView3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        myPaint = new Paint();
        myPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        myPaint.setColor(Color.BLACK);
        myPaint.setStrokeWidth(5);
        myPaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(myPath, myPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            //按下
            case MotionEvent.ACTION_DOWN:
                myPath.moveTo(event.getX(), event.getY());
                return true;
            case MotionEvent.ACTION_MOVE:
                myPath.lineTo(event.getX(), event.getY());
                //刷新页面
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 重制
     */
    public void reSet(){
        myPath.reset();
        //刷新页面
        invalidate();
    }
}
