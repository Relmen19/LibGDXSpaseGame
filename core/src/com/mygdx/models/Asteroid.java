package com.mygdx.models;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Asteroid extends SpaceObject{

    private int type;

    public static final int SMALL = 0;
    public static final int MEDIUM = 1;
    public static final int LARGE = 2;

    private int numPoint;
    private float[] dists;

    private boolean remove;

    public Asteroid(float x, float y, int type) {
        this.type = type;
        this.x = x;
        this.y = y;

        switch (type) {
            case SMALL:
                numPoint = 18;
                width = height = 12;
                speed = MathUtils.random(70, 100);
                break;
            case MEDIUM:
                numPoint = 20;
                width = height = 20;
                speed = MathUtils.random(50, 65);
                break;
            case LARGE:
                numPoint = 24;
                width = height = 40;
                speed = MathUtils.random(20, 35);
                break;
        }

        rotationSpeed = MathUtils.random(-1 ,1);

        radians = MathUtils.random(2 * 3.1415f);

        dx = MathUtils.cos(radians) * speed;
        dy = MathUtils.sin(radians) * speed;

        shapex = new float[numPoint];
        shapey = new float[numPoint];

        dists = new float[numPoint];

        int radius = width / 2;

        for(int i = 0; i < numPoint; i++)
            dists[i] = MathUtils.random(3 * radius / 4, radius);

        setShape();
    }

    private void setShape() {
        float angle = 0;
        for(int i = 0; i < numPoint; i++){
            shapex[i] = x + MathUtils.cos(angle + radians) * dists[i];
            shapey[i] = y + MathUtils.sin(angle + radians) * dists[i];
            angle += 2 * 3.1415f / numPoint;
        }
    }

    public int getType() {
        return type;
    }

    public boolean shouldRemove() {
        return remove;
    }

    public void update(float dt){

        x += dx * dt;
        y += dy * dt;

        radians += rotationSpeed * dt;
        setShape();
        wrap();
    }

    public void draw(ShapeRenderer sh){
        sh.setColor(1, 1, 1, 1);
        sh.begin(ShapeRenderer.ShapeType.Filled);

        for(int i = 0, j = shapex.length - 1;
            i < shapex.length; j = i++)
            sh.line(shapex[i], shapey[i], shapex[j], shapey[j]);

        sh.end();
    }
}




















