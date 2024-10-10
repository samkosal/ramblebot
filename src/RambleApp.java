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
        // Initialize the tokenizer, predictor, and input scanner
        this.tokenizer = new LowercaseSentenceTokenizer();
        this.predictor = new UnigramWordPredictor(tokenizer);
        this.inputScanner = new Scanner(System.in);
    }

    public void run() {
        // Run the main workflow
        String filename = promptForFilename();
        int numWords = promptForNumberOfWords();

        if (!trainPredictor(filename)) {
            System.out.println("Failed to train the predictor. Exiting.");
            return;
        }

        generateText(numWords, filename);
    }

    private String promptForFilename() {
        // Prompt the user to enter the filename
        System.out.print("Enter the filename: ");
        return inputScanner.nextLine();
    }

    private int promptForNumberOfWords() {
        // Prompt the user to enter the number of words to generate
        System.out.print("Enter the number of words to generate: ");
        int numWords = inputScanner.nextInt();
        inputScanner.nextLine(); // Consume the newline
        return numWords;
    }

    private boolean trainPredictor(String filename) {
        // Train the predictor using the specified file
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
                break; // Stop if no prediction can be made
            }
            System.out.print(" " + nextWord);

            // Update the context with the last word
            context.clear();
            context.add(nextWord);
        }

        System.out.println(); // Finish with a newline
    }

    private List<String> getTokensFromFile(String filename) {
        // Get the tokens from the file used to train the predictor
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
