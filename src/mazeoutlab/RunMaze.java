/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeoutlab;

/**
 * This class is responsible for bringing the Maze and MazeRunner classes together,
 * and allows the MazeRunner to run the Maze. It takes in one parameter, a maze
 * to run.
 * @author grayd
 */
public class RunMaze {
    private Maze maze;
    private MazeRunner mazeRunner;
    
    public RunMaze(Maze maze){
        this.maze = maze;
    }
    
    /**
     * This is the method that is called when the user wants to actually run the
     * maze. It is the recursive function. In the assignment rules, it said that
     * this method should take in several different parameters. However, by creating
     * a maze runner class, and by creating the RunMaze class, I was able to pass
     * in all of the relevant information to this method by simply passing in one
     * parameter.
     * @param mazeRunner
     * @return 
     */
    public MazeRunner runTheMaze(MazeRunner mazeRunner){
        if(this.maze.getElementAt(mazeRunner.getOneStepAhead()).equals("F")){
            handleOneStep(mazeRunner);
            return mazeRunner;
        }else{
            checkSurroundings(mazeRunner);
            return (runTheMaze(mazeRunner));
        }
    }
    
    /**
     * This method takes in a starting position, and determines which way the maze
     * runner is facing at the beginning of the maze. It returns an integer
     * representation of this, with "0" corresponding to north, "1" corresponding
     * to east, "2" corresponding to south, and "3" corresponding to west.
     * @param start
     * @return orientation
     */
    public int determineStartingOrientation(Position start){
        int orientation = 0;
        if(start.getColumn() == 0){
            orientation = 1;
        }else if(start.getColumn() == 11){
            orientation = 3;
        }else if(start.getRow() == 0){
            orientation = 2;
        }else if(start.getRow() == 11){
            orientation = 0;
        }
        return orientation;
    }
    
    /**
     * This method allows the MazeRunner to take one step forward as he goes through
     * the maze.
     * @param mazeRunner 
     */
    private void handleOneStep(MazeRunner mazeRunner){
        maze.setElementAt(mazeRunner.getBodyPosition(), "X");
        mazeRunner.stepForward();
        maze.setElementAt(mazeRunner.getBodyPosition(), "O");
        maze.printMaze();
    }
    
    /**
     * This method is responsible for enabling the mazeRunner to see what is around
     * him, and determine the correct course of action based on the situation that
     * he finds himself in.
     * @param mazeRunner 
     */
    private void checkSurroundings(MazeRunner mazeRunner){
        Position rightHand = mazeRunner.getRightHand();
        Position leftHand = mazeRunner.getLeftHand();
        Position oneStepAhead = mazeRunner.getOneStepAhead();
        if(!maze.getElementAt(rightHand).equals("#")){
            mazeRunner.turnRight();
            handleOneStep(mazeRunner);
        }else if(maze.getElementAt(rightHand).equals("#") && maze.getElementAt(oneStepAhead).equals("#") && maze.getElementAt(leftHand).equals("#")){
            mazeRunner.turnLeft();
        }else if(maze.getElementAt(rightHand).equals("#") && maze.getElementAt(oneStepAhead).equals("#")){
            mazeRunner.turnLeft();
            handleOneStep(mazeRunner);
        }else if(maze.getElementAt(oneStepAhead).equals("#") && maze.getElementAt(leftHand).equals("#")){
            mazeRunner.turnRight();
            handleOneStep(mazeRunner);
        }else{
            handleOneStep(mazeRunner);
        }
    }
}