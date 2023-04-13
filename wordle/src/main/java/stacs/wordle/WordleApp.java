package stacs.wordle;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class WordleApp
{
    public static void main( String[] args ) throws FileNotFoundException
    {
        System.out.println("Welcome to CS5031 - Wordle");

        //invoke file read function.
        ArrayList<String> arrayOfWords = loadWordlist("src/main/resources/wordlist.txt");

        Integer IndexNumber = getRandomIndex(arrayOfWords.size());
        //invoke to generate a computer chosen word.
        String chosenWord = getChosenWord(IndexNumber, arrayOfWords);

        Boolean WIN = false;
        int chances = 1;
        gamePlay(WIN,chances,chosenWord); // start of game.

    }
    /**Function to allow user to guess 6 times or fewer until WIN/LOOSE.
     * @param WIN initially false at start of game
     * @param chances initially set to 1 by default
     * @param chosenWord stores the word chosen by computer
     */
    protected static void gamePlay(Boolean WIN, int chances, String chosenWord) {
        while (chances <= 6) {
            String userInput = getUserInput(chances);
            WIN = validateGuess(chosenWord, userInput);
            if (WIN) {
                System.out.println();
                System.out.println("WIN! the Correct Word is " + chosenWord + ", guesses " + chances);
                break;
            }
            chances++;
        }
        if (!WIN) {
            System.out.println();
            System.out.println("LOOSE! the Correct Word is " + chosenWord);
        }
    }

    /**Function to validate user guess and computer chosen words.
     * @param chosenWord stores the word chosen by computer
     * @param userInput stores the word guessed by player
     * @return true if user guesses is same as chosen word else false
     */
    protected static Boolean validateGuess(String chosenWord, String userInput) {
        
        String selectedWord = chosenWord.toUpperCase();
        String guessedWord = userInput.toUpperCase();
        String[] selectedWordArray = splitIntoArray(selectedWord);
        String[] guessedWordArray = guessedWord.split("");
        String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
        String ANSI_GREEN_BACKGROUND = "\u001B[42m";
        String ANSI_GREY_BACKGROUND = "\u001B[90m";
        String ANSI_RESET = "\u001B[0m";
        Boolean validation = false;

        int lengthSelectedWordArray = selectedWordArray.length;
        int lengthGuessedWordArray = guessedWordArray.length;

        if (selectedWord.equals(guessedWord)) {
            validation = true; //Setting vallidation to true for WIN
            for (int index = 0; index < lengthSelectedWordArray; index++) {
                System.out.print(ANSI_GREEN_BACKGROUND + guessedWordArray[index] + ANSI_RESET);
                System.out.print(" ");

            }
        } else if (lengthSelectedWordArray == lengthGuessedWordArray) {
            for (int index = 0; index < lengthSelectedWordArray; index++) {
                if (selectedWordArray[index].equals(guessedWordArray[index])) {
                    System.out.print(ANSI_GREEN_BACKGROUND + guessedWordArray[index] + ANSI_RESET);
                    System.out.print(" ");

                } else {
                    if (selectedWord.contains(guessedWordArray[index])) {
                        System.out.print(ANSI_YELLOW_BACKGROUND + guessedWordArray[index] + ANSI_RESET);
                        System.out.print(" ");

                    } else {
                        System.out.print(ANSI_GREY_BACKGROUND + guessedWordArray[index] + ANSI_RESET);
                        System.out.print(" ");

                    }
                }

            }
        }
        if(!validation){
            System.out.println("Worng!");
        }
        return validation;
    }

    /**Function to get User input.
     * @param chances stores the number of guesses 
     * @return player input 
     */
    protected static String getUserInput(int chances) {

        int chancesLeft = ((6-chances)+1);
        System.out.println();
        System.out.println();
        System.out.println("Guess 5 characters Word. "+chancesLeft+" chances left");
    
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        if (userInput.length() != 5) {
            System.out.println();
            System.out.print("Error: Input must be 5 characters long.");
        }

        return userInput;
    }

    /**Function to generate computer chosen word.
     * @param randomIndex stores random index generated
     * @param arrayOfWords the array in which a word is chosen to play
     * @return selected word
     */
    protected static String getChosenWord(Integer chosenIndex, ArrayList<String> arrayOfWords) {

        String chosenWord = arrayOfWords.get(chosenIndex);
        return chosenWord;

    }

    /**Function to split String to Array of letters.
     * @param word the word to be split
     * @return array of string of letters
     */
    protected static String[] splitIntoArray(String word) {
        String[] arrayOfLetters;
        arrayOfLetters = word.split("");
        return arrayOfLetters;
    }
    
    /**Function to generate random Index.
     * @param arrayLength the maximum index
     * @return random index number within the array length
     */
    protected static Integer getRandomIndex(Integer arrayLength) {
        int randomIndex = (int) (Math.random() * arrayLength);
        return randomIndex;
    }

    /** Function to read File
     * @param wordlistPath stores path of file to read
     * @return Array list of words 
     * @throws FileNotFoundException when file not found
     */
    protected static ArrayList<String> loadWordlist(String wordlistPath) throws FileNotFoundException {
        
        ArrayList<String> wordsArray = new ArrayList<String>();

        try {
            FileReader file = new FileReader(wordlistPath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                wordsArray.add(line);
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error Reading File");
            System.out.println(e);
        }

        return wordsArray;
    }
}
