package com.google.android.gms.samples.vision.face.photo;

import android.graphics.Bitmap;

/**
 * Created by renanrhoden on 17/12/17.
 */

class Eyes {
    private static final Eyes ourInstance = new Eyes();

    static Eyes getInstance() {
        return ourInstance;
    }

    private Eyes() {
    }

    Bitmap leftEye;

    Bitmap rightEye;

    public Bitmap getRightEye() {
        return rightEye;
    }

    public void setRightEye(Bitmap rightEye) {
        this.rightEye = rightEye;
    }

    public Bitmap getLeftEye() {
        return leftEye;
    }

    public void setLeftEye(Bitmap leftEye) {
        this.leftEye = leftEye;
    }
}
