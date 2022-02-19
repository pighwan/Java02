package j19_lamdaTest;

import java.util.Random;

//1) 매개변수 둘, 반환형 void 
interface Calculate {
	void cal(int a, int b);
}

//2) 매개변수 둘, 반환형 int
interface CalculateR {
	int cal(int a, int b);
}

//3) 매개변수 1, 반환형 int
interface HowLong {
	int len(String s); // int 형 값을 반환하는 추상 메소드
}

//4) 매개변수가 없는 람다식
interface Generator {
	int rand();
}

class LamdaEx02_01Param {
	public static void main(String[] args) {
		// Test 1) 매개변수 둘, 반환형 void
		System.out.println("** Test 1) 매개변수 둘, 반환형 void **");
		Calculate c;
		c = (a, b) -> System.out.println(a + b);
		c.cal(4, 3);

		c = (a, b) -> System.out.println(a - b);
		c.cal(4, 3);

		c = (a, b) -> System.out.println(a * b);
		c.cal(4, 3);

		// Test 2) 매개변수 둘, 반환형 int
		System.out.println("** Test 2) 매개변수 둘, 반환형 int **");
		CalculateR r;
		r = (a, b) -> {
			return a + b;
		};
		System.out.println("Test 2.1) =>" + r.cal(4, 3));
		// => 메서드 몸체가 return문 이므로 중괄호 생략은 불가능 하지만
		// => 메서드의 몸체가 연산식 이면 그 처리 결과로 값이 남게되며
		// => 이때는 return을 명시하지 않아도 그 결과값이 return 대상이 됨.
		// => 그러므로 아래와 같은 람다식으로 표현 가능하며, 이것이 보편적인 방법이다.
		r = (a, b) -> a + b;
		System.out.println("Test 2.2) " + r.cal(4, 3));

		// Test 3) 연습 => 매개변수 1, 반환형 int
		System.out.println("** Test 3) 연습 => 매개변수 1, 반환형 int **");
		HowLong h = s -> s.length();
		System.out.println(h.len("I am so happy"));
		
		// Test 4) 매개변수 없는 람다식
		// => 매개변수를 표현하는 소괄호 ( ) 를 비워준다.
		// => 그러나 둘이상의 문장으로 구성된 람다식은 반드시 중괄호로 감싸야 됨.
		Generator gen = () -> {
			Random rand = new Random();
			return rand.nextInt(50);
		};
		System.out.println(gen.rand());

	} // main
} // class