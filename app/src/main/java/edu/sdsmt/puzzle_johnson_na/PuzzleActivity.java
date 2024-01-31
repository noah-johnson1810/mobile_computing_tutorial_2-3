package edu.sdsmt.puzzle_johnson_na;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PuzzleActivity extends AppCompatActivity {
    public static final String PLAYER_NAME = "user_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);
            if( getIntent() !=null ){
                String name = getIntent().getStringExtra(PLAYER_NAME);
                TextView view = findViewById(R.id.player_name);
                String message = getString(R.string.player) + ": " + name;
                view.setText(message);
            }
    }
}