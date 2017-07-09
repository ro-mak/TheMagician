package com.makproductions.magician;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MagicianGameClass extends ApplicationAdapter {

    private SpriteBatch batch;
    private Background background;
    private Hero hero;
    private Skeleton skeleton;

    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Background();
        hero = new Hero();
        skeleton = new Skeleton();
    }

    @Override
    public void render() {
        update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.render(batch);
        hero.render(batch);
        skeleton.render(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        hero.dispose();
        skeleton.dispose();
    }

    public void update() {
        background.update();
        hero.update();
        skeleton.update();
        fire();
    }

    public void fire(){
        skeleton.fire(hero);
    }
}
