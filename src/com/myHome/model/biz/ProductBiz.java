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
}
