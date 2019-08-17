/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Goksu
 */
public class Maze {

    private static final int Max_Maze_Row = 20;
    private static final int Max_Maze_Column = 50;
    private static Entity maze[][];
    private static char symbol;

    public Maze() {
        maze = new Entity[Max_Maze_Row][Max_Maze_Column];

    }

    public static int getMax_Maze_Row() {     //For nuts class; create() method
        return Max_Maze_Row;
    }

    public static int getMax_Maze_Column() {
        return Max_Maze_Column;
    }

    public static char getSymbol() {
        return symbol;
    }

    public static void create(String filename) {

        ArrayList<String> mazeList = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new File(filename));

            while (sc.hasNext()) {

                for (int i = 0; i < Max_Maze_Row; i++) {
                    mazeList.add(sc.nextLine());
                    for (int j = 0; j < Max_Maze_Column; j++) {

                        char temp = mazeList.get(i).charAt(j);

                        if (temp == '*') {
                            maze[i][j] = new Wall();
                        } else {
                            maze[i][j] = new Blank();
                        }
                    }
                }
            }

            for (int i = 0; i < mazeList.size(); i++) {
                System.out.println(mazeList.get(i));
            }
            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("Maze File does not exist.");
        }
    }

    public static void display() {

        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 50; col++) {
                System.out.print(maze[row][col]);
            }

            System.out.println();
        }

    }

    public static void findAndChange() {    //detects and removes all cashews so that they can be re-generated in the main in different random positions

        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 50; col++) {
                if (maze[row][col].symbol == 'C') {
                    maze[row][col] = new Blank();

                }
            }

        }

    }

    public static boolean available(int row, int col) {
        boolean isBlank = true;
        if (maze[row][col] != null && maze[row][col].getSymbol() == '*') {  //when there is a wall available method returns false; otherwise true
            isBlank = false;
        }

        return isBlank;

    }

    public static boolean cashewAvailable(int row, int col) {
        boolean isBlank = true;
        if (maze[row][col] != null && maze[row][col].getSymbol() == '*') {   //when there is a wall available method returns false; otherwise true
            isBlank = false;
        }
        //also checking nuts and the squirrel if the position cashew will move is available
        if (maze[row][col].getSymbol() == 'A' || maze[row][col].getSymbol() == 'P' || maze[row][col].getSymbol() == 'C' || maze[row][col].getSymbol() == '@') {
            isBlank = false;
        }

        return isBlank;

    }

    public static Entity[][] getMaze() {
        return maze;
    }

}
