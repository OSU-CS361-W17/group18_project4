package edu.oregonstate.cs361.battleship.ship;

import edu.oregonstate.cs361.battleship.Coordinate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Yingshi on 3/3/2017.
 */
class CivilianShipTest {

    @Test
    public void covers() {
        CivilianShip test_ship = new CivilianShip("clipper", 2, new Coordinate(0, 0), new Coordinate(0, 0));
        //horizontal
        Coordinate s = new Coordinate(7,3);
        Coordinate e = new Coordinate(7,5);
        test_ship.setLocation(s, e);

        List<Coordinate> result = new ArrayList<>();
        if (s.getAcross() == e.getAcross()) {
            for (int i = s.getDown(); i <= e.getDown(); i++) {
                result.add(new Coordinate(s.getAcross(), i));
            }
        }
        //vertical
        s = new Coordinate(2,2);
        e = new Coordinate(4,2);
        test_ship.setLocation(s, e);
    }
    @Test
    public void testisLegal() {
        CivilianShip test_ship = new CivilianShip("clipper", 2, new Coordinate(0, 0), new Coordinate(0, 0));
        Coordinate s = new Coordinate(7,3);
        Coordinate e = new Coordinate(7,5);
        test_ship.setLocation(s, e);
        assertEquals(true,s.isLegal());


    }



}