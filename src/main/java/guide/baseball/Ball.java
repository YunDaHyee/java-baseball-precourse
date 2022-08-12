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

public class Ball {
	// getMessage는 아니고 같은 private이지만 같은 클래스내에 있기때문에 접근 가능한 것이기 때문에 그냥 메세지형태로 보내는 게 나을 듯 하다.
	private final int position;
	//private int ballNo; // 객체지향생활체조 원칙의 원시값을 포장한다는 게 있는데 integer를 포장한다는건데 BallNumber 클래스를 만들어서 해본다.
	private final BallNumber ballNo; // └> 리팩토링 :  원시값 포장

	// TDD 싸이클의 3단계 - 리팩토링 : i->position, j->ballNo
	public Ball(int position, int ballNo) {
		// 이 안에 내용 채워넣는 것도 꺼구로 하는거야.
		// tdd 구현이 이런식으로 이뤄지는 것임.
		this.position = position;
		//this.ballNo = ballNo;
		this.ballNo = new BallNumber(ballNo); // └> 리팩토링 :  원시값 포장 -> 1~9 값 보장 가능
	}

	/**
	 * @param ball
	 * @return
	 */
	public BallStatus play(Ball ball) {
		//if( position == ball.position && ball.matchBallNo(ballNo)){ // ▩ 객체지향적 측면에서 생각해보면 이 방법은 절차지향적일 수 있어. 그리고 자바 객체가 갖고 있는 특성을 제대로 활용하지 못하는거야.
		if (this.equals(ball)) { // ▩ 위에 방법 대신 이런식으로 사고하는 게 좋다. this.equals(ball)랑 같은 표현. 더 안전하게 하려면 앞에 null인지 체크하는 부분이 있어야하는데 이거는 일단 안하고 넘어간다.
			return BallStatus.STRIKE;
		}// 여기 뒤부터는 포지션이 다르고 값만 같은지를 비교하는 것

		// 우선 포지션은 상관없고 볼 넘버가 같은지만 확인하는 것.
		// 같으면 볼을 반환하는 식으로 구현
		//if (ballNo == ball.ballNo) { // ♠ 객체의 필드에 접근하고 있는데 이것도 리팩토링 할 수 있다. 해당하는 필드에 직접 접근하는 것보다 객체 메시지를 보내는 형태로 바꿀 수 있다.
		if (ball.matchBallNo(ballNo)) { // ♠위에 것을 리팩토링한 결과. 메세지를 보내는 형태.
			return BallStatus.BALL;
		}
		return BallStatus.NOTHING; // 실행헀을 때 에러가 나면 빠르게 패스해서 안정감을 느끼는 것도 중요하기 때문에 이런식으로 잠깐 해두는 것. 그래서 어쨌든 성공단계를 만든다.
	}

	private boolean matchBallNo(int ballNo) {
		return this.ballNo == ballNo; // 리팩토링 전에 play() 내 if문의 조건문이 여기로 옴.
		// 나중에 여기에 또 다른 로직이 포함된다면 play() 내부는 변경 없이, matchBallNo() 내부만 변경하면 돼.
		// private이라 접근하는 데에 문제가 없다고 생각할 수 있지만 가능한 객체에 있는 인스턴스변수 필드에 직접 접근하기 보다는
		// 해당하는 객체에 메시지를 보내야한다는 사고를 하는 게 좋을 듯 하다 한다.
	}

}
