package com.makproductions.magician;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Skeleton implements Unit {

    private static final int FRAME_COLS = 2, FRAME_ROWS = 1;

    private Texture texture;
    Animation<TextureRegion> walkAnimation;
    TextureRegion[][] tmp;
    TextureRegion[] walkFrames;

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
    public boolean isAlive() {
        return alive;
    }
    public boolean isAttacking() {
        return attacking;
    }

    public Skeleton(){
        this.speed = 1;
        this.alive = true;
        this.position = new Vector2(1000,300);
        this.texture = new Texture("SkeletonSpriteSheet.png");
        this.circle = new Circle(position,350);
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
        stateTime = 0f;
    }

    public void render(SpriteBatch batch){
        if(isAlive()) {
            stateTime += Gdx.graphics.getDeltaTime();
            currentFrame = walkAnimation.getKeyFrame(stateTime, true);
            batch.draw(currentFrame, position.x, position.y);
        }
    }

    public void fire(Unit unit){
        Vector2 unitPosition = unit.getPosition();
        Circle attackCircle = new Circle(position,400);
        if(this.circle.contains(unit.getPosition())){
            attacking = false;
            alive = false;

        }else if(attackCircle.contains(unitPosition)){
            attacking = true;
            if (unit.getPosition().x > position.x && position.x < 1280 && position.x > 0) {
                position.x+=speed;
            } else if (unit.getPosition().x < position.x && position.x > 0 && position.x < 1280) {
                position.x-=speed;
            }

            if (unit.getPosition().y  > position.y && position.y > 0 && position.y < 768) {
                position.y+=speed;
            } else if (unit.getPosition().y < position.y && position.y > 0 && position.y < 768) {
                position.y-=speed;
            }
        }
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }


    public void move(){
        if(!isAttacking())position.x -= speed;
    }

    public void update(){
        move();
    }
    public void dispose(){
        texture.dispose();
    }
}
