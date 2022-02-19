package j17_fileIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// ** 직렬화 가능한 객체 정의 후 Test
// => 객체 직렬화를 적용하려면 Serializable 을 implements 해야함.
//    java.io.NotSerializableException: file_IO.Box...
class Box implements Serializable {
	
	private static final long serialVersionUID = 1L;
	// => Serializable 을 implements 하고 위의 상수를 정의 하지 않으면 경고
	// => 객체직렬화 및 역직렬화 과정에서 클래스의 버전을 확인하는 중요한 용도
	String name;
	int age;
	
	public Box(String name, int age) {
		this.name=name;
		this.age=age;
	}
	public void printBox() {
		System.out.println("** name= "+name+" , age= "+age);
	}
} //Box

public class Ex04_02MyObjectIOStream {

	public static void main(String[] args) throws IOException {
		// ** Object 준비
		Box b1 = new Box("홍길동",100);
		Box b2 = new Box("Jerry",12);
		// ** 스트림 생성
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("objectBox.txt"));
		// 기존에 없는 상태에서 작업할때는 아웃풋스트림을 먼저 사용
		// 기존에 있는 상태라면 인풋스트림으로 사용해도 상관없음.
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("objectBox.txt"));
		try {
			// * 객체 직렬화 -> to 화일
			out.writeObject(b1);
			out.writeObject(b2);
			out.writeObject(new Box("Tom",20));
			// * 화일 역직렬화 -> 객체화
			String name = "김" ;
			Box nb1=(Box)in.readObject();
			nb1.printBox();
			name = "이" ;
			nb1=(Box)in.readObject();
			nb1.printBox();
			nb1=(Box)in.readObject();
			nb1.printBox();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// ** 스트림 소멸
			if (in!=null) in.close();
			if (out!=null) out.close();
		}
		System.out.println("** Object I/O Stream Test2 종료 **");
	} //main
} //class
