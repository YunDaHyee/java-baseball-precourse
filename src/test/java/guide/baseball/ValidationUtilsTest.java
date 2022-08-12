/**
 *
 */
package guide.baseball;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
	유효성 검사 TEST CODE
 */

public class ValidationUtilsTest {
	@Test
	@DisplayName("야구_숫자_1_9_검증") // 이게 커밋 메세지가 되는거야.
	void 야구_숫자_1_9_검증() {
		// TODOLIST에 작성했던 하나하나가 테케가 될 수 있는것.

		// ┎
		/*
				클래스명 같은 거 고민하기 귀찮으면 TDD에서 리팩토링할 수 있으니까 나중에 하고
				input,output이 중요한거니까 딱 그렇게 두개 먼저 작성하는 게 중요함. 클래스는 그 다음.
		*/
		// boolean result = validNo(9); // 이렇게 하고 난 다음에 여기서 구현하면 이렇게만 하면 되지만 클래스로 가져올꺼면 여기서 아래처럼 ValidationUtils 클래스 따로 생성해서 해줌
		/*
			boolean result = ValidationUtils.validNo(9); // ValidationUtils 클래스 생성 -> validNo 메소드 생성 : 이런식으로 거꾸로 만드는 것에 익숙해져야해
			assertThat(result).isTrue();
		*/
		// TDD 싸이클의 3단계 - 리팩토링
		assertThat(ValidationUtils.validNo(9)).isTrue(); // 로컬변수 필요없게 느껴져서 함수 리턴값이 인자값이 되도록 리팩토링
		// ┛이렇게까지 하면 TDD의 한 싸이클이 끝난거야.

		// ┎ TDD 2번째 싸이클 시작
		// 테스트에 대한 값은 경계값(1~9)이 유효한지를 체크하는 것이기 때문에 경계값을 가지고서 테스트를 해나가는 게 중요.
		assertThat(ValidationUtils.validNo(1)).isTrue();
		//이게 끝이야. 더이상 리팩토링할 게 없어. 실패하는 테스트를 만든 다음에 프로덕션 코드를 보라고 했었자나
		// ┛TDD 2번째 싸이클의 끝

		// ┎ TDD 3번째 싸이클 시작
		assertThat(ValidationUtils.validNo(0)).isFalse(); // 여기서 fail이 나면 이때서야 프로덕션 코드에 조건을 수정해줌.
		// ┛TDD 3번째 싸이클의 끝

		// ┎ TDD 4번째 싸이클 시작
		assertThat(ValidationUtils.validNo(10)).isFalse(); // 이 검증에서 경계값에 해당하는 수(0,1,9,10)가 테스트 데이터로 적합함.
		// 1~9라 해서 그걸 다 하는 것은 낭비이고 유지보수에 힘들기 때문에 가능한 적은 테스트데이터를 가지고 모든 경우의 수를 검증할 수 있는 게 최적의 테스트코드다!
		// ┛TDD 4번째 싸이클의 끝
	}

}
