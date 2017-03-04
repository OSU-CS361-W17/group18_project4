package edu.oregonstate.cs361.battleship.ship;

import java.util.Collections;
import java.util.List;

import edu.oregonstate.cs361.battleship.Coordinate;

/**
 * Created by michaelhilton on 1/5/17.
 */
public class BaseShip {
    private String name;
    private int length;
    private Coordinate start;
    private Coordinate end;

    public BaseShip(String n, int l,Coordinate s, Coordinate e) {
        name = n;
        length = l;
        start = s;
        end = e;
    }
    
    public void setLocation(Coordinate s, Coordinate e) {
        start = s;
        end = e;
    }
    


    public Coordinate getStart() {
		return start;
	}

   
    
    public Coordinate getEnd() {
		return end;
	}

    public List<Coordinate> covers(Coordinate test) {
    	 if(start.getAcross() == end.getAcross()){
            if(test.getAcross() == start.getAcross()){
                if((test.getDown() >= start.getDown()) && (test.getDown() <= end.getDown()))
                    return Collections.singletonList(test);
                }
                else {
                    return Collections.emptyList();
            }
        }
        //vertical
        else{
            if(test.getDown() == start.getDown()){
                if((test.getAcross() >= start.getAcross()) && (test.getAcross() <= end.getAcross()))
                    return Collections.singletonList(test);
                }
                else {
                    return Collections.emptyList();
                }

        }
        return Collections.emptyList();
    }

    public boolean scan(Coordinate test) {
    	//System.out.println("BaseShip scan");
        //horizontal
        if(start.getAcross() == end.getAcross()){
            if(test.getAcross() == start.getAcross()){
                if((test.getDown() >= start.getDown()) &&
                (test.getDown() <= end.getDown()))
                return true;
            } else {
                return false;
            }
        }
        //vertical
        else{
            if(test.getDown() == start.getDown()){
                if((test.getAcross() >= start.getAcross()) &&
                        (test.getAcross() <= end.getAcross()))
                    return true;
            } else {
                return false;
            }

        }
        return false;
    }
}
