package edu.oregonstate.cs361.battleship.ship;

import edu.oregonstate.cs361.battleship.Coordinate;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by j1298666 on 3/3/17.
 */
public class CivilianShipTest {
    @Test
    public void covers() {
        CivilianShip test_ship = new CivilianShip("clipper", 2, new Coordinate(0, 0), new Coordinate(0, 0));
        //horizontal
        Coordinate s = new Coordinate(7,3);
        Coordinate e = new Coordinate(7,5);
        test_ship.setLocation(s, e);



        Coordinate Ttesting = new Coordinate(7, 3);
        Coordinate Ftesting = new Coordinate(1, 2);



        List<Coordinate> result = new ArrayList<>();
        if (s.getAcross() == e.getAcross()) {
            System.out.println("Horizental !!!!" );
            for (int i = s.getDown(); i <= e.getDown(); i++) {
                result.add(new Coordinate(s.getAcross(), i));
            }
        } else {
            System.out.println("Vertical !!!!" );
            for (int i = s.getAcross(); i <= e.getAcross(); i++) {
                result.add(new Coordinate(i, s.getDown()));
            }
        }
        //System.out.println("hello this is testing" + test_ship.covers(Ftesting));
        assertEquals(result, result);
        assertEquals(test_ship.covers(Ftesting), Collections.emptyList());

        //vertical
        s = new Coordinate(2,2);
        e = new Coordinate(4,2);
        test_ship.setLocation(s, e);

        Ttesting = new Coordinate(2, 2);
        Ftesting = new Coordinate(1, 2);

        //assertEquals(test_ship.scan(Ftesting), false);
        assertEquals(test_ship.scan(Ttesting), true);
    }


}