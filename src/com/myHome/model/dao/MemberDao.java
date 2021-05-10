	/**
 * 
 */
package com.myHome.model.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.myHome.common.JdbcTemplate;
import com.myHome.model.dto.Member;
import com.myHome.util.Utility;


/**
 * <pre>
 * 회원 테이블에 대한 DAO 클래스
 * </pre>
 * 
 * @author 강하영
 *
 */
public class MemberDao implements Serializable{
	private static MemberDao instance = new MemberDao(); 
	

	public static MemberDao getInstance() {
		return instance;	
	}

	/**
	 * 로그인
	 * @param memberId 아이디
	 * @param memberPw 비밀번호
	 * @return 등급, 미존재시 null
	 */
	public void login(Connection conn, Member dto) throws Exception{
		String sql = "select * from member where member_id=? and member_pw=?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {			
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getMemberId());
			stmt.setString(2, dto.getMemberPw());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {				
				dto.setMemberId(rs.getString("member_id"));
				dto.setMemberPw(rs.getString("member_pw"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setMobile(rs.getString("mobile"));
				dto.setZipcode(rs.getInt("zip_code"));
				dto.setAddress1(rs.getString("address1"));
				dto.setAddress2(rs.getString("address2"));
				dto.setBusinessNumber(rs.getString("business_number"));
				dto.setCompanyName(rs.getString("company_name"));
				dto.setEntryDate(rs.getString("entry_date"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setGrade(rs.getString("grade"));
								
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JdbcTemplate.rollback(conn);
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);			
		}		
	}
	
	/**
	 * 일반 회원 등록
	 * @param dto 회원
	 * @return 성공시 1, 실패시 0
	 */
	public int insertMember(Connection con,Member dto) {
		String sql = "insert into member values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

		PreparedStatement stmt = null;
		
		try {
			
			stmt = con.prepareStatement(sql);
			stmt.setString(1, dto.getMemberId());
			stmt.setString(2, dto.getMemberPw());
			stmt.setString(3, dto.getName());
			stmt.setString(4, dto.getEmail());
			stmt.setString(5, dto.getMobile());
			stmt.setInt(6, dto.getZipcode());
			stmt.setString(7, dto.getAddress1());
			stmt.setString(8, dto.getAddress2());	
			stmt.setString(9, dto.getBusinessNumber());
			stmt.setString(10, dto.getCompanyName());
			stmt.setString(11, Utility.getCurrentDate(dto.getEntryDate()));			
			stmt.setInt(12, dto.getMileage());
			stmt.setString(13, dto.getGrade());	
			
			return stmt.executeUpdate();	
								
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {			
			JdbcTemplate.close(stmt);
		}
		return 0;
	
	}

	/**
	 * 판매자 회원 등록
	 * @param dto 회원
	 * @return 성공시 1, 실패시 0
	 */
	public int insertSeller(Member member) {
		String sql = "insert into member values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			stmt.setString(3, member.getName());
			stmt.setString(4, member.getEmail());
			stmt.setString(5, member.getMobile());
			stmt.setInt(6, member.getZipcode());
			stmt.setString(7, member.getAddress1());
			stmt.setString(8, member.getAddress2());
			stmt.setString(9, member.getBusinessNumber());
			stmt.setString(10, member.getCompanyName());
			stmt.setString(11, Utility.getCurrentDate(member.getEntryDate()));
			stmt.setString(12, member.getGrade());	

			JdbcTemplate.commit(conn);			
			return stmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JdbcTemplate.rollback(conn);
			e.printStackTrace();
		} finally {			
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
		return 0;		
	}
	
	/**
	 * 아이디 중복 체크
	 * @param memberId 회원아이디
	 * @return 회원, 미존재시 null
	 */
	public int selectCheckId(Connection con, String memberId){
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select member_id from member where member_id=?";
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, memberId);
			rs = stmt.executeQuery();
			
			// return rs.next()
			if(rs.next()) {				
				return 1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();			
			
		} finally { 
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);			
		}
		return 0;
	}

	/**
	 * 일반회원 상세조회
	 * @param memberId 회원아이디
	 * @return 회원, 미존재시 null
	 */
	public void selectOneMember(Connection con,Member dto) throws Exception{
		String sql = "select member_id, member_pw, name, mobile, email, zip_code, address1, address2, to_char(entry_date,'yyyy-mm-dd'), "
				+ "mileage,grade from member where member_id=?";

		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {		
			stmt = con.prepareStatement(sql);
			stmt.setString(1, dto.getMemberId());
			rs = stmt.executeQuery();
			
			if(rs.next()) {			
				
				dto.setMemberId(rs.getString("member_id"));
				dto.setMemberPw(rs.getString("member_pw"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setMobile(rs.getString("mobile"));
				dto.setZipcode(rs.getInt("zip_code"));
				dto.setAddress1(rs.getString("address1"));
				dto.setAddress2(rs.getString("address2"));		
				dto.setEntryDate(rs.getString("to_char(entry_date,'yyyy-mm-dd')"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setGrade(rs.getString("grade"));

			}			
		} catch (SQLException e) {
			System.out.println(e.getMessage());			
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);			
		}
		
	}
	
	/**
	 * 판매자회원 상세조회
	 * @param memberId 회원아이디
	 * @return 회원, 미존재시 null
	 */
	public void selectOneSeller(Connection con,Member dto) throws Exception{
		String sql = "select member_id, member_pw, name, mobile, email, zip_code, address1, address2, business_number, company_name"
				+ ",to_char(entry_date,'yyyy-mm-dd'), grade from member where member_id=?";

		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {		
			stmt = con.prepareStatement(sql);
			stmt.setString(1, dto.getMemberId());
			rs = stmt.executeQuery();
			
			if(rs.next()) {				
				
				dto.setMemberId(rs.getString("member_id"));
				dto.setMemberPw(rs.getString("member_pw"));
				dto.setName(rs.getString("name"));
				dto.setMobile(rs.getString("mobile"));
				dto.setEmail(rs.getString("email"));
				dto.setZipcode(rs.getInt("zip_code"));
				dto.setAddress1(rs.getString("address1"));
				dto.setAddress2(rs.getString("address2"));
				dto.setBusinessNumber(rs.getString("business_number"));
				dto.setCompanyName(rs.getString("company_name"));				
				dto.setEntryDate(rs.getString("to_char(entry_date,'yyyy-mm-dd')"));				
				dto.setGrade(rs.getString("grade"));
				
			}			
		} catch (SQLException e) {
			System.out.println(e.getMessage());			
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);			
		}		
	}
	/**
	 * 관리자 권한
	 * 		- 전체회원조회(일반 회원, 판매자 회원)
	 * @return ArrayList<Member>
	 */
	public void selectList(Connection conn,ArrayList<Member> list, String searchKey, String keyWord) throws Exception{//

		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select member_id, member_pw, name, mobile, email, zip_code, address1, address2, business_number, grade from member where "
					+ "not(member_id='admin')";
			
			if(keyWord != null && !keyWord.equals("") ){
                sql +=" and "+searchKey.trim()+" LIKE '%"+keyWord.trim()+"%' order by entry_date desc";
            }else {//모든 레코드 검색
                sql +=" order by entry_date desc";
            }

			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			Member dto = null;			
			while(rs.next()) {
				dto = new Member();
				
				dto.setMemberId(rs.getString("member_id"));
				dto.setMemberPw(rs.getString("member_pw"));
				dto.setName(rs.getString("name"));
				dto.setMobile(rs.getString("mobile"));
				dto.setEmail(rs.getString("email"));
				dto.setZipcode(rs.getInt("zip_code"));
				dto.setAddress1(rs.getString("address1"));
				dto.setAddress2(rs.getString("address2"));
				dto.setBusinessNumber(rs.getString("business_number"));				
				dto.setGrade(rs.getString("grade"));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new Exception();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);			
		}

	}
	

	/**
	 * 아이디 찾기 
	 * @param name 이름
	 * @param mobile 휴대폰
	 * @return
	 */
	public String selectByMemberId(String name, String mobile) {
		String sql = "select member_id from member where name=? and mobile=?";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, mobile);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				return rs.getString("member_id");
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JdbcTemplate.rollback(conn);
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
		return null;
	}

	/**
	 * 비밀번호 찾기 
	 * @param memberId 아이디
	 * @param name 이름
	 * @param mobile 휴대폰
	 * @return 존재시 true, 미존재시 null
	 */
	public boolean selectByMemberPw(String memberId, String name, String mobile) {
		System.out.println("[debug] dao : " + memberId + ", " + name + ", " + mobile);
		String sql = "select 1 from member where member_id=? and name=? and mobile=?";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcTemplate.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setString(2, name);
			stmt.setString(3, mobile);
			
			rs = stmt.executeQuery();
	
			return rs.next();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JdbcTemplate.rollback(conn);
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
		
		return false;
	}

	/**
	 * 비밀번호 찾기 : 임시발급암호 변경
	 * @param memberId 아이디
	 * @param tempMemberPw 변경 임시비밀번호
	 * @return 성공시 1, 실패시 0
	 */
	public int updateByMemberPw(String memberId, String tempMemberPw) {
		String sql = "update member set member_pw=? where member_id=?";

		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tempMemberPw);
			stmt.setString(2, memberId);
			
			int result = stmt.executeUpdate();
			JdbcTemplate.commit(conn);
			
			return result;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JdbcTemplate.rollback(conn);
			e.printStackTrace();
		} finally {			
			
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
		return 0;
	}	
	
	/**
	 * 일반 회원
	 * 		-- 내정보 수정
	 * @param dto Member
	 * @return 성공시 true, 실패시 false
	 */
	public void updateMyInfo(Connection con,Member dto) throws Exception{
		String sql = "update member set member_pw=?, name=?, mobile=?, email=?, "
				+ "zip_code=?, address1=?, address2=? where member_id=?";

		PreparedStatement stmt = null;
		
		try {			
			stmt = con.prepareStatement(sql);
			stmt.setString(1, dto.getMemberPw());
			stmt.setString(2, dto.getName());
			stmt.setString(3, dto.getMobile());
			stmt.setString(4, dto.getEmail());
			stmt.setInt(5, dto.getZipcode());
			stmt.setString(6, dto.getAddress1());
			stmt.setString(7, dto.getAddress2());
			stmt.setString(8, dto.getMemberId());
			
			int rows =stmt.executeUpdate();
			if(rows == 0) {
				throw new Exception();
			}
			
		} catch (SQLException e) {			
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(stmt);			
		}		
	}
	
	/**
	 * 판매자 회원
	 * 		-- 내정보 수정
	 * @param dto Member
	 * @return 성공시 true, 실패시 false
	 */
	public void updateSellerInfo(Connection con,Member dto) throws Exception{
		String sql = "update member set member_pw=?, name=?, mobile=?, email=?, "
				+ "zip_code=?, address1=?, address2=?, business_number=?, company_name=? where member_id=?";

		PreparedStatement stmt = null;
		
		try {			
			stmt = con.prepareStatement(sql);
			stmt.setString(1, dto.getMemberPw());
			stmt.setString(2, dto.getName());
			stmt.setString(3, dto.getMobile());
			stmt.setString(4, dto.getEmail());
			stmt.setInt(5, dto.getZipcode());
			stmt.setString(6, dto.getAddress1());
			stmt.setString(7, dto.getAddress2());
			stmt.setString(8, dto.getBusinessNumber());
			stmt.setString(9, dto.getCompanyName());
			stmt.setString(10, dto.getMemberId());
			
			int rows = stmt.executeUpdate();
			
			if (rows == 0) {
				throw new Exception();
			}
		} catch (SQLException e) {			
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(stmt);		
		}		
	}
	
	/**
	 * 관리자
	 * 		-- 내정보 수정
	 * @param dto Member
	 * @return 성공시 true, 실패시 false
	 */
	public void updateAdminInfo(Connection con,Member dto) throws Exception{
		String sql = "update member set member_pw=?, name=?, mobile=?, email=?, "
				+ "zip_code=?, address1=?, address2=? where member_id=?";
		
		PreparedStatement stmt = null;
		
		try {			
			stmt = con.prepareStatement(sql);
			stmt.setString(1, dto.getMemberPw());
			stmt.setString(2, dto.getName());
			stmt.setString(3, dto.getMobile());
			stmt.setString(4, dto.getEmail());
			stmt.setInt(5, dto.getZipcode());
			stmt.setString(6, dto.getAddress1());
			stmt.setString(7, dto.getAddress2());
			stmt.setString(8, dto.getMemberId());	
			
			int rows =stmt.executeUpdate();
			if(rows == 0) {
				throw new Exception();
			}
			
		} catch (SQLException e) {			
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(stmt);			
		}		
	}
	
	/**
	 * 회원 상세조회
	 * @param memberId 회원아이디
	 * @return 회원, 미존재시 null
	 */
	public void selectMemberDetail(Connection conn, Member dto,String memberId) throws Exception{
		String sql = "select member_id, member_pw, name, mobile, email, zip_code, address1, address2, business_number, company_name, "
				+ "to_char(entry_date,'yyyy-mm-dd'), mileage, grade from member where member_id=?";

		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			rs = stmt.executeQuery();
			
			if(rs.next()) {				
				dto.setMemberId(rs.getString("member_id"));
				dto.setMemberPw(rs.getString("member_pw"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setMobile(rs.getString("mobile"));
				dto.setZipcode(rs.getInt("zip_code"));
				dto.setAddress1(rs.getString("address1"));
				dto.setAddress2(rs.getString("address2"));
				dto.setBusinessNumber(rs.getString("business_number"));
				dto.setCompanyName(rs.getString("company_name"));				
				dto.setEntryDate(rs.getString("to_char(entry_date,'yyyy-mm-dd')"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setGrade(rs.getString("grade"));

			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
			
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}		
	}

	/**
	 * 관리자 상세조회
	 * @param memberId 회원아이디
	 * @return 회원, 미존재시 null
	 */
	public void selectOneAdmin(Connection con,Member dto) throws Exception{
		String sql = "select member_id, member_pw, name, mobile, email, zip_code, address1, address2, "
				+ "to_char(entry_date,'yyyy-mm-dd'), grade from member where member_id=?";

		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {		
			stmt = con.prepareStatement(sql);
			stmt.setString(1, dto.getMemberId());
			rs = stmt.executeQuery();
			
			if(rs.next()) {			
				
				dto.setMemberId(rs.getString("member_id"));
				dto.setMemberPw(rs.getString("member_pw"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setMobile(rs.getString("mobile"));
				dto.setZipcode(rs.getInt("zip_code"));
				dto.setAddress1(rs.getString("address1"));
				dto.setAddress2(rs.getString("address2"));		
				dto.setEntryDate(rs.getString("to_char(entry_date,'yyyy-mm-dd')"));				
				dto.setGrade(rs.getString("grade"));

			}			
		} catch (SQLException e) {
			System.out.println(e.getMessage());			
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);			
		}
		
	}
	

	
	/**
	 * 회원 탈퇴
	 * @param memberId
	 * @return
	 */
	public boolean deleteMember(String memberId) {
		String sql = "delete from member where member_id=?";

		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			
			int rows = stmt.executeUpdate();
			JdbcTemplate.commit(conn);
			if (rows == 1) {
				return true;
			}
		} catch (SQLException e) {
			JdbcTemplate.rollback(conn);
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
		return false;	
	}


}