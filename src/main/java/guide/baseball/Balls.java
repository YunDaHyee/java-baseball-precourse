package guide.baseball;

import java.util.ArrayList;
import java.util.List;

public class Balls {
	private List<Ball> answers;

	public Balls(List<Integer> answers) { // 리팩토링 : asList -> answers-정답인볼
		/*
		int를 가지고 있는데 앞에서 구혆했떤 Ball 클래스의 생성자에서 Ball을 갖다넣고 play에서 결과를 판독하게했는것처럼
		Balls에서도 int가 아니라 ball로 갖고있어야지, 그걸 활용해서 반복문 돌면서 비교하면서 결과를 판독할 수 있따.
		*/
		// List<Ball> balls = mapBall(answers); //리팩토링 ▼
		this.answers = mapBall(answers);
	}

	// 일단 무식하게 구현하는데 생성자가 긴 게 싫으면 메서드 추출 가능함.
	// 이거는 인스턴스 변수에 의존하는 데가 없으니까 static 써서 클래스 메소드 만들어줘도 돼
	private static List<Ball> mapBall(List<Integer> answers) {
		List<Ball> balls = new ArrayList<>();
		for( int i=0;i<3;i++ ) {
			balls.add(new Ball(i+1,answers.get(i)));
		}
		return balls;
	}

	public BallStatus play(Ball userBall) { // 리팩토링 : ball -> userBall-사용자가입력한볼. 이름 바뀐 게 무의미하면 원상복귀하는 것도 방법임.
		// 포문돌면서 answers에서 꺼내서 판독을 해야하는데 이 부분을 깔끔하게 구현하기 위해서 자바의 stream,람다,옵셔널을 해서 체인으로 한 라인으로 구현해본다.
		return answers.stream()
			.map(answer -> answer.play(userBall)) // map을 쓰면 세 개의 값에 대한 결과를 가지는 리스트 콜렉션을 반환함.
												// 이거를 통해서 결과는 Ball값이 아니라 실행결과된 BallStatus의 3개값을 가지는거야.
			//.filter(status -> status != BallStatus.NOTHING )// 낫싱이 아닌 경우만 필터를 하는 것. 그러면 어떤 경우만 생기냐면, strike이나 ball 둘 중 하나만 남게되는거지.
			//.filter(status -> status.isNotNothing() )// └>리팩토링 : enum도 jvm 내의 유일하게 하나만 존재하는 인스턴스야. 위에처럼 접근하면 필드에 직접 접근하는 거랑 마찬가지라서 메세지를 보내는 형태로 바꾸는거야. status한테 너 낫싱이 아니냐 하고 물어보는거지. 메세지를 보내는것! enum도 하나의 클래스고 객체이기 때문에 enum한테 메세지를 보낼 수 있어.
			.filter(BallStatus::isNotNothing )// └>리팩토링. 테스트가 있으니까 되는지 안되는지 해보고 하나씪 문법을 알아가는거야. 테스트의 좋은점이 리팩토링할 수 있따는 것도 있따.
			.findFirst() // 볼이나 스트라이크인 경우만 값이 있을거고 
			.orElse(BallStatus.NOTHING);//없을 경우에는 낫싱 리턴
		// 스트림과 람다 쓰고 함수형 프로그래밍 스타일임.
		// 비효율적인 부분도 있을거지만 스트림을 이용해서 구현할 수 있따.
		/*
		 구현할라면 포문돌고 해야하는데 스트림 이용해서 한번 해보는 것임..
		 왜냐면 테스트가 있으니까.
		 테스트가 있으면 얼마든지 내부를 바꾸면서 리팩토링 끊임없이 하면서
		 모르는 stream,map,filter,optional..같은 것들을 추가 학습하는 게 엄청 효과적이야.
		 이 후에 리팩토링 할 수 있어 또. 
		 */
	}
	/*
	 * 질문 : TDD에 용이하게 private 메서드를 자제하는 게 좋을까요?
	 => private 메서드는 당연히 생기고 지금 이걸 개발할 때 개발 편의성을 위해서 public 한다음에 거꾸러 만들어갔는데
	 실제로는 밑에거를 통해서 위에 play가 테스트 되는 것이기 떄문에
	 private 메소드를 굳이 테슽 ㅡ안해도돼.
	 public을 통해 테스트 하면 돼. 근데 구현하다보면 private인 위에 play에 로직이 있꼬 구체적으로 테스트하는 경향이 있는데
	 메서드를 분리하다보면 이런 요구들이 생겨.
	 그럴 떄에 실용적으로 썜은 테스트코드와 ㅍ로덕션 코드가 같은 패키지내에 있기 떄문에 패키지 접근제어자인 default로 변환을 해서 
	 이걸 싫어하는 원칙론자들이 있대.
	 그런 경우에 테스트를 위해서 private을 제거하는 게 맞느냐 하면서!
	 테스트 프레임워크를 활용하면 리플렉션을 이용해서 private 메서드를 호출할 수 있대.
	 하지만 그렇게까지 굳이 해야하는 주의래.
	 약간 실용주의적인 방법을 추구한대.
	 그리고 private을 테스트 해야하는 욕구가 생기는 경우에는
	 play메서드가 여기있는 게 맞는건지에 대한 의구심을 가져보는 것도 리팩토링의 힌트가 될 수 있따.
	 private play 메서드가 여기 ㅣ위치하는 게 아니라 다른 클래스로 이동하면은
	 얘는 오픈되자나. 그럼 얜 테스트 가능해지거든.
	 그럼 그런식으로 private인 메서드를 테스트 하면된다.
	 private 메서드를 잘게 쪼개서 private 많이 만드는 건 추천하는데
	 각각의 private 메섣르ㅡㄹ tdd로 단위테스트할 필요 없다.
	 왜냐면 대부분 public을 통해서 테스트를 할 수 있기 떄문에.
	 
	*/
	public PlayResult play(List<Integer> balls) {
		Balls userBalls = new Balls(balls);
		PlayResult result = new PlayResult();
		for( Ball answer : answers ) {
			BallStatus status = userBalls.play(answer); //answers에서 userBall과 컴퓨터볼이 같자나. Balls 인스턴스의 play를 호출하는거야.
			result.report(status); // 하나하나를 result에 담으면 된다. 내부에서 status에 따라서 결과를 판독해준다. 얘도 tdd로 개발할 수 있는데 얘를 통해서 개발하는 거니까 바로 갈 수 있따.
		}
		return result;
	}

}
