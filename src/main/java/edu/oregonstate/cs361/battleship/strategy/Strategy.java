package edu.oregonstate.cs361.battleship.strategy;

import java.util.ArrayList;

import edu.oregonstate.cs361.battleship.Coordinate;

public interface Strategy {
    public Coordinate shootAtPlayer(ArrayList<Coordinate> playerMisses, ArrayList<Coordinate> playerHits);
}
