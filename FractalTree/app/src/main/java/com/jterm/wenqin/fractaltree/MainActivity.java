package com.jterm.wenqin.fractaltree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int mDepth = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FractalView treeView = (FractalView) findViewById(R.id.tree_view);
        final TextView depthText = (TextView) findViewById(R.id.depth);
        depthText.setText(String.valueOf(mDepth));

        Button resetBtn = (Button) findViewById(R.id.reset);

        resetBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                resetFromDepth(treeView, depthText);
            }
        });

        Button decreaseBtn = (Button) findViewById(R.id.decrease_depth);
        decreaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDepth > 0) {
                    mDepth--;
                }
                depthText.setText(String.valueOf(mDepth));
            }
        });

        Button increaseBtn = (Button) findViewById(R.id.increase_depth);
        increaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                depthText.setText(String.valueOf(++mDepth));
            }
        });

        resetFromDepth(treeView, depthText);

    }

    private void resetFromDepth(FractalView treeView, TextView depthText) {
        String depthString = depthText.getText().toString();
        int depth = Integer.parseInt(depthString);
        treeView.reset(depth);
    }

}
