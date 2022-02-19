package mysqlJDBC01;

import java.sql.*;

public class Ex02_StudentMain {
	
	private static Connection cn = Ex01_DBConnection.getConnection();
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql;
	
	// ** DB Access Test01
	public static void listTest() {
		// 1) Connection 생성
		// => DBConnection.getConnection() 으로 생성
		
		// 2) SQL구문 실행
		sql="select * from student";
		try {
			st = cn.createStatement();
			rs = st.executeQuery(sql);
			// select 의 결과가 있는지 확인
			if (rs.next()) {
				// 3) 결과처리
				System.out.println("** Student List **");
				System.out.println("idno          | name |성별|age|java|html ");
				do {
					// Console 출력
					System.out.print(rs.getString("idno")+"  "); // 컬럼인덱스가능 -> 1 부터 가능
					System.out.print(rs.getString(2)+"  ");
					System.out.print(rs.getString(3)+"   ");
					System.out.print(rs.getInt(4)+"  ");
					System.out.print(rs.getInt(5)+"  ");
					System.out.print(rs.getInt(6)+"\n");
				}while(rs.next());				
			}else System.out.println("** 출력할 자료가 1건도 없습니다 **");
		} catch (Exception e) {
			System.out.println("** List Exception => "+e.toString());
		}
	} // listTest
	
	public static void detailTest(String idno) {
		// 1) Statement 
		sql = "select * from student where idno='"+idno+"'" ;
		// select * from student where idno='000713-4039888'
		// 2) PreparedStatement
		// => 바인딩변수 ? 사용가능 (? 없어도 적용가능)
		// => 실행전에 ? 에 값을 대입
		sql = "select * from student where idno=?" ;
		try {
			// 1) Statement 
			//st=cn.createStatement();
			//rs=st.executeQuery(sql);
			
			// 2) PreparedStatement
			pst = cn.prepareStatement(sql);
			pst.setString(1, idno);
			rs=pst.executeQuery();
			
			if (rs.next()) {
				System.out.println("** Student Detail **");
				System.out.print(rs.getString("idno")+"  "); 
				System.out.print(rs.getString(2)+"  ");
				System.out.print(rs.getString(3)+"   ");
				System.out.print(rs.getInt(4)+"  ");
				System.out.print(rs.getInt(5)+"  ");
				System.out.print(rs.getInt(6)+"\n");	
			}else System.out.println("** idno 에 해당하는 자료가 없습니다 **");
			
		} catch (Exception e) {
			System.out.println("** Detail Exception => "+e.toString());
		}
	} //detailTest
	
	public static void main(String[] args) {
		listTest();
		detailTest("880528-1111111"); // 없는자료 Test
	} //main
} //class
