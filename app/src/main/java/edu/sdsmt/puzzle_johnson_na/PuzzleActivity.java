package edu.sdsmt.puzzle_johnson_na;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class PuzzleActivity extends AppCompatActivity {
    /**
     * The puzzle view in this activity's view
     */
    private PuzzleView puzzleView;

    public static final String PLAYER_NAME = "user_name";

    @Override
    protected void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);

        puzzleView.saveInstanceState(bundle);
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_puzzle);

        puzzleView = this.findViewById(R.id.puzzleView);

        if (bundle != null) {
            // We have saved state
            puzzleView.loadInstanceState(bundle);
        }

        if (getIntent() != null) {
            String name = getIntent().getStringExtra(PLAYER_NAME);
            TextView view = findViewById(R.id.player_name);
            String message = getString(R.string.player) + ": " + name;
            view.setText(message);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_puzzle, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_shuffle) {
            puzzleView.getPuzzle().shuffle();
            puzzleView.invalidate();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}