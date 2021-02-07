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

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float[] getShapex() {
        return shapex;
    }

    public float[] getShapey() {
        return shapey;
    }

    //
    public boolean contain(float x, float y){
        boolean b = false;

        for(int i = 0, j = shapex.length - 1; i < shapex.length; j = i++){
            if((shapey[i] > y) != (shapey[j] > y) && (x < (shapex[j] - shapex[i])
                    * (y - shapey[i]) / (shapey[j] - shapey[i]) + shapex[i]))
                b = !b;
        }

        return b;
    }

    public boolean intersects(SpaceObject other){
        float sx[] = other.getShapex();
        float sy[] = other.getShapey();
        for(int i = 0; i < sx.length; i++){
            if(contain(sx[i], sy[i])) return true;
        }
        return false;
    }

    // бесконечное поле
    protected void wrap(){
        if(x < 0) x = MyGdxGame.WIDTH;
        if(x > MyGdxGame.WIDTH) x = 0;
        if(y < 0) y = MyGdxGame.HEIGHT;
        if(y > MyGdxGame.HEIGHT) y = 0;
    }

    public void hit() {
        this.dx = -1 * this.dx;
        this.dy = -1 * this.dy;
    }
}
