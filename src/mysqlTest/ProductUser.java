package mysqlTest;

public class ProductUser {
	private static ProductService service = new ProductService();
	public static void main(String[] args) {
		service.selectList();
		int lastPno = service.insert();
		System.out.println("** Insert 확인 **");
		service.selectList();
		service.update(lastPno);
		service.selectOne(lastPno);
		service.delete(lastPno);
		System.out.println("** Delete 확인 **");
		service.selectList();
	} //main
} //class
