
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Goksu
 */
public class PoisonousCashew extends Nut implements Movable {

    private Maze maze;

    public PoisonousCashew() {
        super('C', -15);        

    }

    @Override
    public void create(String filename) {

        int col, row;
        Random random = new Random();

        do {
            row = random.nextInt(19);
            col = random.nextInt(49);
        } while (!Maze.available(row, col));

        put(row, col);
    }

    @Override
    public void move(char direction) {      //no usage of the char (it will be randomly selected); overriding the method in the Moveable interface, will send ' ' from main

        int prev_row = getRow();
        int prev_column = getColumn();

        for (int i = 0; i < 5; i++) {
            Random random = new Random();
            int randomMove = random.nextInt(4 - 1 + 1) + 1;
            switch (randomMove) {
                case 1:
                    //if random number is 1 it goes upwards
                    if (maze.cashewAvailable(this.row - 1, this.column)) {
                        prev_row = this.row;
                        maze.getMaze()[prev_row][this.column] = new Blank();
                        this.row = this.row - 1;
                        put(row, column);
                    } else {
                        ;  //do nothing

                    }
                    break;
                case 2:
                    //if random number is 2 it goes downwards
                    if (maze.cashewAvailable(this.row + 1, this.column)) {
                        prev_row = this.row;
                        maze.getMaze()[prev_row][this.column] = new Blank();
                        this.row = this.row + 1;
                        put(row, column);
                    } else {
                        ;  //do nothing

                    }
                    break;
                case 3:
                    //if random number is 3 it goes to left
                    if (maze.cashewAvailable(this.row, this.column - 1)) {
                        prev_column = this.column;
                        maze.getMaze()[this.row][prev_column] = new Blank();
                        this.column = this.column - 1;
                        put(row, column);
                    } else {
                        ; //do nothing

                    }
                    break;
                default:
                    //if random number is smt else it goes to left
                    if (maze.cashewAvailable(this.row, this.column + 1)) {
                        prev_column = this.column;
                        maze.getMaze()[this.row][prev_column] = new Blank();
                        this.column = this.column + 1;
                        put(row, column);

                    } else {
                        ; //do nothing

                    }
                    break;
            }
        }
    }

}
