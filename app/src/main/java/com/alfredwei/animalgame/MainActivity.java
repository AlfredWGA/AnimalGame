package com.alfredwei.animalgame;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.KeyEvent;
import android.os.Handler;
import android.content.res.Configuration;


import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    // Indicate the state of the game, 0: start, 1: gaming, 2: win, 3: lose
    private int mState = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Random object used to generate random number;
    private Random r;

    private <T> void swap(T [] a, int i, int j)
    {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private <T> void shuffle(T[] array)
    {
        int randomIndex = 0;
        for (int i = 0; i < array.length; i++)
        {
            randomIndex = r.nextInt(array.length);
            swap(array, i, randomIndex);
        }
    }

    // Current score.
    private int mScore = 0;
    // Index of the correct image.
    private int mCorrectImg;

    // Achieve this score will win.
    static public int mMaxScore = 10;

    // Indicate which image does each ImageView shows.
    // Corresponding to mImgHints[] and mImgIds[];
    public Integer [] mImgIndex = {0, 1, 2, 3};
    public ImageView [] mImgViews = new ImageView[4];
    static public int [] mImgIds = {R.drawable.cat, R.drawable.elephant, R.drawable.giraffe, R.drawable.wolf};
    static public String [] mImgHints = {"cat", "elephant", "giraffe", "wolf"};

    public void onStartButtonClick(View view)
    {
        r = new Random();
        setContentView(R.layout.gaming);
        mImgViews[0] = (ImageView)findViewById(R.id.img_1);
        mImgViews[1] = (ImageView)findViewById(R.id.img_2);
        mImgViews[2] = (ImageView)findViewById(R.id.img_3);
        mImgViews[3] = (ImageView)findViewById(R.id.img_4);
        updateGamingView();
    }

    public void updateGamingView()
    {
        mCorrectImg = r.nextInt(mImgHints.length);
        shuffle(mImgIndex);
        ((TextView)findViewById(R.id.text_score)).setText(String.valueOf(mScore));
        ((TextView)findViewById(R.id.text_hint)).setText(mImgHints[mCorrectImg]);
        for (int i = 0; i < mImgViews.length; i++)
        {
            mImgViews[i].setImageResource(mImgIds[mImgIndex[i]]);
        }

    }

    private boolean mIsExit;


    /**
     * Tap backspace for 2 times to exit.
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mIsExit) {
                this.finish();

            } else {
                Toast.makeText(this, "Tap again to quit.", Toast.LENGTH_SHORT).show();
                mIsExit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIsExit = false;
                    }
                }, 2000);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void onImage1Click(View view)
    {
        if (mCorrectImg == mImgIndex[0])
        {
            mScore++;
            updateGamingView();
        }
        else
        {
            mScore--;
            updateGamingView();
        }
        if (mScore <= 0)
            setContentView(R.layout.lose);
        if (mScore >= mMaxScore)
            setContentView(R.layout.win);
    }

    public void onImage2Click(View view)
    {
        if (mCorrectImg == mImgIndex[1])
        {
            mScore++;
            updateGamingView();
        }
        else
        {
            mScore--;
            updateGamingView();
        }
        if (mScore <= 0)
            setContentView(R.layout.lose);
        if (mScore >= mMaxScore)
            setContentView(R.layout.win);
    }

    public void onImage3Click(View view)
    {
        if (mCorrectImg == mImgIndex[2])
        {
            mScore++;
            updateGamingView();
        }
        else
        {
            mScore--;
            updateGamingView();
        }
        if (mScore <= 0)
            setContentView(R.layout.lose);
        if (mScore >= mMaxScore)
            setContentView(R.layout.win);
    }

    public void onImage4Click(View view)
    {
        if (mCorrectImg == mImgIndex[3])
        {
            mScore++;
            updateGamingView();
        }
        else
        {
            mScore--;
            updateGamingView();
        }
        if (mScore <= 0)
            setContentView(R.layout.lose);
        if (mScore >= mMaxScore)
            setContentView(R.layout.win);
    }

    public void onContinueButtonClick(View view)
    {
        mScore = 0;
        setContentView(R.layout.gaming);
        mImgViews[0] = (ImageView)findViewById(R.id.img_1);
        mImgViews[1] = (ImageView)findViewById(R.id.img_2);
        mImgViews[2] = (ImageView)findViewById(R.id.img_3);
        mImgViews[3] = (ImageView)findViewById(R.id.img_4);
        updateGamingView();
    }
}

