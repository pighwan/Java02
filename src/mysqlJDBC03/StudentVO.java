package mysqlJDBC03;

// ** VO (Value Object) , DTO (Data Transfer Object)
//=> 자료의 구조를 표현하는 클래스이며, 자료의 전달에 이용됨
//=> 대부분 Table 별로 만들며, Table 과 동일한 필드명을 사용한다.
//=> Table과 무관하게 자료전달용으로만 정의한 경우 DTO 라 함.

// ** Person 활용하기
// => 중복 필드는 제거, 생성자 추가 (default, 초기화생성자)

public class StudentVO extends Person {
	
	private int java;
	private int html;
	private int sum;
	private double avg;
	
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	// ** 생성자 추가 (default, 초기화생성자)
	StudentVO() {System.out.println("** StudentVO default 생성자 **"); }
	StudentVO(String idno, String name, int java, int html) {
		super(idno,name);
		this.java=java;
		this.html=html;
		System.out.println("** StudentVO 초기화 생성자 **");
	}
	public int getJava() {
		return java;
	}
	public void setJava(int java) {
		this.java = java;
	}
	public int getHtml() {
		return html;
	}
	public void setHtml(int html) {
		this.html = html;
	}
	@Override
	public String toString() {
		return "StudentVO [idno=" + idno + ", name=" + name + ", gender=" + gender + ", age=" + age + ", java=" + java
				+ ", html=" + html + ", sum="+sum+", avg="+avg+ "]";
	}
} //class
