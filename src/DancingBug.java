import info.gridworld.actor.Bug;
import java.util.Arrays;

public class DancingBug extends Bug {
    /*
     * index is the index of the array turns and is to make the bug do turns
     * circularly
     * totalnumber is size of the array turns.
     * each entity in turns decides the numbers of turn that the bug do 
     * before each move.
     */
    private int index;
    private int totalNumber;
    private int[] turns;

    public DancingBug(int[] turnsArray) {
        index = 0;
        totalNumber = turnsArray.length;
        turns = Arrays.copyOf(turnsArray, turnsArray.length);
    }

    public void act() {
        /*
         * when index is equal to totalnumber,
         * set index to zero so as to make the bug do turns circularly.
         */
        if (index == totalNumber) {
            index = 0;
        }
        /*
         * bug do turns before moving
         */
        for (int i = 0; i < turns[index]; i++) {
            turn();
        }
        index++;
        move();
    }
}
