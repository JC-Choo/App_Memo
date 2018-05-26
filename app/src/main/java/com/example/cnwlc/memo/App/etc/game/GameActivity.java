package com.example.cnwlc.memo.App.etc.game;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cnwlc.memo.App.etc.game.one.OneToFifty;
import com.example.cnwlc.memo.App.etc.game.snake.Snake;
import com.example.cnwlc.memo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameActivity extends Activity {
    private boolean bOneTofifty = false;
    private boolean bSnake = false;

    @BindView(R.id.gameA_text_view_progressView)
    TextView textViewPercent;
    @BindView(R.id.gameA_progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.gameA_button_1to50)
    Button buttonOneToFifty;
    @BindView(R.id.gameA_button_snake)
    Button buttonSnake;

    private BackgroundTask task;
    private int value;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.gameA_button_1to50, R.id.gameA_button_snake, R.id.gameA_button_cancel})
    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.gameA_button_1to50:
                task = new BackgroundTask();
                task.execute(100);

                bOneTofifty = true;
                buttonSnake.setEnabled(false);
                break;
            case R.id.gameA_button_snake:
                task = new BackgroundTask();
                task.execute(100);

                bSnake = true;
                buttonOneToFifty.setEnabled(false);
                break;
            case R.id.gameA_button_cancel:
                task.cancel(true);
                break;
        }
    }

    // OneToFifty Game - AsyncTask<Params, Progress, Result>
    class BackgroundTask extends AsyncTask<Integer, Integer, Integer> {
        protected void onPreExecute() {
            value = 0;
            progressBar.setProgress(value);
        }

        protected Integer doInBackground(Integer... values) {
            while (isCancelled() == false) {
                value++;
                if (value >= 100) {
                    break;
                } else {
                    publishProgress(value);
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                }
            }
            return value;
        }

        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0].intValue());
            textViewPercent.setText("" + values[0].toString() + " %");
        }

        protected void onPostExecute(Integer result) {
            progressBar.setProgress(0);
            textViewPercent.setText("Finished.");

            if (bOneTofifty == true) {
                Intent Game_1to50_intent = new Intent(GameActivity.this, OneToFifty.class);
                startActivity(Game_1to50_intent);
            }
            if (bSnake == true) {
                Intent Game_Snake_intent = new Intent(GameActivity.this, Snake.class);
                startActivity(Game_Snake_intent);
            }
        }

        protected void onCancelled() {
            buttonSnake.setEnabled(true);
            buttonOneToFifty.setEnabled(true);
            bOneTofifty = false;
            bSnake = false;

            progressBar.setProgress(0);
            textViewPercent.setText("Cancelled.");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        buttonSnake.setEnabled(true);
        buttonOneToFifty.setEnabled(true);
        bOneTofifty = false;
        bSnake = false;

        textViewPercent.setText("");
    }
}