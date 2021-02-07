package com.mygdx.gameStates;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.managers.GameKeys;
import com.mygdx.managers.GameStateManager;
import com.mygdx.models.SpaceObject;
import com.mygdx.models.Asteroid;
import com.mygdx.models.Bullet;
import com.mygdx.models.Player;

import java.util.ArrayList;

public class PlayState extends GameState{

    private ShapeRenderer sh;
    private Player player;
    private ArrayList <Bullet> bullets;
    private ArrayList <Asteroid> asteroids;

    private int level;
    private int totalAsteroids;
    private int numAsteroidsLeft;

    public PlayState(GameStateManager gms) {
        super(gms);
    }

    @Override
    public void init() {
        sh = new ShapeRenderer();

        bullets = new ArrayList<Bullet>();

        player = new Player(bullets);

        asteroids = new ArrayList<Asteroid>();

        level = 3;
        spawnAsteroids();
    }

    private void spawnAsteroids(){

        asteroids.clear();

        int numToSpawn = 3 + level;
        totalAsteroids = numToSpawn * 7;
        numAsteroidsLeft = totalAsteroids;

        float x = 0, y = 0, dx, dy, dist = 0;

        for(int i = 0; i < numToSpawn; i++){

            // checking coordinate to closeness with player
            while (dist < 100) {

                x = MathUtils.random(MyGdxGame.WIDTH);
                y = MathUtils.random(MyGdxGame.HEIGHT);

                for(int j = 0; j < asteroids.size(); j++){
                    Asteroid a = asteroids.get(j);
                    if(!checkDistanceAsteroid(a, x, y)) break;
                }

                dx = x - player.getX();
                dy = y - player.getY();

                dist = (float) Math.sqrt(dx * dx + dy * dy);
            }

            // add asteroids with random coordinate in ArrayList
            asteroids.add(new Asteroid(x, y, Asteroid.LARGE));
            dist = 0;
        }
    }

    private boolean checkDistanceAsteroid(Asteroid a, float x, float y){
        float dx = x - a.getX();
        float dy = y - a.getY();
        float dist = (float) Math.sqrt(dx * dx + dy * dy);
        if(dist > 120) return true;
        return false;
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

        // update asteroids
        for(int i = 0; i < asteroids.size(); i++){
            asteroids.get(i).update(dt);
            if(asteroids.get(i).shouldRemove()){
                asteroids.remove(i);
                i--;
            }
        }
        
        // check collision
        checkCollision();
    }

    private void checkCollision() {

        // bullet-asteroid collision
        for(int i = 0; i < bullets.size(); i++){
            Bullet b = bullets.get(i);
            for(int j = 0; j < asteroids.size(); j++){
                Asteroid s = asteroids.get(j);
                if(s.contain(b.getX(), b.getY())){
                    bullets.remove(i); i--;
                    asteroids.remove(j); j--;
                    splitAsteroid(s);
                    break;
                }
            }
        }

        // player-asteroid collision
        for(int i = 0; i < asteroids.size(); i++){
            Asteroid a = asteroids.get(i);
            if(a.intersects(player)){
                player.hit();
                a.hit();splitAsteroid(a);
                asteroids.remove(i); i--;

                break;
            }
        }

        // asteroid-asteroid collision
//        for(int i = 0; i < asteroids.size(); i++){
////            ArrayList<Asteroid> as = asteroids;
//            Asteroid a = asteroids.get(i);
//            for(int j = i + 1; j < asteroids.size() - 1; j++){
//                Asteroid as = asteroids.get(j);
//                if(a.intersects(as) && i != j){
//                    a.hit();
//                    as.hit();
//                }
//            }
//        }
    }

    private void splitAsteroid(Asteroid s) {
        numAsteroidsLeft--;
        switch (s.getType()){
            case Asteroid.LARGE:
                asteroids.add(new Asteroid(s.getX(), s.getY(), Asteroid.MEDIUM));
                asteroids.add(new Asteroid(s.getX(), s.getY(), Asteroid.MEDIUM));
                break;
            case Asteroid.MEDIUM:
                asteroids.add(new Asteroid(s.getX(), s.getY(), Asteroid.SMALL));
                asteroids.add(new Asteroid(s.getX(), s.getY(), Asteroid.SMALL));
                break;
            case Asteroid.SMALL:
                    break;
        }
    }

    @Override
    public void draw() {

        //draw player
        player.draw(sh);

        // draw bullet
        for(int i = 0; i < bullets.size(); i++)
            bullets.get(i).draw(sh);

        // draw asteroids
        for(int i = 0; i < asteroids.size(); i++)
            asteroids.get(i).draw(sh);
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
