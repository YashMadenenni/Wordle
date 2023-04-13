package stacs.wordle;


import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class WordleAppTest
{
    /** Test case to test reading wordlist-test.txt file.
     * @throws FileNotFoundException if file not found at the path
     */
    @Test
    public void shouldLoadWordlist() throws FileNotFoundException
    {
       ArrayList<String> wordlist = WordleApp.loadWordlist("src/test/resources/wordlist-test.txt");

        // test wordlist only contains 3 words, so wordlist should have the size of 3
        assertEquals(3, wordlist.size());
    }
    /** Test case to test reading wordlist.txt file.
     * @throws FileNotFoundException if file not found at the path
     */
    @Test
    public void shouldLoadMainWordlistFile() throws FileNotFoundException
    {
       ArrayList<String> wordlist = WordleApp.loadWordlist("src/main/resources/wordlist.txt");

        // test wordlist only contains 3 words, so wordlist should have the size of 3
        assertTrue(wordlist.size()>0);
    }

    /**Test case to test getRandomIndex() method.
     * Tesing to get random index between the range
     */
    @Test
    public void shouldLoadRandomNumber(){
        //test Not Null
        assertNotNull(WordleApp.getRandomIndex(20));

        //test the value between the given range
        Integer returnedValue = (WordleApp.getRandomIndex(80));
        assertTrue(returnedValue>0 && returnedValue <80);

    }

    /**Test case to test getChosenWord() method.
     * Testing to get valid value
     */
    @Test
    public void shouldReturnWordAtSelectedIndex(){
        ArrayList<String> testArray =  new ArrayList<>();
        testArray.add("First Element");
        testArray.add("Secound Element");
        testArray.add("Third Element");
        testArray.add("Fourth Element");

        assertEquals("Secound Element", WordleApp.getChosenWord(1, testArray));
    }

    /**Test case to test getChosenWord().
     * Testing IndexOutOfBoundsException
     */
    @Test
    public void testInvalidIndex(){
        ArrayList<String> testArray =  new ArrayList<>();
        testArray.add("First Element");
        testArray.add("Secound Element");
        testArray.add("Third Element");
        testArray.add("Fourth Element");

        assertThrows(IndexOutOfBoundsException.class, () -> {
            WordleApp.getChosenWord(5, testArray);
        });

    }

    /**Test case to test splitIntoArray().
     * Testing if the method splits word as expected
     */
    @Test
    public void shouldSplitWordToArray(){
        String testWord = "TestWord";

        //Should return 8 as the no. of words is 8 in testWord
        assertEquals(8, WordleApp.splitIntoArray(testWord).length);
    }

    /**Test case to test splitIntoArray().
     * Testing if the method splits word as expected
     */
    @Test
    public void invalidSplitToArray(){
        String testWord = "TestWord";
        assertNotEquals(6, WordleApp.splitIntoArray(testWord).length);
    }

    /**Test case to test validateGuess().
     * Testing for senario when user input is in lower case or both lower and upper case.
     */
    @Test
    public void testGuessWithLowecase(){
        assertFalse(WordleApp.validateGuess("TWORD", "UOwRD"));
        assertTrue(WordleApp.validateGuess("CREAM", "cream"));  
    }

    /**Test case to test validateGuess().
     * Testing for senario when user input is in upper case.
     */
    @Test
    public void testGuessWithUppercase(){   
        assertFalse(WordleApp.validateGuess("TWORD", "UWORD"));
        assertTrue(WordleApp.validateGuess("DIVER", "DIVER"));
    }

    /**Test case to test validateGuess().
     * Testing for senario when computer chosen word has reated letters.
     */
    @Test
    public void testGuessWithRepeatedLetters(){
        assertFalse(WordleApp.validateGuess("BALLS", "bells"));
        assertTrue(WordleApp.validateGuess("BELLS", "bells"));

    }

}
