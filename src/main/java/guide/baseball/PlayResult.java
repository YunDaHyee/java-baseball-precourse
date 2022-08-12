package guide.baseball;

import java.util.function.IntPredicate;

// 나중에 리팩토링할 것 훨씬 많기 한데 시간떔에 못하고 있는 것..
// 걍 어떤식으로 tdd 하는지 맛보기로 보여주는 거임
public class PlayResult {
	private int strike = 0;
	private int ball = 0;
	
	public int getStrike() {
		return this.strike;
	}

	public int getBall() {
		// TODO Auto-generated method stub
		return this.ball;
	}

	public void report(BallStatus status) {
		// 이넘도 꺼내서 확인하지말고 물어봐! 왜냐면 스트라이크 판단하는 부분에 로직이 바뀌면 어칼꺼야. 돌아다니면서 다 바꿔야되잖아. 이렇게면은 isStrike() 부분만 바꾸면 되니까. 
		// 이게 좀 더 객체지향적인 설계까 될 수 있어. 그레서 항상 객체가 가지고 있는 상태 데이터를 직접 접근하거나 get을 통해 접근하는 게 있따면 그렇게 하지말고 message를 보내서 상태 데이터를 가지는 데이터가 일을 하게 만들어라.
		// 그것만 하더라도 tdd할 수 있는 부분이 많다.
		//if( status == BallStatus.STRIKE){
		if( status.isStrike() ) { // └> 리팩토링 : 이렇게 직접 접근하기보다는 status한테 strike냐고 메시를 보내기! 메세지를 보내는 습관을 들이는 것도 좋다.
			this.strike += 1;
		}
		
		if( status.isBall() ) { // └> 리팩토링 : 이렇게 직접 접근하기보다는 status한테 strike냐고 메시를 보내기! 메세지를 보내는 습관을 들이는 것도 좋다.
			this.ball += 1;
		}
	}

	public IntPredicate isGameEnd() {
		return strike == 3; // 스트라이크가 3인지 내부에서 처리함!
	}

}
