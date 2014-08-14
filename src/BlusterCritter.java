import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.awt.Color;

public class BlusterCritter extends Critter {
	private int c;
	private static final double DARKENING_FACTOR = 0.10;

	public BlusterCritter(int cValue) {
		c = cValue;
		setColor(Color.GRAY);
	}
	/*
     *the getActors method returns the actors within two steps of the BlusterCritter
     */
	public ArrayList<Actor> getActors() {
		ArrayList<Actor> actors = new ArrayList<Actor>();
		//adjacentLocations holds the locations of these actors that are to be return
		ArrayList<Location> adjacentLocations = getGrid().getValidAdjacentLocations(getLocation());
		ArrayList<Location> temp = new ArrayList<Location>();
		for (int i = 0; i < adjacentLocations.size(); i++) {
			temp.add(adjacentLocations.get(i));
		}
		
		for (Location loc : temp) {
			ArrayList<Location> secondLocations = getGrid().getValidAdjacentLocations(loc);
			for (Location second : secondLocations) {
				int exisit = adjacentLocations.indexOf(second);
				if (exisit == -1) {
					adjacentLocations.add(second);
				}
			}
		}
		/*
		 *get actors in these locations that are two steps within
		 *the BlusterCritter
		 */
		for (Location loc : adjacentLocations) {
			Actor actor = getGrid().get(loc);
			if (actor instanceof Critter) {
				actors.add(actor);
			}
		}
		return actors;
 	}

 	public void processActors(ArrayList<Actor> actors) {
 		int actorsNumber = actors.size() - 1;
        Color  color = getColor();
        /*
         *if the number of actors within two steps is less than c, blustercritter's color
         *become pale
         */
 		if (actorsNumber < c) {
 		    int red = (int) (color.getRed() +1 );
            int green = (int) (color.getGreen() + 1);
            int blue = (int) (color.getBlue() + 1);
            /*
             *if the color value is not out of bound, set the color
             *else do nothing.
             */
            if (red <= 255 && green <= 255 && blue <= 255) {
               setColor(new Color(red, green, blue));
            }
            return;
 		} else {
 			/*
 			 *if it equal or large than c, blustercritter's color becomes dark.
 			 */
            int red = (int) (color.getRed() * (1 - DARKENING_FACTOR));
            int green = (int) (color.getGreen() * (1 - DARKENING_FACTOR));
            int blue = (int) (color.getBlue() * (1 - DARKENING_FACTOR));
            setColor(new Color(red, green, blue));
            return;
 		}
 	}
}
