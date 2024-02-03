package edu.sdsmt.puzzle_johnson_na;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;


/**
 * Custom view class for our Puzzle.
 */
public class PuzzleView extends View {


    /**
     * The actual puzzle
     */
    private Puzzle puzzle;

    public PuzzleView(Context context) {
        super(context);
        init();
    }

    public PuzzleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PuzzleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = puzzle.onTouchEvent(this, event);
        if (result) {
            invalidate();
        }
        return result;
    }

    private void init() {
        puzzle = new Puzzle(getContext());
        puzzle.setPuzzleView(this);

        Paint linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(0xff008000);
        linePaint.setStrokeWidth(3);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        puzzle.draw(canvas);
    }

    /**
     * Save the puzzle to a bundle
     * @param bundle The bundle we save to
     */
    public void saveInstanceState(Bundle bundle) {
        puzzle.saveInstanceState(bundle);
    }

    public void loadInstanceState(Bundle bundle) {
        puzzle.loadInstanceState(bundle);
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }
}