package com.mygdx.gameStates;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.managers.GameKeys;
import com.mygdx.managers.GameStateManager;
import com.mygdx.models.Bullet;
import com.mygdx.models.Player;

import java.util.ArrayList;

public class PlayState extends GameState{

    private ShapeRenderer sh;
    private Player player;
    private ArrayList <Bullet> bullets;

    public PlayState(GameStateManager gms) {
        super(gms);
    }

    @Override
    public void init() {
        sh = new ShapeRenderer();

        bullets = new ArrayList<>();

        player = new Player(bullets);
    }

    @Override
    public void update(float dt) {
        // get user input
        handleInput();

        // update player
        player.update(dt);

        // update player bullets
        for(int i = 0; i < bullets.size(); i++){
            bullets.get(i).update(dt);
            if(bullets.get(i).shouldRemove()) {
                bullets.remove(i);
                i--;
            }
        }
    }

    @Override
    public void draw() {

        //draw player
        player.draw(sh);

        // draw bullet
        for(int i = 0; i < bullets.size(); i++)
            bullets.get(i).draw(sh);
    }

    @Override
    public void handleInput() {

        player.setLeft(GameKeys.isDown(GameKeys.LEFT));
        player.setRight(GameKeys.isDown(GameKeys.RIGHT));
        player.setUp(GameKeys.isDown(GameKeys.UP));
        if(GameKeys.isPressed(GameKeys.SPACE)) player.shoot();
    }

    @Override
    public void dispose() {

    }
}
