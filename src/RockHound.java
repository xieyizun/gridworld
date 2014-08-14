import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;

public class RockHound extends Critter {
	/*
	 *a RockHound removes the neighboring rocks out of the grid
	 */
	public void processActors(ArrayList<Actor> actors) {
		for (Actor a : actors)
        {
            if (a instanceof Rock) {
                a.removeSelfFromGrid();
            }
        }
	}
}