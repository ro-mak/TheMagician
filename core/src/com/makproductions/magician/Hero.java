package com.makproductions.magician;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import java.util.Vector;

public class Hero implements Unit {

    private FireballPool fireballPool;
    private Texture texture;
    private Vector2 position;
    private Circle circle;
    private float speed;
    private final float INITIAL_POSITION_X = 300;
    private final float INITIAL_POSITION_Y = 300;
    private final int COLLISION_RADIUS = 350;
    private final int INITIAL_SPEED = 2;

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public Circle getCircle() {
        return circle;
    }

    public Hero() {

        this.position = new Vector2(INITIAL_POSITION_X, INITIAL_POSITION_Y);
        this.texture = new Texture("HeroOnly.png");
        this.circle = new Circle(position, COLLISION_RADIUS);
        this.speed = INITIAL_SPEED;
        this.fireballPool = new FireballPool();

    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
        fireballPool.renderFireballs(batch);
    }

    private boolean hasFired = false;
    private long fireTime;

    public void fire() {
        if (!hasFired) {
            Fireball fireball = fireballPool.getFireball();
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                fireball.fire();
            }
            fireTime = System.currentTimeMillis();
            hasFired = true;
        }
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

    private final long CASTING_TIME = 700;

    public void update() {
        move();
        fire();
        fireballPool.updateFireballs();
        if (System.currentTimeMillis() - fireTime > CASTING_TIME) {
            hasFired = false;
        }
    }

    public void dispose() {
        texture.dispose();
    }

    private class FireballPool {
        private Vector<Fireball> freeFireballs = new Vector<Fireball>();
        private Vector<Fireball> firedFireballs = new Vector<Fireball>();

        public FireballPool() {
            initFireballPool();
        }

        public Fireball getFireball() {
            Fireball fireball = null;
            if (freeFireballs.size() <= 0) {
                initFireballPool();
            }
            updatePositions();
            for (int i = 0; i < freeFireballs.size(); i++) {
                if (!freeFireballs.get(i).isShot()) {
                    fireball = freeFireballs.get(i);
                    freeFireballs.remove(i);
                    firedFireballs.add(fireball);
                    break;
                }
            }
            if (fireball == null) {
                throw new UninitialisedPoolException();
            }
            return fireball;
        }

        public void renderFireballs(SpriteBatch batch) {
            for (int i = 0; i < firedFireballs.size(); i++) {
                if (firedFireballs.get(i).isShot()) {
                    firedFireballs.get(i).render(batch);
                }
            }
        }

        private void updatePositions() {
            Vector2 vector2 = new Vector2(position.x + 20, position.y + 110);
            for (int i = 0; i < freeFireballs.size(); i++) {
                freeFireballs.get(i).setPosition(vector2);
            }
        }

        public void updateFireballs() {
            for (int i = 0; i < firedFireballs.size(); i++) {
                if (firedFireballs.get(i).isShot()) {
                    firedFireballs.get(i).update();
                } else if (!firedFireballs.get(i).isShot()) {
                    freeFireballs.add(firedFireballs.get(i));
                    firedFireballs.remove(i);
                }
            }
        }


        private void initFireballPool() {
            for (int i = 0; i < 10; i++) {
                freeFireballs.add(new Fireball(0, 0));
            }
        }

        private class UninitialisedPoolException extends RuntimeException {

        }
    }
}
