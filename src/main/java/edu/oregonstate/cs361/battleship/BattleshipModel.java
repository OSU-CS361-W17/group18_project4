package edu.oregonstate.cs361.battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.oregonstate.cs361.battleship.ship.AircraftCarrier;
import edu.oregonstate.cs361.battleship.ship.BaseShip;
import edu.oregonstate.cs361.battleship.ship.Battleship;
import edu.oregonstate.cs361.battleship.ship.Clipper;
import edu.oregonstate.cs361.battleship.ship.Dinghy;
import edu.oregonstate.cs361.battleship.ship.Submarine;


public class BattleshipModel {

    private AircraftCarrier aircraftCarrier = new AircraftCarrier(new Coordinate(0, 0), new Coordinate(0, 0));
    private Battleship battleship = new Battleship(new Coordinate(0, 0), new Coordinate(0, 0));
    private Submarine submarine = new Submarine(new Coordinate(0, 0), new Coordinate(0, 0));
    private Clipper clipper = new Clipper(new Coordinate(0, 0), new Coordinate(0, 0));
    private Dinghy dinghy = new Dinghy(new Coordinate(0, 0), new Coordinate(0, 0));

    private AircraftCarrier computer_aircraftCarrier = new AircraftCarrier(new Coordinate(2, 2), new Coordinate(2, 7));
    private Battleship computer_battleship = new Battleship(new Coordinate(2, 8), new Coordinate(6, 8));
    private Submarine computer_submarine = new Submarine(new Coordinate(4, 1), new Coordinate(4, 4));
    private Clipper computer_clipper = new Clipper(new Coordinate(7, 3), new Coordinate(7, 5));
    private Dinghy computer_dinghy = new Dinghy(new Coordinate(9, 6), new Coordinate(9, 6));

    private ArrayList<Coordinate> playerHits;
    private ArrayList<Coordinate> playerMisses;
    private ArrayList<Coordinate> computerHits;
    private ArrayList<Coordinate> computerMisses;

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
        } else {
            return null;
        }
    }

    public BattleshipModel placeShip(String shipName, String row, String col, String orientation, BattleshipModel currModel) {
        int rowint = Integer.parseInt(row);
        int colInt = Integer.parseInt(col);
        System.out.println(rowint);
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
        return currModel;
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
    }


    public void scanAt(int row, int col) {
        Coordinate coor = new Coordinate(row,col);
        List<Coordinate> covers = new ArrayList<>();
        System.out.println(row);
        System.out.println(col);

        if(computer_aircraftCarrier.scan(coor) || computer_clipper.scan(coor) || computer_dinghy.scan(coor) || computer_battleship.scan(coor) || computer_submarine.scan(coor)){
            computerHits.add(coor);}
        else{computerMisses.add(coor);}

        coor = new Coordinate(row+1,col);
        if(computer_aircraftCarrier.scan(coor) || computer_clipper.scan(coor) || computer_dinghy.scan(coor) || computer_battleship.scan(coor) || computer_submarine.scan(coor)){
            computerHits.add(coor);}
        else{computerMisses.add(coor);}

        coor = new Coordinate(row-1,col);
        if(computer_aircraftCarrier.scan(coor) || computer_clipper.scan(coor) || computer_dinghy.scan(coor) || computer_battleship.scan(coor) || computer_submarine.scan(coor)){
            computerHits.add(coor);}
        else{computerMisses.add(coor);}

        coor = new Coordinate(row,col+1);
        if(computer_aircraftCarrier.scan(coor) || computer_clipper.scan(coor) || computer_dinghy.scan(coor) || computer_battleship.scan(coor) || computer_submarine.scan(coor)){
            computerHits.add(coor);}
        else{computerMisses.add(coor);}

        coor = new Coordinate(row,col-1);
        if(computer_aircraftCarrier.scan(coor) || computer_clipper.scan(coor) || computer_dinghy.scan(coor) || computer_battleship.scan(coor) || computer_submarine.scan(coor)){
            computerHits.add(coor);}
        else{computerMisses.add(coor);}





    }

    public void shootAtPlayer() {
        int max = 10;
        int min = 1;
        Random random = new Random();
        int randRow = random.nextInt(max - min + 1) + min;
        int randCol = random.nextInt(max - min + 1) + min;
        Coordinate coor = new Coordinate(randRow,randCol);
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