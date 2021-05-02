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
	public String login(String memberId, String memberPw) {
		String sql = "select grade from member where member_id=? and member_pw=?";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcTemplate.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setString(2, memberPw);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				return rs.getString("grade");
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
	 * 일반 회원 등록
	 * @param dto 회원
	 * @return 성공시 1, 실패시 0
	 */
	public int insertMember(Member dto) {
		String sql = "insert into member values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getMemberId());
			stmt.setString(2, dto.getMemberPw());
			stmt.setString(3, dto.getName());
			stmt.setString(4, dto.getMobile());
			stmt.setString(5, dto.getEmail());
			stmt.setString(6, dto.getZipcode());
			stmt.setString(7, dto.getAddress1());
			stmt.setString(8, dto.getAddress2());			
			stmt.setString(9, dto.getEntryDate());
			stmt.setInt(10, dto.getMileage());
			stmt.setString(11, dto.getGrade());	
			
			// 마일리지 부여
			sql = "update member set mileage = meileage+1000 where member_id=?";
			
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
			stmt.setString(4, member.getMobile());
			stmt.setString(5, member.getEmail());
			stmt.setString(6, member.getZipcode());
			stmt.setString(7, member.getAddress1());
			stmt.setString(8, member.getAddress2());
			stmt.setString(9, member.getBusinessNumber());
			stmt.setString(10, member.getCompanyName());
			stmt.setString(11, member.getEntryDate());
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
	 * 회원 상세조회
	 * @param memberId 회원아이디
	 * @return 회원, 미존재시 null
	 */
	public Member selectOne(String memberId) {
		String sql = "select * from member where member_id=?";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				Member dto = new Member();
				
				dto.setMemberId(rs.getString("memberId"));
				dto.setMemberPw(rs.getString("memberpw"));
				dto.setName(rs.getString("name"));
				dto.setMobile(rs.getString("mobile"));
				dto.setEmail(rs.getString("email"));
				dto.setZipcode(rs.getString("zipcode"));
				dto.setAddress1(rs.getString("address1"));
				dto.setAddress2(rs.getString("address2"));
				dto.setBusinessNumber(rs.getString("businessNumber"));
				dto.setCompanyName(rs.getString("companyName"));				
				dto.setEntryDate(rs.getString("entryDate"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setGrade(rs.getString("grade"));
				
				return dto;
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
	 * 관리자 권한
	 * 		- 전체회원조회(일반 회원, 판매자 회원)
	 * @return ArrayList<Member>
	 */
	public ArrayList<Member> selectList(String searchKey, String keyWord) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from member";
			
			if(keyWord != null && !keyWord.equals("") ){
                sql +=" where "+searchKey.trim()+" LIKE '%"+keyWord.trim()+"%' order by memberid";
            }else {//모든 레코드 검색
                sql +=" order by memberid";
            }
			
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			ArrayList<Member> list = new ArrayList<Member>();
			Member dto = null;			
			while(rs.next()) {
				dto = new Member();
				
				dto.setMemberId(rs.getString("memberId"));
				dto.setMemberPw(rs.getString("memberpw"));
				dto.setName(rs.getString("name"));
				dto.setMobile(rs.getString("mobile"));
				dto.setEmail(rs.getString("email"));
				dto.setZipcode(rs.getString("zipcode"));
				dto.setAddress1(rs.getString("address1"));
				dto.setAddress2(rs.getString("address2"));
				dto.setBusinessNumber(rs.getString("businessNumber"));
				dto.setCompanyName(rs.getString("companyName"));				
				dto.setEntryDate(rs.getString("entryDate"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setGrade(rs.getString("grade"));
				
				list.add(dto);
			}
			
			return list;
			
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
				return rs.getString("memberId");
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
		String sql = "update member set memberpw=? where memberid=?";

		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tempMemberPw);
			stmt.setString(2, memberId);
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
	 * 일반 회원
	 * 		-- 내정보 수정
	 * @param dto Member
	 * @return 성공시 true, 실패시 false
	 */
	public boolean updateMyInfo(Member dto) {
		String sql = "update member set member_pw=?, name=?, mobile=?, email=? "
				+ "zip_code=?, address1=?, address2=? where member_id=?";

		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getMemberPw());
			stmt.setString(2, dto.getName());
			stmt.setString(3, dto.getMobile());
			stmt.setString(4, dto.getEmail());
			stmt.setString(5, dto.getZipcode());
			stmt.setString(6, dto.getAddress1());
			stmt.setString(7, dto.getAddress2());
			stmt.setString(8, dto.getMemberId());
			
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
	
	/**
	 * 판매자 회원
	 * 		-- 내정보 수정
	 * @param dto Member
	 * @return 성공시 true, 실패시 false
	 */
	public boolean updateSellerInfo(Member dto) {
		String sql = "update member set member_pw=?, name=?, mobile=?, email=? "
				+ "zip_code=?, address1=?, address2=?, business_number=?, company_name=? where member_id=?";

		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getMemberPw());
			stmt.setString(2, dto.getName());
			stmt.setString(3, dto.getMobile());
			stmt.setString(4, dto.getEmail());
			stmt.setString(5, dto.getZipcode());
			stmt.setString(6, dto.getAddress1());
			stmt.setString(7, dto.getAddress2());
			stmt.setString(8, dto.getBusinessNumber());
			stmt.setString(9, dto.getCompanyName());
			stmt.setString(10, dto.getMemberId());
			
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
	
	/**
	 * 관리자 : 전체회원 정보 변경
	 * @param dto Member
	 * @return 성공시 true, 실패시 false
	 */
	public boolean updateAll(Member dto) {
		StringBuilder sql = new StringBuilder();
		sql.append("update member set ");
		sql.append("member_pw=?, name=?, mobile=?, email=? ");
		sql.append(", entry_date=?, grade=?, mileage=?");
		sql.append("where member_id=?");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql.toString()); 
			stmt.setString(1, dto.getMemberPw());
			stmt.setString(2, dto.getName());
			stmt.setString(3, dto.getMobile());
			stmt.setString(4, dto.getEmail());
			stmt.setString(5, dto.getEntryDate());
			stmt.setString(6, dto.getGrade());
			stmt.setInt(7, dto.getMileage());			
			stmt.setString(8, dto.getMemberId());
			
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