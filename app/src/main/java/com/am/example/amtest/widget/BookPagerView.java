package com.am.example.amtest.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class BookPagerView extends View{

    private static final int STYLE_NO_ACTION = -1;
    private static final int STYLE_RIGHT_CONNER = 0;
    private static final int STYLE_RIGHT = 1;
    private static final int STYLE_LEFT = 2;
    private static final int STYLE_MIDDLE = 3;

    private Paint pathAPaint;
    private Paint pathCPaint;
    private Paint pathBPaint;

    private Path pathA;
    private Path pathC;
    private Path pathB;

    private PointF a,f,g,e,h,c,j,b,k,d,i;

    private int defaultHeight;
    private int defaultWidth;
    private int viewHeight;
    private int viewWidth;

    private int currState = STYLE_NO_ACTION;

    public BookPagerView(Context context) {
        super(context);
        init();
    }

    public BookPagerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BookPagerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        defaultWidth = 600;
        defaultHeight = 1000;

        viewHeight = defaultHeight;
        viewWidth = defaultWidth;

        a = new PointF();
        f = new PointF();
        g = new PointF();
        e = new PointF();
        h = new PointF();
        c = new PointF();
        j = new PointF();
        b = new PointF();
        k = new PointF();
        d = new PointF();
        i = new PointF();
        //calcPointsXY(a,f);

        Paint pointPaint = new Paint();
        pointPaint.setColor(Color.RED);
        pointPaint.setTextSize(25);

        pathAPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pathAPaint.setColor(Color.GREEN);
        pathBPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pathBPaint.setColor(Color.BLUE);
        pathBPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));
        pathCPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pathCPaint.setColor(Color.YELLOW);
        pathCPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));
        Paint bgPaint = new Paint();
        bgPaint.setColor(Color.GREEN);

        pathA = new Path();
        pathC = new Path();
        pathB = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = measureSize(defaultHeight, heightMeasureSpec);
        int width = measureSize(defaultWidth, widthMeasureSpec);
        setMeasuredDimension(width, height);

        viewWidth = width;
        viewHeight = height;

        a.x = -1;
        a.y = -1;

        //calcPointsXY(a,f);
    }

    private int measureSize(int defaultSize, int measureSpec){
        int result = defaultSize;
        int size = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);
        if(mode == MeasureSpec.EXACTLY){
            result = size;
        } else if(mode == MeasureSpec.AT_MOST){
            result = Math.min(result, size);
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap bufferBitmap = Bitmap.createBitmap(viewWidth, viewHeight, Bitmap.Config.ARGB_4444);
        Canvas bitmapCanvas = new Canvas(bufferBitmap);

        if(a.x == -1 && a.y == -1){
            bitmapCanvas.drawPath(getDefaultPath(), pathAPaint);
        } else {
            switch (currState){
                case STYLE_RIGHT:
                case STYLE_RIGHT_CONNER:
                    bitmapCanvas.drawPath(f.y == 0 ? getPathAFromTopRight() : getPathAFromLowerRight(), pathAPaint);
                    break;
            }
            bitmapCanvas.drawPath(getPathC(), pathCPaint);
            bitmapCanvas.drawPath(getPathB(), pathBPaint);
        }
        canvas.drawBitmap(bufferBitmap, 0, 0, null);

        //canvas.drawRect(0,0,viewWidth,viewHeight,bgPaint);

        /*
        canvas.drawText("a",a.x,a.y,pointPaint);
        canvas.drawText("f",f.x,f.y,pointPaint);
        canvas.drawText("g",g.x,g.y,pointPaint);

        canvas.drawText("e",e.x,e.y,pointPaint);
        canvas.drawText("h",h.x,h.y,pointPaint);

        canvas.drawText("c",c.x,c.y,pointPaint);
        canvas.drawText("j",j.x,j.y,pointPaint);

        canvas.drawText("b",b.x,b.y,pointPaint);
        canvas.drawText("k",k.x,k.y,pointPaint);

        canvas.drawText("d",d.x,d.y,pointPaint);
        canvas.drawText("i",i.x,i.y,pointPaint);
        */
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(eventX > viewWidth*3/4){
                    if(eventY > viewHeight/3 && eventY < viewHeight*2.0f/3){
                        setTouchPoint(eventX, eventY, STYLE_RIGHT);
                    } else {
                        setTouchPoint(eventX, eventY, STYLE_RIGHT_CONNER);
                    }
                } else if (eventX < viewWidth/4){
                    setTouchPoint(eventX, eventY, STYLE_LEFT);
                } else {
                    setTouchPoint(eventX, eventY, STYLE_MIDDLE);
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                setTouchPoint(eventX, eventY, currState);
                break;
            case MotionEvent.ACTION_UP:
                setDefaultPath();
                break;
        }
        return false;
    }

    private void setTouchPoint(float x, float y, int style){
        currState = style;
        switch (style){
            case STYLE_RIGHT_CONNER:
                a.x = x;
                a.y = y;
                f.x = viewWidth;
                f.y = y <= viewHeight/3 ? 0 : viewHeight;
                calcPointsXY(a, f);
                PointF temp = new PointF(x,y);
                if(calcPointCX(temp,f) < 0){
                    calcPointAByTouchPoint();
                    calcPointsXY(a, f);
                }
                postInvalidate();
                break;
            case STYLE_LEFT:
                break;
            case STYLE_MIDDLE:
                break;
            case STYLE_RIGHT:
                a.x = x;
                a.y = viewHeight - 1;
                f.x = viewWidth;
                f.y = viewHeight;
                calcPointsXY(a, f);
                postInvalidate();
                break;
            case STYLE_NO_ACTION:
                break;
        }
    }

    private void calcPointsXY(PointF a, PointF f){
        g.x = (a.x + f.x) / 2;
        g.y = (a.y + f.y) / 2;

        e.x = g.x - (f.y - g.y) * (f.y - g.y) / (f.x - g.x);
        e.y = f.y;

        h.x = f.x;
        h.y = g.y - (f.x - g.x) * (f.x - g.x) / (f.y - g.y);

        c.x = e.x - (f.x - e.x) / 2;
        c.y = f.y;

        j.x = f.x;
        j.y = h.y - (f.y - h.y) / 2;

        b = getIntersectionPoint(a,e,c,j);
        k = getIntersectionPoint(a,h,c,j);

        d.x = (c.x + 2 * e.x + b.x) / 4;
        d.y = (2 * e.y + c.y + b.y) / 4;

        i.x = (j.x + 2 * h.x + k.x) / 4;
        i.y = (2 * h.y + j.y + k.y) / 4;
    }

    private PointF getIntersectionPoint(PointF lineOne_My_pointOne, PointF lineOne_My_pointTwo, PointF lineTwo_My_pointOne, PointF lineTwo_My_pointTwo){
        float x1,y1,x2,y2,x3,y3,x4,y4;
        x1 = lineOne_My_pointOne.x;
        y1 = lineOne_My_pointOne.y;
        x2 = lineOne_My_pointTwo.x;
        y2 = lineOne_My_pointTwo.y;
        x3 = lineTwo_My_pointOne.x;
        y3 = lineTwo_My_pointOne.y;
        x4 = lineTwo_My_pointTwo.x;
        y4 = lineTwo_My_pointTwo.y;

        float pointX = ((x1 - x2) * (x3 * y4 - x4 * y3) - (x3 - x4) * (x1 * y2 - x2 * y1))
                        / ((x3 - x4) * (y1 - y2) - (x1 - x2) * (y3 - y4));
        float pointY = ((y1 - y2) * (x3 * y4 - x4 * y3) - (x1 * y2 - x2 * y1) * (y3 - y4))
                        / ((y1 - y2) * (x3 - x4) - (x1 - x2) * (y3 - y4));

        return new PointF(pointX,pointY);
    }

    private Path getPathAFromLowerRight(){
        pathA.reset();
        pathA.lineTo(0, viewHeight);//移动到左下角
        pathA.lineTo(c.x,c.y);//移动到c点
        pathA.quadTo(e.x,e.y,b.x,b.y);//从c到b画贝塞尔曲线，控制点为e
        pathA.lineTo(a.x,a.y);//移动到a点
        pathA.lineTo(k.x,k.y);//移动到k点
        pathA.quadTo(h.x,h.y,j.x,j.y);//从k到j画贝塞尔曲线，控制点为h
        pathA.lineTo(viewWidth,0);//移动到右上角
        pathA.close();//闭合区域
        return pathA;
    }

    private Path getPathAFromTopRight(){
        pathA.reset();
        pathA.lineTo(c.x,c.y);//移动到c点
        pathA.quadTo(e.x,e.y,b.x,b.y);//从c到b画贝塞尔曲线，控制点为e
        pathA.lineTo(a.x,a.y);//移动到a点
        pathA.lineTo(k.x,k.y);//移动到k点
        pathA.quadTo(h.x,h.y,j.x,j.y);//从k到j画贝塞尔曲线，控制点为h
        pathA.lineTo(viewWidth,viewHeight);//移动到右下角
        pathA.lineTo(0, viewHeight);//移动到左下角
        pathA.close();
        return pathA;
    }

    public void setDefaultPath(){
        a.x = -1;
        a.y = -1;
        postInvalidate();
    }

    private Path getDefaultPath(){
        pathA.reset();
        pathA.lineTo(0, viewHeight);
        pathA.lineTo(viewWidth, viewHeight);
        pathA.lineTo(viewWidth,0);
        pathA.close();
        return pathA;
    }

    private Path getPathC(){
        pathC.reset();
        pathC.moveTo(i.x,i.y);//移动到i点
        pathC.lineTo(d.x,d.y);//移动到d点
        pathC.lineTo(b.x,b.y);//移动到b点
        pathC.lineTo(a.x,a.y);//移动到a点
        pathC.lineTo(k.x,k.y);//移动到k点
        pathC.close();//闭合区域
        return pathC;
    }

    private Path getPathB(){
        pathB.reset();
        pathB.moveTo(0,0);
        pathB.lineTo(0, viewHeight);
        pathB.lineTo(viewWidth, viewHeight);
        pathB.lineTo(viewWidth,0);
        pathB.close();
        return pathB;
    }

    private float calcPointCX(PointF a, PointF f){
        PointF g,e;
        g = new PointF();
        e = new PointF();
        g.x = (a.x + f.x) / 2;
        g.y = (a.y + f.y) / 2;

        e.x = g.x - (f.y - g.y) * (f.y - g.y) / (f.x - g.x);
        e.y = f.y;

        return e.x - (f.x - e.x) / 2;
    }

    private void calcPointAByTouchPoint(){
        float w0 = viewWidth - c.x;

        float w1 = Math.abs(f.x - a.x);
        float w2 = viewWidth * w1 / w0;
        a.x = Math.abs(f.x - w2);

        float h1 = Math.abs(f.y - a.y);
        float h2 = w2 * h1 / w1;
        a.y = Math.abs(f.y - h2);
    }
}
