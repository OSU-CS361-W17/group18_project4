package edu.oregonstate.cs361.battleship;

import edu.oregonstate.cs361.battleship.ship.BaseShip;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Yingshi on 3/3/2017.
 */
class BattleshipModelTest {
    @Test
    void getShip() {
        BattleshipModel base = new BattleshipModel();
        String two = "aircraftcarrier";

        Coordinate s = new Coordinate(1,1);
        Coordinate e = new Coordinate(1,6);
        BaseShip test = base.getShip(two);
        test.setLocation(s,e);
        BaseShip aircraftCarrier = base.getShip(two);
        aircraftCarrier.setLocation(s,e);
        assertEquals(aircraftCarrier,test);


        String one = "battleship";
        Coordinate st = new Coordinate(2,1);
        Coordinate en = new Coordinate(2,5);
        BaseShip testOne = base.getShip(one);
        testOne.setLocation(st,en);
        BaseShip battleship = base.getShip(one);
        battleship.setLocation(st,en);
        assertEquals(battleship,testOne);


        String three = "dinghy";
        Coordinate S = new Coordinate(3,1);
        Coordinate E = new Coordinate(3,1);
        BaseShip testThree = base.getShip(three);
        testThree.setLocation(S,E);
        BaseShip dinghy  = base.getShip(three);
        dinghy.setLocation(st,en);
        assertEquals(dinghy,testThree);
        
        String four = "clipper";
        Coordinate ST = new Coordinate(4,1);
        Coordinate EN = new Coordinate(4,3);
        BaseShip testFour = base.getShip(four);
        testFour.setLocation(ST,EN);
        BaseShip clipper  = base.getShip(four);
        clipper.setLocation(ST,EN);
        assertEquals(clipper,testFour);

        String five = "submarine";
        Coordinate STA = new Coordinate(5,1);
        Coordinate End = new Coordinate(5,4);
        BaseShip Testfive = base.getShip(five);
        Testfive.setLocation(STA,End);
        BaseShip submarine  = base.getShip(five);
        submarine.setLocation(st,en);
        assertEquals(submarine,Testfive);

        BaseShip TEST = base.getShip("");
        assertEquals(null,TEST );
    }

    @Test
    void placeShip() {
        BattleshipModel base = new BattleshipModel();
        String two = "aircraftcarrier";
        base = base.placeShip(two,"1","1","horizontal",base);

        String one = "battleship";
        base = base.placeShip(one,"2","1","horizontal",base);

        String three = "dinghy";
        base = base.placeShip(three,"3","1","horizontal",base);

        String four = "clipper";
        base = base.placeShip(four,"4","1","horizontal",base);

        String five = "submarine";
        base = base.placeShip(five,"5","1","horizontal",base);

        base = base.placeShip(two,"1","1","vertical",base);
        base = base.placeShip(one,"2","1","vertical",base);
        base = base.placeShip(three,"3","1","vertical",base);
        base = base.placeShip(four,"4","1","vertical",base);
        base = base.placeShip(five,"5","1","vertical",base);

    }

    @Test
    void shootAtComputer() {
        BattleshipModel Test = new BattleshipModel();
        Test.shootAtComputer(3,4);
        Test.shootAtComputer(2,2);

    }

    @Test
    void scanAt() {
        BattleshipModel Test = new BattleshipModel();
        Test.scanAt(3,4);
        Test.scanAt(7,4);
        Test.scanAt(6,4);
        Test.scanAt(8,8);

    }

    @Test
    void shootAtPlayer() {
        BattleshipModel Test = new BattleshipModel();
        Test.shootAtPlayer();
        

    }

}