package mysqlJDBC03;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import mysqlJDBC01.Ex01_DBConnection;

// ** DAO(Data Access Object)
// => SQL 구문 처리
// => CRUD 구현 
//    Create(insert), Read(select), Update, Detete

public class StudentDAO {
	// ** 전역변수 정의
	private Connection cn = Ex01_DBConnection.getConnection();
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	private String sql;
	
	// ** Read
	public int rowsCount() {
		sql="select count(*) from student" ;
		try {
			st=cn.createStatement();
			rs=st.executeQuery(sql);
			rs.next();
			return rs.getInt(1);
		} catch (Exception e) {
			System.out.println("rowsCount Exception => "+e.toString());
			return 0;
		} //try
	} // rowsCount
	
	public List<StudentVO> selectList() {
		//sql="select * from student";
		sql="select idno,name,gender,age,java,html,(java+html) sum, (java+html)/2 avg from student";
		List<StudentVO> list = new ArrayList<StudentVO>();
		//StudentVO vo = new StudentVO(); // XXXX 주의하세요 !!!
		try {
			st=cn.createStatement();
			rs=st.executeQuery(sql);
			if (rs.next()) {
				do {
					StudentVO vo = new StudentVO();
					vo.setIdno(rs.getString(1));
					vo.setName(rs.getString("name"));
					vo.setGender(rs.getString(3));
					vo.setAge(rs.getInt(4));
					vo.setJava(rs.getInt(5));
					vo.setHtml(rs.getInt(6));
					vo.setSum(rs.getInt(7));
					vo.setAvg(rs.getDouble(8));
					list.add(vo);
				}while(rs.next());
			}else {
				System.out.println(" ~~ 출력할 자료가 1건도 없습니다 ~~");
				list = null;
			}
		} catch (Exception e) {
			System.out.println("** selectList Exception => "+e.toString());
			list = null;
		}
		return list;
	} //selectList
	
	public StudentVO selectOne(StudentVO vo) {
		sql = "select * from student where idno = ?";
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1, vo.getIdno());
			rs=pst.executeQuery();
			if ( rs.next() ) {
				vo.setIdno(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setGender(rs.getString(3));
				vo.setAge(rs.getInt(4));
				vo.setJava(rs.getInt(5));
				vo.setHtml(rs.getInt(6));
			}else vo=null;
		} catch (Exception e) {
			System.out.println("** selectOne Exception => "+e.toString());
			vo=null;
		} //try
		return vo;
	} //selectOne
	
	// ** Create
	public int insert(StudentVO vo) {
		sql = "insert into student values(?,?,?,?,?,?)";
		// insert into student values("890528-2659884","김그린","여",10,50,60)
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1, vo.getIdno());
			pst.setString(2, vo.getName());
			pst.setString(3, vo.getGender());
			pst.setInt(4, vo.getAge());
			pst.setInt(5, vo.getJava());
			pst.setInt(6, vo.getHtml());
//			int count = pst.executeUpdate();
//			return count ;
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** insert Exception => "+e.toString());
			return 0;   
		}
	} //insert 
	
	// ** Update
	public int update(StudentVO vo) {
		sql = "update student set name=?, java=?, html=? where idno=?" ;
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1, vo.getName());
			pst.setInt(2, vo.getJava());
			pst.setInt(3, vo.getHtml());
			pst.setString(4, vo.getIdno());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** update Exception => "+e.toString());
			return 0;
		}
	} //update
	
	// ** Delete
	public int delete(StudentVO vo) {
		sql = "delete from student where idno=?";
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1, vo.getIdno());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** delete Exception => "+e.toString());
			return 0;
		}
	} //delete
	
	// ** Transaction Test 
	// => Connection 객체가 관리
	// => 기본값은 AutoCommit  true 임.
	// => setAutoCommit(false) -> commit 또는 rollback 
	// 1) Test1 
	// => Transaction 적용하지 않고 오류 유발
	// => 동일 자료 2회 입력 : 첫번째는 입력되고 , 두번째 입력에서 Exception 발생
	// => Data 의 무결성에 영향을 주게됨
	// 2) Test2
	// => Transaction 적용하고 오류 유발
	// => 동일 자료 2회 입력 : 첫번째 입력후, 두번째 입력에서 Exception 발생하지만 모두 rollback 됨.
	//    즉, 모두 입력되지 않음 
	public void transactionTest() {
		try {
			cn.setAutoCommit(false);
			transactionInsert();
			transactionInsert(); // 키중복 오류
			// 정상실행 => commit
			cn.commit();
		} catch (Exception e) {
			System.out.println("transactionTest Exception => "+e.toString());
			// Exception 발생 => rollback
			try {
				cn.rollback();
				System.out.println("** Rollback 성공 **");
			} catch (Exception e2) {
				System.out.println("rollback Exception => "+e2.toString());
			}  // rollback_try
		} // transaction_try
	} //transactionTest
	
	public void transactionInsert() throws Exception {
		sql = "insert into student values('010713-3039888','Green2','여',30,90,80)" ;
		pst=cn.prepareStatement(sql);
		pst.executeUpdate();
	} //transactionInsert()
	
} //class
