package edu.sdsmt.puzzle_johnson_na;

/*
Tutorial 2 Grading

Complete the following checklist.


___X___	10pt 	T2: Package is named correctly (not com.example.puzzle)

___X___	10pt 	T2: The layout items are named correctly (grubbyImage, buttonStart, textWelcome)

___X___	50pt	T2: Tutorial is completed

___X___	15pt 	T2: TASK: Custom view covered entire screen (-5pt each for minor error)

___X___	15pt 	T2: TASK: Landscape task (-3pt each for minor error)

__N/A__	15pt 	T2: CSC 578 ONLY TASK: Border around the board area (-5pt each for minor error)


The checklist is the starting point for course staff, who reserve the right to change the grade if they disagree with your assessment and to deduct points for other issues they may encounter, such as errors in the submission process, naming issues, etc.

-----


Tutorial 3 Grading

Complete the following checklist.

___X___	55 	T3: Tutorial completed (Graded on percent done)

___X___	15 	T3: TASK: Pieces can be dragged around (-3pt each for minor error)

___X___	10 	T3: TASK: Snapped piece is on bottom (-5pt for sometimes works)

___X___	10 	T3: TASK: Refreshes immediately after shuffle menu option (-5pt each for minor error)

___X___	10	T3: TASK: Refreshes immediately after dialog shuffle button (-5pt each for minor error)

__N/A__	10 	T3: CSC 576 ONLY: Displays solved version of the puzzle

The checklist is the starting point for course staff, who reserve the right to change the grade if they disagree with your assessment and to deduct points for other issues they may encounter, such as errors in the submission process, naming issues, etc.

 */


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