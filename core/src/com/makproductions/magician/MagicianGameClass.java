package com.makproductions.magician;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MagicianGameClass extends ApplicationAdapter {

    private SpriteBatch batch;
    private Background background;
    private Hero hero;
    private Skeleton[] skeleton = new Skeleton[10];
    private CollisionDetector collisionDetector;

    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Background();
        hero = new Hero();
        for (int i = 0; i < skeleton.length ; i++) {
            skeleton[i] = new Skeleton();
        }
        collisionDetector = new CollisionDetector(hero,skeleton);
    }

    @Override
    public void render() {
        update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.render(batch);
        hero.render(batch);
        for (int i = 0; i < skeleton.length; i++) {
          if(skeleton[i].isAlive())  skeleton[i].render(batch);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        hero.dispose();
        for (int i = 0; i < skeleton.length; i++) {
            skeleton[i].dispose();
        }
    }

    public void update() {
        background.update();
        hero.update();
        for (int i = 0; i < skeleton.length; i++) {
            if(skeleton[i].isAlive()) skeleton[i].update();
        }
        attack();
        collisionDetector.checkForCollision();

    }

    public void attack(){
        for (int i = 0; i < skeleton.length; i++) {
            if(skeleton[i].isAlive())  skeleton[i].attack(hero);
        }
    }
}
