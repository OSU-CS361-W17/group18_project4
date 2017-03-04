package edu.oregonstate.cs361.battleship.ship;

import edu.oregonstate.cs361.battleship.Coordinate;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Yingshi on 3/3/2017.
 */
class BaseShipTest {
    @Test
    void setLocation() {
        BaseShip testship = new BaseShip("submarine", 2, new Coordinate(0, 0), new Coordinate(0, 0));
        Coordinate s = new Coordinate(2,2);
        Coordinate e = new Coordinate(2,5);
        testship.setLocation(s, e);
        assertEquals(testship.getStart(),s );
        assertEquals(testship.getEnd(), e);
    }

    @Test
    void getStart() {
        BaseShip testship = new BaseShip("submarine", 2, new Coordinate(0, 0), new Coordinate(0, 0));
        Coordinate s = new Coordinate(2,2);
        Coordinate e = new Coordinate(2,5);
        testship.setLocation(s, e);
        assertEquals(testship.getStart(),s );

    }

    @Test
    void getEnd() {
        BaseShip testship = new BaseShip("submarine", 2, new Coordinate(0, 0), new Coordinate(0, 0));
        Coordinate s = new Coordinate(2,2);
        Coordinate e = new Coordinate(2,5);
        testship.setLocation(s, e);
        assertEquals(testship.getStart(),s );

    }

    @Test
    void covers() {
        BaseShip test_ship = new BaseShip("submarine", 2, new Coordinate(0, 0), new Coordinate(0, 0));
        //horizontal
        Coordinate s = new Coordinate(2,2);
        Coordinate e = new Coordinate(2,5);
        test_ship.setLocation(s, e);

        Coordinate Ttesting = new Coordinate(2, 2);
        Coordinate Ftesting = new Coordinate(1, 2);


        System.out.println("hello this is testing" + test_ship.covers(Ftesting));
        assertEquals(test_ship.covers(Ttesting), Collections.singletonList(Ttesting));
        assertEquals(test_ship.covers(Ftesting), Collections.emptyList());

        //vertical
        s = new Coordinate(2,2);
        e = new Coordinate(5,2);
        test_ship.setLocation(s, e);

        Ttesting = new Coordinate(2, 2);
        Ftesting = new Coordinate(1, 2);
        assertEquals(test_ship.covers(Ttesting), Collections.singletonList(Ttesting));
        assertEquals(test_ship.covers(Ftesting), Collections.emptyList());

    }

    @Test
    void scan() {
        BaseShip test_ship = new BaseShip("submarine", 2, new Coordinate(0, 0), new Coordinate(0, 0));
        //horizontal
        Coordinate s = new Coordinate(2,2);
        Coordinate e = new Coordinate(2,5);
        test_ship.setLocation(s, e);

        Coordinate Ttesting = new Coordinate(2, 2);
        Coordinate Ftesting = new Coordinate(1, 2);

        assertEquals(test_ship.scan(Ftesting), false);
        assertEquals(test_ship.scan(Ttesting), true);

        //vertical
        s = new Coordinate(2,2);
        e = new Coordinate(5,2);
        test_ship.setLocation(s, e);

        Ttesting = new Coordinate(2, 2);
        Ftesting = new Coordinate(1, 2);

        assertEquals(test_ship.scan(Ftesting), false);
        assertEquals(test_ship.scan(Ttesting), true);
    }

}