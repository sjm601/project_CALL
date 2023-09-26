package ezen.call.domain.product.dao;

import java.sql.Connection;
import java.util.List;

import ezen.call.domain.product.dto.ProductImage;

/**
 * 상품 이미지 DAO 인터페이스
 * @author 김종원
 */
public interface ProductImageDao {
    
    // 이미지 추가
    public boolean create(Connection connection, ProductImage productImage);
    // 이미지 삭제
    public boolean delete(Connection connection, ProductImage productImage);
    // 단일 상품 이미지 조회 (상세 페이지)
    public List<ProductImage> findImage(Connection connection, int productNum);
}