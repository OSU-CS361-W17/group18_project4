package edu.oregonstate.cs361.battleship.ship;

import edu.oregonstate.cs361.battleship.Coordinate;

public class Submarine extends MilitaryShip {

	public Submarine(Coordinate s, Coordinate e) {
		super("submarine", 2, s, e);
	}

	@Override
    public boolean scan(Coordinate test) {
    	return false;
    }
}
