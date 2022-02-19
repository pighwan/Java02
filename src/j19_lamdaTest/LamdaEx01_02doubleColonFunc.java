package j19_lamdaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// ** 메서드 참조  람다식 ::
// :: ( double conlon operator , 정식 명칭은 method reference )
// => 람다를 더 간결하게 표현하는 문법.
// => 람다식이 하나의 메서드만을 호출하는 경우 메소드 참조로 람다식을 간단하게 표현.
//	  메소드를 참조해서 매개변수의 정보 및 리턴타입을 미리 알아내어,
//	  람다식에서 사용하는 매개 변수를 생략하는 방식의 표현법.

// => 대상::메소드 (대상은 클래스 또는 참조변수가 될수있음)
//	  대상에서 메소드의 정보를 추출하여 람다식 처럼 익명 구현객체를 생성하는것.
//    참조하는 메소드의 매개변수를 생략할 수 있음.
// => ppt 4p 
// => https://myhappyman.tistory.com/65

//** Generic 타입제한 (Wildcards_와일드카드타입 이용으로)
//=> <?>
// Unbounded Wildcards (제한없음_모든 클래스나 인터페이스 타입 가능)
//=> <? extends ...>
// Upper Bounded Wildcards (상위클래스 제한_같거나 하위 타입 가능)
//=> <? super ...>
// Lower Bounded Wildcards (하위클래스 제한_ 같거나 상위타입 가능)

public class LamdaEx01_02doubleColonFunc {

	public static void main(String[] args) {
		// 1) 단순 반복문 출력
		List<String> names = new ArrayList<String>();
		names.add("Matilda");
		names.add("Leon");
		names.add("Naples");
		names.add("Harry Potter");
		names.add("Iron Man");
		System.out.println("** 1) 단순 반복문 출력 **");
		for(int i=0; i<names.size(); i++) {
			System.out.println(names.get(i));
		}
		
		// 2) forEach 메서드를 이용해 Lamda 적용
		// => void forEach(Consumer<? super String> action)
		// => Consumer<? super String> : 같거나 상위타입 가능
		// => Consumer 클래스 소스 확인	
		//	  - @FunctionalInterface
		//    - void accept(T t);   
		//	  - 인자는 전달 받지만 return 하지않는 즉, 인자를 소비하는 형태의 추상메서드
		//	  - 그러므로 인자를 가지고 어떤 결과를 보여야 할때 유용하게 사용할 수 있음.    
		
		System.out.println("** 2) forEach 메서드를 이용해 Lamda 적용 **");
		names.forEach(d-> System.out.println(d));
		
		// 3) 메소드 참조(::) 적용
		System.out.println("** 3) 메소드 참조(::) 적용 **");
		names.forEach(System.out::println);
		
		// 4) Function<T,R> 사용예
		// => T type 의 값을 받아 R type 으로 Return 하는 apply 추상메서드를 가지고 있음.
		//    R apply(T t); 
		//Function<String, Integer> f1 = (String s) -> Integer.parseInt(s);
		Function<String, Integer> f1 = s -> Integer.parseInt(s);
		Function<String, Integer> f2 = Integer::parseInt;
		System.out.println("** 4) Function<T,R> 사용예 => "
				+ (f1.apply("123")+f2.apply("500"))); 
	} // main

} // class
