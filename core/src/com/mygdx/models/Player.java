package com.mygdx.models;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.MyGdxGame;

import java.util.ArrayList;

public class Player extends SpaceObject{

    private float[] flamey;
    private float[] flamex;

    private boolean left;
    private boolean right;
    private boolean up;

    private float maxSpeed;
    private float acceleration;
    private float deceleration;

    private float acceleratorTimer;

    private ArrayList<Bullet> bullets;
    private final int MAX_BULLETS = 4;

    public Player(ArrayList<Bullet> bullets) {

        this.bullets = bullets;

        x = MyGdxGame.WIDTH / 2;
        y = MyGdxGame.HEIGHT / 2;

        maxSpeed = 300;
        acceleration = 200;
        deceleration = 10;

        shapex = new float[4];
        shapey = new float[4];

        flamex = new float[3];
        flamey = new float[3];

        radians =  3.1415f / 2;

        rotationSpeed = 9;
    }

    private void setShape(){
        shapex[0] = x + MathUtils.cos(radians) * 8;
        shapey[0] = y + MathUtils.sin(radians) * 8;

        shapex[1] = x + MathUtils.cos(radians - 4 * 3.1415f / 5) * 8;
        shapey[1] = y + MathUtils.sin(radians - 4 * 3.1415f / 5) * 8;

        shapex[2] = x + MathUtils.cos(radians + 3.1415f) * 5;
        shapey[2] = y + MathUtils.sin(radians + 3.1415f) * 5;

        shapex[3] = x + MathUtils.cos(radians + 4 * 3.1415f / 5) * 8;
        shapey[3] = y + MathUtils.sin(radians + 4 * 3.1415f / 5) * 8;
    }

    private void setFlame(){
        flamex[0] = x + MathUtils.cos(radians - 5 * 3.1415f / 6) * 5;
        flamey[0] = y + MathUtils.sin(radians - 5 * 3.1415f / 6) * 5;

        flamex[1] = x + MathUtils.cos(radians - 3.1415f) * (6 + acceleratorTimer * 100);
        flamey[1] = y + MathUtils.sin(radians - 3.1415f) * (6 + acceleratorTimer * 100);

        flamex[2] = x + MathUtils.cos(radians + 5 * 3.1415f / 6) * 5;
        flamey[2] = y + MathUtils.sin(radians + 5 * 3.1415f / 6) * 5;
    }

    public void shoot(){
        if(bullets.size() > MAX_BULLETS) return;
        bullets.add(new Bullet(x, y, radians));
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void update(double dt){

        // turning
        if(left) radians += rotationSpeed * dt;
        if(right) radians -= rotationSpeed * dt;

        // acceleration
        if(up){
            dx += MathUtils.cos(radians) * acceleration * dt;
            dy += MathUtils.sin(radians) * acceleration * dt;

            acceleratorTimer += dt;
            if(acceleratorTimer > 0.05f) acceleratorTimer = 0;
        } else acceleratorTimer = 0;

        // deceleration
        float vec = (float) Math.sqrt(dx * dx + dy * dy);
        if(vec > 0){
            dx -= (dx / vec) * deceleration * dt;
            dy -= (dy / vec) * deceleration * dt;
        }

        if(vec > maxSpeed){
            dx = (dx / vec) * maxSpeed;
            dy = (dy / vec) * maxSpeed;
        }

        // set position
        x += dx * dt;
        y += dy * dt;

        // set shape
        setShape();

        //set flame
        setFlame();

        // screen wrap
        wrap();
    }

    public void draw(ShapeRenderer sh){
        sh.setColor(0, 1, 1, 1);

        sh.begin(ShapeType.Line);

//        *** vector of flying player ***
//        float vec = (float) Math.sqrt(dx * dx + dy * dy);
//        sh.line(x, y,dx + x,dy + y);

        // draw the player
        for(int i = 0, j = shapex.length - 1;
            i < shapex.length; j = i++)
            sh.line(shapex[i], shapey[i], shapex[j], shapey[j]);

        // draw the flame
        for(int i = 0, j = flamex.length - 1;
            i < flamex.length; j = i++)
            sh.line(flamex[i], flamey[i], flamex[j], flamey[j]);

        sh.end();
    }
}
