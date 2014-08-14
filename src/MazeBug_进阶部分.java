import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
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
	int dirWeight[] = {1,1,1,1};

	//Appraisement function
	int selectIndex(ArrayList<Location> validLocations) {
		
		ArrayList<Integer> validDirs = new ArrayList<Integer>();
		int dirs[] = { Location.AHEAD, Location.RIGHT, Location.HALF_CIRCLE, Location.LEFT };
		for (int i = 0; i < validLocations.size()-1; i++) {
			for (int j = 0; j < 4; j++) {
				if (getLocation().getAdjacentLocation(getDirection()+dirs[j]).equals(validLocations.get(i))) {
					validDirs.add(j);
					break;
				}
			}
		}
		
		double probabilitys[] = new double[validDirs.size()];
		for (int i = 0; i < validDirs.size(); i++) {
			double probability = dirWeight[validDirs.get(i)]/(stepCount+1);
			probabilitys[i] = probability;
		}
		int indexs[] = new int[validDirs.size()];
		for (int i = 0; i < validDirs.size(); i++) {
			indexs[i] = validDirs.get(i);
		}
		//sorted the probabilities in descending order and adjuct the index of the validlocation accordingly.
		for (int i = 0; i < validDirs.size(); i++) {
			for (int j = i; j < validDirs.size(); j++) {
				if (probabilitys[j] > probabilitys[i]) {
					double temp = probabilitys[i];
					probabilitys[i] = probabilitys[j];
					probabilitys[j] = temp;
					int temp2 = indexs[i];
					indexs[i] = indexs[j];
					indexs[j] = temp2;
					//indexs[i] = validDirs.get(j);
				}
			}
		}
		//adjust the validlocations, put the locations that have the bigger possibility
		//in the front locations of the arraylist validLocations.
		for (int i = 0; i < validLocations.size()-1; i++) {
			Location loc = getLocation().getAdjacentLocation(getDirection()+dirs[indexs[i]]);
			validLocations.set(i, loc);
		}
		//0,1,2... possibility's rate is decreased.
		int max = validDirs.size()-1;
		int bigend = ((1+max)*max)/2;
		int nextIndex = (int)(Math.random()*bigend);
		int sum = 0;
		for (int i = 0; i < max; i++) {
			sum += (max-i);
			if (sum > nextIndex) {
				return i;
			}
		}
		return 0;

	}
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
		if (gr == null) {
			return null;
		}
		ArrayList<Location> valid = new ArrayList<Location>();
		int dirs[] = { Location.AHEAD, Location.RIGHT, Location.HALF_CIRCLE, Location.LEFT };
		for (int i = 0; i < 4; i++) {
			/*
			 *the valid locations are those that is inside the grid and a null locaton or a instance of Flower and not the last location
			 */
			Location location = loc.getAdjacentLocation(getDirection() + dirs[i]);
			if (gr.isValid(location) && (gr.get(location) == null || gr.get(location) instanceof Flower) && !location.equals(last)) {
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
		//First, we shoudl put the first position and its valid neighbors in to the stack as an arrayList
		//each arrayList holds the a position that the bug has cross and its valid neighboring positions that can move to in the 
		//next step.
		if (stepCount == 0) {
			ArrayList<Location> neighbors = getValid(getLocation());
			neighbors.add(getLocation());
			crossLocation.push(neighbors);
			Grid<Actor> gr = getGrid();
			//judge whether there is an exit in the beginning
			int dirs[] = { Location.AHEAD, Location.RIGHT, Location.HALF_CIRCLE, Location.LEFT };
		    for (int i = 0; i < 4; i++) {
				Location location = getLocation().getAdjacentLocation(getDirection() + dirs[i]);
				if (gr.isValid(location) && gr.get(location) instanceof Rock && gr.get(location).getColor() == Color.RED) {
					isEnd = true;
					break;
				}
			}
		}
		if (isEnd) {
			return false;
		}
		//neighbors hold the top arraylist of the stack
		ArrayList<Location> neighbors = crossLocation.peek();
		//if there are some position that the bug can move to in the next step
		//then select a valid neigboring position of current location randomly
		//then remove this position from current location's arraylist
		//get this position's valid neighbors and put in an arraylist with 
		//this position as the last one. push this arraylist in the stack.
		if (neighbors.size() > 1) {

			//int nextIndex = (int)(Math.random()*(neighbors.size()-2));
			int nextIndex = selectIndex(neighbors);
			next = neighbors.get(nextIndex);
			crossLocation.peek().remove(next);
			ArrayList<Location> nextNeighbors = getValid(next);
			nextNeighbors.add(next);
			crossLocation.push(nextNeighbors);
			last = getLocation();
			return true;
		} 
		//if the top arrylist of the stack have one element, that the current position itself
		//then it need to roll back.
		else if (neighbors.size() == 1) {
			crossLocation.pop();
			last = getLocation();
			if (!crossLocation.empty()) {
				//roll back
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
		if (gr == null) {
			return;
		}
		Location loc = getLocation();
		if (gr.isValid(next)) {
			setDirection(getLocation().getDirectionToward(next));
			//increase the weight of each direction
			if (getDirection() == Location.AHEAD) {
				dirWeight[0]++;
				dirWeight[2]--;
			} else if (getDirection() == Location.RIGHT) {
				dirWeight[1]++;
				dirWeight[3]--;
			} else if (getDirection() == Location.HALF_CIRCLE) {
				dirWeight[2]++;
				dirWeight[0]--;
			} else if (getDirection() == Location.LEFT) {
				dirWeight[3]++;
				dirWeight[1]--;
			}
			moveTo(next);
			//after moving to a new position, searching the neighbors of this positions, 
			//check if there is an exit
			int dirs[] = { Location.AHEAD, Location.RIGHT, Location.HALF_CIRCLE, Location.LEFT };
		    for (int i = 0; i < 4; i++) {
				Location location = getLocation().getAdjacentLocation(getDirection() + dirs[i]);
				if (gr.isValid(location) && gr.get(location) instanceof Rock && gr.get(location).getColor() == Color.RED) {
					isEnd = true;
					break;
				}
			}
		} else {
			removeSelfFromGrid();
		}
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}
}
