import info.gridworld.actor.Bug;

public class SpiralBug extends Bug {
    /*
     * steps is to record the number of cells that the bug go across
     * in ane direction.
     * dynamicLength is dynamically increased by one each time that 
     * steps is equal to dynamicLength.
     */
    private int steps;
    private int dynamicLength;

    public SpiralBug(int length) {
        steps = 0;
        dynamicLength = length;
    }

    public void act() {
        if (steps < dynamicLength && canMove()) {
            move();
            steps++;
        } else {
            turn();
            turn();
            dynamicLength++;
            steps=0;
        }
    }
}
