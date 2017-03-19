package edu.oregonstate.cs361.battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.gson.annotations.SerializedName;
import edu.oregonstate.cs361.battleship.ship.AircraftCarrier;
import edu.oregonstate.cs361.battleship.ship.BaseShip;
import edu.oregonstate.cs361.battleship.ship.Battleship;
import edu.oregonstate.cs361.battleship.ship.Clipper;
import edu.oregonstate.cs361.battleship.ship.Dinghy;
import edu.oregonstate.cs361.battleship.ship.Submarine;
import edu.oregonstate.cs361.battleship.strategy.CleverStrategy;
import edu.oregonstate.cs361.battleship.strategy.NormalStrategy;
import edu.oregonstate.cs361.battleship.strategy.Strategy;

/**
 * Created by michaelhilton on 1/4/17.
 */
public class BattleshipModel {

    private AircraftCarrier aircraftCarrier = new AircraftCarrier(new Coordinate(0, 0), new Coordinate(0, 0));
    private Battleship battleship = new Battleship(new Coordinate(0, 0), new Coordinate(0, 0));
    private Submarine submarine = new Submarine(new Coordinate(0, 0), new Coordinate(0, 0));
    private Clipper clipper = new Clipper(new Coordinate(0, 0), new Coordinate(0, 0));
    private Dinghy dinghy = new Dinghy(new Coordinate(0, 0), new Coordinate(0, 0));

    private AircraftCarrier computer_aircraftCarrier = new AircraftCarrier(new Coordinate(0, 0), new Coordinate(0, 0));
    private Battleship computer_battleship = new Battleship(new Coordinate(0, 0), new Coordinate(0, 0));
    private Submarine computer_submarine = new Submarine(new Coordinate(0, 0), new Coordinate(0, 0));
    private Clipper computer_clipper = new Clipper(new Coordinate(0, 0), new Coordinate(0, 0));
    private Dinghy computer_dinghy = new Dinghy(new Coordinate(0, 0), new Coordinate(0, 0));

    private ArrayList<Coordinate> playerHits;
    private ArrayList<Coordinate> playerMisses;
    private ArrayList<Coordinate> computerHits;
    private ArrayList<Coordinate> computerMisses;
    
    public Difficulty difficulty = Difficulty.EASY;

    public BattleshipModel() {
        playerHits = new ArrayList<>();
        playerMisses= new ArrayList<>();
        computerHits = new ArrayList<>();
        computerMisses= new ArrayList<>();

    }

    public BaseShip getShip(String shipName) {
        if (shipName.equalsIgnoreCase("aircraftcarrier")) {
            return aircraftCarrier;
        } if(shipName.equalsIgnoreCase("battleship")) {
            return battleship;
        } if(shipName.equalsIgnoreCase("dinghy")) {
            return dinghy;
        } if(shipName.equalsIgnoreCase("clipper")) {
            return clipper;
        }if(shipName.equalsIgnoreCase("submarine")) {
            return submarine;
        }else {
            return null;
        }
    }

    public BattleshipModel placeShip(String shipName, String row, String col, String orientation, BattleshipModel currModel) {
        int rowint = Integer.parseInt(row);
        int colInt = Integer.parseInt(col);
        if(orientation.equals("horizontal")){
            if (shipName.equalsIgnoreCase("aircraftcarrier")) {
                currModel.getShip(shipName).setLocation(new Coordinate(rowint,colInt),new Coordinate(rowint,colInt+5));
            } if(shipName.equalsIgnoreCase("battleship")) {
                currModel.getShip(shipName).setLocation(new Coordinate(rowint,colInt),new Coordinate(rowint,colInt+4));
            } if(shipName.equalsIgnoreCase("dinghy")) {
                currModel.getShip(shipName).setLocation(new Coordinate(rowint,colInt),new Coordinate(rowint,colInt));
            } if(shipName.equalsIgnoreCase("clipper")) {
                currModel.getShip(shipName).setLocation(new Coordinate(rowint,colInt),new Coordinate(rowint,colInt+2));
            }if(shipName.equalsIgnoreCase("submarine")) {
                currModel.getShip(shipName).setLocation(new Coordinate(rowint, colInt), new Coordinate(rowint, colInt + 2));
            }
        }else{
            //vertical
            if (shipName.equalsIgnoreCase("aircraftcarrier")) {
                currModel.getShip(shipName).setLocation(new Coordinate(rowint,colInt),new Coordinate(rowint+5,colInt));
            } if(shipName.equalsIgnoreCase("battleship")) {
                currModel.getShip(shipName).setLocation(new Coordinate(rowint,colInt),new Coordinate(rowint+4,colInt));
            } if(shipName.equalsIgnoreCase("dinghy")) {
                currModel.getShip(shipName).setLocation(new Coordinate(rowint,colInt),new Coordinate(rowint,colInt));
            } if(shipName.equalsIgnoreCase("clipper")) {
                currModel.getShip(shipName).setLocation(new Coordinate(rowint,colInt),new Coordinate(rowint+2,colInt));
            }if(shipName.equalsIgnoreCase("submarine")) {
                currModel.getShip(shipName).setLocation(new Coordinate(rowint, colInt), new Coordinate(rowint + 2, colInt));
            }
        }


        if ((difficulty == Difficulty.EASY) && (computer_aircraftCarrier.getStart().getAcross() == 0)) {
            System.out.println("easy mode");
            computer_aircraftCarrier = new AircraftCarrier(new Coordinate(2, 2), new Coordinate(2, 7));
            computer_battleship = new Battleship(new Coordinate(2, 8), new Coordinate(6, 8));
            computer_submarine = new Submarine(new Coordinate(4, 1), new Coordinate(4, 4));
            computer_clipper = new Clipper(new Coordinate(7, 3), new Coordinate(7, 5));
            computer_dinghy = new Dinghy(new Coordinate(9, 6), new Coordinate(9, 6));
        }
        else if((difficulty == Difficulty.HARD) && (computer_aircraftCarrier.getStart().getAcross() == 0)){
            System.out.println("hard mode");
            Random rand = new Random();
            int dir, across, down;
            dir = rand.nextInt(1);  //0 for horizontal, 1 for vertical
            Coordinate randPlace = randomPlace(6, dir);
            across = randPlace.getAcross();
            down = randPlace.getDown();
            if(dir == 0) {
                computer_aircraftCarrier = new AircraftCarrier(new Coordinate(across, down), new Coordinate(across+5, down));
            } else{
                computer_aircraftCarrier = new AircraftCarrier(new Coordinate(across, down), new Coordinate(across, down+5));
            }
            dir = rand.nextInt(1);  //0 for horizontal, 1 for vertical
            randPlace = randomPlace(5, dir);
            across = randPlace.getAcross();
            down = randPlace.getDown();
            if(dir == 0) {
                computer_battleship = new Battleship(new Coordinate(across, down), new Coordinate(across+4, down));
            } else{
                computer_battleship = new Battleship(new Coordinate(across, down), new Coordinate(across, down+4));
            }

            dir = rand.nextInt(1);  //0 for horizontal, 1 for vertical
            randPlace = randomPlace(3, dir);
            across = randPlace.getAcross();
            down = randPlace.getDown();
            if(dir == 0) {
                computer_submarine = new Submarine(new Coordinate(across, down), new Coordinate(across+2, down));
            } else{
                computer_submarine = new Submarine(new Coordinate(across, down), new Coordinate(across, down+2));
            }

            dir = rand.nextInt(1);  //0 for horizontal, 1 for vertical
            randPlace = randomPlace(3, dir);
            across = randPlace.getAcross();
            down = randPlace.getDown();
            if(dir == 0) {
                computer_clipper = new Clipper(new Coordinate(across, down), new Coordinate(across+2, down));
            } else{
                computer_clipper = new Clipper(new Coordinate(across, down), new Coordinate(across, down+2));
            }

            dir = rand.nextInt(1);  //0 for horizontal, 1 for vertical
            randPlace = randomPlace(1, dir);
            across = randPlace.getAcross();
            down = randPlace.getDown();
            if(dir == 0) {
                computer_dinghy = new Dinghy(new Coordinate(across, down), new Coordinate(across, down));
            } else{
                computer_dinghy = new Dinghy(new Coordinate(across, down), new Coordinate(across, down));
            }
        }
        /*
        System.out.println("computer_aircraftCarrier = " + computer_aircraftCarrier.getStart() + " " + computer_aircraftCarrier.getEnd());
        System.out.println("computer_battleship = " + computer_battleship.getStart() + " " + computer_battleship.getEnd());
        System.out.println("computer_submarine = " + computer_submarine.getStart() + " " + computer_submarine.getEnd());
        System.out.println("computer_clipper = " + computer_clipper.getStart() + " " + computer_clipper.getEnd());
        System.out.println("computer_dinghy = " + computer_dinghy.getStart() + " " + computer_dinghy.getEnd());
        */

        return currModel;
    }

    public Coordinate randomPlace(int length, int dir){
        Coordinate place;
        int row, col, pass = 0;
        Random rand = new Random();

        if(dir == 0){
            row = rand.nextInt(10-length+1) + 1;        //1 ~ length-1
            col = rand.nextInt(10) + 1;                 //1~10
        }
        else {
            row = rand.nextInt(10) + 1;
            col = rand.nextInt(10-length+1) + 1;
        }

        while(pass == 0){
            Coordinate coor;

            List<Coordinate> covers = new ArrayList<>();
            if(dir == 0) {          //horizontal
                for(int i = 0; i < length; i++) {
                    coor = new Coordinate(row + i,col);
                    covers.addAll(computer_aircraftCarrier.covers(coor));
                    covers.addAll(computer_battleship.covers(coor));
                    covers.addAll(computer_dinghy.covers(coor));
                    covers.addAll(computer_clipper.covers(coor));
                    covers.addAll(computer_submarine.covers(coor));
                }
            }
            else{
                for(int i = 0; i < length; i++) {
                    coor = new Coordinate(row,col + i);
                    covers.addAll(computer_aircraftCarrier.covers(coor));
                    covers.addAll(computer_battleship.covers(coor));
                    covers.addAll(computer_dinghy.covers(coor));
                    covers.addAll(computer_clipper.covers(coor));
                    covers.addAll(computer_submarine.covers(coor));
                }
            }
            if (covers.isEmpty()) {
                pass = 1;
            }
            else{                                                       //assign coordinate again
                if(dir == 0){
                    row = rand.nextInt(10-length+1) + 1;        //1 ~ length-1
                    col = rand.nextInt(10) + 1;                 //1~10
                }
                else {
                    row = rand.nextInt(10) + 1;
                    col = rand.nextInt(10-length+1) + 1;
                }
            }
        }
        place = new Coordinate(row,col);
        return place;
    }

    public void shootAtComputer(int row, int col) {

        Coordinate coor = new Coordinate(row,col);

        List<Coordinate> covers = new ArrayList<>(); 
        covers.addAll(computer_aircraftCarrier.covers(coor));
        covers.addAll(computer_battleship.covers(coor));
        covers.addAll(computer_dinghy.covers(coor));
        covers.addAll(computer_clipper.covers(coor));
        covers.addAll(computer_submarine.covers(coor));

        if (covers.isEmpty()) {
            computerMisses.add(coor);
        }
        else {
            computerHits.addAll(covers);
        }

        /*
        for (Coordinate number : computerHits) {
            System.out.println("computerHits = " + number);
        }
        for (Coordinate number : computerMisses) {
            System.out.println("computerMisses = " + number);
        }
        */

    }



    public void shootAtPlayer() {
    	
    	Strategy strategy;
    	if (difficulty == Difficulty.EASY) {
    		strategy = new NormalStrategy();
    	} else if (difficulty == Difficulty.HARD) {
    		strategy = new CleverStrategy();
    	} else {
    		throw new RuntimeException("difficulty should be easy or hard!");
    	}
    	
    	Coordinate coor = strategy.shootAtPlayer(playerMisses, playerHits);

        List<Coordinate> covers = new ArrayList<>(); 
        covers.addAll(aircraftCarrier.covers(coor));
        covers.addAll(battleship.covers(coor));
        covers.addAll(dinghy.covers(coor));
        covers.addAll(clipper.covers(coor));
        covers.addAll(submarine.covers(coor));
        if (covers.isEmpty()) {playerMisses.add(coor);}
        else {playerHits.addAll(covers);}
    }
}