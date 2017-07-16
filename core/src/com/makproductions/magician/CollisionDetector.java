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
                    if (units[i].getCircle().contains(fireballs.get(j).getCircle())) {
                        units[i].setHealth(50);
                        System.out.println("Hit!");
                        fireballs.get(j).setShot(false);
                    }
                }
                if(hero.isAlive()) {
                    float unitX = units[i].getPosition().x;
                    float unitY = units[i].getPosition().y;
                    float heroX = hero.getPosition().x;
                    float heroY = hero.getPosition().y;

                    float distanceX = heroX - unitX;
                    float distanceY = heroY - unitY;

                    float boundaryForUnitCoordinateX = distanceX > 0 ? -100 : 100;
                    float boundaryForUnitCoordinateY = distanceY > 0 ? -150 : 150;

                    float boundaryForHeroCoordinateX = distanceX < 0 ? -100 : 100;
                    float boundaryForHeroCoordinateY = distanceY < 0 ? -150 : 150;

                    if (distanceX < 100 && distanceX > -100
                            && distanceY < 150 && distanceY > -150) {
                       // hero.setHealth(10);
                        units[i].setPosition(new Vector2(unitX + boundaryForUnitCoordinateX,unitY + boundaryForUnitCoordinateY));
                        hero.setPosition(new Vector2(heroX + boundaryForHeroCoordinateX,heroY + boundaryForHeroCoordinateY));
                    }
                }
            }
        }
    }
}
