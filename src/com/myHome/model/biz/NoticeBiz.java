package com.myHome.model.biz;

import java.sql.Connection;
import java.util.ArrayList;

import com.myHome.common.JdbcTemplate;
import com.myHome.model.dao.NoticeDao;
import com.myHome.model.dto.Notice;

public class NoticeBiz {
	private NoticeDao dao = new NoticeDao();
	/**
	 * 모든 공지사항 게시글 리스트 반환 메서드
	 */
	public void noticeList(ArrayList<Notice> list){
		dao.selectList(list);
	}
	
	/**
	 * 게시글 상세 조회 메서드
	 * @param noticeNum 게시글 번호
	 * @return Notice 상세 조회할 NoticeEntity
	 */
	public Notice NoticeDetail(int nNo) {
		return dao.NoticeDetail(nNo);
	}
}
