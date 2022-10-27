import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Please enter the absolute path to the file you want to read.");

        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        //Example: C:\Users\User\word-counts-from-file\src\TestFile.txt

        Map<String, Integer> wordCounts = new HashMap<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            String currentLine = br.readLine();

            while (currentLine != null) {
                String[] words = currentLine.split(" ");

                for (String word : words) {
                    String lowerWord = word.toLowerCase();
                    Integer currentCount = wordCounts.get(lowerWord);

                    //Strip out anything non-alpha
                    lowerWord = lowerWord.replaceAll("[^a-zA-Z]", "");

                    if (currentCount != null) {
                        wordCounts.put(lowerWord, currentCount + 1);
                    } else {
                        wordCounts.put(lowerWord, 1);
                    }
                }
                currentLine = br.readLine();
            }

            //Sort the map based on the values and display the results
            wordCounts.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(System.out::println);


        } catch (FileNotFoundException e) {
            System.out.println("The file path you supplied could not be found.");
        } catch (IOException e) {
            System.out.println("The file could not be read.");
        }
    }
}