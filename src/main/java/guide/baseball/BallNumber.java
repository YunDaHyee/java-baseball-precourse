package guide.baseball;

// 클래스분리할 떄 꼭 해보기! 원칙중에 원시값을 포장한다는 것!
public class BallNumber {
	public static final int MIN_NO = 1;
	public static final int MAX_NO = 9;
	
	private int no;
	
	/*
	 * 객체를 포장하게 되면 생성자에서 예외처리를 할 수 있어.
	 * 그러면 생성이 되면 1과 9라는 값은 딱 보장이 되는거야.
	 * 보장하는 차원에서 이렇게 객체 추가한다음에 Ball.java에서 쓰일 수 있어.
	 * 유효성체크도 UTILL 만들어서 처리하지말고 원시값이나 문자의 값 자체를 포장을 해서
	 * 여기다가 로직을 추가해갈 수 있어.
	 * 
	 */
	public BallNumber(int no) { 
		if( no < MIN_NO || no > MAX_NO ) {
			throw new IllegalArgumentException("볼 숫자는 1부터 9의 값이어야 합니다.");
		}
		this.no = no;
	}
}
