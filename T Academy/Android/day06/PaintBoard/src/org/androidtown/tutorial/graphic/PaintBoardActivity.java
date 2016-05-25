package org.androidtown.tutorial.graphic;

import android.app.Activity;
import android.os.Bundle;

public class PaintBoardActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        PaintBoard board = new PaintBoard(this);
        setContentView(board);
    }
}