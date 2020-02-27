package com.example.pingpong.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.pingpong.R;
import com.example.pingpong.threads.PongThread;
import com.example.pingpong.views.PongView;

public class PongActivity  extends Activity {

        private static final int MENU_NEW_GAME = 1;
        private static final int MENU_RESUME = 2;
        private static final int MENU_EXIT = 3;

        private PongThread mGameThread;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.pong_layout);
            Bundle extras = getIntent().getExtras();
            int ballSpeed = extras.getInt("ballSpeed");
            float computerDifficulty= extras.getFloat("computerProbability");
            boolean isTwoPlayer = extras.getBoolean("isTwoPlayer");
            final PongView mPongView = (PongView) findViewById(R.id.main);
            mPongView.setStatusView((TextView) findViewById(R.id.status));
            mPongView.setScoreView((TextView) findViewById(R.id.score));

            mGameThread = mPongView.getGameThread();
            mGameThread.setGameValues(ballSpeed, computerDifficulty, isTwoPlayer);
            if (savedInstanceState == null) {
                mGameThread.setState(PongThread.STATE_READY);
            } else {
                mGameThread.restoreState(savedInstanceState);
            }
        }

        @Override
        protected void onPause() {
            super.onPause();
            mGameThread.pause();
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            mGameThread.saveState(outState);
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            super.onCreateOptionsMenu(menu);

            menu.add(0, MENU_NEW_GAME, 0, R.string.menu_new_game);
            menu.add(0, MENU_RESUME, 0, R.string.menu_resume);
            menu.add(0, MENU_EXIT, 0, R.string.menu_exit);

            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case MENU_NEW_GAME:
                    mGameThread.startNewGame();
                    return true;
                case MENU_EXIT:
                    finish();
                    return true;
                case MENU_RESUME:
                    mGameThread.unPause();
                    return true;
            }
            return false;
        }

}

