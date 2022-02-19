package mysqlJDBC02;

import java.util.List;

public class StudentService {
	// ** 전역변수 정의
	private StudentDAO dao = new StudentDAO();
	private StudentVO vo = new StudentVO();
	
	public void selectList() {
		List<StudentVO> list = dao.selectList();
		// User 의 요청에 따라서 list를 해당되는 장치로 출력
		// => console 출력하기
		// => list 결과가 있으면 출력 / 아니면 Message 출력
		System.out.println("** selectList **");
		if (list != null) {
			for (StudentVO s:list) {
				System.out.println(s);
			} //for
		}else {
			System.out.println("=> 출력할 자료가 없습니다 **");
		}
	} //selectList
	
	public void selectOne(String idno) {
		System.out.println("\n** selectOne : "+idno+" **");
		vo.setIdno(idno);
		vo=dao.selectOne(vo);
		if (vo!=null) {
			//출력
			System.out.println("* Idno : "+ vo.getIdno());
			System.out.println("* Name : "+ vo.getName());
			System.out.println("* 성별  : "+ vo.getGender());
			System.out.println("* 나이  : "+ vo.getAge());
			System.out.println("* Java : "+ vo.getJava());
			System.out.println("* Html : "+ vo.getHtml());
			System.out.println("* 합계  : "+ (vo.getJava()+vo.getHtml()));
		}else {
			System.out.println("=> idno에 해당하는 자료가 없습니다.");
		}
	} //selectOne
	
	// Insert
	public void insert() {
		vo.setIdno("123456-1234567");
		vo.setName("김그린");
		vo.setGender("여");
		vo.setAge(33);
		vo.setJava(88);
		vo.setHtml(77);
		// ** 필요하지 않으면 변수 count 를 반드시 선언할 필요는 없음 
		// => 일반적으로 update() 메서드 처럼 작성함.  
		int count = dao.insert(vo);
		if (count>0) { // insert 성공
			System.out.println("** Insert 성공 **");
		}else // insert 실패
			System.out.println("** Insert 실패 **");
	} //insert
	
	// Update
	// => name, java, html 수정
	public void update() {
		vo.setIdno("123456-1234567");
		vo.setName("박나라");
		vo.setJava(80);
		vo.setHtml(90);
		if ( dao.update(vo)>0 ) 
			 System.out.println("** Update 성공 **");
		else System.out.println("** Update 실패 **");
	} //update
	
	// Delete
	public void delete() {
		vo.setIdno("123456-1234567");
		if ( dao.delete(vo)>0 )
			 System.out.println("** Delete 성공 **");
		else System.out.println("** Delete 실패 **");
		
	} //delete

} //class
