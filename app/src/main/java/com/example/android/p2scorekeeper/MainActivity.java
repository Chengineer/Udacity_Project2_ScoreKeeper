package com.example.android.p2scorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.p2scorekeeper.R;


public class MainActivity extends AppCompatActivity {

    // Team A's current number of cups.
    int scoreTeamA = 10;
    // Team B's current number of cups.
    int scoreTeamB = 10;
    // Counts how many times one of the teams had 0 cups, and the other one had > 0 cups.
    int timesOneTeamZeroCups = 0;
    // "Enables"/"disables" the first two conditions evaluation in check method.
    boolean rebuttalState = false;
    // "Enables"/"disables" buttons.
    boolean endGame = false;
    // "Enables"/"disables" Team A's extra bounce button.
    boolean extraBounceStateA = false;
    // "Enables"/"disables" Team B's extra bounce button.
    boolean extraBounceStateB = false;

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText((String.valueOf(score)) + " Cups");
    }

    /**
     * Team A loses 1 of its cups.
     * The button is "disabled" if the game is over or Team A's score is 0.
     *
     * @param view is button "TOSSED SHOT" under Team B, or "PENALTY" under Team A.
     */
    public void minusOneTeamA(View view) {
        extraBounceStateA = false;
        extraBounceStateB = false;
        if ((scoreTeamA > 0) && (endGame != true))
            scoreTeamA = scoreTeamA - 1;
        displayForTeamA(scoreTeamA);
        check(scoreTeamA, scoreTeamB);
    }

    /**
     * If there are 2 or less cups left for team A, it loses just one of its cups.
     * If there are more than 2 cups left for team A, it loses two of its cups, and extra bounces scoring may be allowed.
     * The button is "disabled" if the game is over or Team A's score is 0.
     *
     * @param view is button "BOUNCED SHOT" under Team B.
     */
    public void bouncedTeamB(View view) {
        extraBounceStateA = false;
        extraBounceStateB = false;
        if ((scoreTeamA <= 2) && (scoreTeamA > 0) && (endGame != true))
            scoreTeamA = scoreTeamA - 1;
        else if ((scoreTeamA > 2) && (endGame != true)) {
            scoreTeamA = scoreTeamA - 2;
            extraBounceStateB = true;
        }
        displayForTeamA(scoreTeamA);
        check(scoreTeamA, scoreTeamB);
    }

    /**
     * If there is 1 cup left for Team A, an extra bounce is not allowed, and the score doesn't change.
     * Else, if there is more than 1 cup left, an extra bounce is allowed, and team A loses one more cup.
     * The button may be "enabled" only after "BOUNCED SHOT" button is clicked, and there were more than 2 cups left for Team A one moment before.
     * The button is "disabled" if there's only 1 cup left for Team A, or if any other button is clicked (because then extraBounceStateB becomes false).
     *
     * @param view is button "EXTRA BOUNCE" under Team B.
     */
    public void extraBounceTeamB(View view) {
        if (scoreTeamA == 1)
            extraBounceStateB = false;
        else {
            if (extraBounceStateB == true)
                scoreTeamA = scoreTeamA - 1;
        }
        displayForTeamA(scoreTeamA);
        check(scoreTeamA, scoreTeamB);
    }

    /**
     * Displays the given score for Team B.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText((String.valueOf(score)) + " Cups");
    }

    /**
     * Team B loses 1 of its cups.
     * The button is "disabled" if the game is over or Team B's score is 0.
     *
     * @param view is button "TOSSED SHOT" under Team A, or "PENALTY" under team B.
     */
    public void minusOneTeamB(View view) {
        extraBounceStateA = false;
        extraBounceStateB = false;
        if ((scoreTeamB > 0) && (endGame != true))
            scoreTeamB = scoreTeamB - 1;
        displayForTeamB(scoreTeamB);
        check(scoreTeamA, scoreTeamB);
    }

    /**
     * If there are 2 or less cups left for team B, it loses just one of its cups.
     * If there are more than 2 cups left for team B, it loses two of its cups, and extra bounces scoring may be allowed.
     * The button is "disabled" if the game is over or Team B's score is 0.
     *
     * @param view is button "BOUNCED SHOT" under Team A.
     */
    public void bouncedTeamA(View view) {
        extraBounceStateA = false;
        extraBounceStateB = false;
        if ((scoreTeamB <= 2) && (scoreTeamB > 0) && (endGame != true))
            scoreTeamB = scoreTeamB - 1;
        else if ((scoreTeamB > 2) && (endGame != true)) {
            scoreTeamB = scoreTeamB - 2;
            extraBounceStateA = true;
        }
        displayForTeamB(scoreTeamB);
        check(scoreTeamA, scoreTeamB);
    }

    /**
     * If there is 1 cup left for Team B, an extra bounce is not allowed, and the score doesn't change.
     * Else, if there is more than 1 cup left, an extra bounce is allowed, and team B loses one more cup.
     * The button may be "enabled" only after "BOUNCED SHOT" button is clicked, and there were more than 2 cups left for Team B one moment before.
     * The button is "disabled" if there's only 1 cup left for Team B, or if any other button is clicked (because then extraBounceStateA becomes false).
     *
     * @param view is button "EXTRA BOUNCE" under Team A
     */
    public void extraBounceTeamA(View view) {
        if (scoreTeamB == 1)
            extraBounceStateA = false;
        else {
            if (extraBounceStateA == true)
                scoreTeamB = scoreTeamB - 1;
        }
        displayForTeamB(scoreTeamB);
        check(scoreTeamA, scoreTeamB);
    }

    /**
     * Checks if one of the following conditions is true: 1. A=0 B>0, 2. A>0 B=0, 3. A=0 B=0.
     * If yes, calls the relevant method:
     * For conditions 1 and 2: Increases timesOneTeamZeroCups by 1. Then, if its current value is 1, it is the first time we get this kind of condition and thus the game goes into "rebuttal".
     * if its current value is 2, it is the second time we get this kind of condition and thus the game is over (after an unsuccessful rebuttal, or during overtime).
     * For the third condition: the game goes into "overtime" (this condition is possible only after a successful rebuttal).
     * During "rebuttal" (while no shot is missed) only A=B=0 scenario is checked, because the initial condition of a rebuttal is that one of them is already equal to 0.
     *
     * @param scoreA is Team A's current # of cups.
     * @param scoreB is Team B's current # of cups.
     */
    public void check(int scoreA, int scoreB) {
        if (rebuttalState != true) {
            if ((scoreA == 0) && (scoreB > 0)) {
                timesOneTeamZeroCups += 1;
                if (timesOneTeamZeroCups == 1)
                    rebuttal("Team A");
                else if (timesOneTeamZeroCups == 2)
                    gameOver("B");
            }
            if ((scoreB == 0) && (scoreA > 0)) {
                timesOneTeamZeroCups += 1;
                if (timesOneTeamZeroCups == 1)
                    rebuttal("Team B");
                else if (timesOneTeamZeroCups == 2)
                    gameOver("A");
            }
        }
        if ((scoreA == 0) && (scoreB == 0) && (timesOneTeamZeroCups == 1)) {
            rebuttalState = false;
            overtime();
        }

    }

    /**
     * Sets a "mini" new game: each one of the teams starts with 6 cups.
     * Updates the status of the game to "overtime"
     */
    public void overtime() {
        scoreTeamA = 6;
        scoreTeamB = 6;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        displayMessage("Overtime");
    }

    /**
     * Updates the status of the game to "rebuttal".
     *
     * @param rbtlTeam is the name of the team which gets the chance for rebuttal (the currently "losing" team).
     */
    public void rebuttal(String rbtlTeam) {
        displayMessage(rbtlTeam + "'s chance for Rebuttal");
        rebuttalState = true;
    }

    /**
     * Stops the rebuttal due to a missed shot, and sends the current scores to check method for evaluation.
     *
     * @param view is the button "REBUTTAL SHOT MISSED"
     */
    public void rebuttalShotMissed(View view) {
        extraBounceStateA = false;
        extraBounceStateB = false;
        rebuttalState = false;
        check(scoreTeamA, scoreTeamB);
    }

    /**
     * Displays the status of the game (new,rebuttal,overtime,end).
     *
     * @param msg is the textView under the BeerPong imageView.
     */
    public void displayMessage(String msg) {
        TextView messageView = (TextView) findViewById(R.id.message);
        messageView.setText(msg);
    }

    /**
     * Updates the status of the game to "game over".
     *
     * @param winner is the name of the winning team.
     */
    public void gameOver(String winner) {
        endGame = true;
        displayMessage("Game Over. The Winner is Team " + winner + "!");
    }

    /**
     * Sets a new game- each one of the teams starts with 10 cups.
     * Resets timesOneTeamZeroCups and all flags.
     * Updates the status of the game to "new game".
     *
     * @param view is the button "NEW GAME".
     */
    public void newGame(View view) {
        scoreTeamA = 10;
        scoreTeamB = 10;
        timesOneTeamZeroCups = 0;
        rebuttalState = false;
        endGame = false;
        extraBounceStateA = false;
        extraBounceStateB = false;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        displayMessage("New Game. May the best team win!");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
