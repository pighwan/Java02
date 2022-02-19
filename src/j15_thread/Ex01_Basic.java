package j15_thread;

// ** Thread 클래스 구현
// => Thread 를 적용할 수 있는 클래스를 구현
// 1) Thread 클래스 상속
// 2) Runnable interface 구현

//** 계층구조 : Runnable interface -> Thread 클래스
//=> Thread 클래스에 Thread 를 지원하는 다양한 메서드 들이 정의 되어있음 
//=> Thread 클래스 가 Runnable 을 구현함. 
//   public class Thread implements Runnable { ....   }

//=> Thread 클래스의 생성자 중 Runnable을 매개변수로 하는 생성자가 있음
//	 -> Runnable 을 구현한 MyThread02 생성시 이용됨
//   public Thread(Runnable target) {
//   	this(null, target, "Thread-" + nextThreadNum(), 0);
//   }

//** run() 메서드
//=> thread 실행의 주체 메서드
//   생성된 thread 의 main 메서드
//=> thread 를 통해 실행하려는 기능 (객체가 해야되는 일) 을 여기에 작성함.
//=> start() 메서드 호출에 의해 실행됨.
//   자신의 일을 다 마치면 (run 메서드의 종료) 자동으로 소멸됨   
//** start() 메서드
//=> Thread 를 시작함 (Runnable 상태로)

class MyThread01 extends Thread {
	@Override
	public void run() {
		//super.run();
		for (int i=0; i<50; i++) {
			//조상 Thread의 getName() 호출
			System.out.printf("* Thread01 i=%d, Thread_Name=%s \n",i,getName());
		}
	} //run
} //MyThread01

class MyThread02 implements Runnable {
	@Override
	public void run() {
		for (int i=0; i<50; i++) {
			// Thread 클래스에 정의된 getName() 직접 호출 불가능
			// => Thread.currentThread() : 현재 실행중인 Thread 를 반환함 
			System.out.printf("* Thread02 i=%d, Thread_Name=%s \n",i, Thread.currentThread().getName());
		}
	} //run
} //MyThread02 

public class Ex01_Basic {

	public static void main(String[] args) {
		// 1. 생성
		// 1.1) Thread01 생성
		// => Thread를 상속했으므로 생성시에 Thread 클래스도 생성됨 
		MyThread01 t01 = new MyThread01();
		
		// 1.2) Thread02 생성
		//MyThread02 tr = new MyThread02(); // OK
		Runnable tr = new MyThread02();     // OK 
		// => Thread 와 무관한 일반클래스 생성
		//    Thread 에서 제공하는 메서드등 맴버 접근불가 ( tr.start() -> XXX )
		Thread t02 = new Thread(tr);
		// => Runnable 의 참조값(인스턴스)을 이용하여 Thread 를 생성해야 함
		// => Thread 클래스의 생성자 중에는 Runnable 인터페이스의 참조값(인스턴스)을 인자로 하는 생성자가 구현되어있음				
		//    생성자 Thread(Runnable target)
		
		// Thread t02 = new Thread(new MyThread02());
		// => 불필요한 tr 인스턴스 생성 없이 바로 처리해도됨
		
		// 2. 실행
		// => start() 호출 : thread Start -> run() 메서드 실행
		t01.start(); 
		t02.start();
		
		// 3. 결과 비교
		// => multi thread 는 실행되지 않고 main 이 일반 메서드 호출 실행.  
		//    thread 적용없이 순서대로 실행 
//		t01.run();
//		t02.run();

		System.out.println("** Program_main() Stop **");
	} //main
} //class
