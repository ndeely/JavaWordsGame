import java.util.Scanner;

public class WordsGame {
	private int lives = 5; //set lives per game
	private int p1Lives, p2Lives; //player 1 and 2 lives
	private int p1Points, p2Points; //player 1 and 2 points
	private int gameCount; //games to be played
	private Scanner sc = new Scanner(System.in);
	private int roundWinner = (int)(Math.random() * 2 + 1); //pick random first round winner to start

	private String[] extraWords = new String[10]; //array for extra valid words to be added
	private int extraWordCounter = 0; //counter for extraWords array

	//constructor
	public WordsGame() {
		showIntro();
		readGameCountInt(); //set number of games to be played
		int gameCounter = 0;
		while(gameCounter < this.gameCount) {
			System.out.println("\n=== GAME " + (gameCounter + 1) + " ===");
			playGame(this.lives);
			gameCounter++;
		}
	}

	//show introduction screen
	private void showIntro() {
		String introString = "===   Welcome to    ===\n=== JAVA WORDS GAME ===\n\n";
		introString += "=== GAME DESCRIPTION ===\n\n";
		introString += "The aim of the game is to avoid being the first player to lose all your lives.\n\n";
		introString += "The first player to take their turn in each round must choose a valid word\nstarting with the randomly chosen letter given.\n\n";
		introString += "The word chosen for each subsequent turn must begin with the last two letters\nof the previous player's valid word.\n";
		introString += "\nWords must:\n";
		introString += "- Be at least 3 letters long\n";
		introString += "- Contain no characters outside of the English alphabet\n";
		introString += "- Start with the letter/letters stated\n";
		introString += "- Be a valid word in the list OR be a valid word as agreed by both players\n";
		introString += "\nIf you cannot think of a valid word to meet the criteria, you can enter '-'\nand you will lose a life and end the round.\n\n";
		System.out.println(introString);
	}

	//shows lives and points rule
	private void showDescription() {
		String descString = "\n=== LIVES AND POINTS ===\n\n";
		descString += "Both players have received " + this.lives + " lives.\n\n";
		descString += "Points will be awarded for each vowel in each valid word you have chosen.\n";
		System.out.println(descString);
	}

	//get and set number of games to be played
	private void readGameCountInt() {
		boolean iError = true; //input error
		while(iError) {
			System.out.println("How many games would you like to play?");
			if(sc.hasNextInt()) {
				this.gameCount = sc.nextInt();
				if(this.gameCount > 0) {
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

	//plays a game
	private void playGame(int lives) {
		this.p1Lives = this.p2Lives = lives; //sets lives for game
		this.p1Points = this.p2Points = 0; //starts players with 0 points
		showDescription();
		//play rounds until a player runs out of lives
		int roundCounter = 1;
		while(this.p1Lives != 0 & this.p2Lives != 0) {
			System.out.println("\n=== ROUND " + roundCounter + " ===");
			playRound();
			roundCounter++;
		}
		gameEnd();
	}

	//plays a round
	private void playRound() {
		int currentPlayer = this.roundWinner;

		//pick random letter to start
		int randNo = (int)(Math.random() * 26);
		String startWith = "abcdefghijklmnopqrstuvwxyz".substring(randNo, randNo + 1);

		//create loop until round is complete
		boolean playing = true;
		while(playing) {
			System.out.println("\nPlayer " + currentPlayer + "'s turn.\nPlease enter a word beginning with '" + startWith + "':");
			String currentWord = this.sc.next();
			//deduct life if player enters '-'
			if (currentWord.equals("-")) {
				if(currentPlayer == 1) {
					this.p1Lives -= 1;
				} else {
					this.p2Lives -= 1;
				}
				System.out.println("\nPlayer " + currentPlayer + " lost a life!");
				this.roundWinner = (currentPlayer == 1) ? 2 : 1; //set round winner as other player
				System.out.println("Current Lives:\nPlayer 1: " + this.p1Lives + "\nPlayer 2: " + this.p2Lives);
				playing = false; //exits round loop
			} else if(currentWord.length() < 3) {
				System.out.println("'" + currentWord + "' is not long enough. It needs to be at least 3 letters long.");
			} else if (isProperWord(currentWord)) {
				//if word is valid, assign points, switch startWith and currentPlayer
				if((new LimitedVocabulary().isValidWord(currentWord) || isValidExtraWord(currentWord))) {
					//check that current word begins with correct letters
					if((currentWord.substring(0,startWith.length()).equals(startWith))) {
						countVowels(currentPlayer, currentWord);
						startWith = currentWord.substring(currentWord.length() - 2);
						currentPlayer = (currentPlayer == 1) ? 2 : 1; //set current player as other player
					} else {
						System.out.println("'" + currentWord + "' does not begin with '" + startWith + "'.");
					}

				} else {
					addExtraWord(currentWord);
				}
			}
		}
	}

	// Potentially adds words to a new list of valid words if both players agree
	private void addExtraWord(String word) {

		//create loop until both players agree ('y') or disagree ('n')
		boolean invalidResponse = true;
		while(invalidResponse) {
			System.out.println("Do both players agree that '" + word + "' is a valid word? (y/n)");
			String response = this.sc.next();
			response = response.substring(0,1).toLowerCase();
			if(response.equals("y")) {
				//increase array size by 10 if we have reached the storage limit
				if((this.extraWordCounter != 0) && (this.extraWordCounter % 10 == 0)) {
					String[] tempArray = new String[this.extraWords.length + 10];
					for(int i = 0; i < this.extraWordCounter; i++) {
						tempArray[i] = this.extraWords[i];
					}
					this.extraWords = tempArray;
				}
				//add the new word as a valid one
				this.extraWords[extraWordCounter] = word;
				this.extraWordCounter += 1;
				invalidResponse = false;
				System.out.println("'" + word + "' is now a valid word, please try it again.");
			} else if(response.equals("n")) {
				System.out.println("'" + word + "' is not a valid word. Please try another word, or enter '-' to give up.");
				invalidResponse = false;
			} else {
				System.out.println("Invalid response, please enter 'y' or 'n'");
			}
		}


	}

	//check extraWords array for specific word
	private boolean isValidExtraWord(String word) {
		for(int i = 0; i < extraWordCounter; i++) {
			if(word.equalsIgnoreCase(this.extraWords[i])) {
				return true;
			}
		}
		return false;
	}

	//check if word is a proper word (doesn't contain non-letter characters)
	private boolean isProperWord(String word) {
		String[] letters = "abcdefghijklmnopqrstuvwxyz".split("");
		String[] wordLetters = word.toLowerCase().split("");
		int validLetters = 0;
		//invalid if it contains spaces
		if(word.split(" ").length > 1) {
			System.out.println("The word cannot contain spaces.");
			return false;
		}
		//check each letter
		for(String letter:letters) {
			for(String wordLetter:wordLetters) {
				if(letter.equals(wordLetter)) {
					validLetters += 1;
				}
			}
		}
		if(validLetters == word.length()) {
			return true;
		} else {
			System.out.println(word + " contains invalid characters.");
			return false;
		}
	}

	//counts vowels in words, and assigns appropriate player points
	private void countVowels(int player, String word) {
		String[] vowels = "aeiou".split("");
		String[] letters = word.split("");
		int points = 0;
		for(String letter:letters) {
			for(String vowel:vowels) {
				//if any letter equals any vowel, add a point
				if(letter.equals(vowel)) {
					points += 1;
				}
			}
		}

		//assign points to relevant player
		if(player == 1) {
			this.p1Points += points;
		} else {
			this.p2Points += points;
		}
		String grammar = (points == 1) ? " point" : " points"; //check and fix grammar (eg '1 point', not '1 points')
		System.out.println("Player " + player + " has gained " + points + grammar + " with the word '" + word + "'!");
	}

	//display winner and points accumulated
	private void gameEnd() {
		String winner = (p1Lives == 0) ? (p2Lives == 0) ? "no one" : "Player 2" : "Player 1"; //decide the winner
		String gameString = "The winner is " + winner + "!\n";
		String grammar = (this.p1Points == 1) ? " point" : " points";
		String grammar2 = (this.p2Points == 1) ? " point" : " points";
		gameString += "Player 1 accumulated " + this.p1Points + grammar + " and Player 2 accumulated " + this.p2Points + grammar2 + ".";
		System.out.println(gameString);
	}

}