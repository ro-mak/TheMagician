package com.makproductions.magician;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public interface Unit {
    Vector2 getPosition();
    void setPosition(Vector2 position);
    Circle getCircle();
    void setHealth(int health);
    boolean isAlive();
}
