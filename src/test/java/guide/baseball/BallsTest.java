package guide.baseball;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

// 여기서 중복 많으니까 뺄 수 있어.
// 리팩토링 함 해보기
public class BallsTest {
	@Test
	void nothing() {
		// 값을 로컬변수로 쓰는 게 익숙할텐데 안쓰는 연습 들이기
		/*
		 TDD로 구현할 때 랜덤값을 컴터가 가지고 있을텐데
		 현재로써는 어떻게 로직이 처리되는지는 모름
		 하지만 세개의 값을 가지고 있따는 건 확실하니까
		 그걸 리스트로 갖고 있을거라고 추측하고 리스트를 던지는거야.
		 그걸 분리할 수 있어야돼. 그래야 TDD가 수월해져.
		 어딘가에서 만들어서 리스트를 갖고있을거다. 그 갖고있떤 값을 던지는거라고 생각하는 것.
		*/
		Balls answers = new Balls(Arrays.asList(1, 2, 3));
		BallStatus status = answers.play(new Ball(1, 4));
		assertThat(status).isEqualTo(BallStatus.NOTHING);
		// 값 3개에 대해서 사용자의 볼은 하나, 
	}
	
	@Test
	void ball() {
		Balls answers = new Balls(Arrays.asList(1, 2, 3));
		BallStatus status = answers.play(new Ball(1, 2));
		assertThat(status).isEqualTo(BallStatus.BALL);
	}
	
	@Test
	// play에서 strike 부분까지 예측해서 미리 했기떄문에 이거만 작성하면 바로 패쓰될 수 있어.
	// 여기까지 하고 리팩토링까지 했으면 커밋하고 다음 단계로 넘어가느 ㄴ것.
	void strike() {
		Balls answers = new Balls(Arrays.asList(1, 2, 3));
		BallStatus status = answers.play(new Ball(1, 1));
		assertThat(status).isEqualTo(BallStatus.STRIKE);
	}
	
	@Test
	//값을 answer가 다 갖고있으니까 여기서 구현 바로 가능.
	void play_nothing() {
		Balls answers = new Balls(Arrays.asList(1, 2, 3)); // 아까는 new Ball(1, 4) 이런식으로 하나의 값만 던졌는데 3개의 값을 가지도록 만드는 것.
		PlayResult result = answers.play(Arrays.asList(4,5,6)); // 값을 리턴 받을 때, 아까는 enum으로 받았는데 스트라이크 수도 가져야되고 볼의 수도 가질 수 있는 여러개가 될 수 있으니까 객체를 만드는거지.
		assertThat(result.getStrike()).isEqualTo(0);
		assertThat(result.getBall()).isEqualTo(0);
		// 낫싱은 굳이 알 필요가 없으니까 두개만 하는거야.
		// 항상 input과 output을 tdd에서 고민하는거야.
	}
	
	@Test
	void play_1strike_1ball() {
		Balls answers = new Balls(Arrays.asList(1, 2, 3));
		PlayResult result = answers.play(Arrays.asList(1,4,2));
		assertThat(result.getStrike()).isEqualTo(1);
		assertThat(result.getBall()).isEqualTo(1);
	}

	@Test
	void play_3strike() {
		Balls answers = new Balls(Arrays.asList(1, 2, 3));
		PlayResult result = answers.play(Arrays.asList(1,2.3));
		assertThat(result.getStrike()).isEqualTo(3);
		assertThat(result.getBall()).isEqualTo(0);
		assertThat(result.isGameEnd()).isTrue(); // 3스트라이크이면 게임 끝인지 체크
		// 이것도 역시 꺼내서 스트라이크가 3인지를 확인하는 게 아니라 메세지를 보내서 겜 끝났는지 물어봐
		// 무ㅏ너가를 처리할 떄 객체한테 메세지를 보내서 확인해! 객체지향 책에 항상 나오는 얘끼야. 객체에 데이터 자꾸 꺼낼라 하지말고! get을 해서 가져오는 게 아니라 is 이런식으로 물어봐!
		// result.getStrike() == 3 이런식으로 노노
	}
}
