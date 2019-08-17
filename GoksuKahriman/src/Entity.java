/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Goksu
 */
public abstract class Entity {

    protected int row;
    protected int column;
    protected char symbol;
    protected Entity entity;

    public Entity(char symbol) {
        this.symbol = symbol;

    }

    public char getSymbol() {
        return symbol;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public abstract void create(String filename);

    public Entity put(int newRow, int newCol) { //putting an entity to the new location

        Entity oldLocation = Maze.getMaze()[newRow][newCol];
        Maze.getMaze()[newRow][newCol] = this;
        this.row = newRow;
        this.column = newCol;
        return oldLocation; 

    }

    @Override
    public String toString() {              //returns the symbol of what`s in the maze(entities)
        return symbol + "";
    }

}
