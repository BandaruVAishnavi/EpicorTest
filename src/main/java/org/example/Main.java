package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/main/resources/moby.txt");
        Set<String> wordsToExclude = new HashSet<>(Arrays.asList("a", "an", "the", "in", "on", "at", "to", "by", "for", "with", "about", "as", "of", "from", "up", "down",
                "over", "under", "after", "before", "during", "between", "and", "but", "or", "nor", "so", "yet", "he", "she", "it", "they",
                "them", "his", "her", "its", "their", "in", "on", "at", "he", "she", "it", "or", "but", "am", "was", "were", "is", "are",
                "will", "would", "shall", "should", "can", "could", "may", "might", "do", "does", "did", "done", "has", "have", "had", "been", "being", "to",
                "from", "of", "for", "with", "by", "as", "in", "into", "onto", "upon", "out", "over", "under", "above", "below", "between", "among", "through",
                "across", "against", "along", "around", "at", "before", "behind", "beneath", "beside", "besides", "beyond", "during", "inside", "outside", "since",
                "throughout", "till", "until", "within", "without", "about", "against", "among", "around", "before", "behind", "below", "beneath",
                "beside", "between", "beyond", "during", "inside", "outside", "through", "throughout", "under", "underneath", "upon", "with", "within", "without",
                "above", "across", "after"
        ));
        System.out.println(file.exists());


//    Total word count (excluding the filtered words).
//    b. Top 5 most frequent words with counts (excluding the filtered words).
//    c. Alphabetically sorted list of all unique words (excluding the filtered words).

        try {
            List<String> words = readFileAndProcess(file, wordsToExclude);
            int wordCount = words.size();
            System.out.println("a. Total word count: " + wordCount);


        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

    }

    private static List<String> readFileAndProcess(File file, Set<String> wordsToExclude) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);

        String line;
        StringBuilder fileText = new StringBuilder();

        // Read the file line by line and append to fileText
        while ((line = reader.readLine()) != null) {
            fileText.append(line);
        }

        // Split the fileText into words and convert to lowercase
        String[] words = fileText.toString().toLowerCase().split("[^a-zA-Z]+");
        ;

        // Filter out the words that are in the wordsToExclude set
        List<String> filteredWords = Arrays.stream(words)
                .filter(word -> !word.isEmpty() && !wordsToExclude.contains(word))
                .toList();

//        System.out.println(filteredWords);

        return filteredWords;
    }
}