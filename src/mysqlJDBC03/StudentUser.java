package mysqlJDBC03;

public class StudentUser {

	private static StudentService service = new StudentService();
	
	public static void main(String[] args) {
//		service.transactionTest();
		service.selectList();
//		service.insert();
//		System.out.println("** Insert 확인 **");
//		service.selectList();
//		service.update();
//		service.selectOne("123456-1234567");
//		service.delete();
//		System.out.println("** Delete 확인 **");
//		service.selectList();
	} //main

} //class
