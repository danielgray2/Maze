/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeoutlab;

/**
 * This is the Position class. It allows us to access positions in the Maze without
 * having to type out a row and column postion each time. It's implementation is
 * straight-forward.
 * @author grayd
 */
public class Position {
    private int row;
    private int column;

    public Position(int row, int column){
        this.row = row;
        this.column = column;    
    }
    
    public Position(){}
    
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    
    public void setPosition(int row, int column){
        this.row = row;
        this.column = column;
    }
    
    @Override
    public String toString(){
        return "(" + this.row + ", " + this.column + ")";
    }
}