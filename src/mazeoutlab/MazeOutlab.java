/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeoutlab;

/**
 *
 * @author grayd
 */
public class MazeOutlab {

    /**
     * This is the main function. It makes a new Maze object, a new RunMaze object,
     * and a new MazeRunnerObject. It then calls the runTheMaze() method from the
     * RunMaze object, passing in the mazeRunner as a variable.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Maze maze = new Maze();
        RunMaze runMaze = new RunMaze(maze);
        MazeRunner mazeRunner = new MazeRunner(maze.findStartOfMaze(), runMaze.determineStartingOrientation(maze.findStartOfMaze()));
        runMaze.runTheMaze(mazeRunner);
    }
}