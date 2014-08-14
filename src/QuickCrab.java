

import info.gridworld.grid.Location;


import java.util.ArrayList;

public class QuickCrab extends CrabCritter {

    public ArrayList<Location> getMoveLocations() {
        //locs holds the move locations that are to be returned.
        ArrayList<Location> locs = new ArrayList<Location>();
        Location loc = getLocation();
        /*
         *if there are one empty location in left, search whether the second location in left is 
         *also empty. If yes, put the two location in locs and search the right direction in the same way
         *else seach the right direction. If both direction has no two adjacent locations that are
         *both empty. return the moveLoactions as the class CrabCritter do.
         */
        Location leftOne = loc.getAdjacentLocation(getDirection() + Location.LEFT);
        if (getGrid().isValid(leftOne) && getGrid().get(leftOne) == null) {
            Location leftTwo = leftOne.getAdjacentLocation(getDirection() + Location.LEFT);
            //leftwo location is empty, too. so add them to locs
            if (getGrid().isValid(leftTwo) && getGrid().get(leftTwo) == null) {
                locs.add(leftOne);
                locs.add(leftTwo);
                //search whether the right directions have the same case, too.
                Location rightOne = loc.getAdjacentLocation(getDirection() + Location.RIGHT);
                if (getGrid().isValid(rightOne) && getGrid().get(rightOne) == null) {
                    Location rightTwo = rightOne.getAdjacentLocation(getDirection() + Location.RIGHT);
                    //if right has the same case, add the two locations to locs and return
                    //else return locs directly
                    if (getGrid().isValid(rightTwo) && getGrid().get(rightTwo) == null) {
                        locs.add(rightOne);
                        locs.add(rightTwo);
                        return locs;
                    } else {
                        return locs;
                    }
                } else {
                    return locs;
                }
            }
            /*
             *if the lefttwo is not empty or not valid, search the right location directly
             */ 
            else {
                Location rightOne = loc.getAdjacentLocation(getDirection() + Location.RIGHT);
                if (getGrid().isValid(rightOne) && getGrid().get(rightOne) == null) {
                    Location rightTwo = rightOne.getAdjacentLocation(getDirection() + Location.RIGHT);
                    if (getGrid().isValid(rightTwo) && getGrid().get(rightTwo) == null) {
                        locs.add(rightOne);
                        locs.add(rightTwo);
                        return locs;
                    } else {
                       //if the right two locations is not empty or not valid, return moving location as the base class
                       return super.getMoveLocations();
                    }
                } else {
                    //if the right one location is not empty or not valid, return moving locations of base class directly
                    return super.getMoveLocations();
                }
            }
        }
        /*
         * if the left on location is not empty or not valid
         * search the right direction directly
         */ 
        else {
            Location rightOne = loc.getAdjacentLocation(getDirection() + Location.RIGHT);
            if (getGrid().isValid(rightOne) && getGrid().get(rightOne) == null) {
                Location rightTwo = rightOne.getAdjacentLocation(getDirection() + Location.RIGHT);
                if (getGrid().isValid(rightTwo) && getGrid().get(rightTwo) == null) {
                    locs.add(rightOne);
                    locs.add(rightTwo);
                    return locs;
                } else {
                    //if the right two is not qualified,return moving locations of base class directly
                    return super.getMoveLocations();
                }
            } else {
                //return moving locations of base class directly
                return super.getMoveLocations();
            }
        }
     }
}
