package com.nutricycle.nutricycle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class SimpleBarChart extends View {

    private List<BarData> dataList;
    private Paint barPaint;
    private Paint textPaint;
    private Paint labelPaint;
    private int maxValue;
    private int barSpacing;
    private int barWidth;
    private int paddingTop;
    private int paddingBottom;
    private int paddingLeft;
    private int paddingRight;

    public SimpleBarChart(Context context) {
        super(context);
        init();
    }

    public SimpleBarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimpleBarChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        dataList = new ArrayList<>();
        
        barPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        barPaint.setColor(ContextCompat.getColor(getContext(), R.color.nutri_green_primary));
        
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(ContextCompat.getColor(getContext(), R.color.nutri_card_text));
        textPaint.setTextSize(32);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTypeface(android.graphics.Typeface.DEFAULT_BOLD);
        
        labelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        labelPaint.setColor(ContextCompat.getColor(getContext(), R.color.nutri_text_secondary));
        labelPaint.setTextSize(36);
        labelPaint.setTextAlign(Paint.Align.CENTER);
        
        barSpacing = 16;
        paddingTop = 40;
        paddingBottom = 60;
        paddingLeft = 40;
        paddingRight = 40;
    }

    public void setData(List<BarData> data) {
        this.dataList = data;
        if (data != null && !data.isEmpty()) {
            maxValue = 0;
            for (BarData bar : data) {
                if (bar.value > maxValue) {
                    maxValue = bar.value;
                }
            }
            // Add some padding to max value for better visualization
            maxValue = (int) (maxValue * 1.1f);
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        if (dataList == null || dataList.isEmpty()) {
            return;
        }

        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;
        
        if (width <= 0 || height <= 0) {
            return;
        }

        int totalBars = dataList.size();
        if (totalBars == 0) return;

        barWidth = (width - (barSpacing * (totalBars - 1))) / totalBars;
        if (barWidth < 20) {
            barWidth = 20;
        }

        float startX = paddingLeft;
        
        for (int i = 0; i < dataList.size(); i++) {
            BarData bar = dataList.get(i);
            
            // Calculate bar height
            float barHeight = maxValue > 0 ? (float) bar.value / maxValue * height : 0;
            
            // Draw bar
            float left = startX;
            float top = paddingTop + height - barHeight;
            float right = startX + barWidth;
            float bottom = paddingTop + height;
            
            RectF rect = new RectF(left, top, right, bottom);
            canvas.drawRoundRect(rect, 8, 8, barPaint);
            
            // Draw value text on top of bar
            if (barHeight > 40) {
                textPaint.setTextSize(32);
                canvas.drawText(String.valueOf(bar.value), left + barWidth / 2, top - 10, textPaint);
            }
            
            // Draw label below bar
            labelPaint.setTextSize(36);
            canvas.drawText(bar.label, left + barWidth / 2, bottom + 40, labelPaint);
            
            startX += barWidth + barSpacing;
        }
    }

    public static class BarData {
        String label;
        int value;

        public BarData(String label, int value) {
            this.label = label;
            this.value = value;
        }
    }
}

