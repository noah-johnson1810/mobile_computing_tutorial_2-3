package edu.sdsmt.puzzle_johnson_na;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartPuzzle(View view) {
        Intent intent = new Intent(this, PuzzleActivity.class);
        EditText name = findViewById(R.id.name);
        intent.putExtra(PuzzleActivity.PLAYER_NAME, name.getText().toString());
        startActivity(intent);
    }
}