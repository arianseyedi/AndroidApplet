package ca.roumani.jumbleapp;

import android.content.res.Resources;
import android.os.Environment;
import android.support.annotation.NonNull;

import java.io.InputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Create word signature for all the words in the dictionary.
 *
 * @author Arian Seyedi
 */
public class Dictionary {
    Map<Map<Character, Integer>, List<String>> dictionaryFootprint
            = new HashMap<Map<Character, Integer>, List<String>>();

    /**
     * Initializes this dictionary from the given stream.  Each line of the file contains a
     * single word.
     *
     * @param stream stream from which a file containing the words of this dictionary can be read.
     */
    public Dictionary(InputStream stream) {
        Scanner input = new Scanner(stream);
        Map<Character, Integer> wordFootprint;
        while (input.hasNextLine()) {
            String word = input.nextLine();
            System.out.println(word);

            wordFootprint = getFootprint(word);
            if (dictionaryFootprint.containsKey(wordFootprint)) {

                List<String> tempStringList = dictionaryFootprint.get(wordFootprint);
                tempStringList.add(word);
                dictionaryFootprint.put(wordFootprint, tempStringList);
            } else {

                List<String> tempStringList = new ArrayList<String>();
                tempStringList.add(word);
                dictionaryFootprint.put(wordFootprint, tempStringList);
            }

        }

        input.close();

    }

    /**
     * Returns the list of words that are unscramblings of the given word.
     *
     * @param word word to be unscrambled.
     * @return list of words that are unscramblings of the given word.
     */
    public List<String> getUnscramblings(String word) {

        Map<Character, Integer> wordFootprint;
        wordFootprint = getFootprint(word);
        List<String> matchingWordList;
        if (dictionaryFootprint.containsKey(wordFootprint)) {
            matchingWordList = dictionaryFootprint.get(wordFootprint);
            return matchingWordList;
        } else {
            return new ArrayList<String>();
        }


    }

    private static int getLetterRepetition(String word, char letter) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (Character.toLowerCase(word.charAt(i)) == letter)
                count++;
        }
        return count;
    }

    private static Map<Character, Integer> getFootprint(String word) {
        //Initialize the footprint as HashMap and set of unique character corresponding to the
        // input word.
        Map<Character, Integer> footprint = new HashMap<Character, Integer>();
        Set<Character> uniqueLettersInWordSet = new HashSet<Character>();
        // Generating the set
        for (int i = 0; i < word.length(); i++) {
            uniqueLettersInWordSet.add(Character.toLowerCase(word.charAt(i)));
        }
        //Here the map gets created using every character of the word and its repetition
        for (Character charLetter : uniqueLettersInWordSet) {
            int repetition;
            repetition = getLetterRepetition(word, charLetter);
            footprint.put(charLetter, repetition);
        }
        return footprint;
    }
}
