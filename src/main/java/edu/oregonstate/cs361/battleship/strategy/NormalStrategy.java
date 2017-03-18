package edu.oregonstate.cs361.battleship.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.oregonstate.cs361.battleship.Coordinate;

public class NormalStrategy implements Strategy {

	@Override
	public Coordinate shootAtPlayer(ArrayList<Coordinate> playerMisses, ArrayList<Coordinate> playerHits) {
        int max = 10;
        int min = 1;
        Random random = new Random();
        int randRow = random.nextInt(max - min + 1) + min;
        int randCol = random.nextInt(max - min + 1) + min;
        return new Coordinate(randRow,randCol);
	}

}
