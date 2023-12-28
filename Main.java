package lastpencil;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Main {

    public static int botMove(int stickStack){
        Random randomizer = new Random();
        int random = randomizer.nextInt(3)+1;
        String newStack = "";
        int rest = stickStack % 4;
        int reduction = 0;
        int newNumber;


        if(stickStack == 1){
            System.out.println(1);
            return stickStack - 1;
        }

        switch(rest){
            case 1: reduction = random; break;
            case 0: reduction = 3; break;
            case 2: reduction = 1; break;
            case 3: reduction = 2; break;
        }

        System.out.println(reduction);

        newNumber = stickStack-reduction;

        for(int i = 0; newNumber > i; i++){
            newStack += "|";
        }
        System.out.println(newStack);
        return newNumber;
    }

    public static String createOpponent(String whoNext){
        // give name to opponent
        String opponent = "";
        if(whoNext.equals("Mischa")){
            opponent = "Till";
        } else {
            opponent = "Mischa";
        }
        return opponent;
    }
    public static void firstSticks(int howMany){
        // Create initial sticks
        while(howMany > 0){
            System.out.print("|");
            howMany--;
        }
            System.out.println("");
    }
    public static int stickCreation(int sticks){
        Scanner scan = new Scanner(System.in);
        // wie viele sticks werden von dem stapel gezogen?

            //declare
        int reduce;
            //check if number
        while(!scan.hasNextInt()){
            //error message
            System.out.println("Possible values: '1', '2' or '3'");
            //catch wrong input
            scan.next();
        }
            //initiate
        reduce = scan.nextInt();
            //check other conditions
        while(reduce < 1 || reduce > 3){
            System.out.println("Possible values: '1', '2' or '3'");

            while(!scan.hasNextInt()){
                //error message
                System.out.println("Possible values: '1', '2' or '3'");
                //catch wrong input
                scan.next();
            }

            reduce = scan.nextInt();
        }

        while(sticks-reduce < 0){
            System.out.println("Too many pencils were taken");
            reduce = scan.nextInt();
        }

        //clear nowpens
        String nowPens = "";


        // calculation of NEW STICK AMOUNT
        sticks -= reduce;

        // print die remaining sticks
        for(int i = 0; sticks > i; i++){
            nowPens += "|";
        }

        System.out.println(nowPens);
        return sticks;
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        // START:
        System.out.println("How many pencils would you like to use:");
        int howMany;

        while (true) {
            // Read the entire line of input as a string
            String input = scan.nextLine().trim();

            // Check if the input is not empty
            if (!input.isEmpty()) {
                try {
                    // Attempt to parse the input as an integer
                    howMany = Integer.parseInt(input);

                    // Check if the parsed integer is positive
                    if (howMany > 0) {
                        // Break the loop if conditions are satisfied
                        break;
                    } else {
                        // Display an error message if the integer is not positive
                        System.out.println("The number of pencils should be positive");
                    }
                } catch (NumberFormatException e) {
                    // Catch an exception if parsing the input as an integer fails
                    System.out.println("The number of pencils should be numeric");
                }
            } else {
                // Display an error message if the input is empty
                System.out.println("The number of pencils should be numeric");
            }
        }

/*
 while(!scan.hasNextInt()){
            System.out.println("The number of pencils should be numeric");
            // consume wrong input
            scan.next();
        }

        // initialisation here
        howMany = scan.nextInt();

        while(howMany == 0){
            System.out.println("The number of pencils should be positive");

            while(!scan.hasNextInt()){
                System.out.println("The number of pencils should be numeric");
                // consume wrong input
                scan.next();
            }

            howMany = scan.nextInt();
        }

        while(howMany < 0)
        {
            System.out.println("The number of pencils should be numeric");
            howMany = scan.nextInt();
        }

 */

        System.out.println("Who will be the first (Mischa, Till)");
        String whoNext = scan.next();

        while(!whoNext.equals("Mischa") && !whoNext.equals("Till")){
            System.out.println("Choose between 'Mischa' and 'Till'");
            whoNext = scan.next();
        }
        String opponent = createOpponent(whoNext);

        int sticks = howMany;

        firstSticks(howMany);

        // reduce sticks until 0
        while(sticks > 0){

            System.out.println(whoNext + "'s turn!");
            if(whoNext.equals("Till")){
                sticks = botMove(sticks);
            } else {
                sticks = stickCreation(sticks);
            }

            if(sticks == 0){
                System.out.print(opponent + " won!");
                break;
            }

            System.out.println(opponent + "'s turn!");
            if(opponent.equals("Till")){
                sticks = botMove(sticks);
            } else {
                sticks = stickCreation(sticks);
            }

            if(sticks == 0){
                System.out.print(whoNext + " won!");
                break;


            }
        }

    }
}