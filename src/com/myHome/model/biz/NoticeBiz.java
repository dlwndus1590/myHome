package com.myHome.model.biz;

import java.sql.Connection;
import java.util.ArrayList;

import com.myHome.common.JdbcTemplate;
import com.myHome.model.dao.NoticeDao;
import com.myHome.model.dto.Answer;
import com.myHome.model.dto.Notice;
import com.myHome.model.dto.Qnotice;
import com.myHome.util.Utility;

public class NoticeBiz {
	private NoticeDao dao = NoticeDao.getInstance(); // 3. singleton pattern
	
	/**
	 * 모든 공지사항 게시글 리스트 반환 메서드
	 */
	public void noticeList(ArrayList<Notice> list){
		dao.getInstance().selectList(list);
	}
	
	/**
	 * 공지사항 게시글 상세 조회 메서드
	 * @param noticeNum 게시글 번호
	 * @return Notice 상세 조회할 NoticeEntity
	 */
	public void NoticeDetail(int nNo, Notice dto) {
		dao.getInstance().NoticeDetail(nNo, dto);
	}

	/**
	 * 마지막 게시글 번호 반환 메서드
	 * @return 마지막 게시글 번호
	 */
	public int noticeLastNum() {
		return dao.getInstance().noticeLastNum();
	}
	
	/**
	 * 공지사항 게시글 등록 메서드
	 * @param dto
	 */
	public boolean addNotice(Notice dto) {
		/* 마지막 게시글 번호 + 1*/
		int index = noticeLastNum() + 1;
		dto.setnNo(index);
		dto.setnRegDate(Utility.getCurrentDate("yyyy-MM-dd"));
		dto.setnHits(0);
		return dao.getInstance().addNotice(dto);
	}

	/**
	 * 공지사항 게시글 삭제 메서드
	 * @param nNo 공지사항 게시글 번호
	 */
	public boolean noticeDelete(int nNo) {
		return dao.getInstance().noticeDelete(nNo);
	}

	/**
	 * 공지사항 게시글 수정 메서드
	 * @param nNo 게시글 번호
	 * @param nTitle 게시글 제목
	 * @param nContent 게시글 내용
	 */
	public void noticeUpdate(int nNo, String nTitle, String nContent) {
		dao.getInstance().noticeUpdate(nNo, nTitle, nContent);
	}

	
	/**
	 * 공지사항 게시글 검색 메서드
	 * @param noticeType 검색 유형
	 * @param searchInfo 검색내용
	 */
	public void searchList(String noticeType, String searchInfo, ArrayList<Notice> list) {
		String title = "제목";
		String contents = "내용";
		String writer ="작성자";
		
		if(noticeType.equals("titleContents")) {
			/* 제목 + 내용 검색*/
			dao.getInstance().searchTitleContentsList(searchInfo, list);
		}else if(noticeType.equals("title")) {
			/* 제목만 검색*/
			dao.getInstance().searchTitleList(searchInfo, list);
		}else {
			/* 작성자 검색*/
			dao.getInstance().writerSearchWriterList(searchInfo, list);
		}
	}
	
	/**
	 * 질문 게시판 리스트 요청 메서드
	 * @param list 질문게시판 목록
	 */
	public void qNoticeList(ArrayList<Qnotice> list) {
		dao.getInstance().qNoticeList(list);
	}

	/**
	 * 질문 게시글 상세 보기 요청 메서드
	 * @param qNo 질문 게시글 번호
	 * @param dto 질문 게시글 객체
	 */
	public void qNoticeDetail(int qNo, Qnotice dto) {
		dao.getInstance().qNoticeDetail(qNo, dto);
	}

	/**
	 * 해당 질문 게시글 답변 목록 요청 메서드
	 * @param qNo 질문 게시글 번호
	 * @param list 해당 질문 게시글 답변 목록
	 */
	public void answerList(int qNo, ArrayList<Answer> list) {
		dao.getInstance().answerList(qNo, list);
	}
}
