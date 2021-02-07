package com.mygdx.models;

import com.mygdx.game.MyGdxGame;

public class SpaceObject {

    protected float x;
    protected float y;

    protected float dy;
    protected float dx;

    protected float radians;
    protected float speed;
    protected float rotationSpeed;

    protected int width;
    protected int height;

    protected float[] shapex;
    protected float[] shapey;


    // бесконечное поле
    protected void wrap(){
        if(x < 0) x = MyGdxGame.WIDTH;
        if(x > MyGdxGame.WIDTH) x = 0;
        if(y < 0) y = MyGdxGame.HEIGHT;
        if(y > MyGdxGame.HEIGHT) y = 0;

    }

}
