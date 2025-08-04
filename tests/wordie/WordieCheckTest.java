package wordie;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.JLabel;

public class WordieCheckTest {

    private WordieCheck wordieCheck;

    @BeforeEach
    public void setUp() {
        wordieCheck = new WordieCheck();
    }
    
    ///Generates 5 empty labels used in the tests
    private JLabel[] createEmptyLabels(int lettersAmount) {
        JLabel[] labels = new JLabel[lettersAmount];
        for (int i = 0; i < lettersAmount; i++) {
            labels[i] = new JLabel();
        }
        return labels;
    }

    /**
     * Test that letterCountCheck returns true when the input word has exactly 5 letters.
     */
    @Test
    public void testLetterCountCheckValid() {
        assertTrue(wordieCheck.letterCountCheck("apple"));
    }

    /**
     * Test that letterCountCheck returns false when the input word is too short.
     */
    @Test
    public void testLetterCountCheckTooShort() {
        assertFalse(wordieCheck.letterCountCheck("cat"));
    }

    /**
     * Test that letterCountCheck returns false when the input word is too long.
     */
    @Test
    public void testLetterCountCheckTooLong() {
        assertFalse(wordieCheck.letterCountCheck("pumpkin"));
    }

    /**
     * Test that compareWords sets all labels to green when the guess is completely correct.
     */
    @Test
    public void testCompareWordsAllGreen() {
    	JLabel[] labels = createEmptyLabels(5);

        wordieCheck.compareWords("apple", "apple", labels, 2);

        for (int i = 0; i < 5; i++) {
            assertEquals("APPLE".charAt(i), labels[i].getText().charAt(0));
            assertEquals(java.awt.Color.GREEN, labels[i].getBackground());
        }
    }

    /**
     * Test that compareWords properly sets yellow for correct letters in the wrong position.
     */
    @Test
    public void testCompareWordsSomeYellow() {
    	JLabel[] labels = createEmptyLabels(5);

        wordieCheck.compareWords("pleap", "apple", labels, 2);

        // We expect some green, some yellow
        assertEquals(java.awt.Color.YELLOW, labels[0].getBackground()); // 'P'
        assertEquals(java.awt.Color.YELLOW, labels[1].getBackground()); // 'L'
        assertEquals(java.awt.Color.YELLOW, labels[2].getBackground()); // 'E'
        assertEquals(java.awt.Color.YELLOW, labels[3].getBackground()); // 'A'
        assertEquals(java.awt.Color.YELLOW, labels[4].getBackground()); // 'P'
    }

    /**
     * Test that compareWords sets correct colors including grey for letters not in the actual word.
     */
    @Test
    public void testCompareWordsWithGray() {
    	JLabel[] labels = createEmptyLabels(5);

        wordieCheck.compareWords("qwzrt", "apple", labels, 4);

        assertEquals(java.awt.Color.GRAY, labels[0].getBackground());
        assertEquals(java.awt.Color.GRAY, labels[1].getBackground());
        assertEquals(java.awt.Color.GRAY, labels[2].getBackground());
        assertEquals(java.awt.Color.GRAY, labels[3].getBackground());
        assertEquals(java.awt.Color.GRAY, labels[4].getBackground());
    }

    /**
     * Test that compareWords correctly handles repeated letters.
     */
    @Test
    public void testCompareWordsWithDuplicateLetters() {
    	JLabel[] labels = createEmptyLabels(5);

        wordieCheck.compareWords("lemon", "hello", labels, 1);

        assertEquals(java.awt.Color.YELLOW, labels[0].getBackground()); 
        assertEquals(java.awt.Color.GREEN, labels[1].getBackground());
        assertEquals(java.awt.Color.GRAY, labels[2].getBackground());   
        assertEquals(java.awt.Color.YELLOW, labels[3].getBackground()); 
        assertEquals(java.awt.Color.GRAY, labels[4].getBackground());  
    }
    
    /**
     * Test that compareWords does not assign yellow to repeated letters in the guess
     * when the actual word contains fewer instances of that letter. 
     */
    @Test
    public void testCompareWordsWithRepeatedLettersLimitedInActualWord() {
    	JLabel[] labels = createEmptyLabels(5);

        wordieCheck.compareWords("aaaaa", "adapt", labels, 3);

        // "adapt" has 'a' at positions 0 and 2 only no others should be yellow
        assertEquals(java.awt.Color.GREEN, labels[0].getBackground());
        assertEquals(java.awt.Color.GRAY, labels[1].getBackground());  
        assertEquals(java.awt.Color.GREEN, labels[2].getBackground()); 
        assertEquals(java.awt.Color.GRAY, labels[3].getBackground());  
        assertEquals(java.awt.Color.GRAY, labels[4].getBackground());  
    }

}