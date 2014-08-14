import info.gridworld.actor.Bug;

public class CircleBug extends Bug {
    /*
     * steps is to record the number of cells so as
     * to decide wether these cells can form a side
     * of the circle with sideLength as radius
     *
    */
    private int steps;
    private int sideLength;

    public CircleBug(int length) {
        steps = 0;
        sideLength = length;
    }
    public void act() {
        if (steps < sideLength && canMove()) {
            move();
            steps++;
        } else {
            /*
             * call method turn once so as to create the angle of 45 degree
             * so as to create a shape of circle.
             */
            turn();
            steps = 0;
        }
    }
}
