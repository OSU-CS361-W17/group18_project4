package edu.oregonstate.cs361.battleship.ship;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.oregonstate.cs361.battleship.Coordinate;

public class CivilianShip extends BaseShip {

	public CivilianShip(String n, int l, Coordinate s, Coordinate e) {
		super(n, l, s, e);
	}
	
	@Override
	public List<Coordinate> covers(Coordinate test) {
		System.out.println("CivilianShip covers");
		if (super.covers(test).isEmpty()) {
			return Collections.emptyList();
		} 

		List<Coordinate> result = new ArrayList<>();
		if (getStart().getAcross() == getEnd().getAcross()) {
			for (int i = getStart().getDown(); i <= getEnd().getDown(); i++) {
				result.add(new Coordinate(getStart().getAcross(), i));
			}
		} else {
			for (int i = getStart().getAcross(); i <= getEnd().getAcross(); i++) {
				result.add(new Coordinate(i, getStart().getDown()));
			}
		}
		
		System.out.println(result);
		return result;
	}

}
