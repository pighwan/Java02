package j19_lamdaTest;

interface Printable {
	// int i = 0 ;
	void print(String s);
} // Printable

class Printer implements Printable {
	public void print(String s) {
		System.out.println("LamdaTest1 =>" + s);
	}
} // Printer

public class LamdaEx01_01basic {

	public static void main(String[] args) {
		// 1) 일반적 방법
		// => interface Printable 의 구현 클래스 Printer 를 작성해놓고 사용
		Printable p1 = new Printer();
		p1.print("~ Hello Lamda !!!");

		// 2) 익명 내부 클래스 정의
		// => 구현클래스를 일회성으로 사용하는 경우
		Printable p2 = new Printable() {
			public void print(String s) {
				System.out.println("LamdaTest2 =>" + s);
			} // print
		};// 내부 클래스
		p2.print("~ 익명 클래스 Test");

		// 3) Lamda 식으로 표현 ( 자바 8 부터 추가 )
		// => 인터페이스가 하나의 추상메서드만 가지고 있는 경우
		// 이를 함수형 인터페이스(Functional Interface) 라 하며
		// 이러한 함수형 인터페이스를 구현하는 경우
		// 짧은 코드로 완성할수 있도록 지원하는것이 람다식.

		// ** @FunctionalInterface (열혈 671p)
		// => 함수형 인터페이스임을 확인하는 어노테이션
		// => 그러므로 위의 어노테이션이 붙은 인터페이스에 둘이상의 추상메서드가 존재하면 컴파일오류
		// => 그러나 구현부가 있는 static, default 선언이 붙은 메서드는 무관함.
		// => 미리 정의된 표준 함수형 인터페이스 (java.util.function 패키지 열혈 674p)
		//    Predicate<T>, Supplier<T>, Consumer<T>, Function<T,R>
		//    이들은 다양하게 활용할수 있도록 각각 추상메서드를 정의해놓고 있으므로
		//    필요에 따라 이용.
		
		// => 매개변수의 타입도 이미 정의되어 알수있으므로 생략가능
		// Printable p3 = (String s) -> {
		// Printable p3 = (s) -> {
		Printable p3 = s -> { 
			System.out.println("LamdaTest3 =>" + s); 
		};
		p3.print("~ Lamda 출력 ~~~");
		
		// 4) Simple Lamda 람다식
		// => 매개변수가 하나이면 소괄호() 생략가능
		// => 메서드의 몸체가 한문장인 경우 중괄호 { } 생략가능
		// 단, 이때는 문장끝의 세미콜론도 지워야 함.
		// => 단, 이 한문장이 return 문 이면 중괄호 생략 불가능.
		Printable p4 = s -> System.out.println("LamdaTest4 => " + s);
		p4.print("~ Hello Simple Lamda !!! ~~~");
		// 출력 : LamdaTest4 => ~ Hello Lamda !!! ~~~
		
		// 5) 메서드 참조  람다식
		// :: ( double colon operator , 정식 명칭은 method reference )
		// => 람다를 더 간결하게 표현하는 문법.
		// => 예제 LamdaEx01_2doubleColon.java 참고 
		Printable p5 = System.out::println;
		p5.print("LamdaTest5 => 메서드 참조 Lamda !!! ~~~");
		
		Printable pig = System.out::println;
		pig.print("왕돼지테스트 => 아니 이걸 하아..");
				
		
	} // main

} // class
