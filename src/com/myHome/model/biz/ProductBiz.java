package com.myHome.model.biz;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.myHome.common.JdbcTemplate;
import com.myHome.model.dao.ProductDao;
import com.myHome.model.dto.Category;
import com.myHome.model.dto.Product;

public class ProductBiz {
	
	/**
	 * 카테고리 리스트 반환 메서드
	 * @param categoryList
	 * @throws Exception
	 */
	public void getCategoryList(ArrayList<Category> categoryList) throws Exception{
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			ProductDao.getInstance().getCategoryList(conn, categoryList);
		} catch (SQLException e) {
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	/**
	 * 상품 전체조회 메서드
	 * @param productList
	 * @throws Exception
	 */
	public void getProductList(ArrayList<Product> productList) throws Exception{
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			ProductDao.getInstance().getProductList(conn, productList);
		} catch (SQLException e) {
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	/**
	 * 카테고리별 상품조회 메서드
	 * @param categoryId
	 * @param productList
	 * @throws Exception
	 */
	public void getProductListByCategory(int categoryId, ArrayList<Product> productList) throws Exception{
		Connection conn= JdbcTemplate.getConnection();
		
		try {
			ProductDao.getInstance().getProductListByCategory(conn, categoryId, productList);
		} catch(SQLException e) {
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	/**
	 * 관련상품 조회 메서드
	 * @param categoryId
	 * @param productList
	 * @throws Exception
	 */
	public void getRelatedProductList(int categoryId, int pNo, ArrayList<Product> productList) throws Exception{
		Connection conn= JdbcTemplate.getConnection();
		
		try {
			ProductDao.getInstance().getRelatedProductList(conn, categoryId, pNo, productList);
		} catch(SQLException e) {
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	/**
	 * 상품 상세조회 메서드
	 * @param pNo
	 * @param product
	 * @throws Exception
	 */
	public void selectProductOne(int pNo, Product product) throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			ProductDao.getInstance().selectProductOne(conn, pNo, product);
		} catch(SQLException e) {
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	/**
	 * 카테고리 이름 반환 메서드
	 * @param category
	 * @throws Exception
	 */
	public void getCategory(Category category) throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			ProductDao.getInstance().getCategory(conn, category);
		} catch(SQLException e) {
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	/**
	 * 베스트 상품 조회 메서드
	 * @param productList
	 * @throws Exception
	 */
	public void productListbyBest(ArrayList<Product> productList) throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			ProductDao.getInstance().productListByBest(conn, productList);
		} catch(SQLException e) {
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	/**
	 * 카테고리별 베스트 상품 조회 메서드
	 * @param categoryId
	 * @param productList
	 * @throws Exception
	 */
	public void productListbyBestCategory(int categoryId, ArrayList<Product> productList) throws Exception{
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			ProductDao.getInstance().productListbyBestCategory(conn, categoryId, productList);
		} catch(SQLException e) {
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}

	}

	/**
	 * 판매자회원 - 등록한 상품 리스트 조회
	 * @param companyName
	 * @param productList
	 * @throws Exception
	 */
	public void getEnrolledProductList(String companyName, ArrayList<Product> productList) throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			ProductDao.getInstance().getEnrolledProductList(conn, companyName, productList);
		} catch(SQLException e) {
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}		
	}
	
	/**
	 * 상품 마지막번호 반환 메서드
	 * @return 상품 마지막번호 반환
	 */
	public int getMaxProductNum() throws Exception{
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			int count = ProductDao.getInstance().getMaxProductNum(conn);
			return count;
		} catch(SQLException e) {
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}	
	}

	/**
	 * 상품등록 메서드
	 * @param product
	 * @throws Exception
	 */
	public void addProduct(Product product) throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			ProductDao.getInstance().addProduct(conn, product);
			JdbcTemplate.commit(conn);
		} catch(SQLException e) {
			JdbcTemplate.rollback(conn);
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}	
	}
}
