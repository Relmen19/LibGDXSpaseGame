package com.mygdx.managers;

import com.mygdx.gameStates.GameState;
import com.mygdx.gameStates.MenuState;
import com.mygdx.gameStates.PlayState;

public class GameStateManager {

    private GameState gameState;

    public static final int MENU = 0;
    public static final int PLAY = 1;

    public GameStateManager(){
        setState(PLAY);
    }

    public void setState(int s){
        if (gameState != null) gameState.dispose();
        switch (s){
            case MENU:
                // switch to menu
                gameState = new MenuState(this);
                break;
            case PLAY:
                // switch to play
                gameState = new PlayState(this);
                break;
        }
    }

    public void init() {
        gameState.init();
    }

    public void update(float dt){

        gameState.update(dt);
    }

    public void draw(){

        gameState.draw();
    }


}
