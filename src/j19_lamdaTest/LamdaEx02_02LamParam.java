package j19_lamdaTest;

//람다식을 메서드의 매개변수로 사용하는 경우
interface Printable2 {
	void print(String s);
}

public class LamdaEx02_02LamParam {

	public static void showString(Printable2 p, String s) {
		p.print(s);
	}

	public static void main(String[] args) {
		
		// showString(p, s)
		showString((s) -> {
			System.out.println(s);
		}, "What is Lambda?");
		// showString 메서드의 첫번째 매개변수로는 Printable2를 구현한 클래스가 필요하며
		// 이를 람다식으로 적용함.
	} // main
} // class
