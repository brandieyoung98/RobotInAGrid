import java.util.*;
import java.lang.*;
import java.io.*;
import java.awt.*;

public class RobotInAGrid{
    private static int [][] grid = {{0, 0, 1}, {0, 1, 0}, {0, 0, 0}, {1, 1, 0}}; //0 = good spots, 1 = "off limit" spots
    //note that the robot will start in the top left(0,0) and will need to travel to the bottom right
    //the point coordinates for my grid will increase moving right and down
    
	public static void main (String[] args){
		robotInAGrid();
	}
  
	//dynamic programming will be used so that the solution doesn't take exponential time
	//to find a path to the bottom right from the origin, work backwards...
	//starting from the bottom right try to find a path to each of its adjacent cells
	public static void robotInAGrid(){
	    ArrayList<Point> fails = new ArrayList<Point>(); //contains unreachable points
	    //to start we'll put all "off limits" points in fails and then we'll add any points we determine to be unreachable as we go
	    for(int r = 0; r< grid.length; r++){
	        for(int c = 0; c <grid[0].length; c++){
	            if(grid[r][c] == 1){
	                fails.add(new Point(r, c));
	            }
	        }
	    }
	    
	    ArrayList<Point> path = new ArrayList<Point>(); //contains the robot's path
	    //call our recursive helper function
	    if(getPath(grid.length-1, grid[0].length-1, path, fails)){
	        for(Point p: path){
	            System.out.print("(" + p.x + ", " + p.y + "), ");
	        }
	    }
	    else{
	        System.out.println("No path is possible");
	    }
	}
	
	
	//determines if there's a path to a certain location at <row, col>
	//we build up the path of points to the bottom right in the path arraylist
	//logic: if there's a path to the bottom right, you have to go through an adjacent spot to get there...
	//then there must be a path to an adjacent spot, which itself has adjacent spots that there must be a path through
	public static boolean getPath(int row, int col, ArrayList<Point> path, ArrayList<Point> fails){
	    if(row == 0 && col == 0){ //origin
	        path.add(new Point(row, col));
	        return true; //if we've made it here from the target space, then a working path was found!
	    }
      
      //validating the desired grid location
	    if(row < grid.length && col < grid[0].length && row >= 0 && col >= 0){
	        if(!fails.contains(new Point(row, col))){
              //if either of the above or left adjacent squares to this current one have a path to it, then this current one does!
              //remember that the robot can only move down and right
	            if(getPath(row-1, col, path, fails)){
	                path.add(new Point(row, col));
	                return true;
	            }
	            else if(getPath(row, col-1, path, fails)){
	               path.add(new Point(row, col));
	               return true;
	            }
	        }
          //if this line is reached, there was no valid path to the desired point so add it to our fails list and return false
	        fails.add(new Point(row, col));
	    }
	   return false;
	}
}
