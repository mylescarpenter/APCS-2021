/**
 * class used to create a Word object that holds a String and an Integer, and provides methods to manipulate the Word
 * @author Myles Carpenter
 * date - March 26, 2020
 */

public class Word {

    String text;
    int count;

    /**
     * initializes a word with specified text and count
     * @param word - the text value of the Word
     * @param count - the count of the Word
     */
    public Word(String word, int count){
        this.count = count;
        text = word;
    }

    /**
     * initializes Word with a specified text value and a default count of 1
     * @param word - text value of Word
     */
    public Word(String word){
        text = word;
        count = 1;
    }

    /**
     * takes a String as a parameter and returns a new String holding only alphabetic terms,
     * valid hyphens, and apostrophes
     * @param word - word to be trimmed
     * @return - the word with only the word's valid punctuation
     */
    public static String trimWord(String word){
        String result = "";
        word = word.toLowerCase();
        for(int i = 0; i < word.length(); ++i){
            if(Character.isAlphabetic(word.charAt(i))) result += word.charAt(i);
            else if(i != 0 && word.charAt(i) == '-') result += "-";
            else if(word.charAt(i) == '\'') result += "'";
        }
        return result;
    }

    /**
     * increments the count of said Word
     */
    public void incrementCount(){
        count++;
    }

    /**
     * setter method for count
     * @param a - sets count equal to a
     */
    public void setCount(int a){
        count = a;
    }

    /**
     * getter method for count
     * @return - value of count
     */
    public int getCount(){
        return count;
    }

    /**
     * setter method for text
     * @param a - sets text equal to a
     */
    public void setText(String a){
        text = a;
    }

    /**
     * getter method for text
     * @return - the value of text
     */
    public String getText(){
            return text;
    }

    /**
     * compares the counts of two words
     * @param word - word to be compared to
     * @return - positive number if count of first is greater than count of second, negative number if
     * count of first is less than count of second, 0 if the two counts are equal
     */
    public int compareTo(Word word){
        return Integer.compare(count, word.getCount());
    }

    /**
     * toString method of Word
     * @return - information about text and count
     */
    public String toString(){
        return text + " " + count;
    }
}
