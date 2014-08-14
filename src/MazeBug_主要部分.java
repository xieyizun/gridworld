

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	public Location next;
	public Location last;
	public boolean isEnd = false;
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	public Integer stepCount = 0;
	boolean hasShown = false;
	

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		last = new Location(0, 0);
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		boolean willMove = canMove();
		if (isEnd) {
		//to show step count when reach the goal		
			if (!hasShown) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			move();
			//increase step count when move 
			stepCount++;
		} else if (!willMove) {
			String msg = "can't find the exit!";
			JOptionPane.showMessageDialog(null, msg);
		}
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return null;
		ArrayList<Location> valid = new ArrayList<Location>();
		int dirs[] = { Location.AHEAD, Location.RIGHT, Location.HALF_CIRCLE, Location.LEFT };
		for (int i = 0; i < 4; i++) {
			Location location = loc.getAdjacentLocation(getDirection() + dirs[i]);
			if (gr.isValid(location) && (gr.get(location) == null || gr.get(location) instanceof Flower) && location != last) {
				valid.add(location);
			}
		}
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		if (stepCount == 0) {
			ArrayList<Location> neighbors = getValid(getLocation());
			neighbors.add(getLocation());
			crossLocation.push(neighbors);
			Grid<Actor> gr = getGrid();
			int dirs[] = { Location.AHEAD, Location.RIGHT, Location.HALF_CIRCLE, Location.LEFT };
		    for (int i = 0; i < 4; i++) {
				Location location = getLocation().getAdjacentLocation(getDirection() + dirs[i]);
				if (gr.isValid(location) && gr.get(location) instanceof Rock && gr.get(location).getColor().equals(Color.RED)) {
					isEnd = true;
					break;
				}
			}
		}
		if (isEnd) {
			return false;
		}
		ArrayList<Location> neighbors = crossLocation.peek();
		if (neighbors.size() > 1) {
			
			int nextIndex = (int)(Math.random()*(neighbors.size()-2));
			next = neighbors.get(nextIndex);
			crossLocation.peek().remove(next);
			ArrayList<Location> nextNeighbors = getValid(next);
			nextNeighbors.add(next);
			crossLocation.push(nextNeighbors);
			last = getLocation();
			return true;
		} else if (neighbors.size() == 1) {
			crossLocation.pop();
			last = getLocation();
			if (!crossLocation.empty()) {
				next = crossLocation.peek().get(crossLocation.peek().size()-1);
			} else if (crossLocation.empty()) {
				return false;
			}
			return true;
		}
		return false;
	}
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		Location loc = getLocation();
		if (gr.isValid(next)) {
			setDirection(getLocation().getDirectionToward(next));
			moveTo(next);
			int dirs[] = { Location.AHEAD, Location.RIGHT, Location.HALF_CIRCLE, Location.LEFT };
		    for (int i = 0; i < 4; i++) {
				Location location = getLocation().getAdjacentLocation(getDirection() + dirs[i]);
				if (gr.isValid(location) && gr.get(location) instanceof Rock && gr.get(location).getColor() == Color.RED) {
					isEnd = true;
					break;
				}
			}
		} else
			removeSelfFromGrid();
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}
}
