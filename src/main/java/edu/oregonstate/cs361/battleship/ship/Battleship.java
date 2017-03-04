package edu.oregonstate.cs361.battleship.ship;

import edu.oregonstate.cs361.battleship.Coordinate;

public class Battleship extends MilitaryShip {

    public Battleship(Coordinate s, Coordinate e) {
		super("battleship",4, s, e);
	}
}
