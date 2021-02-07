package com.mygdx.gameStates;

import com.mygdx.managers.GameStateManager;

public abstract class GameState {

    protected GameStateManager gsm;

    protected GameState(GameStateManager gms){
        this.gsm = gms;
    }

    public abstract void init();
    public abstract void update(float dt);
    public abstract void draw();
    public abstract void handleInput();
    public abstract void dispose();
}
