package com.myHome.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * <pre>
 *  직원정보 관리 시스템에서 사용하는 공통 기능을 갖는 유틸리티 클래스입니다.
 * </pre>
 * 
 * @author 알파코드_수강생이름(반드시 본인의 이름으로 표기하기 바랍니다.)
 */
public class Utility {
	/**
	 * <pre>
	 * 현재 날짜를 아규먼트로 전달받은 날짜형식, 로케일로 문자열로 변환한 날짜 반환 메서드
	 * </pre>
	 * @param pattern 날짜형식
	 * @param locale 로케일
	 * @return 현재날짜 변환 문자열
	 */
	public static String getCurrentDate(String pattern) {
		return new SimpleDateFormat(pattern).format(new Date());
	}
	
	/**
	 * <pre>
	 * 아규먼트로 받은 숫자데이터를 천단위 컴마표기로 변환 문자열 반환 메서드
	 * </pre>
	 * @param data 정수형 숫자
	 * @return 천단위 컴마표기 변환 문자열
	 */
	public static String convertNumberString(long data) {
		return NumberFormat.getInstance().format(data);
	}
	
	/**
	 * <pre>
	 * 키보드로 입력한 데이터를 문자열 반환 메서드
	 * </pre>
	 * @return 입력한 데이터 문자열
	 */
	public static String inputString() {
		String inputData = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			inputData = in.readLine();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return inputData;
	}
	
	/**
	 * 회원정보 전체 조회 시, 비밀번호를 전부 가려서 보여주는 메서드
	 * @param MemberPw 회원 비밀번호
	 * @return 일부가 가려진 회원 비밀번호 반환
	 */
	public static String convertSecurePw(String memberPw) {
		String securePw = "";
		//securePw += memberPw.substring(0, 2);
		for(int index = 0; index < memberPw.length(); index++) {
			/* 보여준 내용을 제외한 나머지 정보 자릿수만큼 "*" 넣는 for문 */
		securePw += "*";
		}
		return securePw;
	}
	
	/**
	 * 키보드로 입력한 숫자 데이터를 int 반환 메서드
	 * @return 입력한 숫자 데이터 int 변환
	 */
	public static int inputNumber() {
		String inputData = null;
		
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			inputData = in.readLine();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return Integer.parseInt(inputData);
	}
	
	/**임시 비밀번호 발급 메서드
	 * <pre>
	 * 지정한 길이, 영문대문자 또는 영문소문자 영문대소문자 + 숫자 형식의 문자열 반환하는 메서드
	 * 알파벳 26자, 영문자 대문자 A -65, 영문 소문자 a - 97
	 * type casting, Random#nextBoolean()
	 * </pre>
	 * @param length
	 * @param isUpper
	 * @return
	 */
	public static String getSecureString(int length, boolean isUpper) {
		Random extractNo = new Random((long)(Math.random() * System.nanoTime()));
		String secureCode = "";
		for (int index = 0; index < length; index++) {
			if (extractNo.nextBoolean()) {
				secureCode += extractNo.nextInt(10); // 0 ~ 9 숫자
			} else {
				if (isUpper) {	// 영문 대문자
					secureCode += (char)(extractNo.nextInt(26) + 65);
				} else {		// 영문 소문자
					secureCode += (char)(extractNo.nextInt(26) + 97);
				}
			}
		}
		
		return secureCode;
	}
	
	/**
	 * <pre>
	 * 보안문자 숫자+영문조합 반환
	 * </pre>
	 * @param length 길이
	 * @param isUpper 보안영문 대소문자 
	 * @return 지정한 길의 영문대소문자+숫자결합 
	 */
	public static String getSecureNumberAndString(int length, boolean isUpper) {
		StringBuilder secureNumber = new StringBuilder();
		Random random = new Random((long)(System.nanoTime() * Math.random()));
		for (int index = 0; index < length; index++) {
			if (random.nextBoolean()) {
				secureNumber.append(random.nextInt(10));
			} else {
				if (isUpper) {
					secureNumber.append((char)(random.nextInt(26) + 65));
				} else {
					secureNumber.append((char)(random.nextInt(26) + 97));
				}
			}
		}
		return secureNumber.toString();
	}	
	
	/**
	 * <pre>
	 * 보안문자 숫자+영문조합 반환
	 * </pre>
	 * @return 기본 숫자+영문조합 6자리 
	 */
	public static String getSecureNumberAndString() {
		return getSecureNumberAndString(6, true);
	}
	
	public static String getSecureNumberAndString(int length) {
		return getSecureNumberAndString(length, true);
	}
}
