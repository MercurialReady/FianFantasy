package com.example.mercu.finalfantasy.utils.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.mercu.finalfantasy.app.FinalFantasyApp;

/**
 * Created by qicheng on 2018/9/17.
 */

public class CoolImageView extends View
{
    private Matrix mMatrix;
    private Bitmap mBitmap;
    private float lastX;
    private float lastY;
    private float secondX;
    private float secondY;
    private float dx;
    private float dy;
    private float initRatio;
    private float scaledRatio;
    private float totalRatio;
    private double lastDistanceBetweenFingers;
    private double distanceBetweenFingers;
    private float centerX;
    private float centerY;
    private float bitmapCenterX;
    private float bitmapCenterY;
    private float screenCenterX;
    private float screenCenterY;
    private int status;
    private float totalTranslateX;
    private float totalTranslateY;
    private float movedX;
    private float movedY;
    private float currentBitmapWidth;
    private float currentBitmapHeight;
    private float fingerFirstX;
    private float fingerFirstY;
    private float fingerSecondX;
    private float fingerSecondY;
    private float totalAngle;

    private static final int INIT = 0;
    private static final int ZOOM_OUT = 1;
    private static final int ZOOM_IN = 2;
    private static final int MOVING = 3;

    public CoolImageView(Context context)
    {
        this(context,null);
        init();
    }

    public CoolImageView(Context context, @Nullable AttributeSet attrs)
    {
        this(context, attrs,0);
        init();
    }

    public CoolImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        status = INIT;
        mMatrix = new Matrix();
        mBitmap = Bitmap.createBitmap(400,400, Bitmap.Config.ARGB_8888);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(totalRatio != initRatio)
        {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        else
        {
            getParent().requestDisallowInterceptTouchEvent(false);
        }
        switch (event.getActionMasked())
        {
            case MotionEvent.ACTION_DOWN:
            {
                lastX = event.getX();
                lastY = event.getY();
            }
            break;

            case MotionEvent.ACTION_POINTER_DOWN:
            {
                Logger.d("ACTION_POINTER_DOWN");
                fingerFirstX = event.getX(0);
                fingerFirstY = event.getY(0);
                fingerSecondX = event.getX(1);
                fingerSecondY = event.getY(1);
                getFingersDistance(event);
                lastDistanceBetweenFingers = distanceBetweenFingers;
            }
            break;

            case MotionEvent.ACTION_POINTER_UP:
            {
                //抬起来一根手指，这个时候要重置lastX,lastY，move时会重新得到
                lastY = -1;
                lastX = -1;
                fingerFirstX = -1;
                fingerFirstY = -1;
                fingerSecondX = -1;
                fingerSecondY = -1;
                if(totalRatio < initRatio)
                {
                    //todo 这里就可以执行回到上一个界面的动画

                }
            }
            break;

            case MotionEvent.ACTION_UP:
            {
                lastY = -1;
                lastX = -1;
                //backToInit();
            }
            break;

            case MotionEvent.ACTION_MOVE:
            {
                int fingerPoint = event.getPointerCount();
                if(fingerPoint == 2)
                {
                    getFingersDistance(event);
                    scaledRatio = (float)(distanceBetweenFingers / lastDistanceBetweenFingers);
                    totalRatio = (totalRatio * scaledRatio);

                    if(totalRatio > initRatio)
                    {
                        status = ZOOM_OUT;
                    }
                    else if(totalRatio < initRatio)
                    {
                        status = ZOOM_IN;
                        totalAngle += angle(event);
                        //backToInit();
                    }
                    lastDistanceBetweenFingers = distanceBetweenFingers;

                    fingerFirstX = event.getX(0);
                    fingerFirstY = event.getY(0);
                    fingerSecondX = event.getX(1);
                    fingerSecondY = event.getY(1);
                }
                else if(fingerPoint == 1)
                {
//                    if(totalRatio > initRatio)
//                    {
//                        status = MOVING;
//                    }
//                    else
//                    {
//                        status = INIT;
//                    }

                    if(lastX == -1 && lastY == -1)
                    {
                        lastX = event.getX();
                        lastY = event.getY();
                        Log.e("Mercurial","init");
                    }
                    movedX = event.getX() - lastX;
                    movedY = event.getY() - lastY;
                    Log.d("Mercurial","movedX = " + movedX);
                    Log.d("Mercurial","movedY = " + movedY);
                    lastX = event.getX();
                    lastY = event.getY();
                }
                postInvalidate();
            }
            break;
        }
        return true;
    }

    private void backToInit()
    {
        mMatrix.reset();
        totalRatio = initRatio = (FinalFantasyApp.getScreen_width() / (mBitmap.getWidth() * 1.0f));
        mMatrix.postScale(initRatio,initRatio);
        mMatrix.postTranslate(0,(FinalFantasyApp.getScreen_height()-mBitmap.getHeight() * initRatio)/2);
        totalTranslateX = 0;
        totalTranslateY = (FinalFantasyApp.getScreen_height()-mBitmap.getHeight() * initRatio)/2;
    }

    private void getFingersDistance(MotionEvent event)
    {
        float x1 = event.getX(0);
        float y1 = event.getY(0);
        float x2 = event.getX(1);
        float y2 = event.getY(1);
        centerX = (x1 + x2)/2;
        centerY = (y1 + y2)/2;
        float disX = Math.abs(x1 - x2);
        float disY = Math.abs(y1 - y2);
        distanceBetweenFingers = Math.sqrt(disX * disX + disY * disY);
    }

    private float angle(MotionEvent event)
    {
        float x1 = event.getX(0);
        float y1 = event.getY(0);
        float x2 = event.getX(1);
        float y2 = event.getY(1);
        float angle1 = getAngle(fingerFirstX,fingerFirstY,fingerSecondX,fingerSecondY,x1,y1);
        float angle2 = getAngle(fingerSecondX,fingerSecondY,x1,y1,x2,y2);
        Logger.d("angle1 + angle2 = ",angle1);
        return angle1 + angle2;
    }

    private float getAngle(float startX, float startY, float centerX, float centerY, float endX, float endY)
    {
        //小于0即为顺时针
        boolean isClockWise = ((startX - centerX) * (endY - centerY) - (endX - centerX) * (startY - centerY)) > 0;

        float dx1 = startX - centerX;
        float dy1 = startY - centerY;
        float dx2 = endX - centerX;
        float dy2 = endY - centerY;
        double cosDegree = (dx1* dx2 + dy1 * dy2)/(Math.sqrt(dx1*dx1+dy1*dy1) * Math.sqrt(dy2*dy2+dx2*dx2));
        if (cosDegree > 1) {
            cosDegree = 1;
        } else if (cosDegree < -1) {
            cosDegree = -1;
        }
        Logger.d("cosDegree = ",cosDegree);
        double radian = Math.acos(cosDegree);

        float finalAngle = (float) (isClockWise ? Math.toDegrees(radian) : -Math.toDegrees(radian));
        Logger.d("angle = ",finalAngle);
        return finalAngle;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        switch (status)
        {
            case INIT:
            {
                backToInit();
            }
            break;

            case MOVING:
            {
                totalTranslateX += (movedX * totalRatio);
                totalTranslateY += (movedY * totalRatio);
                Log.e("Mercurial","Moving");
                mMatrix.reset();
                mMatrix.postScale(totalRatio,totalRatio);
                mMatrix.postTranslate(movedX * totalRatio + totalTranslateX,movedY * totalRatio + totalTranslateY);

            }
            break;

            case ZOOM_IN:
            {
                //缩小与放大不一样，缩小都是以屏幕中心点为中心缩小的
                mMatrix.reset();
                mMatrix.postScale(totalRatio,totalRatio);
                mMatrix.postRotate(totalAngle);
                mMatrix.preTranslate(-bitmapCenterX,-bitmapCenterY);
                mMatrix.postTranslate(screenCenterX,screenCenterY);
                //totalTranslateX = (FinalFantasyApp.getScreenWidth() - totalRatio * mBitmap.getWidth())/2;
                //totalTranslateY = (FinalFantasyApp.getScreenHeight() - totalRatio * mBitmap.getHeight())/2;
                currentBitmapWidth = mBitmap.getWidth() * totalRatio;
                currentBitmapHeight = mBitmap.getHeight() * totalRatio;
            }
            break;
            case ZOOM_OUT:
            {

                //放大状态,以两个手指的中心点为图片缩放的中心点
                //此处用的是postScale(float sx, float sy, float px, float py)这个api
                //这个api的好处是缩放后又平移了，平移的公式是：X2 = centerX - (centerX - X1)*scaledRatioX
                //即(X2-X1)/(X1-centerX) = scaledRatioX;
                //mMatrix.postScale(totalRatio,totalRatio,centerX,centerY);
                //mMatrix.getValues();

                //上下两段代码作用一致
                mMatrix.reset();
                mMatrix.postScale(totalRatio,totalRatio);
                totalTranslateX = centerX * (1 - scaledRatio) + scaledRatio * totalTranslateX;
                totalTranslateY = centerY * (1 - scaledRatio) + scaledRatio * totalTranslateY;
                mMatrix.postTranslate(totalTranslateX,totalTranslateY);
                currentBitmapWidth = mBitmap.getWidth() * totalRatio;
                currentBitmapHeight = mBitmap.getHeight() * totalRatio;
            }
            break;
        }
        canvas.drawBitmap(mBitmap,mMatrix,null);
    }

    public void setBitmap(Bitmap bitmap)
    {
        mBitmap = bitmap;
        totalRatio = initRatio = (FinalFantasyApp.getScreen_width() / (mBitmap.getWidth() * 1.0f));
        Logger.d("init ratio = ",initRatio);
//        mMatrix.preTranslate(0,(FinalFantasyApp.getScreenHeight()-mBitmap.getHeight() * initRatio)/2);
//        mMatrix.preScale(initRatio,initRatio);
//        mMatrix.postTranslate(FinalFantasyApp.getScreenWidth()/2,0);
//        mMatrix.preRotate(90);
        mMatrix.postScale(initRatio,initRatio);
        mMatrix.postTranslate(0,(FinalFantasyApp.getScreen_height()-mBitmap.getHeight() * initRatio)/2);
        totalTranslateX = 0;
        totalTranslateY = (FinalFantasyApp.getScreen_height()-mBitmap.getHeight() * initRatio)/2;
        //mMatrix.preTranslate(-(mBitmap.getWidth()/2 - FinalFantasyApp.getScreenWidth()/2),0);
        CoolImageView.this.postInvalidate();
        bitmapCenterX = bitmap.getWidth()/2;
        bitmapCenterY = bitmap.getHeight()/2;
        screenCenterY = (getBottom() - getTop())/2;
        screenCenterX = (getRight() - getLeft())/2;
        Logger.d("getBottom()",getBottom());
        Logger.d("getTop()",getTop());
        Logger.d("getRight()",getRight());
        Logger.d("getLeft()",getLeft());
        Logger.d("screenCenterX",screenCenterX);
        Logger.d("screenCenterY",screenCenterY);
    }
}
