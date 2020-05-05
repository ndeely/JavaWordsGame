import java.util.Scanner;

public class WordsGame {
	private int lives = 5; //set lives per game
	private int p1Lives, p2Lives; //player 1 and 2 lives
	private int p1Points, p2Points; //player 1 and 2 points
	private int gameCount; //games to be played
	Scanner sc = new Scanner(System.in);
	private int roundWinner = (int)(Math.random() * 2 + 1); //pick random first round winner to start

	public WordsGame() {
		showIntro();
		readGameCountInt(); //set gameCount
		while(this.gameCount > 0) {
			playGame(this.lives);
			gameCount -= 1;
		}
	}

	//display number of lives, inform players about points
	public void showIntro() {
		String introString = "===   Welcome to    ===\n=== JAVA WORDS GAME ===\n";
		introString += "Each player will receive " + lives + " lives.\n";
		introString += "If you choose a valid word, you will receive the same amount of points as the number of vowels in the word.\n";
		System.out.println(introString);
	}

	public void readGameCountInt() {
		boolean iError = true; //input error
		while(iError) {
			System.out.println("How many games would you like to play?");
			if(sc.hasNextInt()) {
				this.gameCount = sc.nextInt();
				if(this.gameCount >= 0) {
					iError = false;
				} else if(this.gameCount < 0) {
					System.out.println("Invalid number, please try again.");
				} else {
					System.out.println("Congratulations! You have successfully played 0 games!");
					gameEnd();
					iError = false;
				}
			} else {
				System.out.println("That's not a number.");
				sc.reset();
				sc.next();
			}
		}
	}

	public void playGame(int lives) {
		this.p1Lives = this.p2Lives = lives; //sets lives for game
		this.p1Points = this.p2Points = 0; //starts players with 0 points
		while(this.p1Lives != 0 & this.p2Lives != 0) {
			playRound();
		}
		gameEnd();
	}

	//pick random letter
	//first player picks word beginning with letter
	//second player picks word beginning with last two letters of previous word
	//repeat
	//ends with -
	//round winner starts next round
	public void playRound() {
		int currentPlayer = this.roundWinner;
		int randNo = (int)(Math.random() * 26);
		String startWith = "abcdefghijklmnopqrstuvwxyz".substring(randNo, randNo + 1);
		boolean playing = true;
		while(playing) {
			System.out.println("Player " + currentPlayer + "'s turn. Please enter a word beginning with '" + startWith + "':");
			String currentWord = this.sc.next();
			//if word is valid, assign points, switch startsWith and currentPlayer
			if(new LimitedVocabulary().isValidWord(currentWord)) {
				countVowels(currentPlayer, currentWord);
				startWith = currentWord.substring(currentWord.length() - 2);
				currentPlayer = (currentPlayer == 1) ? 2 : 1;
			} else if (currentWord.equals("-")) {
				if(currentPlayer == 1) {
					this.p1Lives -= 1;
				} else {
					this.p2Lives -= 1;
				}
				System.out.println("Player " + currentPlayer + " lost a life!");
				this.roundWinner = (currentPlayer == 1) ? 2 : 1; //set round winner as other player
				System.out.println("Current Lives:\nPlayer 1: " + this.p1Lives + "\nPlayer 2: " + this.p2Lives);
				playing = false;
			} else {
				System.out.println("Invalid word.");
			}
		}
	}

	//counts vowels in words, and assigns appropriate player points
	public void countVowels(int player, String word) {
		String[] vowels = "aeiou".split("");
		String[] letters = word.split("");
		int points = 0;
		for(String letter:letters) {
			for(String vowel:vowels) {
				if(letter.equals(vowel)) {
					points += 1;
				}
			}
		}
		if(player == 1) {
			this.p1Points += points;
		} else {
			this.p2Points += points;
		}
		System.out.println("Player " + player + " has gained " + points + " points with the word " + word + "!");
	}

	public void gameEnd() {
		String winner = (p1Lives == 0) ? (p2Lives == 0) ? "no one" : "Player 2" : "Player 1"; //decide the winner
		String gameString = "The winner is " + winner + "!\n";
		gameString += "Player 1 accumulated " + this.p1Points + " points and Player 2 accumulated " + this.p2Points + " points.";
		System.out.println(gameString);
	}

}
