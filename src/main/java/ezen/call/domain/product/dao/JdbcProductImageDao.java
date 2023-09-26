package ezen.call.domain.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ezen.call.domain.product.dto.ProductImage;

/**
 * 상품 이미지 데이터베이스 CRUD
 * @author 김종원
 */
public class JdbcProductImageDao implements ProductImageDao {
    
    /**
     * 이미지 등록
     * @param Connection 커넥션
     * @param ProductImage 이미지
     * @return Boolean 성공 여부
     */
    @Override
    public boolean create(Connection connection, ProductImage productImage) {
        
        boolean success = false;
        StringBuilder sb = new StringBuilder();
        sb.append(" INSERT INTO product_image (")
          .append("     product_image_number,")
          .append("     product_image_path,")
          .append("     product_number")
          .append(" ) VALUES (")
          .append("     product_image_number_seq.NEXTVAL,")
          .append("     ?, ?)");
        
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setString(1, productImage.getProductImagePath());
            pstmt.setInt(2, productImage.getProductImageNum());
            pstmt.executeUpdate();
            success = true;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {}
        }
        return success;
    }
    
    /**
     * 이미지 삭제
     * @param Connection 커넥션
     * @param ProductImage 이미지
     * @return Boolean 삭제 여부
     */
    @Override
    public boolean delete(Connection connection, ProductImage productImage) {
        
        boolean success = false;
        StringBuilder sb = new StringBuilder();
        sb.append(" DELETE FROM product_image")
          .append(" WHERE product_image_number = ?"); 
        
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setInt(1, productImage.getProductNum());
            pstmt.executeUpdate();
            success = true;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {}
        }
        return success;
    }
    
    /**
     * 이미지 조회
     * @param Connection 커넥션
     * @param Int 제품 번호
     * @return List<ProductImage> 제품 이미지 리스트
     */
    @Override
    public List<ProductImage> findImage(Connection connection, int productNum) {
        List<ProductImage> list = null;
        
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT *")
          .append(" FROM product_image")
          .append(" WHERE product_image_number = ?");
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setInt(1, productNum);
            rs = pstmt.executeQuery();
            
            list = new ArrayList<ProductImage>();
            
            while (rs.next()) {         
                int productImageNum = rs.getInt("product_image_number");
                String productImagePath = rs.getString("product_image_path");
                int fproductNum = rs.getInt("product_number");
                
                ProductImage productImage = new ProductImage();
                productImage.setProductImageNum(productImageNum);
                productImage.setProductImagePath(productImagePath);
                productImage.setProductNum(fproductNum);
                list.add(productImage);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {}
        }
        return list;
    }
}