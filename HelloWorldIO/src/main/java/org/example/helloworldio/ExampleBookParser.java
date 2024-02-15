package org.example.helloworldio;

import com.sun.source.tree.Tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ExampleBookParser {
    public Map<String, Integer> getWordFrequency (File bookDir){
        Map<String, Integer> wordFrequencyMap = new TreeMap<>();
        File[] bookFiles = bookDir.listFiles();
        int bookFilesCount = bookFiles.length;
        for(File bookFile : bookFiles){
            Map<String, Integer> bookFileFrequencyMap = calculateWordFrequency(bookFile);
            Set<String> words = bookFileFrequencyMap.keySet();
            Iterator<String> wordIterator = words.iterator();
            while(wordIterator.hasNext()){
                String word = wordIterator.next();
                int wordCount = bookFileFrequencyMap.get(word);
                if(!wordFrequencyMap.containsKey(word)){
                    wordFrequencyMap.put(word, wordCount);
                }
                else{
                    int oldCount = wordFrequencyMap.get(word);
                    wordFrequencyMap.put(word, oldCount + wordCount);
                }
            }
        }
        return wordFrequencyMap;
    }

    private Map<String, Integer> calculateWordFrequency(File bookFile) {
        Map<String, Integer> wordFrequencyMap = new TreeMap<>();
        try {
            Scanner bookScanner = new Scanner(bookFile);
            while(bookScanner.hasNext()){
                String word = bookScanner.next();
                word = word.toLowerCase();
                if(isWord(word)){
                    if (!wordFrequencyMap.containsKey(word)){
                        wordFrequencyMap.put(word,1);

                    }
                    else{
                        int oldCount = wordFrequencyMap.get(word);
                        wordFrequencyMap.put(word, oldCount+1);
                    }
                }

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return wordFrequencyMap;
    }

    private boolean isWord(String word) {
        if(word == null | "".equals(word)) {
            return false;
        }
        String wordPattern = "^[a-z]*$";
        if(word.matches(wordPattern)){
            return true;
        }
        return false;
    }
}
