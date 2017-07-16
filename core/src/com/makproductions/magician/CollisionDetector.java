package com.makproductions.magician;


import com.badlogic.gdx.math.Vector2;

import java.util.Vector;

public class CollisionDetector {

    private Unit[] units;
    private Hero hero;

    public CollisionDetector(Hero hero, Unit[] units){
        this.hero = hero;
        this.units = units;
    }

    public void checkForCollision(){
        Vector<Fireball> fireballs = Hero.fireballPool.getFiredFireballs();

        for (int i = 0; i < units.length; i++) {
            if (units[i].isAlive()) {
                for (int j = 0; j < fireballs.size(); j++) {
//                    if (units[i].getCircle().contains(fireballs.get(j).getCircle())) {
//                        units[i].setHealth(50);
//                        System.out.println("Hit!");
//                        fireballs.get(j).setShot(false);
//                    }
                    checkHit(units[i],fireballs.get(j));
                }
                if(hero.isAlive()) {
                    checkHit(hero,units[i]);
                }
            }
        }
    }

    private void checkHit(Unit unit, GameObject gameObject){
        float unitX = unit.getPosition().x;
        float unitY = unit.getPosition().y;
        float gameObjectX = gameObject.getPosition().x;
        float gameObjectY = gameObject.getPosition().y;

        float distanceX = gameObjectX - unitX;
        float distanceY = gameObjectY - unitY;

        float boundaryForUnitCoordinateX = distanceX > 0 ? -20 : 20;
        float boundaryForUnitCoordinateY = distanceY > 0 ? -20 : 20;

        float boundaryForGameObjectCoordinateX = distanceX < 0 ? -20 : 20;
        float boundaryForGameObjectCoordinateY = distanceY < 0 ? -20 : 20;

        if (distanceX < 100 && distanceX > -100
                && distanceY < 250 && distanceY > -50) {
            unit.setHealth(10);
            unit.setPosition(new Vector2(unitX + boundaryForUnitCoordinateX,unitY + boundaryForUnitCoordinateY));

            gameObject.setPosition(new Vector2(gameObjectX + boundaryForGameObjectCoordinateX,gameObjectY + boundaryForGameObjectCoordinateY));
        }
    }
}
