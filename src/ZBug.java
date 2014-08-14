import info.gridworld.actor.Bug;

public class ZBug extends Bug {
    /*
     * zLength is the length of charater Z's upper side.
     * totallength is the total number of cells that Z occupies.
     * steps is to record the path that bug go across so as to
     * form a path with the shape of Z.
     */
    private int zLength;
    private int totalLength;
    private int steps;

    public ZBug(int length) {
        steps = 0;
        zLength = length;
        totalLength = length * 3;
        turn();
        turn();
    }

    public void act() {
        /*
         * when the path has formed a shape of Z,the bug stop moving.
         */
        if (steps == totalLength) {
            return;
        } else {
            if (canMove()) {
                if (steps >= 0 && steps < zLength) {
                    move();
                } else if (steps == zLength) {
                /*
                 * turn a angle of 135 degrees at the first corner of Z
                 */
                    turn();
                    turn();
                    turn();
                    move();
                } else if (steps > zLength  && steps < 2 * zLength) {
                    move();
                } else if ( steps == 2 * zLength) {
                    /*
                     * turn a angle of 225 degrees at the second corner
                     * of Z
                     */
                    turn();
                    turn();
                    turn();
                    turn();
                    turn();
                    move();
                } else if (steps > 2 * zLength && steps < totalLength) {
                    move();
                }
            } else {
                /*
                 * if the bug can't move and the path has not yet formed a
                 * shape of Z, the bug stops moving.
                 */
                return;
            }
            steps++;
        }
    }
}
