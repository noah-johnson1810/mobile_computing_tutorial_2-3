package edu.sdsmt.puzzle_johnson_na;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class Puzzle {

    /**
     * Most recent relative X touch when dragging
     */
    private float lastRelX;

    /**
     * Most recent relative Y touch when dragging
     */
    private float lastRelY;
    /**
     * This variable is set to a piece we are dragging. If
     * we are not dragging, the variable is null.
     */
    private PuzzlePiece dragging = null;

    /**
     * Percentage of the display width or height that
     * is occupied by the puzzle.
     */
    final static float SCALE_IN_VIEW = 0.9f;

    /**
     * Paint for filling the area the puzzle is in
     */
    private final Paint fillPaint;

    /**
     * Paint for outlining the area the puzzle is in
     */
    //private final Paint outlinePaint;

    /**
     * Completed puzzle bitmap
     */
    private final Bitmap puzzleComplete;

    /**
     * Collection of puzzle pieces
     */

    public ArrayList<PuzzlePiece> pieces = new ArrayList<>();

    /**
     * The size of the puzzle in pixels
     */
    private int puzzleSize;

    /**
     * How much we scale the puzzle pieces
     */
    private float scaleFactor;

    /**
     * Left margin in pixels
     */
    private int marginX;

    /**
     * Top margin in pixels
     */
    private int marginY;

    public Puzzle(Context context) {
        // Create paint for filling the area the puzzle will
        // be solved in.
        fillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fillPaint.setColor(0xffcccccc);
        puzzleComplete = BitmapFactory.decodeResource(context.getResources(), R.drawable.grubby_done);

        pieces.add(new PuzzlePiece(context, R.drawable.grubby1, 0.264f, 0.175f));
    }

    public void draw(Canvas canvas) {
        int wid = canvas.getWidth();
        int hit = canvas.getHeight();

        int minDim = Math.min(wid, hit);

        puzzleSize = (int)(minDim * SCALE_IN_VIEW);

        marginX = (wid - puzzleSize) / 2;
        marginY = (hit - puzzleSize) / 2;

        canvas.drawRect(marginX, marginY, marginX + puzzleSize, marginY + puzzleSize, fillPaint);

        scaleFactor = (float)puzzleSize / (float)puzzleComplete.getWidth();

        canvas.save();
        canvas.translate(marginX, marginY);
        canvas.scale(scaleFactor, scaleFactor);
        //canvas.drawBitmap(puzzleComplete, 0, 0, null);
        canvas.restore();

        for (PuzzlePiece piece : pieces) {
            piece.draw(canvas, marginX, marginY, puzzleSize, scaleFactor);
        }
    }

    /**
     * Handle a touch event from the view.
     * @param view The view that is the source of the touch
     * @param event The motion event describing the touch
     * @return true if the touch is handled.
     */
    public boolean onTouchEvent(View view, MotionEvent event) {

        //
        // Convert an x,y location to a relative location in the
        // puzzle.
        //

        float relX = (event.getX() - marginX) / puzzleSize;
        float relY = (event.getY() - marginY) / puzzleSize;

        switch (event.getActionMasked()) {

            case MotionEvent.ACTION_DOWN:
                return onTouched(relX, relY);

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if(dragging != null) {
                    dragging = null;
                    return true;
                }
                break;

            case MotionEvent.ACTION_MOVE:
                // If we are dragging, move the piece
                if(dragging != null) {
                    dragging.move(relX - lastRelX, relY - lastRelY);

                    lastRelX = relX;
                    lastRelY = relY;
                    return true;
                }
                break;
        }

        return false;
    }

    /**
     * Handle a touch message. This is when we get an initial touch
     * @param x x location for the touch, relative to the puzzle - 0 to 1 over the puzzle
     * @param y y location for the touch, relative to the puzzle - 0 to 1 over the puzzle
     * @return true if the touch is handled
     */
    private boolean onTouched(float x, float y) {

        // Check each piece to see if it has been hit
        // We do this in reverse order so we find the pieces in front
        for(int p=pieces.size()-1; p>=0;  p--) {
            if(pieces.get(p).hit(x, y, puzzleSize, scaleFactor)) {
                // We hit a piece!
                dragging = pieces.get(p);
                lastRelX = x;
                lastRelY = y;

                return true;
            }
        }

        return false;
    }
}
