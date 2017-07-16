package com.makproductions.magician;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Fireball extends GameObject {

    private boolean isShot;

    public boolean isShot() {
        return isShot;
    }

    public Fireball(float x, float y){
        texture = new Texture("Fireball.png");
        position = new Vector2(x,y);
    }

    public void fire(){
        isShot = true;
    }

    @Override
    public void render(SpriteBatch batch)
    {
        if(isShot) {
            batch.draw(texture, position.x, position.y);
        }
    }

    @Override
    public void update() {
        position.x += 5;
        if(isShot&&position.x>1100||position.x<0||position.y>720||position.y<0){
            isShot = false;
        }
    }



}
