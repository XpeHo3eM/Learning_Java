package task1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class task1 {
    public static void showWinner(List<String> competitors){
        Map<String, Integer> scores = new HashMap<>();
        String winner = "";
        int maxScore = 0;
        
        for (String competitor : competitors) {
            String[] competitorInfo = competitor.split(" ");
            String name = competitorInfo[0];
            int score = Integer.parseInt(competitorInfo[1]);

            if (scores.containsKey(name)) {
                score += scores.get(name);
            }

            scores.put(name, score);
            
            if (score > maxScore) {
                maxScore = score;
                winner = name;
            }
        }

        System.out.printf("Winner: %s, total score: %d", winner, maxScore);
    }


    public static void main(String[] args) {
        showWinner(List.of("Ivan 5", "Petr 3", "Alex 10", "Petr 8", "Ivan 6", "Alex 5", "Ivan 1", "Petr 5", "Alex 1"));
    }
}
