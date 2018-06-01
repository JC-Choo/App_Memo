package com.choo.application.memo.App.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.choo.application.memo.Common.Dlog;

/**
 * Created by Bridge on 2018-05-28.
 */

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    private OnItemClickListener mListener;
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onLongItemClick(View view, int position);
    }

    // GestureDetector :
    // 제공된 MotionEvents를 사용하여 다양한 제스처 및 이벤트를 검색합니다.
    // GestureDetector.OnGestureListener 콜백은 특정 모션 이벤트가 발생한 경우 사용자에게 알립니다.
    // 이 클래스는 터치를 통해 보고된 동작 이벤트에만 사용해야 합니다(트랙볼 이벤트에는 사용 안 함).
    private GestureDetector mGestureDetector;
    public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
        mListener = listener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && mListener != null) {
                    Dlog.d("long press");
                    mListener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, rv.getChildAdapterPosition(childView));
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
