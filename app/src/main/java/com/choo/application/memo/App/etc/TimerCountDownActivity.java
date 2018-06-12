package com.choo.application.memo.App.etc;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.choo.application.memo.Common.Dlog;
import com.choo.application.memo.R;
import com.choo.application.memo.Util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimerCountDownActivity extends AppCompatActivity implements View.OnClickListener {

    private long startTime = 0, stopTime = 0, elapsed = 0;
    private boolean running = false;
    private double ftime = 0.0;
    private int countDownTime;

    @BindView(R.id.Timer_edit_text_count_down)
    EditText editTextCountDown;
    @BindView(R.id.Timer_text_view_count_down)
    TextView textViewCountDown;
    @BindView(R.id.Timer_text_view_timer)
    TextView textViewTimer;
    @BindView(R.id.Timer_button_count_down_start)
    Button buttonCountDown;
    @BindView(R.id.Timer_button_timer_start)
    Button buttonTimerStart;
    @BindView(R.id.Timer_button_timer_stop)
    Button buttonTimerStop;
    @BindView(R.id.Timer_button_termination)
    Button buttonTermination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_countdown);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        buttonCountDown.setOnClickListener(this);
        buttonTimerStart.setOnClickListener(this);
        buttonTimerStop.setOnClickListener(this);
        buttonTermination.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Timer_button_count_down_start:
                String str = editTextCountDown.getText().toString();
                textViewCountDown.setText(str);

                if (str.equals("")) {
                    ToastUtil.shortToast(TimerCountDownActivity.this, getString(R.string.TimerCountDown_please_enter_the_correct_time));
                } else {
                    countDownTime = Integer.parseInt(str);

                    if (countDownTime == 0) {
                        ToastUtil.shortToast(TimerCountDownActivity.this, getString(R.string.TimerCountDown_please_enter_the_correct_time));
                        return;
                    }

                    buttonCountDown.setEnabled(false);

                    final Handler CountDownHandler = new Handler() {
                        public void handleMessage(Message msg) {
                            Dlog.i("msg.what = " + msg.what);
                            textViewCountDown.setText(String.valueOf(countDownTime));

                            if (countDownTime == 0) {
                                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                                if (vibrator != null) {
                                    vibrator.vibrate(1000);
                                }

                                ToastUtil.shortToast(TimerCountDownActivity.this, getString(R.string.TimerCountDown_the_count_is_complete));
                                buttonCountDown.setEnabled(true);
                            }
                        }
                    };

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (countDownTime > 0) {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                --countDownTime;

                                CountDownHandler.sendEmptyMessage(countDownTime);
                            }
                        }
                    }).start();
                }
                break;
            case R.id.Timer_button_timer_start:
                startThread();
                buttonTimerStart.setEnabled(false);
                break;
            case R.id.Timer_button_timer_stop:
                stopThread();
                buttonTimerStart.setEnabled(true);
                break;
            case R.id.Timer_button_termination:
                finish();
                break;
        }
    }


    //elaspsed time in milliseconds
    private long getElapsedTimeMilli() {
        if (running)
            return elapsed = ((System.nanoTime() - startTime) / 1000000);
        else
            return elapsed = ((stopTime - startTime) / 1000000);
    }

    private double getFormat() {
        double nTime = getElapsedTimeMilli() / 1000.0;

        return Double.parseDouble(String.format("%02f", nTime));
    }

    // Thread 객체 생성, start/stop 쓰레드 정의
    // volatile : 멀티쓰레드에서 최적화를 위해 사용(사용 안하면 데이터가 지맘대로 뽑힘) -> 다시 사용 시 공부
    private volatile Thread thread;
    private synchronized void startThread() {
        if (thread == null) {
            this.startTime = System.nanoTime();
            this.running = true;

            thread = new Thread(runnable);
            thread.start();
        }
    }
    private synchronized void stopThread() {
        if (thread != null) {
            Thread tempThread = thread;
            thread = null;
            tempThread.interrupt();
        }
    }

    // Handler에게 시간 메세지를 얻고 0.01초 간격으로 Thread.메세지를 보냄\
    private Runnable runnable = new Runnable() {
        public void run() {
            if (Thread.currentThread() == thread) {
                while (true) {
                    try {
                        theHandle.sendMessage(theHandle.obtainMessage());
                        Thread.sleep(10);
                    } catch (final InterruptedException e) {
                        return;
                    } catch (final Exception e) {
                        return;
                    }
                }
            }
        }

        Handler theHandle = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                String strTime = String.format("%02d:%02d:%02d", (int) (getFormat() / 60), (int) (getFormat() % 60), (int) ((getFormat() * 100) % 100));
                textViewTimer.setText(strTime);
            }
        };
    };
}
