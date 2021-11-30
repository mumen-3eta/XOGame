package Mumen3eta.xogame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0:yellow  1:red  2: empty
    int activePlayer = 0;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}
            , {6, 7, 8}, {0, 3, 6}
            , {1, 4, 7}, {2, 5, 8}
            , {0, 4, 8}, {2, 4, 6}};

    boolean gameActive = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);
    }

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(view.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-3000);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(3000).rotation(3600).setDuration(400);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    gameActive = false;
                    String winner;
                    if (activePlayer == 0) {
                        winner = "Red";
                    } else {
                        winner = "Yellow";
                    }

                    Button button = (Button) findViewById(R.id.button);
                    TextView textView = (TextView) findViewById(R.id.textView);

                    textView.setText(winner + " has won!");
                    button.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                    return;

                }
            }
            for (int i : gameState) {
                if (i == 2) return;
            }

            Button button = (Button) findViewById(R.id.button);
            TextView textView = (TextView) findViewById(R.id.textView);

            textView.setText("It's a draw!");
            button.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);

            gameActive = false;

        }
    }

    public void playAgain(View view) {
        Button button = (Button) findViewById(R.id.button);
        TextView textView = (TextView) findViewById(R.id.textView);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridlayout);

        button.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView child = (ImageView) gridLayout.getChildAt(i);
            child.setImageDrawable(null);
        }
        activePlayer = 0;
        gameActive = true;

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
    }
}