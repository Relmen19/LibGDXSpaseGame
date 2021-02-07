package com.mygdx.models;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.CircleShape;

public class Bullet extends SpaceObject{

    private float lifeTime;
    private float lifeTimer;

    private boolean remove;

    public Bullet(float x, float y, float radians){
        this.x = x;
        this.y = y;
        this.radians = radians;

        float speed = 500;

        dx = MathUtils.cos(radians) * speed;
        dy = MathUtils.sin(radians) * speed;

        width = height = 2;

        lifeTime = 1.7f;
        lifeTimer = 0;
    }

    public boolean shouldRemove() {
        return remove;
    }

    public void update(float dt){

        // set position
        x += dx * dt;
        y += dy * dt;

        wrap();

        lifeTimer += dt;
        if(lifeTimer > lifeTime) remove = true;

    }

    public void draw(ShapeRenderer sh){
        sh.setColor(110, 0, 255, 1);
        sh.begin(ShapeType.Line);
        sh.circle(x - width / 2, y - height / 2, width / 2);
        sh.end();
    }

}
