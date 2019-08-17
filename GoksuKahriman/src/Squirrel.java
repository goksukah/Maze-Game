import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Goksu
 */
public class Squirrel extends Entity implements Movable {

    protected int pointsCollected = 0;
    protected int totalNutsEaten = 0;
    protected int cashewsEaten = 0;
    private Maze maze;

    public Squirrel() {
        super('@');
        pointsCollected = 0; //while generating the squirrel, points/nuts valuea are zero since it`s the beginning of the game
        totalNutsEaten = 0;
        cashewsEaten = 0;
    }

    @Override
    public void create(String location) {

        int row_s, col_s;
        do {
            row_s = Integer.parseInt(location.split(",")[0]);
            col_s = Integer.parseInt(location.split(",")[1]);
        } while (!Maze.available(row_s, col_s));
        put(row_s, col_s);
    }

    public boolean check(String location) {
        if (!location.matches("(([0-1])([0-9])|([2])([0-0])|([0-9])),(([0-4])([0-9])|([0-5])([0-0])|([0-9]))")) {   //checks for the exact format specified <8,20>
            System.out.println("Invalid format!Please try again!");
            return false;

        }
        return true;
    }

    @Override
    public void move(char direction) {

        int prev_row = getRow();
        int prev_column = getColumn();
        direction = String.valueOf(direction).toUpperCase().charAt(0);
        switch (direction) {

            case 'W':  //for up

                if (maze.available(this.row - 1, this.column)) {
                    this.nutsDetected(this.row - 1, this.column);
                    prev_row = this.row;
                    maze.getMaze()[prev_row][this.column] = new Blank();
                    this.row = this.row - 1;
                    put(row, column);
                    maze.display();
                } else {
                    System.out.println("You`ve hit a wall! Please try again.");
                }
                break;
            case 'S': //for down

                if (maze.available(this.row + 1, this.column)) {
                    this.nutsDetected(this.row + 1, this.column);
                    prev_row = this.row;
                    maze.getMaze()[prev_row][this.column] = new Blank();
                    this.row = this.row + 1;
                    put(row, column);
                    maze.display();
                } else {
                    System.out.println("Ouch! That's a wall. Try again.");
                }
                break;
            case 'A': //for left

                if (maze.available(this.row, this.column - 1)) {
                    this.nutsDetected(this.row, this.column - 1);
                    prev_column = this.column;
                    maze.getMaze()[this.row][prev_column] = new Blank();
                    this.column = this.column - 1;
                    put(row, column);
                    maze.display();
                } else {
                    System.out.println("Ouch! That's a wall. Try again.");
                }
                break;
            case 'D': //for right

                if (maze.available(this.row, this.column + 1)) {
                    this.nutsDetected(this.row, this.column + 1);
                    prev_column = this.column;
                    maze.getMaze()[this.row][prev_column] = new Blank();
                    this.column = this.column + 1;
                    put(row, column);
                    maze.display();
                } else {
                    System.out.println("Ouch! That's a wall. Try again.");
                }
                break;

            case 'Q':
                System.out.println("Oh no! You quit the game!");
                System.exit(0);
        } 

    }

    public void nutsDetected(int row, int col) {

        Entity nut;

        nut = maze.getMaze()[row][col];

        if (nut instanceof Almond) {
            pointsCollected += ((Almond) nut).NutritionPoints;
            totalNutsEaten++;
            System.out.println("Squirrel found an almond and gained 5 points! Total points: " + pointsCollected);

        } else if (nut instanceof Peanut) {
            pointsCollected += ((Peanut) nut).NutritionPoints;
            totalNutsEaten++;
            System.out.println("Squirrel found a peanut and gained 10 points! Total points: " + pointsCollected);

        } else if (nut instanceof PoisonousCashew) {
            pointsCollected += ((PoisonousCashew) nut).NutritionPoints;      //saddding the value in the calculation since nutrition points of cashew is -15 (negative)
            cashewsEaten++;
            System.out.println("Squirrel ate a poisonous cashew and lost 15 points. Total points: " + pointsCollected);
        }

    }

    public int nutsCollected() {
        return totalNutsEaten;
    }

    public int pointsCollected() {
        return pointsCollected;
    }

}
