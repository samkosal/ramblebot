import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UnigramMarkovGenerator implements TextGenerator {
  private Map<String, List<String>> neighborMap;

  public void train(Scanner scanner) {

  }

  public String predictNextWord(List<String> context) {
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
