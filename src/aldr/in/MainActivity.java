package aldr.in;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity
{
    public final static String EXTRA_MESSAGE = "aldr.in.MESSAGE";
    private static final String DEBUG_TAG = "MainActivity";
    private static int MAJOR_MOVE = 60;

    private ScoreGestureListener scoreGestureListener1;
    private ScoreGestureListener scoreGestureListener2;
    private Scoreboard scoreboard;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        Log.d(DEBUG_TAG, "onCreate called");
        //Log.d(DEBUG_TAG, "onCreate called with score1="+score1+" score2="+score2);
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            Log.d(DEBUG_TAG, "onCreate::savedInstanceState.keySet() = " + savedInstanceState.keySet());
        }

        setContentView(R.layout.main);

        final TextView score1TextView = (TextView)findViewById(R.id.score_1);
        final TextView score2TextView = (TextView)findViewById(R.id.score_2);

        if (scoreboard==null) {
            scoreboard = new Scoreboard();
        }
        score1TextView.setText(""+scoreboard.getScore1().getScore());
        score2TextView.setText(""+scoreboard.getScore2().getScore());

        scoreGestureListener1 = new ScoreGestureListener(scoreboard.getScore1(), score1TextView);
        scoreGestureListener2 = new ScoreGestureListener(scoreboard.getScore2(), score2TextView);
        final GestureDetector gestureDetector = new GestureDetector(this, scoreGestureListener1);
        score1TextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }

        });

        final GestureDetector gestureDetector2 = new GestureDetector(this, scoreGestureListener2);
        score2TextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector2.onTouchEvent(event);
            }
        });

    }


    public void resetScores(View view) {
        resetScoreboard(new Scoreboard());

        final TextView score1TextView = (TextView)findViewById(R.id.score_1);
        final TextView score2TextView = (TextView)findViewById(R.id.score_2);

        score1TextView.setText(""+scoreboard.getScore1().getScore());
        score2TextView.setText(""+scoreboard.getScore2().getScore());
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
/*
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
*/
    }

//    @Override
//    protected void onStart() {
//        Log.d(DEBUG_TAG, "onStart called");
//
//        super.onStart();
//    }
//
    @Override
    protected void onResume() {
        Log.d(DEBUG_TAG, "onResume called");
        super.onResume();

        final TextView score1TextView = (TextView)findViewById(R.id.score_1);
        final TextView score2TextView = (TextView)findViewById(R.id.score_2);

        score1TextView.setText(""+scoreboard.getScore1().getScore());
        score2TextView.setText(""+scoreboard.getScore2().getScore());

        Log.d(DEBUG_TAG, "onResume:: scoreboard = "+scoreboard);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(DEBUG_TAG, "onSaveInstanceState called");

        if (outState != null) {
            Log.d(DEBUG_TAG, "onSaveInstanceState :: outState = "+outState.keySet());
            outState.putInt("score1", scoreboard.getScore1().getScore());
            outState.putInt("score2", scoreboard.getScore2().getScore());
        }
        super.onSaveInstanceState(outState);

/*
        Log.d(DEBUG_TAG, "saving score1 = " + scoreboard.getScore1().getScore());
        Log.d(DEBUG_TAG, "saving score2 = " + scoreboard.getScore2().getScore());
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.score1), scoreboard.getScore1().getScore());
        editor.putInt(getString(R.string.score2), scoreboard.getScore2().getScore());
        editor.commit();
*/
        Log.d(DEBUG_TAG, "onSave:: scoreboard = "+scoreboard);


    }

    private void resetScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
        //update the score instance references in the listener
        scoreGestureListener1.setScore(scoreboard.getScore1());
        scoreGestureListener2.setScore(scoreboard.getScore2());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(DEBUG_TAG, "onRestoreInstanceState called");

        if (savedInstanceState != null) {
            Log.d(DEBUG_TAG, "onRestoreInstanceState :: savedInstanceStative = "+savedInstanceState.keySet());
            Log.d(DEBUG_TAG, "onRestoreInstanceState :: savedInstanceStative.score1 = "+savedInstanceState.getInt("score1"));
            Log.d(DEBUG_TAG, "onRestoreInstanceState :: savedInstanceStative.score1 = "+savedInstanceState.getInt("score2"));
            resetScoreboard(new Scoreboard(new Score(savedInstanceState.getInt("score1")),
                    new Score(savedInstanceState.getInt("score2"))));
        }
        super.onRestoreInstanceState(savedInstanceState);

//        score1 = savedInstanceState.getInt("score1");
//        score2 = savedInstanceState.getInt("score2");
//        Log.d(DEBUG_TAG, "retrieving score1 = " + score1);
//        Log.d(DEBUG_TAG, "retrieving score2 = " + score2);

//        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
//        score1 = sharedPref.getInt(getString(R.string.score1), 0);
//        score2 = sharedPref.getInt(getString(R.string.score2), 0);
        Log.d(DEBUG_TAG, "onRestoreInstanceState:: scoreboard = "+scoreboard);

    }

    class ScoreGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "MainActivity";
        private TextView textView;
        private Score score;

        ScoreGestureListener(Score score, TextView textView) {
            this.textView = textView;
            this.score = score;
        }

        void setScore(Score score) {
            this.score = score;
        }

        @Override
        public boolean onDown(MotionEvent event) {
            Log.d(DEBUG_TAG, "onDown: " + event.toString());
            return true;
        }



        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());

            Log.d(DEBUG_TAG, "e2.X - e1.X = " + (event2.getX() - event1.getX()));
            Log.d(DEBUG_TAG, "e2.Y - e1.Y = " + (event2.getY() - event1.getY()));
            int xMovement = Math.round(event2.getX()-event1.getX());
            int yMovement = Math.round(event2.getY()-event1.getY());
            int majorMovement = Math.round(Math.max(Math.abs(xMovement), Math.abs(yMovement)));//the major direction of this fling
            Log.d(DEBUG_TAG, "majorMovement = " + majorMovement);
            Log.d(DEBUG_TAG, "majorMovement == Math.abs(yMovement) ::  " + (majorMovement == Math.abs(yMovement)));

            majorMovement = majorMovement == Math.abs(yMovement) ? yMovement : xMovement;

            if(majorMovement < 0) {
                if (score.getScore()>0) {
                    score.setScore(score.getScore() - 1);
                    textView.setText(""+score.getScore());
                }
            }
            else {
                score.setScore(score.getScore() + 1);
                textView.setText(""+score.getScore());
            }
            return true;
        }
    }
}

