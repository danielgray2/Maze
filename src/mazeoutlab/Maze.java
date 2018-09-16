/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeoutlab;

/**
 * This is the maze class. It is responsible for creating the maze. It has two
 * constructors. The first constructor allows the user to pass in a String[][]
 * to create a new maze. If you pass in a new maze, it must be a 12x12 grid. The 
 * second constructor automatically creates a maze.
 * @author grayd
 */
public class Maze {
    private Position entryPoint;
    private String[][] grid = new String[12][];
    
    public Maze(String[][] grid){
        this.grid = grid;
    }
    
    public Maze(){
        grid[0] = new String[]{"#","#","#","#","#","#","#","#","#","#","#","#"};
        grid[1] = new String[]{"#",".",".",".","#",".",".",".",".",".",".","#"};
        grid[2] = new String[]{".",".","#",".","#",".","#","#","#","#",".","#"};
        grid[3] = new String[]{"#","#","#",".","#",".",".",".",".","#",".","#"};
        grid[4] = new String[]{"#",".",".",".",".","#","#","#",".","#",".","#"};
        grid[5] = new String[]{"#","#","#","#",".","#","F","#",".","#",".","#"};
        grid[6] = new String[]{"#",".",".","#",".","#",".","#",".","#",".","#"};
        grid[7] = new String[]{"#","#",".","#",".","#",".","#",".","#",".","#"};
        grid[8] = new String[]{"#",".",".",".",".",".",".",".",".","#",".","#"};
        grid[9] = new String[]{"#","#","#","#","#","#",".","#","#","#",".","#"};
        grid[10] = new String[]{"#",".",".",".",".",".",".","#",".",".",".","#"}; 
        grid[11] = new String[]{"#","#","#","#","#","#","#","#","#","#","#","#"}; 
    }
    
    /**
     * This method prints the maze
     */
    public void printMaze(){
        for(int i = 0; i < 12; i++){
            for(int j = 0; j < 12; j++){
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }
    
    /**
     * This method searches for the start of the maze.
     * @return Position entryPoint
     */
    public Position findStartOfMaze(){
        for(int i = 0; i < 12; i++){
            for(int j = 0; j < 12; j++){
                if(grid[i][j].equals(".") && (i == 0 || j == 0)){
                    this.entryPoint = new Position(i, j);
                    return entryPoint;
                }
            }
        }        
        return entryPoint;
    }
    
    /**
     * This method returns the element at a given position
     * @param position
     * @return element at a given position.
     */    
    public String getElementAt(Position position){
        return this.grid[position.getRow()][position.getColumn()];
    }
   
    /**
     * This method takes in a row and column location, as well as the String
     * you would like to set that position to.
     * @param position
     * @param setTo 
     */
    public void setElementAt(Position position, String setTo){
        this.grid[position.getRow()][position.getColumn()] = setTo;
    }
}
