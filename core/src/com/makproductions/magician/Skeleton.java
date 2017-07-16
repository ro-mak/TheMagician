package com.makproductions.magician;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Skeleton extends GameObject implements Unit {

    private static final int FRAME_COLS = 2, FRAME_ROWS = 1;

    private Texture texture;
    private int health = 100;
    Animation<TextureRegion> walkAnimation;
    TextureRegion[][] tmp;
    TextureRegion[] walkFrames;

    public boolean isAlive() {
        if(health <= 0){
            return false;
        }
        return alive;
    }

    @Override
    public void setHealth(int health) {
        this.health -= health;
        System.out.println(this + " got hit:" + health);
        System.out.println(this + "Remaining health: "+this.health);
    }

    @Override
    public Circle getCircle() {
        return circle;
    }

    private TextureRegion currentFrame;
    private float stateTime;
    private Vector2 position;
    private Circle circle;
    private boolean alive = false;
    private boolean attacking = false;
    private float speed;

    public boolean isAttacking() {
        return attacking;
    }

    public Skeleton(){
        this.speed = 1;
        this.alive = true;
        this.position = new Vector2(1000,(float) (Math.random()*300));
        this.texture = new Texture("SkeletonSpriteSheet.png");
        this.circle = new Circle(position,100);

    }

    public void render(SpriteBatch batch){
        if(isAlive()) {

            tmp = TextureRegion.split(texture,
                    texture.getWidth() / FRAME_COLS,
                    texture.getHeight() / FRAME_ROWS);

            walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
            int index = 0;
            for (int i = 0; i < FRAME_ROWS; i++) {
                for (int j = 0; j < FRAME_COLS; j++) {
                    walkFrames[index++] = tmp[i][j];
                }
            }
            walkAnimation = new Animation<TextureRegion>(0.5f, walkFrames);

            stateTime += Gdx.graphics.getDeltaTime();

                currentFrame = walkAnimation.getKeyFrame(stateTime, true);

            batch.draw(currentFrame, position.x, position.y);
        }
    }



    public void attack(Unit unit) {
            Vector2 unitPosition = unit.getPosition();
            Circle attackCircle = new Circle(position, 400);

            if (unit.isAlive() && attackCircle.contains(unitPosition)) {
                attacking = true;
                if (unit.getPosition().x > position.x && position.x < 1280 && position.x > 0) {
                    position.x += speed;
                } else if (unit.getPosition().x < position.x && position.x > 0 && position.x < 1280) {
                    position.x -= speed;
                }

                if (unit.getPosition().y > position.y && position.y > 0 && position.y < 768) {
                    position.y += speed;
                } else if (unit.getPosition().y < position.y && position.y > 0 && position.y < 768) {
                    position.y -= speed;
                }
            }else{
                attacking = false;
            }

    }

    @Override
    public Vector2 getPosition() {
        return position;
    }


    public void move(){
        if(!attacking)position.x -= speed;
    }

    public void update(){
        if(isAlive()) {
            move();
            circle.setPosition(position);
        }
    }
    public void dispose(){
        texture.dispose();
    }
}
