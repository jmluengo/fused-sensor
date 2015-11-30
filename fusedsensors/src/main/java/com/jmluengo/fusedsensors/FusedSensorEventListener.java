package com.jmluengo.fusedsensors;

/**
 * Implement this listener to get fused sensor updates
 * @author jmluengo
 */
public interface FusedSensorEventListener {

    /**
     * Call when a new fused sensor update is available
     * @param pitch
     * @param roll
     * @param azimuth
     */
    public void onFusedSensorUpdate(float pitch, float roll, float azimuth);

}
