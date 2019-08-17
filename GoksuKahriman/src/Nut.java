
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
public class Nut extends Entity {

    protected int Total_Nuts;
    protected int NutritionPoints;

    public Nut(char symbol, int NutritionPoints) {
        super(symbol);
        this.NutritionPoints = NutritionPoints;

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

}
