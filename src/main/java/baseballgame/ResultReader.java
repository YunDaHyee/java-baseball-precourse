package baseballgame;

import java.util.ArrayList;
import java.util.List;

public class ResultReader {
/*
// 로컬로 작업한 것
 
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
*/
	
/* github에 있던 것 */
	private final static int NUMBER_OF_DIGIT = 3;
	private static StringBuilder result = null;
	private static List<Integer> redundancy = null;

	public StringBuilder deriveResult(List<Integer> answer, List<Integer> userInput) {
		result = new StringBuilder();
		redundancy = new ArrayList<Integer>();
		int strikeCount = validateStrike(answer, userInput);
		if (strikeCount != NUMBER_OF_DIGIT) {
			int ballCount = validateBall(answer);
			if (strikeCount == 0 && ballCount == 0) {
				result.append("낫싱");
			}
		}
		return result;
	}

	public int validateStrike(List<Integer> answer, List<Integer> userInput) {
		int count = 0;
		for (int i = 0; i < NUMBER_OF_DIGIT; i++) {
			if (answer.get(i) == userInput.get(i)) {
				count++;
				continue;
			}
			redundancy.add(userInput.get(i));
		}
		combinateString(count, " 스트라이크 ");
		return count;
	}

	public int validateBall(List<Integer> answer) {
		int count = 0;
		for (int i = 0; i < redundancy.size(); i++) {
			if (answer.contains(redundancy.get(i))) {
				count++;
			}
		}
		combinateString(count, " 볼");
		return count;
	}

	private void combinateString(int successCount, String targetString) {
		if (successCount != 0) {
			result.append(successCount + targetString);
		}
	}
}
