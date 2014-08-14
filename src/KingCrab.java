import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.util.ArrayList;


public class KingCrab extends CrabCritter {
    public void processActors(ArrayList<Actor> actors) {
        ArrayList<Location> actorsLocations = new ArrayList<Location>();
        /*
         *get the locations of actors that are neighboring the KingCrab
         */
        for (Actor actor : actors) {
            Location location = actor.getLocation();
            actorsLocations.add(location);
        }
        Location loc = getLocation();
        Location ahead = loc.getAdjacentLocation(getDirection());
        Location left = loc.getAdjacentLocation(getDirection() + Location.HALF_LEFT);
        Location right = loc.getAdjacentLocation(getDirection() + Location.HALF_RIGHT);
        /*
         *ajacentLocation holds the locations of the three location that the kingCrab 
         *can get actors.
         */
        ArrayList<Location> ajacentLocations = new ArrayList<Location>();
        ajacentLocations.add(ahead);
        ajacentLocations.add(left);
        ajacentLocations.add(right);
        /*
         *search the three locations that the KingCrab can get actors
         *to see whether there is an actor, if yes, select possible location
         *that can make the actor one location away from the kingcrab
         * the array dirs in each case hold the possible directions
         * that the neighboring actor to be moved.
         */
        for (int i = 0; i < ajacentLocations.size(); i++) {
            for (int j = 0; j < actorsLocations.size(); j++) {
                if (ajacentLocations.get(i).equals(actorsLocations.get(j))) {
                    //deal with the ahead location
                    if (i == 0) {
                        ArrayList<Location> canLocation = new ArrayList<Location>();
                        int[] dirs = {Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT};
                        for (int k = 0; k < 3; k++) {
                            Location aheadCan = ajacentLocations.get(i).getAdjacentLocation(dirs[k]);
                            if (getGrid().isValid(aheadCan) && getGrid().get(aheadCan) == null) {
                                canLocation.add(aheadCan);
                            }
                        }
                        if (canLocation.size() > 0) {
                            int to = (int)(Math.random()*(canLocation.size()-1));
                            Location aheadDir = canLocation.get(to);
                            actors.get(j).moveTo(aheadDir);
                        } else {
                            actors.get(j).removeSelfFromGrid();
                        }
                        break;
                    }
                    //deal with the half-left location 
                    else if (i == 1) {
                        ArrayList<Location> canLocation = new ArrayList<Location>();
                        int[] dirs = {Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT, Location.LEFT, Location.SOUTHWEST};
                        for (int k = 0; k < 5; k++) {
                            Location leftCan = ajacentLocations.get(i).getAdjacentLocation(dirs[k]);
                            if (getGrid().isValid(leftCan) && getGrid().get(leftCan) == null) {
                                canLocation.add(leftCan);
                            }
                        }
                        if (canLocation.size() > 0) {
                            int to = (int)(Math.random()*(canLocation.size()-1));
                            Location leftDir = canLocation.get(to);
                            actors.get(j).moveTo(leftDir);
                        } else {
                            actors.get(j).removeSelfFromGrid();
                        }
                        break;
                    }
                    // deal with the half-right location 
                    else if (i == 2) {
                        ArrayList<Location> canLocation = new ArrayList<Location>();
                        int[] dirs = {Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT, Location.RIGHT, Location.SOUTHEAST};
                        for (int k = 0; k < 5; k++) {
                            Location rightCan = ajacentLocations.get(i).getAdjacentLocation(dirs[k]);
                            if (getGrid().isValid(rightCan) && getGrid().get(rightCan) == null) {
                                canLocation.add(rightCan);
                            }
                        }
                        if (canLocation.size() > 0) {
                            int to = (int)(Math.random()*(canLocation.size()-1));
                            Location rightDir = canLocation.get(to);
                            actors.get(j).moveTo(rightDir);
                        } else {
                            actors.get(j).removeSelfFromGrid();
                        }
                        break;
                    }
                }
            }
        }
    }
}
