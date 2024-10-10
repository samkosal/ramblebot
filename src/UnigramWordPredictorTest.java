import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class UnigramWordPredictorTest {

    @Test
    void testTrainAndGetNeighborMap() {
        // Use a fake tokenizer with predefined tokens
        FakeTokenizer fakeTokenizer = new FakeTokenizer(
            List.of("the", "cat", "sat", ".", "the", "cat", "slept", ".", "the", "dog", "barked", ".")
        );
        UnigramWordPredictor predictor = new UnigramWordPredictor(fakeTokenizer);
        
        predictor.train(null); // The scanner input is ignored by FakeTokenizer
        Map<String, List<String>> neighborMap = predictor.getNeighborMap();

        // Sort the actual lists to ensure order does not affect comparison
        for (List<String> values : neighborMap.values()) {
            values.sort(null); // Sort using natural ordering
        }

        // Pre-sorted expected map
        Map<String, List<String>> expectedMap = Map.of(
            "the", List.of("cat", "cat", "dog"),
            "cat", List.of("sat", "slept"),
            ".", List.of("the", "the"),
            "dog", List.of("barked"),
            "sat", List.of("."),
            "slept", List.of("."),
            "barked", List.of(".")
        );

        assertEquals(expectedMap, neighborMap);
    }

    @Test
    void testPredictNextWord() {
        // Use a fake tokenizer with predefined tokens
        FakeTokenizer fakeTokenizer = new FakeTokenizer(
            List.of("the", "cat", "sat", ".", "the", "cat", "slept", ".", "the", "dog", "barked", ".")
        );
        UnigramWordPredictor predictor = new UnigramWordPredictor(fakeTokenizer);
        
        predictor.train(null); // The scanner input is ignored by FakeTokenizer
        
        // Predicting the next word after "the" should be "cat" or "dog" based on distribution
        String nextWord = predictor.predictNextWord(List.of("the"));
        assertTrue(nextWord.equals("cat") || nextWord.equals("dog"));

        // Predicting the next word after "cat" should be either "sat" or "slept"
        nextWord = predictor.predictNextWord(List.of("cat"));
        assertTrue(nextWord.equals("sat") || nextWord.equals("slept"));

        // Predicting the next word after "dog" should always be "barked"
        nextWord = predictor.predictNextWord(List.of("dog"));
        assertEquals("barked", nextWord);
    }
}
