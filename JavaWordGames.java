import java.util.Random;  //imports random
import java.util.Scanner;  //imports scanner


/**
 *
 * @author Lenovo
 */
public class JavaWordGames {
    final private int stamina = 3; //constant stamina(life) at the start of the game
    private int StaminaPlayer1, StaminaPlayer2; //player 1 and 2 stamina
    private int PointsforPlayer1, PointsforPlayer2; //player 1 and 2 Points
    private int LoopGame; //as task I had to ask firstly the user how many games he wants to play , the variable stores the answer of the user
    Scanner input = new Scanner(System.in); //declaring Scanner
    private int firstLetter = (int)(Math.random() * 2 + 1); //random player will start playing
    private String actualplayer = getPlayerName(firstLetter); // gets the name of the player and correlate it to the first and the second player, depending on who´s turn is in the game
    private String Player1Name; // variable the sores the name of the first player
    private String Player2Name;  // variable the sores the name of the first player



    // constructor that starts the game, takes also the input of the player, calls the big loop that contain the rest of the methods

public JavaWordGames() {
       displayBeginning();

    System.out.println("----------------------------------");
    System.out.println("Player 1: Enter your name here: ");
    System.out.println("----------------------------------");
    Player1Name = input.next();
    System.out.println("----------------------------------");
    System.out.println("Player 2: Enter your name here: ");
    System.out.println("----------------------------------");
    Player2Name = input.next();

    GeneralLoop(); //set LoopGame
    while(this.LoopGame > 0) {
            InitialSettings(this.stamina);
            LoopGame -= 1;
    }
}
    //display number of lives, inform players about Points
    public void displayBeginning() {
        System.out.println("------------------------------------------------------------------");
        String introString = "          *************  JAVA WORDS GAME  ************* \n";
        introString += ("------------------------------------------------------------------\n");
        introString += ("\n");
        introString += "Each player will receive " + stamina + " stamina(lives).\n";
        introString += "The game of words, start your word with the LAST TWO LETTERS from the last word \n";
        introString += "The player receives the same amount of\n" +"points as the number of characters in the\n" + "word when the word contains no vowels;\n" +"otherwise 1 point.\n";
        System.out.println(introString);
    }

    String possible_winner;
    //get the inputed name in the Player names variable
public String getPlayerName(int firstLetter) {
        if (firstLetter==1){
        possible_winner = Player1Name;
        } else {
        possible_winner = Player2Name;
    }
    return possible_winner;
}

//get the inputed name in the Player names variable
public void GeneralLoop() { //create general Loop
    boolean Error = true; //input error
    while(Error) {
        //Play multiple games approach in my case ask before the start of the game how many games one must play
        System.out.println("----------------------------------");
        System.out.println("How many games would you like to play?");
        System.out.println("----------------------------------");
        if(input.hasNextInt()) {
            this.LoopGame = input.nextInt();
            if(this.LoopGame >= 0) {
                    Error = false;
            } else if(this.LoopGame < 0) {
                System.out.println("----------------------------------");
                System.out.println("The number has to be bigger than 0.");
                System.out.println("----------------------------------");
                Error = true;
            }
        } else {
            System.out.println("----------------------------------");
            System.out.println("Please make a numerical input.");
            System.out.println("----------------------------------");
            Error = true;
            input.reset();
            input.next();
        }
        //Games remaining with singular/plural distiction
        if(this.LoopGame >= 0){
            if(this.LoopGame == 1){
        System.out.println("----------------------------------");
        System.out.println("We are going to play only " + this.LoopGame + " game  ");
        System.out.println("----------------------------------");
            }else{
        System.out.println("----------------------------------");
        System.out.println("The game has be repeated for " + this.LoopGame + " Games ..." );
        System.out.println("----------------------------------");
            }
        }
    }
}
    // Initial conditions at the beggining of each game
public void InitialSettings(int stamina) {
    this.StaminaPlayer1 = this.StaminaPlayer2 = stamina; // gives 5 stamina at the beigging of game to all players
    this.PointsforPlayer1 = this.PointsforPlayer2 = 0; //0 Points for each player
    while(this.StaminaPlayer1 != 0 && this.StaminaPlayer2 != 0) {  //the game will play while the stamina doesn´t reach 0
            GameCore();
    }
    FinishGame();
}

/*multiple functions - into a loop, drop a random letter from the alphabet,
chain the next word by using the last two letters from the last one, modify players stamina for each player
*/
public void GameCore() {
    actualplayer = getPlayerName(this.firstLetter);
    int PresentPlayer = this.firstLetter;
        // random letter
        Random rnd = new Random();
        char BeginningLetter = (char) (rnd.nextInt(26) + 'a');
        String FirstLetter=String.valueOf(BeginningLetter);
        //repeating loop
        boolean playing = true;
        while(playing) {
            System.out.println("----------------------------------");
            System.out.println("Player " + PresentPlayer +  " time to play.  Your next word has to begin with '" + FirstLetter + "':");
            System.out.println("----------------------------------");
            String TheWord = this.input.next();
            //Condition that word is found into given file, assigns the word to one player, take cares of players stamina with multiple selection statements
            if(new LimitedVocabulary().WordInDictionary(TheWord)) {
                //Check if the next word respects last two letters or first letter rule
                String startOfWord = TheWord.substring(0, FirstLetter.length());
                        if(!startOfWord.equalsIgnoreCase(FirstLetter)) {
                                System.out.println("----------------------------------");
                                System.out.println("The word is not correct, according to the word chaining rules above, please check the rules and try another word!");
                                System.out.println("----------------------------------");
                        }else{
                //declaring last two letters rule
                FirstLetter = TheWord.substring(TheWord.length() - 2);
                //declaring special rule by which one achieve points
                RuleToAwardPointsPenultimateNo9(PresentPlayer, TheWord);
                        if(PresentPlayer == 1) {
                            PresentPlayer = 2;
                        }else{
                            PresentPlayer = 1;
                        }
                //if no word found the player will loose a stamina
                }
            } else if (TheWord.equals("-")) {
                    if(PresentPlayer == 1) {
                        this.StaminaPlayer1 -= 1;
                    } else {
                    this.StaminaPlayer2 -= 1;
                }
                    //print the lost of a stamina for a player
                    System.out.println("----------------------------------");
                    System.out.println(PresentPlayer + " lost one stamina!");
                    System.out.println("----------------------------------");
                if(PresentPlayer == 1) {
                    firstLetter = 2;
                } else {
                    firstLetter = 1;
                } //print intermediary stamina remaining for each player
                    System.out.println("----------------------------------");
                    System.out.println("Remaining Stamina:\n " + Player1Name + " : " + this.StaminaPlayer1 + " \n " + Player2Name + " : " + this.StaminaPlayer2);
                    System.out.println("----------------------------------");
                    playing = false;
            //if the has less than 3 characters display an error
            } else if (TheWord.length() < 3){
                System.out.println("----------------------------------");
                System.out.println(TheWord + " is not a valid three character word");
                System.out.println("----------------------------------");
             //the only way not to lose stamina is because the word was not found in the dicctionary, or maybe wrong input
            } else{
                System.out.println("----------------------------------");
                System.out.println("The word is not found in the dictionary, Thing about something else!");
                System.out.println("----------------------------------");
            }
        }
    }
/*counts vowels in words, and assigns appropriate player Points,
if vowel present in word, one point per word and player,
if no vowel, 1 point per consonant and player*/
public void RuleToAwardPointsPenultimateNo9(int player, String TheWord) {
  int Points = 0;//points per consonts
  int consonants = 0;//consonants counted
  int Vowels = 0;//vowel counted
  final int VowelPoint = 1;// constant 1 point per word with vowel, almost all
  // counting loop for characters, distinguishing among vowels and consonants from the inputed word
  for (int i=0; i<TheWord.length(); i++){
    char ch = TheWord.charAt(i);
    if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ){
        Vowels ++;
    }else if(ch != ' '){
       consonants++;
       Points = consonants;
    }
}
//points display by each word
if(player == 1) {
    if(Vowels > 0)
        {
            System.out.println("-----------------------------------------------------------------");
            System.out.println("Player " + player + " has achieved " + VowelPoint + " point with the word " + TheWord + "!");
            System.out.println("------------------------------------------------------------------");
        }else{
            System.out.println("Player " + player + " has achieved " + Points + " points with the word " + TheWord + "!");
            System.out.println("-----------------------------------------------------------------");
        }
}else{
    if(Vowels > 0)
        {
            System.out.println("-----------------------------------------------------------------");
            System.out.println("Player " + player + " has scored " + VowelPoint + " point with the word " + TheWord + "!");
            System.out.println("-----------------------------------------------------------------");

        }else{
            System.out.println("Player " + player + " has scored " + Points + " points with the word " + TheWord + "!");
            System.out.println("-----------------------------------------------------------------");

        }
    }
//points sharing at the end of the game
    if(player == 1) {
        if(Vowels >0){
            this.PointsforPlayer1 += VowelPoint ;
            }else{
            this.PointsforPlayer1 += Points;
            }
    } else {
        if(Vowels >0){
            this.PointsforPlayer2 += VowelPoint ;
            }else{
            this.PointsforPlayer2 += Points;
            }
    }
}
//finish the game and prints the winner and Points gathered by each player
public void FinishGame() {
    //the winner is the one who has more stamina, once one of the players reached 0 stamina
    if(StaminaPlayer1== 0) {
        System.out.println("WINNER of the game : " + Player2Name);
        System.out.println("   ***     * :) *    ***       ");
        //Games remaining with singular/plural distiction
        this.LoopGame --;
        if(this.LoopGame == 1){
            System.out.println("-----------------------------------------------------------------");
            System.out.println(" Only " + this.LoopGame + " games left ");
            System.out.println("-----------------------------------------------------------------");
        }else{
            System.out.println("-----------------------------------------------------------------");
            System.out.println("We will play for " + this.LoopGame + " games more ");
            System.out.println("-----------------------------------------------------------------");
        }

    }else if(StaminaPlayer2 == 0){
        System.out.println("WINNER of the game : " + Player1Name);
        System.out.println("   ***     * :) *    ***       ");
    }

    //print total points acumulated during the game
    System.out.println("###   Points summary   ###" );
    System.out.println(Player1Name + ": has gathered " + this.PointsforPlayer1 + " total points . " );
    System.out.println(Player2Name + ": has gathered " + this.PointsforPlayer2 + " total points . " );

    }
}





