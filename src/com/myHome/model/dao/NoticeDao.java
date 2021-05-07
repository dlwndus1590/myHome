package com.myHome.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.myHome.common.JdbcTemplate;
import com.myHome.model.dto.Answer;
import com.myHome.model.dto.Notice;
import com.myHome.model.dto.Qnotice;

public class NoticeDao {
	private static NoticeDao instance = new NoticeDao(); // 1. singleton pattern

	public static NoticeDao getInstance() {
		return instance; // 2. singleton pattern
	}

	public void selectList(ArrayList<Notice> list) {
		String sql = "SELECT N_NO, MEMBER_ID, N_TITLE, N_CONTENT, TO_CHAR(N_REG_DATE,'yyyy-mm-dd'), N_HITS FROM NOTICE ORDER BY N_NO DESC";

		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			Notice dto = null;
			while (rs.next()) {
				dto = new Notice(rs.getInt("N_NO"), rs.getString("MEMBER_ID"), rs.getString("N_TITLE"),
						rs.getString("N_CONTENT"), rs.getString("TO_CHAR(N_REG_DATE,'yyyy-mm-dd')"),
						rs.getInt("N_HITS"));
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("Message : " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(conn);
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 게시글 상세보기 메서드
	 * 
	 * @param noticeNum 게시글 번호
	 * @return Notice
	 */
	public void NoticeDetail(int nNo, Notice dto) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT N_NO, MEMBER_ID, N_TITLE, N_CONTENT, TO_CHAR(N_REG_DATE,'yyyy-mm-dd'), N_HITS ");
		sql.append("FROM NOTICE ");
		sql.append("WHERE N_NO = ?");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, nNo);
			rs = stmt.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					dto.setnNo(rs.getInt("N_NO"));
					dto.setMemberId(rs.getString("MEMBER_ID"));
					dto.setnTitle(rs.getString("N_TITLE"));
					dto.setnContent(rs.getString("N_CONTENT"));
					dto.setnRegDate(rs.getString("TO_CHAR(N_REG_DATE,'yyyy-mm-dd')"));
					dto.setnHits(rs.getInt("N_HITS"));
				}
			}
		} catch (SQLException e) {
			System.out.println("Message : " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 공지사항 게시글 마지막 번번호 반환 메서드
	 * @return 마지막 게시글 번호
	 */
	public int noticeLastNum() {
		ArrayList<Notice> list = new ArrayList<Notice>();
		String sql = "SELECT MAX(N_NO) FROM NOTICE";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					return rs.getInt("MAX(N_NO)");
				}
			}
		} catch (SQLException e) {
			System.out.println("message : " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
		return -1;
	}

	/**
	 * 질문 게시글 마지막 번호 반환 메서드
	 * 
	 * @return 마지막 게시글 번호
	 */
	public int qNoticeLastNum() {
		ArrayList<Notice> list = new ArrayList<Notice>();
		String sql = "SELECT MAX(Q_NO) FROM QNOTICE";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					return rs.getInt("MAX(Q_NO)");
				}
			}
		} catch (SQLException e) {
			System.out.println("message : " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
		return -1;
	}

	/**
	 * 질문 게시글 마지막 번호 반환 메서드
	 * 
	 * @return 마지막 게시글 번호
	 */
	public int answerLastNum() {
		ArrayList<Notice> list = new ArrayList<Notice>();
		String sql = "SELECT MAX(A_NO) FROM ANSWER";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					return rs.getInt("MAX(A_NO)");
				}
			}
		} catch (SQLException e) {
			System.out.println("message : " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
		return -1;
	}

	/**
	 * 게시글 등록 메서드
	 * 
	 * @param dto 게시글
	 * @return boolean
	 */
	public boolean addNotice(Notice dto) {
		String sql = "INSERT INTO NOTICE VALUES(?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, dto.getnNo());
			stmt.setString(2, dto.getMemberId());
			stmt.setString(3, dto.getnTitle());
			stmt.setString(4, dto.getnContent());
			stmt.setString(5, dto.getnRegDate());
			stmt.setInt(6, dto.getnHits());
			stmt.executeUpdate();
			JdbcTemplate.commit(conn);
			return true;
		} catch (SQLException e) {
			JdbcTemplate.rollback(conn);
			System.out.println("Message : " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
		return false;
	}

	/**
	 * 공지사항 게시글 삭제 메서드
	 * 
	 * @param nNo 공지사항 게시글 번호
	 */
	public boolean noticeDelete(int nNo) {
		String sql = "DELETE FROM NOTICE WHERE n_No = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nNo);
			pstmt.executeUpdate();
			JdbcTemplate.commit(conn);
			return true;
		} catch (SQLException e) {
			JdbcTemplate.rollback(conn);
		} finally {
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(conn);
		}
		return false;
	}

	/**
	 * 공지사항 게시글 수정 메서드
	 * 
	 * @param nNo      게시글 번호
	 * @param nTitle   게시글 제목
	 * @param nContent 게시글 내용
	 */
	public void noticeUpdate(int nNo, String nTitle, String nContent) {
		String sql = "UPDATE NOTICE SET N_TITLE = ? , N_CONTENT = ? WHERE n_NO = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nTitle);
			pstmt.setString(2, nContent);
			pstmt.setInt(3, nNo);
			pstmt.executeUpdate();
			JdbcTemplate.commit(conn);
		} catch (SQLException e) {
			JdbcTemplate.rollback(conn);
			System.out.println("Message : " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 제목+내용 검색 메서드
	 * 
	 * @param searchInfo 찾는 내용
	 */
	public void searchTitleContentsList(String searchInfo, ArrayList<Notice> list) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT N_NO, MEMBER_ID, N_TITLE, N_CONTENT, TO_CHAR(N_REG_DATE,'yyyy-mm-dd'), N_HITS ");
		sql.append("FROM NOTICE ");
		sql.append("WHERE N_TITLE like '%' || ? || '%' OR N_CONTENT like '%' || ? || '%' ");
		sql.append("ORDER BY N_NO DESC");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, searchInfo);
			stmt.setString(2, searchInfo);
			rs = stmt.executeQuery();
			Notice dto = null;
			while (rs.next()) {
				dto = new Notice(rs.getInt("N_NO"), rs.getString("MEMBER_ID"), rs.getString("N_TITLE"),
						rs.getString("N_CONTENT"), rs.getString("TO_CHAR(N_REG_DATE,'yyyy-mm-dd')"),
						rs.getInt("N_HITS"));
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("message : " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 제목 검색 메서드
	 * 
	 * @param searchInfo 찾는 내용
	 */
	public void searchTitleList(String searchInfo, ArrayList<Notice> list) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT N_NO, MEMBER_ID, N_TITLE, N_CONTENT, TO_CHAR(N_REG_DATE,'yyyy-mm-dd'), N_HITS ");
		sql.append("FROM NOTICE ");
		sql.append("WHERE N_TITLE like '%' || ? || '%' ");
		sql.append("ORDER BY N_NO DESC");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, searchInfo);
			rs = stmt.executeQuery();
			Notice dto = null;
			while (rs.next()) {
				dto = new Notice(rs.getInt("N_NO"), rs.getString("MEMBER_ID"), rs.getString("N_TITLE"),
						rs.getString("N_CONTENT"), rs.getString("TO_CHAR(N_REG_DATE,'yyyy-mm-dd')"),
						rs.getInt("N_HITS"));
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("message : " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 작성자 검색 메서드
	 * 
	 * @param searchInfo 찾는 내용
	 */
	public void writerSearchWriterList(String searchInfo, ArrayList<Notice> list) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT N_NO, MEMBER_ID, N_TITLE, N_CONTENT, TO_CHAR(N_REG_DATE,'yyyy-mm-dd'), N_HITS ");
		sql.append("FROM NOTICE ");
		sql.append("WHERE MEMBER_ID like '%' || ? || '%' ");
		sql.append("ORDER BY N_NO DESC");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, searchInfo);
			rs = stmt.executeQuery();
			Notice dto = null;
			while (rs.next()) {
				dto = new Notice(rs.getInt("N_NO"), rs.getString("MEMBER_ID"), rs.getString("N_TITLE"),
						rs.getString("N_CONTENT"), rs.getString("TO_CHAR(N_REG_DATE,'yyyy-mm-dd')"),
						rs.getInt("N_HITS"));
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("message : " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 질문 게시판 리스트 요청 메서드
	 * 
	 * @param list 질문게시판 목록
	 */
	public void qNoticeList(ArrayList<Qnotice> list) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT Q_NO, Q_TITLE, Q_CONTENT, Q_IMG, MEMBER_ID, TO_CHAR(Q_REG_DATE,'yyyy-mm-dd'), Q_HITS ");
		sql.append("FROM QNOTICE ");
		sql.append("ORDER BY Q_NO DESC");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			Qnotice dto = null;
			while (rs.next()) {
				dto = new Qnotice(rs.getInt("Q_NO"), rs.getString("Q_TITLE"), rs.getString("Q_CONTENT"),
						rs.getString("Q_IMG"), rs.getString("MEMBER_ID"),
						rs.getString("TO_CHAR(Q_REG_DATE,'yyyy-mm-dd')"), rs.getInt("Q_HITS"));
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("Message : " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 질문 게시글 상세보기 메서드
	 * 
	 * @param qNo 질문 게시글 번호
	 * @param dto 질문 게시글 객체
	 */
	public void qNoticeDetail(int qNo, Qnotice dto) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT Q_NO, Q_TITLE, Q_CONTENT, Q_IMG , MEMBER_ID, TO_CHAR(Q_REG_DATE,'yyyy-mm-dd'), Q_HITS ");
		sql.append("FROM QNOTICE ");
		sql.append("WHERE Q_NO = ?");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, qNo);
			rs = stmt.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					dto.setqNo(rs.getInt("Q_NO"));
					dto.setqTitle(rs.getString("Q_TITLE"));
					dto.setqContent(rs.getString("Q_CONTENT"));
					dto.setqImg(rs.getString("Q_IMG"));
					dto.setMemberId(rs.getString("MEMBER_ID"));
					dto.setqRegDate(rs.getString("TO_CHAR(Q_REG_DATE,'yyyy-mm-dd')"));
					dto.setqHits(rs.getInt("Q_HITS"));
				}
			}
		} catch (SQLException e) {
			System.out.println("Message : " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 해당 질문 게시글 답변 목록 요청 메서드
	 * 
	 * @param qNo  질문 게시글 번호
	 * @param list 해당 질문 게시글 답변 목록
	 */
	public void answerList(int qNo, ArrayList<Answer> list) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT A_NO, Q_NO, A_CONTENT, MEMBER_ID, TO_CHAR(A_REG_DATE,'yyyy-mm-dd') ");
		sql.append("FROM ANSWER WHERE Q_NO = ? ");
		sql.append("ORDER BY A_NO DESC");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, qNo);
			rs = pstmt.executeQuery();
			Answer dto = null;
			while (rs.next()) {
				dto = new Answer(rs.getInt("A_NO"), rs.getInt("Q_NO"), rs.getString("A_CONTENT"),
						rs.getString("MEMBER_ID"), rs.getString("TO_CHAR(A_REG_DATE,'yyyy-mm-dd')"));
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("Message : " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 질문 게시글 등록 요청 메서드
	 * 
	 * @param dto 질문 게시글 객체
	 */
	public boolean addQnotice(Qnotice dto) {
		String sql = "INSERT INTO QNOTICE VALUES(?, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getqNo());
			pstmt.setString(2, dto.getqTitle());
			pstmt.setString(3, dto.getqContent());
			pstmt.setString(4, dto.getqImg());
			pstmt.setString(5, dto.getMemberId());
			pstmt.setString(6, dto.getqRegDate());
			pstmt.setInt(7, dto.getqHits());
			pstmt.executeUpdate();
			JdbcTemplate.commit(conn);
			return true;
		} catch (SQLException e) {
			JdbcTemplate.rollback(conn);
			System.out.println("Message : " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(conn);
		}
		return false;
	}

	/**
	 * 질문 게시글 수정 요청 메서드
	 * 
	 * @param qNo 질문 게시글 번호
	 * @param dto 질문 게시글 객체
	 */
	public void qNoticeUpdate(Qnotice dto) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE QNOTICE ");
		sql.append("SET Q_TITLE = ?, Q_CONTENT = ? ");
		sql.append("WHERE Q_NO = ?");
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getqTitle());
			pstmt.setString(2, dto.getqContent());
			pstmt.setInt(3, dto.getqNo());
			pstmt.executeUpdate();
			JdbcTemplate.commit(conn);
		} catch (SQLException e) {
			JdbcTemplate.rollback(conn);
			System.out.println("message : " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 질문 게시글 삭제 요청 메서드
	 * 
	 * @param qNo 삭제할 질문 게시글 번호
	 */
	public void qNoticeDelete(int qNo) {
		String sql = "DELETE FROM QNOTICE WHERE Q_NO = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qNo);
			pstmt.executeUpdate();
			JdbcTemplate.commit(conn);
		} catch (SQLException e) {
			JdbcTemplate.rollback(conn);
			System.out.println("Message : " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 댓글 등록 요청 메서드
	 * 
	 * @param qNo 댓글 등록할 질문 게시글 번호
	 * @param dto 댓글 등록 객체
	 */
	public void addComment(Answer dto) {
		String sql = "INSERT INTO ANSWER VALUES(?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getaNo());
			pstmt.setInt(2, dto.getqNo());
			pstmt.setString(3, dto.getaContent());
			pstmt.setString(4, dto.getMemberId());
			pstmt.setString(5, dto.getaRegDate());
			pstmt.executeUpdate();
			JdbcTemplate.commit(conn);
		} catch (SQLException e) {
			JdbcTemplate.rollback(conn);
			System.out.println("Message : " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 댓글 수정 요청 서비스
	 * 
	 * @param aNo          수정할 댓글 번호
	 * @param edditContent 수정할 댓글 내용
	 */
	public void updateComment(int aNo, String edditContent) {
		String sql = "UPDATE ANSWER SET A_CONTENT = ? WHERE A_NO = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, edditContent);
			pstmt.setInt(2, aNo);
			pstmt.executeUpdate();
			JdbcTemplate.commit(conn);
		} catch (SQLException e) {
			JdbcTemplate.rollback(conn);
			System.out.println("Message : " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 댓글 삭제 요청 서비스 메서드
	 * 
	 * @param aNo 삭제할 댓글 번호
	 */
	public void deleteComment(int aNo) {
		String sql = "DELETE FROM ANSWER WHERE A_NO = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, aNo);
			pstmt.executeUpdate();
			JdbcTemplate.commit(conn);
		} catch (SQLException e) {
			JdbcTemplate.rollback(conn);
			System.out.println("Message : " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 질문 게시글 검색 요청 메서드
	 * 
	 * @param searchInfo 검색 내용
	 * @param list       검색에 부합하는 질문 게시글 리스트
	 */
	public void searchQnoticeList(String searchInfo, ArrayList<Qnotice> list) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT Q_NO, Q_TITLE, Q_CONTENT, Q_IMG, MEMBER_ID, TO_CHAR(Q_REG_DATE,'yyyy-mm-dd'), Q_HITS ");
		sql.append("FROM QNOTICE ");
		sql.append("WHERE Q_TITLE like '%' || ? || '%' OR Q_CONTENT like '%' || ? || '%' ");
		sql.append("ORDER BY Q_NO DESC");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, searchInfo);
			stmt.setString(2, searchInfo);
			rs = stmt.executeQuery();
			Qnotice dto = null;
			while (rs.next()) {
				dto = new Qnotice(rs.getInt("Q_NO"), rs.getString("Q_TITLE"), rs.getString("Q_CONTENT"),
						rs.getString("Q_IMG"), rs.getString("MEMBER_ID"),
						rs.getString("TO_CHAR(Q_REG_DATE,'yyyy-mm-dd')"), rs.getInt("Q_HITS"));
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("message : " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);

		}
	}

	/**
	 * 작성자이외 회원이 공지사항 게시글 상세 조회를 요청했을 경우
	 * @param nNo 공지사항 게시글 번호
	 */
	public void nHitsUp(int nNo) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE NOTICE ");
		sql.append("SET N_HITS = N_HITS+1 ");
		sql.append("WHERE N_NO = ?");
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, nNo);
			pstmt.executeUpdate();
			JdbcTemplate.commit(conn);
		}catch(SQLException e) {
			JdbcTemplate.rollback(conn);
			System.out.println("Message : " + e.getMessage());
			e.printStackTrace();
		}finally {
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(conn);
		}
	}
	/**
	 * 작성자이외 회원이 질문 게시글 상세 조회를 요청했을 경우
	 * @param nNo 질문 게시글 번호
	 */
	public void qHitsUp(int qNo) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE QNOTICE ");
		sql.append("SET Q_HITS = Q_HITS+1 ");
		sql.append("WHERE Q_NO = ?");
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, qNo);
			pstmt.executeUpdate();
			JdbcTemplate.commit(conn);
		}catch(SQLException e) {
			JdbcTemplate.rollback(conn);
			System.out.println("Message : " + e.getMessage());
			e.printStackTrace();
		}finally {
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 질문 게시글 최신순(등록순서) 리스트 요청 서비스
	 * @param list 최신순(등록순서) 리스트
	 */
	public void qNoticeNewList(ArrayList<Qnotice> list) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT Q_NO, Q_TITLE, Q_CONTENT, Q_IMG, MEMBER_ID, TO_CHAR(Q_REG_DATE,'yyyy-mm-dd'), Q_HITS ");
		sql.append("FROM QNOTICE ");
		sql.append("ORDER BY Q_NO DESC");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			Qnotice dto = null;
			while (rs.next()) {
				dto = new Qnotice(rs.getInt("Q_NO"), rs.getString("Q_TITLE"), rs.getString("Q_CONTENT"),
						rs.getString("Q_IMG"), rs.getString("MEMBER_ID"),
						rs.getString("TO_CHAR(Q_REG_DATE,'yyyy-mm-dd')"), rs.getInt("Q_HITS"));
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("Message : " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 질문 게시글 인기순(조회) 리스트 요청 서비스
	 * @param list 최신순(조회) 리스트
	 */
	public void qNoticePopularityList(ArrayList<Qnotice> list) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT Q_NO, Q_TITLE, Q_CONTENT, Q_IMG, MEMBER_ID, TO_CHAR(Q_REG_DATE,'yyyy-mm-dd'), Q_HITS ");
		sql.append("FROM QNOTICE ");
		sql.append("ORDER BY Q_HITS DESC");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			Qnotice dto = null;
			while (rs.next()) {
				dto = new Qnotice(rs.getInt("Q_NO"), rs.getString("Q_TITLE"), rs.getString("Q_CONTENT"),
						rs.getString("Q_IMG"), rs.getString("MEMBER_ID"),
						rs.getString("TO_CHAR(Q_REG_DATE,'yyyy-mm-dd')"), rs.getInt("Q_HITS"));
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("Message : " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(conn);
		}
	}
}
