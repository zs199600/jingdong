package com.example.administrator.jingdong.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/2/9.
 */

public class Myview extends ViewGroup {
    public Myview(Context context) {
        this(context,null);
    }

    public Myview(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Myview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //测量ViewGroup的宽高
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //ViewGroup的padding
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        //当前宽度
        int curWidth = 0;
        //当前高度
        int curHeight = paddingTop + paddingBottom;
        //最大宽度
        int widthMax = 0;
        for(int i = 0;i <  getChildCount();){
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childView.getLayoutParams();
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();
            if(curWidth == 0 ){
                curWidth = paddingLeft + paddingRight;
                curHeight += marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + childHeight;
            }
            if(curWidth + marginLayoutParams.rightMargin + marginLayoutParams.leftMargin + childWidth <= widthSize){
                //不换行
                curWidth += marginLayoutParams.rightMargin + marginLayoutParams.leftMargin + childWidth;
                i++;
            }else{
                //换行
                widthMax = Math.max(widthMax, curWidth);
                curWidth = 0;
            }
            //判断是否是最后一行
            if(i == getChildCount()){
                widthMax = Math.max(widthMax, curWidth);
                curHeight += marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + childHeight;
            }
        }
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : widthMax ,
                heightMode == MeasureSpec.EXACTLY  ? heightSize : curHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //获取ViewGroup的padding
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        //当前宽度;
        int curWidth = paddingLeft;
        //当前高度
        int curHeight = paddingTop;
        for(int i = 0; i < getChildCount();){
            View childView = getChildAt(i);
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childView.getLayoutParams();
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();
            if(curWidth + paddingRight + marginLayoutParams.rightMargin + marginLayoutParams.leftMargin + childWidth <= getWidth()){
                //不换行
                childView.layout(curWidth + marginLayoutParams.leftMargin,
                        curHeight + marginLayoutParams.topMargin,
                        curWidth + marginLayoutParams.leftMargin + childWidth,
                        curHeight + marginLayoutParams.topMargin + childHeight);
                curWidth += marginLayoutParams.leftMargin + childWidth + marginLayoutParams.rightMargin;
                i ++;
            }else{
                //换行
                curHeight += marginLayoutParams.topMargin + childHeight + marginLayoutParams.bottomMargin;
                curWidth = paddingLeft;
            }
        }

    }
}
