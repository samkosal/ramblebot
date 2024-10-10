import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UnigramWordPredictor implements WordPredictor {
  private Map<String, List<String>> neighborMap;
  private Tokenizer tokenizer;

  public UnigramWordPredictor(Tokenizer tokenizer) {
    this.tokenizer = tokenizer;
  }

  public void train(Scanner scanner) {
    List<String> trainingWords = tokenizer.tokenize(scanner);

    // TODO: Convert the trainingWords into neighborMap here
  }

  public String predictNextWord(List<String> context) {
    // TODO: Return a predicted word given the words preceding it
    // Hint: only the last word in context should be looked at
    return null;
  }
  
  public Map<String, List<String>> getNeighborMap() {
    Map<String, List<String>> copy = new HashMap<>();

    for (Map.Entry<String, List<String>> entry : neighborMap.entrySet()) {
      List<String> newList = new ArrayList<>(entry.getValue());
      copy.put(entry.getKey(), newList);
    }

    return copy;
  }
}
