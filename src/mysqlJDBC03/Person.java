package mysqlJDBC03;

import java.util.Calendar;

// ** Person
public class Person {
	protected String idno;
	protected String name;
	protected String gender;
	protected int age;
	// 생성자1
	public Person() {
		System.out.println("** Person Default 생성자 **");
	}
	// 생성자2
	public Person(String idno, String name) {
		super();
		System.out.println("** Person 초기화 생성자 **");
		this.idno = idno;
		this.name = name;
		// 1) 성별
		char c = idno.charAt(idno.indexOf("-")+1);
		if ( c=='1' || c=='3') gender ="남";
		else gender ="여";
				
		// 2) 나이 계산
		int year = Calendar.getInstance().get(Calendar.YEAR);
		age =  year - Integer.parseInt(idno.substring(0,2)) ; 
		if (c=='1' || c=='2') age-=1900;
		else age-=2000;
	}
	// setter 
	public void setIdno(String idno) {
		this.idno = idno;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setAge(int age) {
		this.age = age;
	}
	// getter
	public String getIdno() {
		return idno ;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public String getGender() {
		return gender;
	}
	// infoPrint()
	public void infoPrint() {
		System.out.println("** info Print **");
		System.out.println("* 번호 : "+getIdno());
		System.out.println("* 이름 : "+getName());
		System.out.println("* 나이 : "+getAge());
		System.out.println("* 성별 : "+getGender());
	}
	// toString
	@Override
	public String toString() {
		return "Person [idno=" + idno + ", name=" + name + ", age=" + age + ", gender=" + gender + "]\n";
	}

} //class
