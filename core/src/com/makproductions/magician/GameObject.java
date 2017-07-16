package com.makproductions.magician;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {

    Vector2 position;
    Texture texture;
    Circle circle;
    public abstract void render(SpriteBatch batch);
    public abstract void update();
    public Circle getCircle(){
        return circle;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
