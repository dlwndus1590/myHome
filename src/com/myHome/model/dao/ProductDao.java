package com.myHome.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.myHome.common.JdbcTemplate;
import com.myHome.model.dto.Category;
import com.myHome.model.dto.Product;
import com.myHome.util.Utility;

/**
 * 상품 dao
 * @author 이주연
 *
 */
public class ProductDao {
	private static ProductDao instance = new ProductDao();
	
	public ProductDao() {}
	
	public static ProductDao getInstance() {
		return instance;
	}
	
	/**
	 * 카테고리 목록조회 
	 * @param conn
	 * @param categoryList
	 * @throws Exception
	 */
	public void getCategoryList(Connection conn, ArrayList<Category> categoryList) throws Exception {
		String sql = "select * from category";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Category category = null;
			while(rs.next()) {
				category = new Category();
				category.setCategoryId(rs.getInt(1));
				category.setCategoryName(rs.getString(2));
				category.setCategoryImg(rs.getString(3));
				categoryList.add(category);
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
	}

	/**
	 * 상품 전체목록 조회
	 * @param conn
	 * @param productList
	 */
	public void getProductList(Connection conn, ArrayList<Product> productList) throws Exception {
		String sql = "select * from product";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Product product = null;
			while(rs.next()) {
				product = new Product();
				product.setpNo(rs.getInt(1));
				product.setpName(rs.getString(2));
				product.setpPrice(rs.getInt(3));
				product.setpImg(rs.getString(4));
				product.setpDescribe(rs.getString(5));
				product.setDeliveryFee(rs.getInt(6));
				product.setCompanyName(rs.getString(7));
				product.setCategoryId(rs.getInt(8));
				product.setpScore(rs.getFloat(9));
				product.setpSales(rs.getInt(10));
				product.setpCount(rs.getInt(11));
				product.setpRegDate(rs.getString(12));
				product.setpReviewCount(rs.getInt(13));
				product.setpTotalScore(rs.getInt(14));
				productList.add(product);
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
	}

	/**
	 * 카테고리별 상품조회 
	 * @param conn
	 * @param productList
	 */
	public void getProductListByCategory(Connection conn, int categoryId ,ArrayList<Product> productList) throws Exception {
		String sql = "select * from product where category_id=?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryId);
			rs = pstmt.executeQuery();
			
			Product product = null;
			while(rs.next()) {
				product = new Product();
				product.setpNo(rs.getInt(1));
				product.setpName(rs.getString(2));
				product.setpPrice(rs.getInt(3));
				product.setpImg(rs.getString(4));
				product.setpDescribe(rs.getString(5));
				product.setDeliveryFee(rs.getInt(6));
				product.setCompanyName(rs.getString(7));
				product.setCategoryId(rs.getInt(8));
				product.setpScore(rs.getFloat(9));
				product.setpSales(rs.getInt(10));
				product.setpCount(rs.getInt(11));
				product.setpRegDate(rs.getString(12));
				product.setpReviewCount(rs.getInt(13));
				product.setpTotalScore(rs.getInt(14));
				productList.add(product);
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
	}

	/**
	 * 상품 상세조회
	 * @param pNo
	 * @param product
	 * @throws Exception
	 */
	public void selectProductOne(Connection conn, Product product) throws Exception {
		String sql = "select * from product where p_no=?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product.getpNo());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				product.setpNo(rs.getInt(1));
				product.setpName(rs.getString(2));
				product.setpPrice(rs.getInt(3));
				product.setpImg(rs.getString(4));
				product.setpDescribe(rs.getString(5));
				product.setDeliveryFee(rs.getInt(6));
				product.setCompanyName(rs.getString(7));
				product.setCategoryId(rs.getInt(8));
				product.setpScore(rs.getFloat(9));
				product.setpSales(rs.getInt(10));
				product.setpCount(rs.getInt(11));
				product.setpRegDate(rs.getString(12));
				product.setpReviewCount(rs.getInt(13));
				product.setpTotalScore(rs.getInt(14));
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
	}
	
	/** 
	 * 카테고리 이름 반환
	 * @param conn
	 * @param category
	 * @throws Exception
	 */
	public void getCategory(Connection conn, Category category) throws Exception{
		String sql = "select * from category where category_id=?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, category.getCategoryId());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				category.setCategoryId(rs.getInt(1));
				category.setCategoryName(rs.getString(2));
				category.setCategoryImg(rs.getString(3));
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
	}

	/**
	 * 관련상품 찾기 
	 * @param conn
	 * @param categoryId
	 * @param pNo
	 * @param productList
	 * @throws Exception
	 */
	public void getRelatedProductList(Connection conn, int categoryId, int pNo, ArrayList<Product> productList) throws Exception {
		String sql = "select * from product where category_id=? and p_no!=?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryId);
			pstmt.setInt(2, pNo);
			rs = pstmt.executeQuery();
			
			Product product = null;
			while(rs.next()) {
				product = new Product();
				product.setpNo(rs.getInt(1));
				product.setpName(rs.getString(2));
				product.setpPrice(rs.getInt(3));
				product.setpImg(rs.getString(4));
				product.setpDescribe(rs.getString(5));
				product.setDeliveryFee(rs.getInt(6));
				product.setCompanyName(rs.getString(7));
				product.setCategoryId(rs.getInt(8));
				product.setpScore(rs.getFloat(9));
				product.setpSales(rs.getInt(10));
				product.setpCount(rs.getInt(11));
				product.setpRegDate(rs.getString(12));
				product.setpReviewCount(rs.getInt(13));
				product.setpTotalScore(rs.getInt(14));
				productList.add(product);
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
	}

	/**
	 * 베스트상품 조회(30개 상품)
	 * @param conn
	 * @param productList
	 * @throws Exception
	 */
	public void productListByBest(Connection conn, ArrayList<Product> productList) throws Exception {
		String sql = "select * from product order by p_score*p_sales desc";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Product product = null;
			while(rs.next()) {
				product = new Product();
				product.setpNo(rs.getInt(1));
				product.setpName(rs.getString(2));
				product.setpPrice(rs.getInt(3));
				product.setpImg(rs.getString(4));
				product.setpDescribe(rs.getString(5));
				product.setDeliveryFee(rs.getInt(6));
				product.setCompanyName(rs.getString(7));
				product.setCategoryId(rs.getInt(8));
				product.setpScore(rs.getFloat(9));
				product.setpSales(rs.getInt(10));
				product.setpCount(rs.getInt(11));
				product.setpRegDate(rs.getString(12));
				product.setpReviewCount(rs.getInt(13));
				product.setpTotalScore(rs.getInt(14));
				productList.add(product);
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
	}

	/**
	 * 카테고리별 베스트상품 조회
	 * @param conn
	 * @param categoryId
	 * @param productList
	 * @throws Exception
	 */
	public void productListbyBestCategory(Connection conn, int categoryId, ArrayList<Product> productList) throws Exception{
		String sql = "select * from product where category_id=? and rownum<=30 order by p_score*p_sales desc";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryId);
			rs = pstmt.executeQuery();
			
			Product product = null;
			while(rs.next()) {
				product = new Product();
				product.setpNo(rs.getInt(1));
				product.setpName(rs.getString(2));
				product.setpPrice(rs.getInt(3));
				product.setpImg(rs.getString(4));
				product.setpDescribe(rs.getString(5));
				product.setDeliveryFee(rs.getInt(6));
				product.setCompanyName(rs.getString(7));
				product.setCategoryId(rs.getInt(8));
				product.setpScore(rs.getFloat(9));
				product.setpSales(rs.getInt(10));
				product.setpCount(rs.getInt(11));
				product.setpRegDate(rs.getString(12));
				product.setpReviewCount(rs.getInt(13));
				product.setpTotalScore(rs.getInt(14));
				productList.add(product);
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
	}

	/**
	 * 판매자회원 - 등록한 상품리스트 조회
	 * @param conn
	 * @param companyName
	 * @param productList
	 * @throws Exception
	 */
	public void getEnrolledProductList(Connection conn, String companyName, ArrayList<Product> productList) throws Exception{
		String sql = "select * from product where company_name=? order by p_score*p_sales desc";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, companyName);
			rs = pstmt.executeQuery();
			
			Product product = null;
			while(rs.next()) {
				product = new Product();
				product.setpNo(rs.getInt(1));
				product.setpName(rs.getString(2));
				product.setpPrice(rs.getInt(3));
				product.setpImg(rs.getString(4));
				product.setpDescribe(rs.getString(5));
				product.setDeliveryFee(rs.getInt(6));
				product.setCompanyName(rs.getString(7));
				product.setCategoryId(rs.getInt(8));
				product.setpScore(rs.getFloat(9));
				product.setpSales(rs.getInt(10));
				product.setpCount(rs.getInt(11));
				product.setpRegDate(rs.getString(12));
				product.setpReviewCount(rs.getInt(13));
				product.setpTotalScore(rs.getInt(14));
				productList.add(product);
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
	}

	/**
	 * 상품 마지막번호 반환
	 * @param conn
	 * @return
	 */
	public int getMaxProductNum(Connection conn) throws Exception {
		String sql = "select max(p_no) from product";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1);
			} 
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		return 0;
	}

	/**
	 * 상품 등록
	 * @param conn
	 * @param product
	 * @throws Exception
	 */
	public void addProduct(Connection conn, Product product) throws Exception {
		String sql = "insert into product values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, to_date(?, 'yyyy-mm-dd'), ?, ?)";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product.getpNo());
			pstmt.setString(2, product.getpName());
			pstmt.setInt(3, product.getpPrice());
			pstmt.setString(4, product.getpImg());
			pstmt.setString(5, product.getpDescribe());
			pstmt.setInt(6, product.getDeliveryFee());
			pstmt.setString(7, product.getCompanyName());
			pstmt.setInt(8, product.getCategoryId());
			pstmt.setFloat(9, product.getpScore());
			pstmt.setInt(10, product.getpSales());
			pstmt.setInt(11, product.getpCount());
			pstmt.setString(12, product.getpRegDate());
			pstmt.setInt(13, product.getpReviewCount());
			pstmt.setInt(14, product.getpTotalScore());
			
			int rows = pstmt.executeUpdate(); 
			if(rows == 0) {
				throw new Exception();
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new Exception();
		} finally {
			JdbcTemplate.close(pstmt);
		}
	}

	/**
	 * 상품 수정
	 * @param conn
	 * @param product
	 */
	public void updateProduct(Connection conn, Product product) throws Exception {
		String sql = "update product set p_name=?, p_price=?, delivery_fee=?, category_id=?, p_count=? where p_no=?";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getpName());
			pstmt.setInt(2, product.getpPrice());
			pstmt.setInt(3, product.getDeliveryFee());
			pstmt.setInt(4, product.getCategoryId());
			pstmt.setInt(5, product.getpCount());
			pstmt.setInt(6, product.getpNo());
			
			int rows = pstmt.executeUpdate();
			if(rows == 0) {
				throw new Exception();
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new Exception();
		} finally {
			JdbcTemplate.close(pstmt);
		}
	}

	/**
	 * 상품 삭제
	 * @param conn
	 * @param pNo
	 */
	public void deleteProduct(Connection conn, int pNo) throws Exception {
		String sql = "delete from product where p_no=?";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pNo);
			
			int rows = pstmt.executeUpdate();
			if(rows == 0) {
				throw new Exception();
			} 
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new Exception();
		} finally {
			JdbcTemplate.close(pstmt);
		}
	}

	/**
	 * 상품 구매시 상품판매량 증가, 재고 감소
	 * @param conn
	 * @param count
	 * @param product
	 */
	public void plusPsales(Connection conn, int count, Product product) throws Exception {
		String sql = "update product set p_sales=?, p_count=? where p_no=?";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product.getpSales()+count);
			pstmt.setInt(2, product.getpCount()-count);
			pstmt.setInt(3, product.getpNo());
			
			int rows = pstmt.executeUpdate();
			if(rows == 0) {
				throw new Exception();
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new Exception();
		} finally {
			JdbcTemplate.close(pstmt);
		}
	}

	/**
	 * 리뷰 등록시 상품별점 변경
	 * @param conn
	 * @param product
	 * @param score
	 * @param dcount
	 */
	public void updatePscore(Connection conn, Product product, int score) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("update product ");
		sql.append("set p_score=?, p_reviewCount = p_reviewCount  + 1, p_totalScore = p_totalScore + ? ");
		sql.append("where p_no=?");
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setFloat(1, ((product.getpTotalScore() + score) / (product.getpReviewCount() + 1) ));
			pstmt.setInt(2, score);
			pstmt.setInt(3, product.getpNo());
			
			int rows = pstmt.executeUpdate();
			if(rows == 0) {
				throw new Exception();
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new Exception();
		} finally {
			JdbcTemplate.close(pstmt);
		}
	}
}
