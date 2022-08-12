package baseballgame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/*
// 로컬로 작업한 것
 * 
public class GameStarter {
	private static List<Integer> userInput = null;
	private static List<Integer> answer = null;
	private static ResultReader resultReader = new ResultReader();
	private int totalCount = 0;
	
	public GameStarter() {
		setAnswerNumber();
	}
	
	public void startGame(Scanner scanner) throws IOException {
		while (totalCount++ < 3) {
			System.out.print("숫자를 입력해주세요 : ");
			processInput(scanner.nextLine().split("{1}"));
			StringBuilder result = resultReader.deriveResult(answer, userInput);
			System.out.println(result.toString());
			if (result.toString().equals("3 스트라이크 ")) {
				System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
				break;
			}
		}
	}
	
	public void setAnswerNumber() {
		answer = new ArrayList<Integer>();
		Random answerNumber = new Random();
		Set<Integer> deduplicationAnswerNumber = new HashSet<Integer>();
		while (deduplicationAnswerNumber.size() < 3) {
			deduplicationAnswerNumber.add(answerNumber.nextInt(8) + 1);
		}
		answer.addAll(deduplicationAnswerNumber);
	}
	
	private void processInput(String[] splitInput) {
		userInput = new ArrayList<Integer>();
		for (int i = 0; i < 3; i++) {
			userInput.add(Integer.parseInt(splitInput[i]));
		}
*/

/* github에 있던 것*/
import org.w3c.dom.ranges.RangeException;

public class GameStarter {
	private final int NUMBER_OF_DIGIT = 3;
	private static ResultReader resultReader = new ResultReader();
	private static List<Integer> answer = null;
	private int totalCount = 0;

	public GameStarter() {
		setAnswerNumber();
	}

	public void setAnswerNumber() {
		Random answerNumber = new Random();
		answer = new ArrayList<Integer>();
		Set<Integer> deduplicationAnswerNumber = new HashSet<Integer>();
		while (deduplicationAnswerNumber.size() < NUMBER_OF_DIGIT) {
			deduplicationAnswerNumber.add(answerNumber.nextInt(8) + 1);
		}
		answer.addAll(deduplicationAnswerNumber);
	}

	public void startGame(Scanner scanner) throws IOException {
		while (totalCount++ < NUMBER_OF_DIGIT) {
			System.out.print("숫자를 입력해주세요 : ");
			List<Integer> userInput = processInput(scanner.nextLine().split("{1}"));
			StringBuilder result = resultReader.deriveResult(answer, userInput);
			System.out.println(result.toString());
			if (result.toString().equals("3 스트라이크 ")) {
				System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
				break;
			}
		}
	}

	public List<Integer> processInput(String[] splitInput) {
		List<Integer> userInput = new ArrayList<Integer>();
		if (splitInput.length != 3) {
			throw new RangeException((short)splitInput.length, "Enter a 3 digit number.");
		}
		for (int i = 0; i < NUMBER_OF_DIGIT; i++) {
			userInput.add(Integer.parseInt(splitInput[i]));
		}
		return userInput;
	}
}
