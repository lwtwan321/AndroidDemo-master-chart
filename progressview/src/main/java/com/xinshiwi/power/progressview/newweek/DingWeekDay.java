package com.xinshiwi.power.progressview.newweek;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by apple on 2018/1/17.
 */

public class DingWeekDay extends View {
    public DingWeekDay(Context context) {
        this(context, null);
    }

    public DingWeekDay(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DingWeekDay(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private final GestureDetector.SimpleOnGestureListener mGestureListener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (mCurrentScrollDirection == DingWeekDay.Direction.NONE) {
                if (Math.abs(distanceX) > Math.abs(distanceY)) {
                    mCurrentScrollDirection = DingWeekDay.Direction.HORIZONTAL;
                } else {
                    mCurrentScrollDirection = DingWeekDay.Direction.VERTICAL;
                }
            }
            mDistanceX = distanceX * mXScrollingSpeed;
            mDistanceY = distanceY;
            invalidate();
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
        }
    };

    private enum Direction {
        NONE, HORIZONTAL, VERTICAL
    }

    private DingWeekDay.Direction mCurrentScrollDirection = DingWeekDay.Direction.NONE;
    private PointF mCurrentOrigin = new PointF(0f, 0f);
    private float mXScrollingSpeed = 1f;
    private final Context mContext;
    private GestureDetectorCompat mGestureDetector;

    private Paint mTimeTextPaint;
    private float mTimeTextWidth;
    private float mTimeTextHeight;
    private float mHeaderMarginBottom;
    private int mHeaderColumnTextColor = Color.BLACK;
    private int mTextSize = 50;

    private Paint mHourSeparatorPaint;
    private int mHourSeparatorColor = Color.rgb(230, 230, 230);

    private int mHeaderColumnPadding = 16;
    private float mHeaderColumnWidth;
    private float mWidthPerDay;
    private float mDistanceY = 0;
    private float mDistanceX = 0;

    private int mHourHeight = 150;
    private int mHourSeparatorHeight = 2;

    private int beginHour = 8;
    private int endHour = 20;
    private int totalHour = 0;

    private List<EventRect> mEventRects = new ArrayList<>();
    private Calendar mToday;

    private Paint mHeaderColumnBackgroundPaint;
    private int mHeaderColumnBackgroundColor = Color.WHITE;

    private Paint mEventBackgroundPaint;

    public int getTotalHour() {
        return endHour + 1 - beginHour;
    }

    private void init() {
        // Get the date today.
        mToday = Calendar.getInstance();
        mToday.set(Calendar.HOUR_OF_DAY, 0);
        mToday.set(Calendar.MINUTE, 0);
        mToday.set(Calendar.SECOND, 0);
        // Scrolling initialization.
        mGestureDetector = new GestureDetectorCompat(mContext, mGestureListener);

        // Measure settings for time column.
        mTimeTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTimeTextPaint.setTextAlign(Paint.Align.RIGHT);
        mTimeTextPaint.setTextSize(mTextSize);
        mTimeTextPaint.setColor(mHeaderColumnTextColor);
        Rect rect = new Rect();
        mTimeTextPaint.getTextBounds("00:00", 0, "00:00".length(), rect);
        mTimeTextWidth = mTimeTextPaint.measureText("00:00");
        mTimeTextHeight = rect.height();
        mHeaderMarginBottom = mTimeTextHeight / 2;

        // Prepare hour separator color paint.
        mHourSeparatorPaint = new Paint();
        mHourSeparatorPaint.setStyle(Paint.Style.STROKE);
        mHourSeparatorPaint.setStrokeWidth(mHourSeparatorHeight);
        mHourSeparatorPaint.setColor(mHourSeparatorColor);

        // Prepare header column background color.
        mHeaderColumnBackgroundPaint = new Paint();
        mHeaderColumnBackgroundPaint.setColor(mHeaderColumnBackgroundColor);

        // Prepare event background color.
        mEventBackgroundPaint = new Paint();
        mEventBackgroundPaint.setColor(Color.rgb(174, 208, 238));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw the header row.
        drawHeaderRowAndEvents(canvas);

        // Draw the time column and all the axes/separators.
        drawTimeColumnAndAxes(canvas);
    }

    private void drawTimeColumnAndAxes(Canvas canvas) {
        // Do not let the view go above/below the limit due to scrolling. Set the max and min limit of the scroll.
        if (mCurrentScrollDirection == DingWeekDay.Direction.VERTICAL) {
            if (mCurrentOrigin.y - mDistanceY > 0) mCurrentOrigin.y = 0;
            else if (mCurrentOrigin.y - mDistanceY < -(mHourHeight * getTotalHour() + 0 - getHeight()))
                mCurrentOrigin.y = -(mHourHeight * getTotalHour() + 0 - getHeight());
            else mCurrentOrigin.y -= mDistanceY;
        }

        // Draw the background color for the header column.
        canvas.drawRect(0, 0, mHeaderColumnWidth, getHeight(), mHeaderColumnBackgroundPaint);

        for (int i = 0; i < getTotalHour(); i++) {
            float top = 0 + mCurrentOrigin.y + mHourHeight * i + mHeaderMarginBottom;
            int index = i + beginHour;
            // Draw the text if its y position is not outside of the visible area. The pivot point of the text is the point at the bottom-right corner.
            String time = getDateTimeInterpreter().interpretTime(index);
            if (time == null)
                throw new IllegalStateException("A DateTimeInterpreter must not return null time");
            if (top < getHeight())
                canvas.drawText(time, mTimeTextWidth + mHeaderColumnPadding, top + mTimeTextHeight, mTimeTextPaint);
        }
    }

    private void drawHeaderRowAndEvents(Canvas canvas) {
        mHeaderColumnWidth = mTimeTextWidth + mHeaderColumnPadding * 2;
        mWidthPerDay = getWidth() - mHeaderColumnWidth;

        // Consider scroll offset.
        if (mCurrentScrollDirection == DingWeekDay.Direction.HORIZONTAL) {
            mCurrentOrigin.x -= mDistanceX;
        }

        int lineCount = getTotalHour();
        float[] hourLines = new float[lineCount * 4];
        int start = (int) mHeaderColumnWidth;
        int i = 0;
        for (int hourNumber = 0; hourNumber < getTotalHour(); hourNumber++) {
            float top = mCurrentOrigin.y + mHourHeight * hourNumber + mTimeTextHeight / 2 + mHeaderMarginBottom;
            hourLines[i * 4] = start;
            hourLines[i * 4 + 1] = top;
            hourLines[i * 4 + 2] = start + mWidthPerDay;
            hourLines[i * 4 + 3] = top;
            i++;
        }

        // Draw the lines for hours.
        canvas.drawLines(hourLines, mHourSeparatorPaint);

        // Draw the events.
        drawEvents(Calendar.getInstance(), 0, canvas);

    }

    /**
     * 画事件
     *
     * @param date
     * @param startFromPixel
     * @param canvas
     */
    private void drawEvents(Calendar date, float startFromPixel, Canvas canvas) {
        mEventRects.clear();
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 30);
        Calendar endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR, 1);
        endTime.add(Calendar.MINUTE, 30);
        WeekViewEvent event = new WeekViewEvent(1, "This is a Event!!", startTime, endTime);
        event.setColor(ColorUtil.getRandomColor());
        EventRect eventRect = new EventRect(event, null);
        computerEventRect(eventRect);
        mEventRects.add(eventRect);

        if (mEventRects != null && mEventRects.size() > 0) {
            for (EventRect er : mEventRects) {
                if (isSameDay(eventRect.getEvent().getStartTime(), date)) {
                    canvas.drawRect(eventRect.getRectF(), mEventBackgroundPaint);
                }
            }
        }
    }

    private void computerEventRect(EventRect eventRect) {
        int startHour = eventRect.getEvent().getStartTime().get(Calendar.HOUR_OF_DAY);
        int startMinutes = eventRect.getEvent().getStartTime().get(Calendar.MINUTE);

        int endHour = eventRect.getEvent().getEndTime().get(Calendar.HOUR_OF_DAY);
        int endMinutes = eventRect.getEvent().getEndTime().get(Calendar.MINUTE);


        int relativePositionStart = startHour - beginHour;
        int halfHeightStart = 0;
        if (startMinutes == 30) {
            halfHeightStart = mHourHeight / 2;
        }

        int relativePositionEnd = endHour - beginHour;
        int halfHeightEnd = 0;
        if (endMinutes == 30) {
            halfHeightEnd = mHourHeight / 2;
        }

        float top = 0 + mCurrentOrigin.y + mHeaderMarginBottom + mTimeTextHeight / 2 + relativePositionStart * mHourHeight + halfHeightStart;
        float left = mCurrentOrigin.x + mHeaderColumnWidth;
        float right = left + mWidthPerDay;
        float bottom = 0 + mCurrentOrigin.y + mHeaderMarginBottom + mTimeTextHeight / 2 + relativePositionEnd * mHourHeight + halfHeightEnd;
        RectF rectF = new RectF(left, top, right, bottom);
        eventRect.setRectF(rectF);
    }

    private boolean isSameDay(Calendar dayOne, Calendar dayTwo) {
        return dayOne.get(Calendar.YEAR) == dayTwo.get(Calendar.YEAR) && dayOne.get(Calendar.DAY_OF_YEAR) == dayTwo.get(Calendar.DAY_OF_YEAR);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            mCurrentScrollDirection = DingWeekDay.Direction.NONE;
        }
        return mGestureDetector.onTouchEvent(event);
    }

    private DateTimeInterpreter mDateTimeInterpreter = null;

    /**
     * Get the interpreter which provides the text to show in the header column and the header row.
     *
     * @return The date, time interpreter.
     */
    public DateTimeInterpreter getDateTimeInterpreter() {
        final String[] weekLabels = {"日", "一", "二", "三", "四", "五", "六"};
        if (mDateTimeInterpreter == null) {
            mDateTimeInterpreter = new DateTimeInterpreter() {
                @Override
                public String interpretDate(Calendar date) {
                    SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
                    String weekday = weekdayNameFormat.format(date.getTime());
                    SimpleDateFormat format = new SimpleDateFormat("d", Locale.getDefault());
                    return format.format(date.getTime());
                }

                @Override
                public String interpretTime(int hour) {
                    return String.format("%02d:00", hour);
                }

                @Override
                public String interpretWeek(int date) {
                    if (date > 7 || date < 1) {
                        return null;
                    }
                    return weekLabels[date - 1];
                }
            };
        }
        return mDateTimeInterpreter;
    }


    private class EventRect {
        public WeekViewEvent event;
        public RectF rectF;
        public float left;
        public float width;
        public float top;
        public float bottom;

        public EventRect(WeekViewEvent event, RectF rectF) {
            this.event = event;
            this.rectF = rectF;
        }

        public WeekViewEvent getEvent() {
            return event;
        }

        public void setEvent(WeekViewEvent event) {
            this.event = event;
        }

        public RectF getRectF() {
            return rectF;
        }

        public void setRectF(RectF rectF) {
            this.rectF = rectF;
        }

        public float getLeft() {
            return left;
        }

        public void setLeft(float left) {
            this.left = left;
        }

        public float getWidth() {
            return width;
        }

        public void setWidth(float width) {
            this.width = width;
        }

        public float getTop() {
            return top;
        }

        public void setTop(float top) {
            this.top = top;
        }

        public float getBottom() {
            return bottom;
        }

        public void setBottom(float bottom) {
            this.bottom = bottom;
        }
    }
}
