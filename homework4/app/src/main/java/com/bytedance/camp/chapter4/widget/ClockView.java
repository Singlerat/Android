package com.bytedance.camp.chapter4.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ClockView extends View {

    private static final int FULL_CIRCLE_DEGREE = 360;
    private static final int UNIT_DEGREE = 6;

    private static final float UNIT_LINE_WIDTH = 8; // 刻度线的宽度
    private static final int HIGHLIGHT_UNIT_ALPHA = 0xFF;
    private static final int NORMAL_UNIT_ALPHA = 0x80;

    private static final float HOUR_NEEDLE_LENGTH_RATIO = 0.4f; // 时针长度相对表盘半径的比例
    private static final float MINUTE_NEEDLE_LENGTH_RATIO = 0.6f; // 分针长度相对表盘半径的比例
    private static final float SECOND_NEEDLE_LENGTH_RATIO = 0.8f; // 秒针长度相对表盘半径的比例
    private static final float HOUR_NEEDLE_WIDTH = 12; // 时针的宽度
    private static final float MINUTE_NEEDLE_WIDTH = 8; // 分针的宽度
    private static final float SECOND_NEEDLE_WIDTH = 4; // 秒针的宽度

    private Calendar calendar = Calendar.getInstance();

    private float radius = 0; // 表盘半径
    private float centerX = 0; // 表盘圆心X坐标
    private float centerY = 0; // 表盘圆心Y坐标

    private List<RectF> unitLinePositions = new ArrayList<>();
    private Paint unitPaint = new Paint();
    private Paint hourPaint = new Paint();
    private Paint minuPaint = new Paint();
    private Paint secPaint = new Paint();
    private Paint numberPaint = new Paint();
    private Paint list_numberPaint = new Paint();
    private int Width=getWidth();

    private Handler handler = new Handler();
    public final int REFRESH = 0;
    public final static int AM = 0;

    public ClockView(Context context) {
        super(context);
        init();
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("HandlerLeak")
    private void init() {
        unitPaint.setAntiAlias(true);
        unitPaint.setColor(Color.WHITE);
        unitPaint.setStrokeWidth(UNIT_LINE_WIDTH);
        unitPaint.setStrokeCap(Paint.Cap.ROUND);
        unitPaint.setStyle(Paint.Style.STROKE);

        // TODO 设置绘制时、分、秒针的画笔: needlePaint
        hourPaint.setAntiAlias(true);
        hourPaint.setColor(Color.WHITE);
        hourPaint.setStrokeWidth(HOUR_NEEDLE_WIDTH);
        hourPaint.setStrokeCap(Paint.Cap.ROUND);

        minuPaint.setAntiAlias(true);
        minuPaint.setColor(Color.WHITE);
        minuPaint.setStrokeWidth(MINUTE_NEEDLE_WIDTH);
        minuPaint.setStrokeCap(Paint.Cap.ROUND);

        secPaint.setAntiAlias(true);
        secPaint.setColor(Color.WHITE);
        minuPaint.setStrokeWidth(SECOND_NEEDLE_WIDTH);
        minuPaint.setStrokeCap(Paint.Cap.ROUND);


        // TODO 设置绘制时间数字的画笔: numberPaint
        numberPaint.setTextSize(60);
        numberPaint.setColor(Color.RED);
        numberPaint.setTextAlign(Paint.Align.CENTER);
        numberPaint.setAntiAlias(true);

        list_numberPaint.setAntiAlias(true);
        list_numberPaint.setColor(Color.WHITE);
        list_numberPaint.setTextAlign(Paint.Align.CENTER);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == REFRESH) {
                    invalidate();
                    sendEmptyMessageDelayed(REFRESH, 1000);
                }
            }
        };


    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        configWhenLayoutChanged();
    }

    private void configWhenLayoutChanged() {
        float newRadius = Math.min(getWidth(), getHeight()) / 3f;
        if (newRadius == radius) {
            return;
        }
        radius = newRadius;
        Width=Math.min(getWidth(), getHeight());
        centerX = getWidth() / 2f;
        centerY = getHeight() / 2f;

        // 当视图的宽高确定后就可以提前计算表盘的刻度线的起止坐标了
        for (int degree = 0; degree < FULL_CIRCLE_DEGREE; degree += UNIT_DEGREE) {
            double radians = Math.toRadians(degree);
            float startX = (float) (centerX + (radius * (1 - 0.05f)) * Math.cos(radians));
            float startY = (float) (centerX + (radius * (1 - 0.05f)) * Math.sin(radians));
            float stopX = (float) (centerX + radius * Math.cos(radians));
            float stopY = (float) (centerY + radius * Math.sin(radians));
            unitLinePositions.add(new RectF(startX, startY, stopX, stopY));
        }
    }

    @SuppressLint("HandlerLeak")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawUnit(canvas);
        drawTimeNeedles(canvas);
        drawTimeNumbers(canvas);
        drawNumber(canvas);
        //invalidate();
        // TODO 实现时间的转动，每一秒刷新一次
    }



    // 绘制表盘上的刻度
    private void drawUnit(Canvas canvas) {
        for (int i = 0; i < unitLinePositions.size(); i++) {
            if (i % 5 == 0) {
                unitPaint.setAlpha(HIGHLIGHT_UNIT_ALPHA);
            } else {
                unitPaint.setAlpha(NORMAL_UNIT_ALPHA);
            }
            RectF linePosition = unitLinePositions.get(i);
            canvas.drawLine(linePosition.left, linePosition.top, linePosition.right, linePosition.bottom, unitPaint);
        }
    }

    private void drawTimeNeedles(Canvas canvas) {
        Time time = getCurrentTime();
        int hour = time.getHours();
        int minute = time.getMinutes();
        int second = time.getSeconds();
        // TODO 根据当前时间，绘制时针、分针、秒针
        /**
         * 思路：
         * 1、以时针为例，计算从0点（12点）到当前时间，时针需要转动的角度
         * 2、根据转动角度、时针长度和圆心坐标计算出时针终点坐标（起始点为圆心）
         * 3、从圆心到终点画一条线，此为时针
         * 注1：计算时针转动角度时要把时和分都得考虑进去
         * 注2：计算坐标时需要用到正余弦计算，请用Math.sin()和Math.cos()方法
         * 注3：Math.sin()和Math.cos()方法计算时使用不是角度而是弧度，所以需要先把角度转换成弧度，
         *     可以使用Math.toRadians()方法转换，例如Math.toRadians(180) = 3.1415926...(PI)
         * 注4：Android视图坐标系的0度方向是从圆心指向表盘3点方向，指向表盘的0点时是-90度或270度方向，要注意角度的转换
         */
        // int hourDegree = 180;
        // float endX = (float) (centerX + radius * HOUR_NEEDLE_LENGTH_RATIO * Math.cos(Math.toRadians(hourDegree)))
        int hourNeedle=(int) (radius*HOUR_NEEDLE_LENGTH_RATIO);
        int minuteNeedle=(int) (radius*MINUTE_NEEDLE_LENGTH_RATIO);
        int secondNeedle=(int) (radius*SECOND_NEEDLE_LENGTH_RATIO);

        float hourangle = hour * 30 + (minute + (float)second / 60) / 60 * 30;
        float minuangle = (minute + (float)second / 60) / 60 * 360;
        float secdangle = (float)second / 60 * 360;

        canvas.save();
        canvas.drawLine(centerX,
                centerY,
                (float)(centerX + hourNeedle * Math.sin(Math.toRadians(hourangle))),
                (float)(centerY - hourNeedle * Math.cos(Math.toRadians(hourangle))),
                hourPaint
        );
        canvas.restore();

        canvas.save();
        canvas.drawLine(centerX,
                centerY,
                (float)(centerX + minuteNeedle * Math.sin(Math.toRadians(minuangle))),
                (float)(centerY - minuteNeedle * Math.cos(Math.toRadians(minuangle))),
                minuPaint
        );
        canvas.restore();

        canvas.save();
        canvas.drawLine(centerX,
                centerY,
                (float)(centerX + secondNeedle * Math.sin(Math.toRadians(secdangle))),
                (float)(centerY - secondNeedle * Math.cos(Math.toRadians(secdangle))),
                secPaint
        );
        canvas.restore();


    }
    private  void drawNumber(Canvas canvas)
    {
        list_numberPaint.setTextSize(Width*0.1f);
        Time time = getCurrentTime();
        int hour = time.getHours();
        int minute = time.getMinutes();
        int second = time.getSeconds();
        int Am = time.getAmpm();

        String clock = String.format("%s:%s:%s%s",
                String.format(Locale.getDefault(), "%02d", hour),
                String.format(Locale.getDefault(), "%02d", minute),
                String.format(Locale.getDefault(), "%02d", second),
                Am == AM ? "AM" : "PM");
        canvas.save();
        canvas.drawText(clock,centerX,centerY+getHeight()/2f,list_numberPaint);
        canvas.restore();


    }
    private void drawTimeNumbers(Canvas canvas) {
        // TODO 绘制表盘时间数字（可选）
        String[] ClockNum = {"12", "01", "02","03","04","05","06","07","08","09","10","11"};
        Paint.FontMetrics fontMetrics=numberPaint.getFontMetrics();
        float top = fontMetrics.top;
        float bottom = fontMetrics.bottom;
        int x,y;
        int CenterY = (int) (centerY + (bottom - top) / 2 - bottom - radius + getWidth() * 0.05f);
        int r = (int) centerY-CenterY;
        for(int i = 0; i < ClockNum.length; ++ i)
        {
            x = (int) (centerY + r * Math.sin(Math.toRadians(i * 30)));
            y = (int) (centerY - r * Math.cos(Math.toRadians(i * 30)));
            canvas.drawText(ClockNum[i], x, y + (bottom - top) / 2 - bottom, numberPaint);
        }

    }

    // 获取当前的时间：时、分、秒
    private Time getCurrentTime() {
        calendar.setTimeInMillis(System.currentTimeMillis());
        return new Time(
                calendar.get(Calendar.AM_PM),
                calendar.get(Calendar.HOUR),
                calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.SECOND));

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        handler.sendEmptyMessage(REFRESH);
    }
}
