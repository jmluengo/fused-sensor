/************************************************************************************
 * Copyright (c) 2015 Jose Miguel Martinez Luengo
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 ************************************************************************************/

package com.jmluengo.sensorfusion;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;

import com.jmluengo.fusedsensors.FusedSensor;
import com.jmluengo.fusedsensors.FusedSensorEventListener;

public class SampleActivity extends AppCompatActivity implements FusedSensorEventListener {

    public final static String TAG = "SampleActivity";

    private SeekBar mSeekBarPitch;
    private SeekBar mSeekBarRoll;
    private SeekBar mSeekBarAzimuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Get ui refereces
        mSeekBarPitch = (SeekBar) findViewById(R.id.seekBarPitch);
        mSeekBarPitch.setMax(180);
        mSeekBarRoll = (SeekBar) findViewById(R.id.seekBarRoll);
        mSeekBarRoll.setMax(360);
        mSeekBarAzimuth = (SeekBar) findViewById(R.id.seekBarAzimuth);
        mSeekBarAzimuth.setMax(360);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Start fused sensor updates
        FusedSensor.getDefault().start(getApplicationContext(), this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Stop fuser sensor
        FusedSensor.getDefault().stop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sample, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFusedSensorUpdate(float pitch, float roll, float azimuth) {
        Log.d(TAG, "pitch:" + (int) Math.toDegrees(pitch) + " roll:" + (int) Math.toDegrees(roll) + " azimuth:" + (int) Math.toDegrees(azimuth));

        // Update pitch, roll and yaw progress
        mSeekBarPitch.setProgress((int) Math.toDegrees(pitch) + 90);
        mSeekBarRoll.setProgress((int) Math.toDegrees(roll) + 180);
        mSeekBarAzimuth.setProgress((int) Math.toDegrees(azimuth) + 180);
    }
}
