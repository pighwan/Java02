package mysqlJDBC02;

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
	// ResultSet의 Data 들을 결과를 처리할 요청 클래스로 전달 
	// => List 전달에 적합한 객체 필요 : ArrayList
	// => ArrayList 의 Type :  StudentVO
	public List<StudentVO> selectList() {
		sql="select * from student";
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
	
} //class
