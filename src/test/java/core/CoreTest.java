package core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import baseballgame.GameStarter;
import baseballgame.ResultReader;

public class CoreTest {

	GameStarter gameStarter;
	ResultReader resultReader;
	
	@Before
	public void init() {
		resultReader = new ResultReader();
		gameStarter = new GameStarter();
	}
	
	@CsvSource({"123,563"})
	@ParameterizedTest
	public void index_duplication_test(String answer,String userInput) {
		int count = 0;
		String[] answerArray = answer.split("{1}");
		String[] userInputArray = userInput.split("{1}");
		for( int i=0;i<3;i++ ){
			int answerCurrentNumber = Integer.parseInt(answerArray[i]);
			int userCurrentNumber = Integer.parseInt(userInputArray[i]);
			if( answerCurrentNumber == userCurrentNumber ){
				count++;
			}
		}
		assertEquals(1, count);
	}
	
	@CsvSource({"321,53"})
	@ParameterizedTest
	public void number_contain_test(String answer,String userInput) {
		int count = 0;
		String[] splitAnswer = answer.split("{1}");
		ArrayList<Integer> answerList = new ArrayList<Integer>();
		for( int i=0;i<3;i++ ) {
			answerList.add(Integer.parseInt(splitAnswer[i]));
		}
		
		String[] userInputArray = userInput.split("{1}");
		for (int i = 0; i < userInputArray.length; i++) {
			if (answerList.contains(Integer.parseInt(userInputArray[i]))){
				count++;
			}
		}
		assertEquals(1, count);
	}
	
	@Test
	public void number_duplication_test() {
		int count=0;
		ArrayList<Integer> answer = new ArrayList<Integer>();
		Random answerNumber = new Random();
		Set<Integer> deduplicationAnswerNumber = new HashSet<Integer>();
		while (deduplicationAnswerNumber.size() < 3) {
			deduplicationAnswerNumber.add(answerNumber.nextInt(8) + 1);
		}
		
		for( int i=0;i<answer.size();i++ ){
			if( answer.contains(answer.get(i))){
				count++;
			}
		}
		assertEquals(0, count);
	}
	
	@CsvSource({"321,531"})
	@ParameterizedTest
	@DisplayName("ResultReader.deriveResult()")
	public void result_validation_test(String rawAnswer,String rawUserInput) {
		ArrayList<Integer> answerList = new ArrayList<Integer>();
		ArrayList<Integer> userInfoList = new ArrayList<Integer>();
		String[] splitAnswer = rawAnswer.split("{1}");
		String[] splitUserInfo = rawUserInput.split("{1}");
		
		for( int i=0;i<3;i++ ) {
			answerList.add(Integer.parseInt(splitAnswer[i]));
			userInfoList.add(Integer.parseInt(splitUserInfo[i]));
		}
		
		StringBuilder result = ResultReader.deriveResult(answerList, userInfoList);
		assertEquals("1 스트라이크 1 볼", result.toString());
	}
	
}
