package study;

import static org.assertj.core.api.Assertions.*;

import java.util.function.Supplier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StringTest {
	
	@Test
	void split_test() {
		String[] splitArr1 = "1,2".split(",");
		assertThat(splitArr1).containsExactly("1","2");
		
		String[] splitArr2 = "1".split(",");
		assertThat(splitArr2).containsExactly("1");
	}
	
	@Test
	void substring_test() {
		String subStr = "(1,2)".substring(1,4);
		assertThat(subStr).as("Substring Result").isEqualTo("1,2");
	}
	
	@Test
	@DisplayName("String의 charAt()로 특정 위치의 문자를 가져올 때 위치 값을 벗어나는지에 관한 테스트 ")
	void charat_test() {
		String str = "abc";
		int idx = 1;
	
		/*
		assertThatThrownBy( () -> {
			str.charAt(idx);
		}).isInstanceOf(StringIndexOutOfBoundsException.class)
		.hasMessageContaining("%w");
		*/
		assertThatExceptionOfType(StringIndexOutOfBoundsException.class).isThrownBy(() -> {
			str.charAt(idx);
		}).withMessageMatching("\\w+");
		
		// when
		/*catchThrowable(() -> {
			assertThat(str.charAt(idx)).isInstanceOf(StringIndexOutOfBoundsException.class);
		}).getMessage();*/
		
		// then
	}
}
