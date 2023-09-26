package ezen.call.domain.product.dao;

import java.sql.Connection;
import java.util.List;

import ezen.call.domain.product.dto.Product;

/**
 * 상품 DAO 인터페이스
 * @author 김종원
 */
public interface ProductDao {
    
	// 상품 추가
    public boolean create(Connection connection, Product product);
    // 상품 활성화
    public boolean enabled(Connection connection, int productNum);
    // 상품 비활성화
    public boolean disabled(Connection connection, int productNum);
    // 단일 상품 조회 (상품 번호)
    public Product findProductByNum(Connection connection, int productNum);
    // 단일 상품 조회 (상품 이름으로)
    public Product findProductByName(Connection connection, String productName);
    // 상품 목록 조회
    public List<Product> findProductByAll(Connection connection);
    // 카테고리 및 정렬조건 이용하여 상품 목록 조회
    public List<Product> findProductByCategory(Connection connection, String category, String sortCondition);
    // 카테고리를 가격조건으로 상품 목록 조회 
    public List<Product> findProductByPriceSort(Connection connection, String category, String sortSc);
    // 메인 페이지 상품 목록 출력
    public List<Product> indexProduct(Connection connection, String category, int rowNum);
    // 제품 전체 리스트 조회
    public List<Product> findByAll(Connection connection);
}