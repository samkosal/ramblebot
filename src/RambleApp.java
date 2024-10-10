import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RambleApp {

    private Tokenizer tokenizer;
    private UnigramWordPredictor predictor;
    private Scanner inputScanner;

    public RambleApp() {
        this.tokenizer = new LowercaseSentenceTokenizer();
        this.predictor = new UnigramWordPredictor(tokenizer);
        this.inputScanner = new Scanner(System.in);
    }

    public void run() {
        String filename = promptForFilename();
        int numWords = promptForNumberOfWords();

        if (!trainPredictor(filename)) {
            System.out.println("Failed to train the predictor. Exiting.");
            return;
        }

        generateText(numWords, filename);
    }

    private String promptForFilename() {
        System.out.print("Enter the filename: ");
        return inputScanner.nextLine();
    }

    private int promptForNumberOfWords() {
        System.out.print("Enter the number of words to generate: ");
        int numWords = inputScanner.nextInt();
        inputScanner.nextLine(); // Consume the newline
        return numWords;
    }

    private boolean trainPredictor(String filename) {
        File file = new File(filename);
        try (Scanner fileScanner = new Scanner(file)) {
            predictor.train(fileScanner);
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            return false;
        }
    }

    private void generateText(int numWords, String filename) {
        // Get the tokens from the file to start the generation
        List<String> tokens = getTokensFromFile(filename);
        if (tokens == null) {
          System.out.println("No tokens returned from tokenizer!");
          System.out.println("This is probably because you haven't implemented it yet");
          System.out.println("Begin with Wave 1 in the instructions, and implement LowercaseSentenceTokenizer");
          System.out.println("If you have implemented it, there's a bug in your code where it's returning null for the tokens.");
          return;
        }
        if (tokens.isEmpty()) {
            System.out.println("The file is empty. No text to generate.");
            return;
        }

        // Start generating text from the first word in the file
        List<String> context = new ArrayList<>();
        context.add(tokens.get(0));
        System.out.print(context.get(0)); // Print the first word

        for (int i = 1; i < numWords; i++) {
            String nextWord = predictor.predictNextWord(context);
            if (nextWord == null) {
                System.out.println("No predition made from Predictor!");
                System.out.println("This is probably because you haven't implemented it yet");
                System.out.println("Implement it, and come back and try again");
                System.out.println("If you have implemented it, there's a bug in your code where it's returning null for a prediction.");
                break;
            }
            System.out.print(" " + nextWord);

            // Update the context with the next word
            context.add(nextWord);
        }

        System.out.println(); 
    }

    private List<String> getTokensFromFile(String filename) {
        File file = new File(filename);
        try (Scanner fileScanner = new Scanner(file)) {
            return tokenizer.tokenize(fileScanner);
        } catch (FileNotFoundException e) {
            System.out.println("Failed to read tokens from file.");
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        // Create an instance of RambleApp and run it
        new RambleApp().run();
    }
}
