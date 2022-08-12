package baseballgame;

import java.util.ArrayList;
import java.util.List;

public class ResultReader {
	private static StringBuilder result = null;
	private static List<Integer> redundancy = null;
	
	public static StringBuilder deriveResult(List<Integer> answer, List<Integer> userInput) {
		result = new StringBuilder();
		redundancy = new ArrayList<Integer>();
		int strikeCount = validateStrike(answer, userInput);
		if (strikeCount != 3) {
			int ballCount = validateBall(answer);
			if (strikeCount == 0 && ballCount == 0) {
				result.append("낫싱");
			}
		}
		return result;
	}

	public static int validateStrike(List<Integer> answer, List<Integer> userInput) {
		int count = 0;
		for (int i = 0; i < 3; i++) {
			int currentNumber = userInput.get(i);
			if (answer.get(i) == userInput.get(i)) {
				count++;
				continue;
			}
			redundancy.add(currentNumber);
		}
		combinateString(count, " 스트라이크 ");
		return count;
	}

	public static int validateBall(List<Integer> answer) {
		int count = 0;
		for (int i = 0; i < redundancy.size(); i++) {
			if (answer.contains(redundancy.get(i))) {
				count++;
			}
		}
		combinateString(count, " 볼");
		return count;
	}

	public static void combinateString(int successCount, String targetString) {
		if (successCount != 0) {
			result.append(successCount + targetString);
		}
	}
}
