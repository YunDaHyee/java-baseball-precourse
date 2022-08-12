/**
 *
 */
package guide.baseball;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BallTest {
	private Ball computerBall; // ◎ 항상 중복이 발생하면 전역으로 빼주고

	@BeforeEach
	void setUp() {
		computerBall = new Ball(1, 4); // ◎ 각 메서드마다 중복으로 발생하는 부분들을 하나로 묶어주는 리팩토링 실행
	}

	@Test
	void nothing() {
		// STEP1) 여기서 코드를 작성한다. => STEP2) 다 작성하고 나면 컴파일에러를 해결하기 위해 여기서 작성된 클래스,메서드들을 생성한다.(인자명 이런 건 리팩토링 단계에서 해도 되니까 생성만 해) => 그랬더니 RUN 함
		//Ball computerBall = new Ball(1, 4);
		BallStatus status = computerBall.play(new Ball(2, 5)); // ▣ 사용자의 값 하나를 넣었을 때
		assertThat(status).isEqualTo(BallStatus.NOTHING); // ▣ Return 값으로 ENUM의 nothing이 나오는 식으로 만들 수 있음.
															// ▣ 짧은 부분에 대한 클래스 설계. 해당하는 기능을 구현하기 위한 클래스는 Ball.

		// 로컬변수를 첨부터 없앤 상태에서 하려면 힘들 수 있으니까 로컬변수에다가 두고 구현하면 그나마 더 수월함.
		// 나중에 리팩토링 해도 되거나 아님 걍 저대로 두든가 가능!
	}

	@Test
	void ball() {
		//Ball computerBall = new Ball(1, 4);
		BallStatus status = computerBall.play(new Ball(2, 4));
		assertThat(status).isEqualTo(BallStatus.BALL);
		// 테스트코드를 만들 때에 카피앤페이스트를 값만 조금씩 바꾸면서 중복이 많이 생겨
		// 그래서 첨이 만들기가 힘들지 두번째 테케부터는 빠르게 만들 수 있어.

	}

	@Test
	void strike() {
		BallStatus status = computerBall.play(new Ball(1, 4));
		assertThat(status).isEqualTo(BallStatus.STRIKE); // 일단 Ball에서 strike 관련 로직을 안짜면 Ball이 떨어짐
	}
}
