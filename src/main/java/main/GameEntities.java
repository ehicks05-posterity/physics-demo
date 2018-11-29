package main;

import java.util.ArrayList;
import java.util.List;

public class GameEntities
{
    static List<Ball> gameEntities = new ArrayList<>();

    public static void addEntity(Ball object)
    {
        gameEntities.add(object);
    }
}
