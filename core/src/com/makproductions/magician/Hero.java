package com.makproductions.magician;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import org.omg.CORBA.PRIVATE_MEMBER;

public class Hero implements MakRenderable, MakFireable, MakMovable, MakUpdatable, Unit {

    private Texture texture;
    private Vector2 position;
    private Circle circle;
    private float speed;
    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public Circle getCircle() {
        return circle;
    }

    public Hero() {
        this.position = new Vector2(300, 300);
        this.texture = new Texture("HeroOnly.png");
        this.circle = new Circle(position,350);
        this.speed = 2;

    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public void fire(Unit unit) {

    }

    public void move() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= speed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += speed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.y -= speed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            position.y += speed;
        }
    }

    public void update() {
            move();
    }
    public void dispose(){
        texture.dispose();
    }
}
