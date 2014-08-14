import static org.junit.Assert.*;
import org.junit.Test;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import info.gridworld.grid.BoundedGrid;

public class TestJumper {
    public Jumper xyz = new Jumper();
    public BoundedGrid<Actor> gr = new BoundedGrid<Actor>(10,10);
    /*
     *the following code is to test thirdLocation method
     */
    @Test
    public void testThirdInformation() {
       //test third location is outsize the grid
       xyz.putSelfInGrid(gr,new Location(0,0));
       xyz.setDirection(0);
       assertEquals(-3, xyz.thirdInformation());
       //test third location is empty
       xyz.setDirection(90);
       assertEquals(2, xyz.thirdInformation());
       //test third location has thing
       Bug testBug = new Bug();
       testBug.putSelfInGrid(gr, new Location(0,2));
       assertEquals(1000, xyz.thirdInformation());
       xyz.removeSelfFromGrid();
    }
    @Test
    public void testNextInformation() {
       //test next location is outsize the grid
       xyz.putSelfInGrid(gr,new Location(0,0));
       xyz.setDirection(0);
       assertEquals(-2, xyz.nextInformation());
       //test next location is empty
       xyz.setDirection(90);
       assertEquals(1, xyz.nextInformation());
       //test next location has thing
       Bug testBug = new Bug();
       testBug.putSelfInGrid(gr, new Location(0,1));
       assertEquals(100, xyz.nextInformation());
       xyz.removeSelfFromGrid(); 
    }
    @Test
    public void testMoveTwo() {
      xyz.putSelfInGrid(gr, new Location(0,0));
      xyz.setDirection(90);
      xyz.moveTwo();
      Location testLoc = new Location(0,2);
      assertEquals(testLoc, xyz.getLocation());
      xyz.removeSelfFromGrid();
    }
    @Test
    public void testMoveOne() {
      xyz.putSelfInGrid(gr, new Location(0,0));
      xyz.setDirection(90);
      xyz.moveOne();
      Location testLoc = new Location(0,1);
      assertEquals(testLoc, xyz.getLocation());
      xyz.removeSelfFromGrid();
    }
    @Test
    public void testGetThirdbug() {
      xyz.putSelfInGrid(gr, new Location(0,0));
      xyz.setDirection(90);
      Bug testBug = new Bug();
      testBug.putSelfInGrid(gr, new Location(0,2));
      // test third location has a bug
      assertEquals(testBug, xyz.getThirdbug());
      testBug.removeSelfFromGrid();
      // test third location is not a bug, is a flower or a rock
      Flower testFlower = new Flower();
      testFlower.putSelfInGrid(gr, new Location(0,2));
      assertEquals(null, xyz.getThirdbug());
      testFlower.removeSelfFromGrid();
      //test third location is outsize the grid
      xyz.setDirection(0);
      assertEquals(null, xyz.getThirdbug());
      xyz.removeSelfFromGrid();
    }
    @Test
    public void testAct() {
      //test third location is empty
      xyz.putSelfInGrid(gr, new Location(0,0));
      xyz.setDirection(90);
      xyz.act();
      Location loc = new Location(0,2);
      assertEquals(loc, xyz.getLocation());
      xyz.removeSelfFromGrid();
      //test third location has a jumper
      xyz.putSelfInGrid(gr, new Location(0,0));
      xyz.setDirection(90);
      Jumper testJumper = new Jumper();
      testJumper.putSelfInGrid(gr, new Location(0,2));
      xyz.act();
      assertEquals(null, gr.get(new Location(0,2)));
      //test third location has a bug not a jumper
      xyz.putSelfInGrid(gr, new Location(0,0));
      xyz.setDirection(90);
      Bug testBug = new Bug();
      testBug.putSelfInGrid(gr, new Location(0,2));
      xyz.act();
      assertNotSame( 90, xyz.getDirection());
      testBug.removeSelfFromGrid();
      xyz.removeSelfFromGrid();
      //test third location has a flower or a rock, next location is empty
      xyz.putSelfInGrid(gr, new Location(0,0));
      xyz.setDirection(90);
      Flower testFlower = new Flower();
      testFlower.putSelfInGrid(gr, new Location(0,2));
      xyz.act();
      Location loc2 = new Location(0,1);
      assertEquals(loc2, xyz.getLocation());
      testFlower.removeSelfFromGrid();
      xyz.removeSelfFromGrid();
      //test third location has flower or a rock, next location is not empty
      xyz.putSelfInGrid(gr, new Location(0,0));
      xyz.setDirection(90);
      Rock testRock = new Rock();
      testRock.putSelfInGrid(gr, new Location(0,2));
      Rock testRock2 = new Rock();
      testRock2.putSelfInGrid(gr, new Location(0,1));
      xyz.act();
      assertNotSame(90, xyz.getDirection());
      testRock.removeSelfFromGrid();
      testRock2.removeSelfFromGrid();
      xyz.removeSelfFromGrid();
      // test third location is outsize the grid, next location is insize
      xyz.putSelfInGrid(gr, new Location(0,1));
      xyz.setDirection(270);
      xyz.act();
      assertNotSame(270, xyz.getDirection());
      xyz.removeSelfFromGrid();
      // test next location is outsize the grid
      xyz.putSelfInGrid(gr, new Location(0,0));
      xyz.setDirection(270);
      xyz.act();
      assertEquals(null, gr.get(new Location(0,0)));
    }
}
