package obstacles;

import java.util.List;

public class ObstacleFactory {

    public static Obstacle createObstacle(){

        int random = (int)(Math.random() * 3);
        switch (random) {
            case 0:
                return new Wall();
            case 1:
                return new Barrel();
            case 2:
                return new Monster();
        }
        return null;
    };

    public enum ObstacleType{
        WALL,
        BARREL,
        MONSTER,
    }
}
