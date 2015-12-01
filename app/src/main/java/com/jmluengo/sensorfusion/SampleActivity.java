/*
 * Copyright (C) 2015 Jose Miguel Martinez Luengo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jmluengo.sensorfusion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

        // Stop fused sensor
        FusedSensor.getDefault().stop();
    }

    @Override
    public void onFusedSensorUpdate(float pitch, float roll, float azimuth) {
        // Update pitch, roll and azimuth progress
        mSeekBarPitch.setProgress((int) Math.toDegrees(pitch) + 90);
        mSeekBarRoll.setProgress((int) Math.toDegrees(roll) + 180);
        mSeekBarAzimuth.setProgress((int) Math.toDegrees(azimuth) + 180);
    }
}
