package mysqlTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mysqlJDBC01.Ex01_DBConnection;
import mysqlJDBC02.StudentVO;

public class ProductDAO {
	
	// ** 전역변수 정의
	private Connection cn = Ex01_DBConnection.getConnection();
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	private String sql;
	
	// ** Read
	public List<ProductVO> selectList() {
		sql="select * from product";
		List<ProductVO> list = new ArrayList<ProductVO>();
		try {
			st=cn.createStatement();
			rs=st.executeQuery(sql);
			if (rs.next()) {
				do {
					ProductVO vo = new ProductVO();
					vo.setPno(rs.getInt(1));
					vo.setPname(rs.getString(2));
					vo.setColor(rs.getString(3));
					vo.setQuantity(rs.getInt(4));
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
	
	public ProductVO selectOne(ProductVO vo) {
		sql = "select * from product where pno = ?";
		try {
			pst=cn.prepareStatement(sql);
			pst.setInt(1, vo.getPno());
			rs=pst.executeQuery();
			if ( rs.next() ) {
				vo.setPno(rs.getInt(1));
				vo.setPname(rs.getString(2));
				vo.setColor(rs.getString(3));
				vo.setQuantity(rs.getInt(4));
			}else vo=null;
		} catch (Exception e) {
			System.out.println("** selectOne Exception => "+e.toString());
			vo=null;
		} //try
		return vo;
	} //selectOne
	
	// ** Create
	public int insert(ProductVO vo) {
		sql = "insert into product(pname,color,quantity) values (?,?,?)";
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1, vo.getPname());
			pst.setString(2, vo.getColor());
			pst.setInt(3, vo.getQuantity());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** insert Exception => "+e.toString());
			return 0;   
		}
	} //insert 
	
	public int lastPno() {
		sql = "select LAST_INSERT_ID() from product where pno = LAST_INSERT_ID() ;";
		try {
			pst=cn.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) return rs.getInt(1);
			else return 0;
		} catch (Exception e) {
			System.out.println("** lastPno Exception => "+e.toString());
			return 0;   
		}
	} //lastPno
	
	// ** Update
	public int update(ProductVO vo) {
		sql = "update product set color=?, quantity=? where pno=?" ;
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1, vo.getColor());
			pst.setInt(2, vo.getQuantity());
			pst.setInt(3, vo.getPno());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** update Exception => "+e.toString());
			return 0;
		}
	} //update
	
	// ** Delete
	public int delete(ProductVO vo) {
		sql = "delete from product where pno=?";
		try {
			pst=cn.prepareStatement(sql);
			pst.setInt(1, vo.getPno());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** delete Exception => "+e.toString());
			return 0;
		}
	} //delete
} //class
