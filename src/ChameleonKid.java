import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.awt.Color;

public class ChameleonKid extends ChameleonCritter {

    public void processActors(ArrayList<Actor> actors)
    {
        /*
         *when there are no actors around the chameleonCritter, its color becomes dark.
         */
        int n = actors.size();
        if (n == 0) {
            Color c = getColor();
            int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
            int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
            int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
            setColor(new Color(red, green, blue));
            return;
        }
        /*
         *Set the neighboring actors in front or back of the chameleonKid 
         *to the color of the the chameleonKis's one
         */
        int frontDirection = getDirection();
        int backDirection = frontDirection + 180;

        Location loc = getLocation();
        Location front = loc.getAdjacentLocation(frontDirection);
        Location back = loc.getAdjacentLocation(backDirection);

        for (Actor a : actors) {
            if (a.getLocation().equals(front) || a.getLocation().equals(back)) {
                setColor(a.getColor());
                return;
            }
        }

    }
}
