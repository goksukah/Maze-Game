
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Goksu
 */
public class HungrySquirrelGame {

    public static void main(String[] args) {
        Maze maze = new Maze();
        System.out.println("Welome to Alvin: The Hungry Squirrel Game!\nGame Rules\n1)Collect nuts (A,P) to get points.\n2)Avoid poisonous cashews (C), they cause negative points.\n3)Cashews are also moving in every time squirrel moves.\n");
        maze.create("Maze.txt");

        System.out.println("Let`s start with placing Alvin, the squirrel!\n"
                + "Enter <row>,<column> : ie. 8,20 \n>");

        String input_pos;
        Scanner user_in = new Scanner(System.in);
        input_pos = user_in.nextLine();
        Squirrel squirrel = new Squirrel();
        while (!squirrel.check(input_pos)) {
            System.out.println("Enter <row>,<column> : ie. 8,20 ");
            input_pos = user_in.nextLine();
        }
        while (!maze.available(Integer.parseInt(input_pos.split(",")[0]), Integer.parseInt(input_pos.split(",")[1]))) {
            System.out.println("Location unavailable! Please enter the <row> and <column> as separated by a comma, ie. 8,20 :\n> ");
            while (!squirrel.check(input_pos)) {
                System.out.println("Enter <row>,<column> : ie. 8,20 \n>");
                input_pos = user_in.nextLine();
            }
            input_pos = user_in.nextLine();
        }
        squirrel.create(input_pos);

        Nut[] nuts = new Nut[5];                                //creating Nuts
        PoisonousCashew[] cashews = new PoisonousCashew[5];     //creating poisonous cashews (differentiating them from Nuts to exclude from totalNumberOfNuts

        for (int i = 0; i < 5; i++) {
            Random random = new Random();
            if (random.nextBoolean()) {
                nuts[i] = new Almond();
            } else {
                nuts[i] = new Peanut();

            }
            cashews[i] = new PoisonousCashew();
            nuts[i].create("Maze.txt");                 //"Maze.txt" is not used; create method is a method of Entity parent class and it has String filename as the method argument
            cashews[i].create("Maze.txt");              //So, the sent String argument will not be used in the method; just sending it to match the parent class`s structure
        }

        maze.display();
        System.out.println("To move the squirrel use W,A,S,D keys: W > up, A > left, S > down, D > right\nor press Q to exit the game.\nPress Enter to perform key actions."); 

        char direction;

        int poisonCounter = 0, tempPoisonCounter = 0; 
        
        do {
            if(tempPoisonCounter < squirrel.cashewsEaten){  //Every time a cashew is eaten all cashews will be replaced with new cashews to display like they have moved
                maze.findAndChange();
                for (int i = 0; i < 5 - squirrel.cashewsEaten; i++) {
                    cashews[i] = new PoisonousCashew();
                    cashews[i].create("Maze.txt");
                }
                tempPoisonCounter = squirrel.cashewsEaten;   //Equalizing the two variables so the if condition will be executed only when # of cashewsEaten increases.
            }
            direction = user_in.next().charAt(0);
            poisonCounter++;
            squirrel.move(direction);

            if (poisonCounter == 5) {                       //Cashews will be randomly moved in every 5 moves performed by the user.5 is the number I chose randomly which I thought would the best by playing the game.
                for (int a = 0; a < (cashews.length - (squirrel.cashewsEaten)); a++) {
                    cashews[a].move(' ');                  //move method for cashews are overridden in PoisonousCashews.java; sent argument is in no usage.      
                }                                          //IMPORTANT NOTICE: Since cashews move every 5 moves; they can disappear and move to another place in the moment you thought you were going to eat them
                poisonCounter = 0;
            }

            if (squirrel.nutsCollected() == 5) {
                System.out.println("\nYou`ve won!!! Squirrel successfully collected all the nuts. Total points: " + squirrel.pointsCollected());
                Maze.display();
            } else if (squirrel.pointsCollected() < 0) {
                System.out.println("Alvin, the squirrel died. Game over!");
                break;
            }
        } while (direction != 'Q' && squirrel.nutsCollected() != 5 && squirrel.pointsCollected() >= 0);

        //Program ends
        System.out.println("\nThank you for playing this game!");
    }

}
