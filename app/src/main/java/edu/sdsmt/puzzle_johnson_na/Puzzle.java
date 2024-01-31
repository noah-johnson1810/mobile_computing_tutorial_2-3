package edu.sdsmt.puzzle_johnson_na;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

public class Puzzle {

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

        int puzzleSize = (int)(minDim * SCALE_IN_VIEW);

        int marginX = (wid - puzzleSize) / 2;
        int marginY = (hit - puzzleSize) / 2;

        canvas.drawRect(marginX, marginY, marginX + puzzleSize, marginY + puzzleSize, fillPaint);

        float scaleFactor = (float)puzzleSize / (float)puzzleComplete.getWidth();

        canvas.save();
        canvas.translate(marginX, marginY);
        canvas.scale(scaleFactor, scaleFactor);
        //canvas.drawBitmap(puzzleComplete, 0, 0, null);
        canvas.restore();

        for (PuzzlePiece piece : pieces) {
            piece.draw(canvas, marginX, marginY, puzzleSize, scaleFactor);
        }
    }

}
