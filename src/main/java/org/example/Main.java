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
        Set<String> wordsToExclude = new HashSet<>((Arrays.asList("a", "an", "the", "about", "above", "across", "after", "against", "along", "among", "around",
                "at", "before", "behind", "below", "beneath", "beside", "besides", "between", "beyond", "by", "despite", "down", "during", "except", "for", "from",
                "in", "inside", "into", "like", "near", "of", "off", "on", "onto", "out", "outside", "over", "past", "since", "through", "throughout", "till",
                "to", "toward", "under", "underneath", "until", "up", "upon", "with", "within", "without", "I", "me", "my", "mine", "myself", "we", "us", "our", "ours",
                "ourselves", "you", "your", "yours", "yourself", "yourselves", "he", "him", "his", "himself", "she", "her", "hers", "herself", "it", "its", "itself",
                "they", "them", "their", "theirs", "themselves", "this", "that", "these", "those", "who", "whom", "whose", "which", "what", "whoever", "whomever",
                "whichever", "whatever", "and", "but", "or", "nor", "for", "yet", "so", "although", "because", "since", "unless", "until", "while", "where", "when", "after",
                "before", "if", "though", "as", "than", "that", "though", "till", "unless", "until", "when", "whenever", "where", "whereas", "wherever", "whether", "while"
                )));

        System.out.println("words count : " + wordsToExclude.size());
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