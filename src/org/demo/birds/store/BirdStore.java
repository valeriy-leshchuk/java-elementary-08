package org.demo.birds.store;

import org.demo.birds.entities.Bird;

import java.util.*;

/**
 * Отнаследоваться от AbstractBirdStore.
 * Реализовать паттерн Singleton.
 */
public class BirdStore extends AbstractBirdStore
{
    private static final BirdStore birdStore_ = null;
    private static final Map<String, Bird> mapNameToBird = new HashMap<>();
    private static final Map<String, List<Bird>> mapLivingAreaToBirds = new HashMap<>();

    public static BirdStore getInstance()
    {
        return birdStore_ == null ? new BirdStore() : birdStore_;
    }

    @Override
    public void addBird(Bird b)
    {
        String name = b.getName();
        if (mapNameToBird.containsKey(name))
        {
            System.out.println("Bird already exist in the store\n");
            return;
        }
        mapNameToBird.put(name, b);

        String livingArea = b.getLivingArea();
        List<Bird> listOfBirdsFromArea;
        if (mapLivingAreaToBirds.containsKey(livingArea))
        {
            listOfBirdsFromArea = mapLivingAreaToBirds.get(livingArea);
            listOfBirdsFromArea.add(b);
        }
        else
        {
            listOfBirdsFromArea = new ArrayList<>();
            listOfBirdsFromArea.add(b);
            mapLivingAreaToBirds.put(livingArea, listOfBirdsFromArea);
        }
    }

    @Override
    public Bird searchByName(String nameToSearch)
    {
        return mapNameToBird.get(nameToSearch);
    }

    @Override
    public List<Bird> searchByLivingArea(String livingAreaToFind)
    {
        return mapLivingAreaToBirds.get(livingAreaToFind);
    }
}
