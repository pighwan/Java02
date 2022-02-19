package j18_fileIOTest;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

// ** 과제
// => DB 에서  member Table 의 모든 Record, 모든 컬럼을 
//    메모장에서 볼 수 있는 텍스트 문서로 저장 한다.

// => 조건1) 화일경로는 프로젝트 위치에 member.txt 로 저장 하기
// => 조건2) 각 컬럼은 , 로 구분
// => 조건3) 레코드 별로 줄바꿈  

// ** 해결
// 1) DB 에서 memberList 가져오기
// 2) 스트림 생성
// 3) write
// 4) 메모장 에서 확인

public class MemberMain {
	
	static MemberDAO dao = new MemberDAO();

	public static void main(String[] args) throws IOException {
		// 1) DB 에서 memberList 가져오기
		// => console 출력하기
		List<MemberVO> list = dao.selectList();
		for (MemberVO vo:list) {
			System.out.println(vo);
		}
		// 2) 스트림 생성 & write
		// 2.1) byte 스트림의 객체직렬화
		// => Java Code 로는 확인가능하지만 메모장으로는 확인불가
		// => 문자스트림으로 처리해야 함
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("memberObj.txt"));
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("memberObj.txt"));
		try {
			// * 객체 직렬화 -> to 화일
			for (MemberVO vo:list) {
				out.writeObject(vo);
			}
			System.out.println("** ReadObject Stream **");
			while (true) {
				// * 객체 역직렬화 -> to vo
				MemberVO vo=(MemberVO)in.readObject();
				if (vo != null) System.out.println(vo);
				else break;
			}
		} catch (EOFException e) {
			System.out.println("** readObject 종료 => "+e.toString());
			System.out.println("** 자료 출력 완료 **");
		} catch (Exception e) {
			System.out.println("main Exception => "+e.toString());
		} finally {
			// ** 스트림 소멸
			if (in!=null) in.close();
			if (out!=null) out.close();
		}
		
		// 2.2) 문자 스트림으로 처리
		// => MemberMain2.java 에서 처리
		
		System.out.println("** Member Test 종료 **");
	} //main
} //class
