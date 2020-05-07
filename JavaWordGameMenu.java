import java.util.Scanner;

/**
 *
 * @author Lenovo
 */
public class JavaWordGameMenu {
        boolean exit;
     public void funcionalMenu(){
        printHeader();
            while(!exit){
                printMenu();
                int choice = getUserChoice();
                performAction(choice);
            }

    }
    // Header of the Game
    private void printHeader(){
        System.out.println("+---------------------------------------------------+");
        System.out.println("|                   Welcome to my                   |");
        System.out.println("|                   JAVA Word Game                  | ");
        System.out.println("|                                                   |");
        System.out.println("+---------------------------------------------------+");
    }

    // Menu Options
    private void printMenu() {
        System.out.println("\n Please select one of the options");
        System.out.println("1) Play Word Game for Java ");
        System.out.println("2) Search for a Word ");
        System.out.println("3) Check the Vocabulary ");
        System.out.println("4) Exit ");
    }

    // gets Input method and checks for errors
    private int getUserChoice(){
        Scanner input = new Scanner(System.in);
        int choice = -1;
        while(choice < 0 || choice > 4){
            if(choice < 0){
                System.out.println("Please choose a non negative number...");
            }else if(choice > 4){
                System.out.println("There is no other selection in these number...");
            }
            try{
                System.out.print("\n Choose an option...   ");
               choice = Integer.parseInt(input.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println("Invalid selection. Plese reconsider your choice, make another try...");
            }
        }
        return choice;
    }

    // Links the menu otions with classes and methods
    private void performAction(int choice){
        switch(choice){
            case 4:
                exit = true;
                System.out.println("rendering out...See you again soon!");
                break;
            case 3:
                new LimitedVocabulary().PrintArray();
                break;
            case 2:
                new LimitedVocabulary().SearchWordInArray();
                break;
            case 1:
                new JavaWordGames();
                break;
            default:
                System.out.println("A strange error happened, chek again your sellection");

        }
    }
}


