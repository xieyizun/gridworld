import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Bug;
import java.awt.Color;


public class Jumper extends Bug {
    private static final int THIRD_OUTSIZE_GRID = -3;
    private static final int NEXT_OUTSIZE_GRID = -2;
    private static final int CURRENT_OUTSIZE_GRID = -1;
    private static final int NEXT_CAN_MOVE = 1;
    private static final int THIRD_CAN_MOVE = 2;
    private static final int THIRD_HAVE_THING = 1000;
    private static final int NEXT_HAVE_THING = 100;

    public Jumper() {
        setColor(Color.BLUE);
    }
    /*
     * method thirdinformation return the information of the third location
     * that is outsize the grid or empty or have thing
     */
    public int thirdInformation() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return CURRENT_OUTSIZE_GRID;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location third = next.getAdjacentLocation(getDirection());
        if (!gr.isValid(third)) {
            return THIRD_OUTSIZE_GRID;
        }
        Actor thirdNeighbor = gr.get(third);
        if (thirdNeighbor == null) {
            return THIRD_CAN_MOVE;
        }
        return THIRD_HAVE_THING;
    }
    /*
     * method nextinformation return the information of the next location
     * that is outsize the grid or empty or have thing.
     */
    public int nextInformation() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return CURRENT_OUTSIZE_GRID;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (!gr.isValid(next)) {
            return NEXT_OUTSIZE_GRID;
        }
        Actor nextNeighbor = gr.get(next);
        if(nextNeighbor == null) {
            return NEXT_CAN_MOVE;
        }
        return NEXT_HAVE_THING;
    }
    /*
     * method moveTwo move jumper two cells each time.
     */
    public void moveTwo() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location third = next.getAdjacentLocation(getDirection());
        if (gr.isValid(third)) {
            moveTo(third);
        } else {
            removeSelfFromGrid();
        }
    }
    /*
     * method moveOne move jumper one cell each time
     */
    public void moveOne() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (gr.isValid(next)) {
            moveTo(next);
        } else {
            removeSelfFromGrid();
        }
    }
    
    public Bug getThirdbug() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return null;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location third = next.getAdjacentLocation(getDirection());
        if (gr.isValid(third)) {
            Actor thirdNeighbor = gr.get(third);
            if (thirdNeighbor instanceof Bug) {
                return (Bug)thirdNeighbor;
            } else {
                return null;
            }
        }
        return null;
    }
    public void act() {
        int thirdInfor = thirdInformation();
        int nextInfor = nextInformation();
        /*
         * if third location can move directly that is the third location is empty,
         * move two cell.
         */
        if (thirdInfor == THIRD_CAN_MOVE) {
             moveTwo();
        }
        /*
         * if third location is not outsize of the grid and not empty,
         * one: if there is jumper in the third location, both jumpers die //question:e's solution
         * two: if there is other bug, the jumper turn to another direction //question:d's solution
         * three: if there is rock or flower, go to the next location, if the next location is empty
         * ,the jumper walk to next location, otherwise turn to another direction. //question:a's solution
         *
         */
        else if (thirdInfor == THIRD_HAVE_THING) {
            Bug thirdBug = getThirdbug();
            if (thirdBug != null) {
               if (thirdBug instanceof Jumper) {
                     thirdBug.removeSelfFromGrid();
                     this.removeSelfFromGrid();
               } else {
                /*
                 *hird location is other bug excepts jumper
                 */
                     this.turn();
               }
            } else {
               /* 
                *third location is  rock or flower
                */
                if (nextInfor == NEXT_CAN_MOVE) {
                    moveOne();
                } else {
                    this.turn();
                }
            }
        }
        /*
         *if the third location is outsize the grid, see the next location,
         *if the next location is also outsize the grid, the jumper die(disappear) //question:c's solution
         *if the next location is insize the grid and whether it's empty or has things, the jumper turn to another direction
         *question:b's solution
         */
        else if (thirdInfor == THIRD_OUTSIZE_GRID) {
            if (nextInfor == NEXT_CAN_MOVE || nextInfor == NEXT_HAVE_THING) {
                this.turn();
            } else if (nextInfor == NEXT_OUTSIZE_GRID) {
                this.removeSelfFromGrid();
            }
        }
    }
}
