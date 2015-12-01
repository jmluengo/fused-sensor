# Fuser Sensor
   
"Sensor Fusion" android library allows you to get pitch, roll and azimuth parameters of your mobile 
phone by processing and combining the information provided by the sensors:

   * Accelerometer
   * Gyroscope
   * Magnetic field
   
Sometimes sensors data is not very precise and getting an stable data flow is not the easiest task. 
This code does a nice attempt to help and achieves so with a decent accuracy.

Download
--------

Download via Gradle:

```groovy
    dependencies {
        compile 'com.jmluengo.fusedsensors:0.1.0'
    }
```

Or via Maven:

```xml
    <dependency>
        <groupId>com.jmluengo.fusedsensors</groupId>
        <artifactId>fusedsensors</artifactId>
        <version>0.1.0</version>
        <type>aar</type>
    </dependency>
```

Usage
-----

To use this library you need to make your activity implements the FusedSensorEventListener, it
 will allow your activity to get fused sensor data. Ensure to start and stop FusedSensor in your 
 activity onResume and onPuase methods.

```java
    import com.jmluengo.fusedsensors.FusedSensor;
    import com.jmluengo.fusedsensors.FusedSensorEventListener;

    public class MyActivity extends Activity implements FusedSensorEventListener {

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
            // Update pitch, roll and azimuth data in your activity
        }
    }
```

Developed By
------------

* José Miguel Martínez Luengo - <jmluengo@gmail.com>

Contributors
------------

* [José Miguel Martínez Luengo][1]

License
-------

    Copyright 2015 José Miguel Martínez Luengo

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


[1]: https://github.com/jmluengo