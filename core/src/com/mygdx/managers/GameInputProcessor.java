package com.mygdx.managers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class GameInputProcessor extends InputAdapter {

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Keys.UP: case Keys.W:
                GameKeys.setKeys(GameKeys.UP, true);
                break;
            case Keys.DOWN: case Keys.S:
                GameKeys.setKeys(GameKeys.DOWN, true);
                break;
            case Keys.LEFT: case Keys.A:
                GameKeys.setKeys(GameKeys.LEFT, true);
                break;
            case Keys.RIGHT: case Keys.D:
                GameKeys.setKeys(GameKeys.RIGHT, true);
                break;
            case Keys.ENTER:
                GameKeys.setKeys(GameKeys.ENTER, true);
                break;
            case Keys.ESCAPE:
                GameKeys.setKeys(GameKeys.ESCAPE, true);
                break;
            case Keys.SPACE:
                GameKeys.setKeys(GameKeys.SPACE, true);
                break;
            case Keys.SHIFT_LEFT: case Keys.SHIFT_RIGHT:
                GameKeys.setKeys(GameKeys.SHIFT, true);
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode){
            case Keys.UP: case Keys.W:
                GameKeys.setKeys(GameKeys.UP, false);
                break;
            case Keys.DOWN: case Keys.S:
                GameKeys.setKeys(GameKeys.DOWN, false);
                break;
            case Keys.LEFT: case Keys.A:
                GameKeys.setKeys(GameKeys.LEFT, false);
                break;
            case Keys.RIGHT: case Keys.D:
                GameKeys.setKeys(GameKeys.RIGHT, false);
                break;
            case Keys.ENTER:
                GameKeys.setKeys(GameKeys.ENTER, false);
                break;
            case Keys.ESCAPE:
                GameKeys.setKeys(GameKeys.ESCAPE, false);
                break;
            case Keys.SPACE:
                GameKeys.setKeys(GameKeys.SPACE, false);
                break;
            case Keys.SHIFT_LEFT: case Keys.SHIFT_RIGHT:
                GameKeys.setKeys(GameKeys.SHIFT, false);
                break;
        }
        return true;
    }
}
