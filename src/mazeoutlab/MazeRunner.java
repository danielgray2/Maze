/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeoutlab;

/**
 * This class creates the MazeRunner. He is the individual who will actually be 
 * going through our maze.
 * @author grayd
 */
public class MazeRunner {
    private Position bodyPosition;
    private Position rightHand = new Position();
    private Position leftHand = new Position();
    private Position oneStepAhead = new Position();
    private final int NORTH = 0;
    private final int EAST = 1;
    private final int SOUTH = 2;
    private final int WEST = 3;
    private int turnCounter;
    //This variable (oneStepBehind) keeps track of the position of the object. However, it is a
    //private variable that has no getter method. This is to comply with the
    //rules of this assignment. The oneStepBehind variable is only used to help
    //with turning the object to the right and left. It is not used to help
    //the object navigate the maze, since the object itself cannot reference it.
    private Position oneStepBehind = new Position();
    private int currentOrientation;
    
    public MazeRunner(Position bodyPosition, int orientation){
        this.bodyPosition = bodyPosition;
        handleMove(orientation, 0);
        this.currentOrientation = orientation;
        this.turnCounter = orientation;
    }

    public Position getRightHand() {
        return rightHand;
    }

    public void setRightHand(Position rightHand) {
        this.rightHand = rightHand;
    }

    public Position getLeftHand() {
        return leftHand;
    }

    public void setLeftHand(Position leftHand) {
        this.leftHand = leftHand;
    }

    public Position getOneStepAhead() {
        return oneStepAhead;
    }

    public void setOneStepAhead(Position oneStepAhead) {
        this.oneStepAhead = oneStepAhead;
    }
    
    public int getOrientation(){
        return this.currentOrientation;
    }
    
    public Position getBodyPosition(){
        return this.bodyPosition;
    }
    
    /**
     * This method is responsible for changing the way that our mazeRunner is
     * facing if he turns. An integer is decremented based on left and right hand
     * turns. it is then modded by four, and the remainder is used to determine
     * his current orientation. If the turnCounter is decremented to -1, it is 
     * reset to three. If the turnCounter is incremented to 5, it is reset to 0.
     * This allows us to avoid any boundary problems.
     * @param whichWay 
     */
    public void switchOrientation(int whichWay){
        turnCounter += whichWay;
        switch(turnCounter % 4){
            case -1: currentOrientation = WEST; turnCounter = 3; break;
            case 0: currentOrientation = NORTH; break;
            case 1: currentOrientation = EAST; break;
            case 2: currentOrientation = SOUTH; break;
            case 3: currentOrientation = WEST; break;
            case 4: currentOrientation = NORTH; turnCounter = 0; break;
        }
    }
    
    /**
     * This function allows our MazeRunner to take one step forward in the maze.
     * It takes a look at current orientation to determine what variables should
     * be passed into the handleMove() function.
     */
    public void stepForward(){
        switch(currentOrientation){
            case NORTH: handleMove(NORTH, -1); break;
            case EAST: handleMove(EAST, 1); break;
            case SOUTH: handleMove(SOUTH, 1); break;
            case WEST: handleMove(WEST, -1);
        }
    }

/**
 * Direction must be an integer from 0 through 3 that pertains to the direction as
 * translated in the switchOrientation() function. forwardOrBackward is either -1 or
 * 1, and is used to decide if the mazeRunner moves forward or backward along the
 * x or y axis.
 * @param direction
 * @param forwardOrBackward 
 */    
    private void handleMove(int direction, int forwardOrBackward){
                
        if(direction == NORTH || direction == SOUTH){
            this.bodyPosition.setRow(this.bodyPosition.getRow() + forwardOrBackward);
            if(forwardOrBackward == 0){
                if(direction == NORTH)
                    forwardOrBackward = -1;
                else
                    forwardOrBackward = 1;
            }
            this.rightHand.setPosition(this.bodyPosition.getRow(), this.bodyPosition.getColumn() - forwardOrBackward);
            this.leftHand.setPosition(this.bodyPosition.getRow(), this.bodyPosition.getColumn() + forwardOrBackward);
            this.oneStepAhead.setPosition(this.bodyPosition.getRow() + forwardOrBackward, this.bodyPosition.getColumn());
            if(this.bodyPosition.getColumn()-forwardOrBackward >= 0){
                this.oneStepBehind.setPosition(this.bodyPosition.getRow()-forwardOrBackward, this.bodyPosition.getColumn());
            }
        }else{
            this.bodyPosition.setColumn(this.bodyPosition.getColumn() + forwardOrBackward);
            if(forwardOrBackward == 0){
                if(direction == WEST)
                    forwardOrBackward = -1;
                else
                    forwardOrBackward = 1;
            }
            this.rightHand.setPosition(this.bodyPosition.getRow()  + forwardOrBackward, this.bodyPosition.getColumn());
            this.leftHand.setPosition(this.bodyPosition.getRow() - forwardOrBackward, this.bodyPosition.getColumn());
            this.oneStepAhead.setPosition(this.bodyPosition.getRow(), this.bodyPosition.getColumn() + forwardOrBackward);
            if(this.bodyPosition.getRow()-forwardOrBackward >= 0){
                this.oneStepBehind.setPosition(this.bodyPosition.getRow(), this.bodyPosition.getColumn() - forwardOrBackward);
            }
        }
    }
    
    /**
     * This turns our MazeRunner to the right by changing his relative directions
     * (front, back, left, right).
     */
    public void turnRight(){
        switchOrientation(1);
        int frontRow = this.oneStepAhead.getRow();
        int frontColumn = this.oneStepAhead.getColumn();
        this.oneStepAhead.setPosition(this.rightHand.getRow(), this.rightHand.getColumn());
        this.rightHand.setPosition(this.oneStepBehind.getRow(), this.oneStepBehind.getColumn());
        this.oneStepBehind.setPosition(this.leftHand.getRow(), this.leftHand.getColumn());
        this.leftHand.setPosition(frontRow, frontColumn);
    }
    
    /**
     * This turns our MazeRunner to the left in the same manner as the turnRight()
     * function.
     */
    public void turnLeft(){
        switchOrientation(-1);
        int frontRow = this.oneStepAhead.getRow();
        int frontColumn = this.oneStepAhead.getColumn();
        this.oneStepAhead.setPosition(this.leftHand.getRow(), this.leftHand.getColumn());
        this.leftHand.setPosition(this.oneStepBehind.getRow(), this.oneStepBehind.getColumn());
        this.oneStepBehind.setPosition(this.rightHand.getRow(), this.rightHand.getColumn());        
        this.rightHand.setPosition(frontRow, frontColumn);        
    }
}