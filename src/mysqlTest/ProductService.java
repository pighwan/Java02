package mysqlTest;

import java.util.List;

import mysqlJDBC02.StudentDAO;
import mysqlJDBC02.StudentVO;

public class ProductService {
	// ** 전역변수 정의
	private ProductDAO dao = new ProductDAO();
	private ProductVO vo = new ProductVO();
	
	public void selectList() {
		List<ProductVO> list = dao.selectList();
		// User 의 요청에 따라서 list를 해당되는 장치로 출력
		// => console 출력하기
		// => list 결과가 있으면 출력 / 아니면 Message 출력
		System.out.println("** selectList **");
		if (list != null) {
			for (ProductVO s:list) {
				System.out.println(s);
			} //for
		}else {
			System.out.println("=> 출력할 자료가 없습니다 **");
		}
	} //selectList
	
	public void selectOne(int pno) {
		System.out.println("** selectOne : "+pno+" **");
		vo.setPno(pno);
		vo=dao.selectOne(vo);
		if (vo!=null) {
			//출력
			System.out.println("* Pno     : "+ vo.getPno());
			System.out.println("* Name    : "+ vo.getPname());
			System.out.println("* Color   : "+ vo.getColor());
			System.out.println("* Quantity: "+ vo.getQuantity());
		}else {
			System.out.println("=> pno에 해당하는 자료가 없습니다.");
		}
	} //selectOne
	
	// Insert
	// => 입력시 auto_increment 로 만들어진 pno 를 return 하도록하여
	//    Update, Delete Test 에 이용함.
	public int insert() {
		vo.setPname("짜장면");
		vo.setColor("Green");
		vo.setQuantity(800);
		
		if (dao.insert(vo)>0) { // insert 성공
			// ** lastPno 확인하기 => DAO 에 메서드 추가
			System.out.println("** Insert 성공 **");
			return dao.lastPno(); 
		}else  {// insert 실패
			System.out.println("** Insert 실패 **");
			return 0;
		}
	} //insert
	
	// Update
	// => name, java, html 수정
	public void update(int pno) {
		vo.setPno(pno);
		vo.setColor("ColorTest");
		vo.setQuantity(999);
		if ( dao.update(vo)>0 ) 
			 System.out.println("** Update 성공 => "+pno);
		else System.out.println("** Update 실패 => "+pno);
	} //update
	
	// Delete
	public void delete(int pno) {
		vo.setPno(pno);
		if ( dao.delete(vo)>0 )
			 System.out.println("** Delete 성공 => "+pno);
		else System.out.println("** Delete 실패 => "+pno);
	} //delete
	
} //class
