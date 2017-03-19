package edu.oregonstate.cs361.battleship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Yingshi on 3/3/2017.
 */

public class CoordinateTest{
    @Test
    public void TestCo(){
        Coordinate one = new Coordinate(3,4);
        int down = one.getDown();
        int across = one.getAcross();
        assertEquals(across, 3);
        assertEquals(down,4);
    }
}