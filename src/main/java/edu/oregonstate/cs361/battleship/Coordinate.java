package edu.oregonstate.cs361.battleship;

/**
 * Created by michaelhilton on 1/8/17.
 */
public class Coordinate {
    private int Across;
    private int Down;

    public Coordinate(int letter, int number) {
        Across = letter;
        Down = number;
    }

    public int getDown() {
        return Down;
    }
    public int getAcross() {
        return Across;
    }

    @Override
    public int hashCode() {
    	return Across * 10000 + Down;
    }

    @Override
    public boolean equals(Object obj) {
    	if (obj instanceof Coordinate) {
    		if (obj.hashCode() == this.hashCode()) {
    			return true;
    		} else {
    			return false;
    		}
    	} else {
    		return false;
    	}
    }
    
    public boolean isLegal() {
    	if (Across > 10) return false;
    	if (Across < 1) return false;
    	if (Down > 10) return false;
    	if (Down < 1) return false;
    	return true;
    }
    
}
