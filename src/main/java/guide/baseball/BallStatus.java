/**
 *
 */
package guide.baseball;

public enum BallStatus {
	NOTHING,BALL,STRIKE;
	// ENUM에 대해 공부하자!
;
	// enum도 똑같이 내부에 메서드를 가질 수 있어. 그럼 Balls에 있떤 로직이 이 안으로 들어오는거야.
	public boolean isNotNothing() {
		return this != NOTHING;
	}

	public boolean isStrike() {
		return this == STRIKE;
	}

	boolean isBall() {
		return this == BALL;
	}
}

/*
각각의 메서드에서 무엇인지를 판단하는 부분을 인터페이스로 구현할 수 있어.
인터페이스로 각각의 변수를 받으면 자바의 다형성을 이용해서 비즈니스로직에서 if문을 제거할 수 있어.
 */
*/