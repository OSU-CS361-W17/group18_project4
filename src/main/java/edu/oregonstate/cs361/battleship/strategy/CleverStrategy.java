package edu.oregonstate.cs361.battleship.strategy;

import java.util.ArrayList;

import edu.oregonstate.cs361.battleship.Coordinate;

public class CleverStrategy implements Strategy {

	/**
	 * We don't know if we hit last time.
	 * 
	 * [x] simple strategy: choose from hit coordinate
	 * 
	 * [] more clever: choose from hit coordinate and if we have two or more hits coordinate connected, 
	 * choose that direction
	 */
	@Override
	public Coordinate shootAtPlayer(ArrayList<Coordinate> playerMisses, ArrayList<Coordinate> playerHits) {
		
		for (int i = 0; i < playerHits.size(); i++) {
			Coordinate coor = playerHits.get(i);
			
			// four directions
			Coordinate c = new Coordinate(coor.getAcross() - 1, coor.getDown());
			if (c.isLegal() && !playerMisses.contains(c) && !playerHits.contains(c)) return c;
			
			c = new Coordinate(coor.getAcross() + 1, coor.getDown());
			if (c.isLegal() && !playerMisses.contains(c) && !playerHits.contains(c)) return c;

			c = new Coordinate(coor.getAcross(), coor.getDown() - 1);
			if (c.isLegal() && !playerMisses.contains(c) && !playerHits.contains(c)) return c;

			c = new Coordinate(coor.getAcross(), coor.getDown() + 1);
			if (c.isLegal() && !playerMisses.contains(c) && !playerHits.contains(c)) return c;
			
		}
		
		// degenerate to normal Strategy
		return new NormalStrategy().shootAtPlayer(playerMisses, playerHits);
		
	}

}
