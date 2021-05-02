package com.myHome.model.biz;

import com.myHome.model.dao.InteriorDao;
import com.myHome.model.dto.Interior;


/**
 * <pre>
 * 		인테리어 테이블에 대한 Biz 클래스
 * </pre>
 * 
 * @author 강하영
 */
public class InteriorBiz {
	private InteriorDao dao = InteriorDao.getInstance();
	
	/**
	 * <pre>
	 * 인테리어 등록
	 * -- 회원가입 필수 데이터 : 업체명, 경력, 업체 부가설명, 위치
	 * </pre>
	 * @param dto 회원객체
	 * @return 성공시 true, 실패시 false
	 */
	public boolean insertMember(Interior dto) {
		int rows = dao.insertInterior(dto);
		if (rows == 1) {
			return true;
		} else {
			return false;
		}
	}
}
