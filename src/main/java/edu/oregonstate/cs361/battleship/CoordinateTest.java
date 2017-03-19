package edu.oregonstate.cs361.battleship;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class CoordinateTest{
    @Test
    public void TestCo(){
        Coordinate one = new Coordinate(3,4);
        int down = one.getDown();
        int across = one.getAcross();
        assertEquals(across, 3);
        assertEquals(down,4);
    }



    @Test
    public void equals() {
    }

    @Test
    public void isLegal() {
        Coordinate one = new Coordinate(3,4);
        assertEquals(one.isLegal(), true);
        one = new Coordinate(11,2);
        assertEquals(one.isLegal(),false);
    }
}