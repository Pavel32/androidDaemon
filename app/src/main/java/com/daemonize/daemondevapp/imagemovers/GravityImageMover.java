package com.daemonize.daemondevapp.imagemovers;


import android.graphics.Bitmap;
import android.util.Pair;

import java.util.List;

public class GravityImageMover extends ImageTranslationMover {

    private boolean touched = false;
    private boolean falling = false;

    public GravityImageMover(List<Bitmap> sprite, float velocity, Pair<Float, Float> startingPos) {
        super(sprite, velocity, startingPos);
    }

    @Override
    public GravityImageMover setBorders(float x, float y) {
        super.setBorders(x, y);
        return this;
    }

    @Override
    public void setTouchDirection(float x, float y) {
        velocity.intensity = initVelocity;
        touched = true;
        super.setTouchDirection(x, y);
    }

    @Override
    public PositionedBitmap move() {

            if(velocity.intensity <= 0) {
                falling = true;
                setTouchDirection(lastX, borderY);
                velocity.intensity = initVelocity;
                return super.move();
            }

            if (Math.abs(lastY - borderY) < 50) {
                falling = false;
                if (touched) {
                    touched = false;
                } else {
                    return null;
                }
            }

            if(!falling)
                velocity.intensity -= 0.3;

            return super.move();
    }
}
