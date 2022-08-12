/**
 *
 */
package guide.baseball;

/**
 	@Question

	@Input

	@Output

	@history

	@Date
		2021/02/
 */

public class ValidationUtils {

	/**
	 *
	 */
	public static final int MAX_NO = 9;
	/**
	 *
	 */
	public static final int MIN_NO = 1;

	/**
	 * @param i
	 * @return
	 */
	public static boolean validNo(/*int i*/ int no) {
		/*
			if (i <= 9) { // STEP2) 이 줄까지 구현하면 조건에 해당되면 성공을 리턴. TDD 싸이클의 2단계 - 성공단계
				return true;
			}
			return false; // STEP1) 이 줄만 구현하면 테스트에서 실패를 해. TDD 싸이클의 1단계 - 실패단계
		*/
		// TDD 싸이클의 3단계 - 리팩토링 : i -> no
		/*
			if (no <= 9) {
				return true;
			}
			return false;
		*/

		// 3번째 싸이클에서 fail가 났으니까 프로덕션 코드 수정 - 조건 수정
		// 테스트코드 기반으로 하면 프로덕트 코드에서 너무 로직 변하는 데에만 머리 안쓰고
		// 일단 구현해보고 fail 나면 그때부터 디버깅하면서 하나씩 수정해나가면 돼
		/*
			if (no >= 1 && no <= 9) {
				return true;
			}
			return false;
		*/

		// TDD 싸이클의 3단계 - 리팩토링 : 한 줄로 만들고 하드코딩 안하기 위해 상수로 뽑음.
		/*
			이런식으로 리팩토링을 지속적으로 한느 하는 것.
			각 싸이클의 단계별로 커밋을 하면 커밋이 넘 많아져.
			그래서 추천하는 건 한 기능에 대해 싸이클을 완성해서 각 테스트케이스를 추가해서  하는 거니까
			리팩토링까지 완성을 해서 만족하는 수준까지 만들고
			그 기능을 커밋하기. 즉 기능별로 커밋해라.
			그럼 단위도 명확하게 끊어져.
		*/
		return no >= MIN_NO && no <= MAX_NO;

	}

}
