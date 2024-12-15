package org.example;

import java.io.*;
import java.util.*;

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
                "before", "if", "though", "as", "than", "that", "though", "till", "unless", "until", "when", "whenever", "where", "whereas", "wherever", "whether", "while", "s"
                )));

        System.out.println("excluding words count : " + wordsToExclude.size());
        System.out.println(file.exists());


//    Total word count (excluding the filtered words).
//    b. Top 5 most frequent words with counts (excluding the filtered words).
//    c. Alphabetically sorted list of all unique words (excluding the filtered words).

        try {
            List<String> words = readFileAndProcess(file, wordsToExclude);
            int wordCount = words.size();
            System.out.println("a. Total word count: " + wordCount);

            // Count the frequency of each word and get the top 5 words
            Map<String, Integer> wordFrequency = calculateWordFrequencies(words);
            List<Map.Entry<String, Integer>> topFiveWords = getTop5Words(wordFrequency);

            //TODO: This is counting "s" from words like one's, artist's, man's etc. Need to fix this.
            // One solution is to add the string "s" in wordsToExclude set. But this is not a good solution.
            // Another solution is to remove the apostrophe and club such words as ones, artists while processing the file. But this will change the meaning of the word.
            // Need to find a better solution. For now, I'm going with the first solution.
            System.out.println("b. Top 5 most frequent words with counts: ");
            topFiveWords.forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

            // Using TreeSet to get the unique words in alphabetical order
            Set<String> uniqueWordsInAlphabeticalOrder = new TreeSet<>(words);
            System.out.println("uniqueWordsInAlphabeticalOrder count : " + uniqueWordsInAlphabeticalOrder.size());
            System.out.println("c. Alphabetically sorted list of all unique words (excluding the filtered words): ");
            uniqueWordsInAlphabeticalOrder.forEach(System.out::println);

            //saving output to a file
            saveOutput(wordCount, topFiveWords, uniqueWordsInAlphabeticalOrder);
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

    }

    private static void saveOutput(int wordCount, List<Map.Entry<String, Integer>> topFiveWords, Set<String> uniqueWordsInAlphabeticalOrder) {
        try {
            PrintWriter writer = new PrintWriter("kotlinTestOutput.txt");
            writer.println("a. Total word count: " + wordCount);
            writer.println("b. Top 5 most frequent words with counts: ");
            topFiveWords.forEach(entry -> writer.println(entry.getKey() + ": " + entry.getValue()));
            writer.println("c. Alphabetically sorted list of all unique words (excluding the filtered words): ");
            uniqueWordsInAlphabeticalOrder.forEach(writer::println);
        } catch (FileNotFoundException e) {
            System.out.println("Error while saving output: " + e.getMessage());
        }
    }

    //calculate top 5 words based on the frequency by sorting the list of words
    private static List<Map.Entry<String, Integer>> getTop5Words(Map<String, Integer> wordFrequency) {
        return wordFrequency
                .entrySet()
                .stream()
                .sorted((a,b) -> b.getValue().compareTo(a.getValue()))
                .limit(5)
                .toList();
    }

    private static Map<String, Integer> calculateWordFrequencies(List<String> words) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        for(String word: words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }
        return frequencyMap;
    }

    private static List<String> readFileAndProcess(File file, Set<String> wordsToExclude) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);

        String line;
        StringBuilder fileText = new StringBuilder();

        // Read the file line by line and append to fileText
        while ((line = reader.readLine()) != null) {
            fileText.append(line);
            fileText.append(" ");
        }

        // Split the fileText into words and convert to lowercase : using lowercase to avoid case sensitivity [since the requirement is to treat words as case-insensitive]
        String[] words = fileText.toString().toLowerCase().split("[^a-zA-Z]+");

        // Filter out and return the words that are in the wordsToExclude set
        return Arrays.stream(words)
                .filter(word -> !word.isEmpty() && !wordsToExclude.contains(word))
                .toList();
    }
}