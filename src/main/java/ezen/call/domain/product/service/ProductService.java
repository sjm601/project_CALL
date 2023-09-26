package ezen.call.domain.product.service;

import java.util.List;

import ezen.call.domain.product.dto.Product;

/**
 * 상품 관련 비즈니스 로직 인터페이스
 * @author 김종원
 */
public interface ProductService {
    
    // 상품 등록
    public void createProduct(Product product);
    // 상품 활성화
    public void enabledProduct(int productNum);
    // 상품 비활성화
    public void disabledProduct(int productNum);
    // 단일 상품 조회 (상품 번호로)
    public Product readProductByNum(int productNum);
    // 단일 상품 조회 (상품 이름으로)
    public Product readProductByName(String productName);
    // 상품 조회 (카테고리 별, 정렬조건 추가)
    public List<Product> readProductListCategory(String category, String sortCondition);
    // 상품 조회( 카테고리 별, 가격순, 내림차순/오름차순 조건 추가)
    public List<Product> readProductListPriceSort(String category, String sortSc);
    // 메인 페이지 상품 조회(3)
    public List<Product> indexProductList(String category);
    // 디테일 페이지 추천상품 조회(4)
    public List<Product> detailProductList(String category);
    // 제품 전체 리스트 조회
    public List<Product> findByAll();
}