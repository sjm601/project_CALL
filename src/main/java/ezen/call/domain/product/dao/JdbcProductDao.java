package ezen.call.domain.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ezen.call.domain.product.dto.Product;

/**
 * 상품 데이터베이스 CRUD
 * @author 김종원
 */
public class JdbcProductDao implements ProductDao {

    /**
     * 상품 추가
     * @param Connection 커넥션
     * @param Product 제품
     * @return Boolean 추가 성공 여부
     */
    @Override
    public boolean create(Connection connection, Product product) {
        boolean success = false;

        StringBuilder sb = new StringBuilder();
        sb.append(" INSERT INTO product (")
          .append("     product_number,")
          .append("     product_name,")
          .append("     category,")
          .append("     price,")
          .append("     thumbnail_path,")
          .append("     description")
          .append(" ) VALUES (")
          .append("     product_number_seq.NEXTVAL,")
          .append("     ?, ?, ?, ?, ?)");

        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setString(1, product.getProductName());
            pstmt.setString(2, product.getCategory());
            pstmt.setInt(3, product.getPrice());
            pstmt.setString(4, product.getThumbnailPath());
            pstmt.setString(5, product.getDescription());
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
     * 상품 활성화
     * @param Connection 커넥션
     * @param Int 제품번호
     * @return Boolean 활성화 성공 여부
     */
    @Override
    public boolean enabled(Connection connection, int productNum) {
        boolean success = false;

        StringBuilder sb = new StringBuilder();
        sb.append(" UPDATE product")
          .append(" SET sale_whether = 'T'")
          .append(" WHERE product_number = ?");

        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setInt(1, productNum);
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
     * 상품 비활성화
     * @param Connection 커넥션
     * @param Int 제품번호
     * @return Boolean 비활성화 성공 여부
     */
    @Override
    public boolean disabled(Connection connection, int productNum) {
        boolean success = false;

        StringBuilder sb = new StringBuilder();
        sb.append(" UPDATE product")
          .append(" SET sale_whether = 'F'")
          .append(" WHERE product_number = ?");

        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setInt(1, productNum);
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
     * 단일 상품 조회 (제품 번호)
     * @param Connection 커넥션
     * @param Int 제품번호
     * @return Product 제품
     */
    @Override
    public Product findProductByNum(Connection connection, int productNum) {
        Product product = null;

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT *")
          .append(" FROM product")
          .append(" WHERE product_number = ? AND sale_whether = 'T'");

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setInt(1, productNum);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int fproductNum = rs.getInt("product_number");
                String productName = rs.getString("product_name");
                String category = rs.getString("category");
                int price = rs.getInt("price");
                String thumbnailPath = rs.getString("thumbnail_path");
                String description = rs.getString("description");
                String saleWhether = rs.getString("sale_whether");

                product = new Product();
                product.setProductNum(fproductNum);
                product.setProductName(productName);
                product.setCategory(category);
                product.setPrice(price);
                product.setThumbnailPath(thumbnailPath);
                product.setDescription(description);
                product.setSaleWhether(saleWhether);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {}
        }
        return product;
    }

    /**
     * 단일 상품 조회 (제품 이름)
     * @param Connection 커넥션
     * @param String 제품이름
     * @return Product 제품
     */
    @Override
    public Product findProductByName(Connection connection, String productName) {
        Product product = null;

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT *")
          .append(" FROM product")
          .append(" WHERE product_name = ? AND sale_whether = 'T'");

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setString(1, productName);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int productNum = rs.getInt("product_number");
                String fproductName = rs.getString("product_name");
                String category = rs.getString("category");
                int price = rs.getInt("price");
                String thumbnailPath = rs.getString("thumbnail_path");
                String description = rs.getString("description");
                String saleWhether = rs.getString("sale_whether");

                product = new Product();
                product.setProductNum(productNum);
                product.setProductName(fproductName);
                product.setCategory(category);
                product.setPrice(price);
                product.setThumbnailPath(thumbnailPath);
                product.setDescription(description);
                product.setSaleWhether(saleWhether);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {}
        }
        return product;
    }

    /**
     * 상품 전체 조회
     * @param Connection 커넥션
     * @return List<Product> 제품 리스트
     */
    @Override
    public List<Product> findProductByAll(Connection connection) {
        List<Product> list = null;

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT *")
          .append(" FROM product");

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            rs = pstmt.executeQuery();
            list = new ArrayList<Product>();

            while (rs.next()) {
                int fproductNum = rs.getInt("product_number");
                String productName = rs.getString("product_name");
                String category = rs.getString("category");
                int price = rs.getInt("price");
                String thumbnailPath = rs.getString("thumbnail_path");
                String description = rs.getString("description");
                String saleWhether = rs.getString("sale_whether");

                Product product = new Product();
                product.setProductNum(fproductNum);
                product.setProductName(productName);
                product.setCategory(category);
                product.setPrice(price);
                product.setThumbnailPath(thumbnailPath);
                product.setDescription(description);
                product.setSaleWhether(saleWhether);
                list.add(product);
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

    /**
     * 카테코리 별 상품 조회 (정렬조건 추가)
     * @param Connection 커넥션
     * @param String 카테고리
     * @param String 정렬 조건
     * @return List<Product> 정렬된 제품 리스트 
     */
    @Override
    public List<Product> findProductByCategory(Connection connection, String category, String sortCondition) {

        List<Product> list = null;
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT *")
          .append(" FROM product")
          .append(" WHERE category = ? AND sale_whether = 'T'")
          .append(" ORDER BY " + sortCondition);

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setString(1, category);
            rs = pstmt.executeQuery();
            list = new ArrayList<Product>();

            while (rs.next()) {
                int fproductNum = rs.getInt("product_number");
                String productName = rs.getString("product_name");
                String fcategory = rs.getString("category");
                int price = rs.getInt("price");
                String thumbnailPath = rs.getString("thumbnail_path");
                String description = rs.getString("description");
                String saleWhether = rs.getString("sale_whether");

                Product product = new Product();
                product.setProductNum(fproductNum);
                product.setProductName(productName);
                product.setCategory(fcategory);
                product.setPrice(price);
                product.setThumbnailPath(thumbnailPath);
                product.setDescription(description);
                product.setSaleWhether(saleWhether);
                list.add(product);
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

    /**
     * 인덱스로 제품 리스트 조회
     * @param Connection 커넥션
     * @param String 카테고리
     * @param Int 인덱스
     * @return 제품 리스트 
     */
    @Override
    public List<Product> indexProduct(Connection connection, String category, int rowNum) {

        List<Product> list = null;
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT *")
        .append(" FROM product")
        .append(" WHERE category = ? AND ROWNUM <= ? AND sale_whether = 'T'");

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setString(1, category);
            pstmt.setInt(2, rowNum);
            rs = pstmt.executeQuery();
            list = new ArrayList<Product>();

            while (rs.next()) {
                int fproductNum = rs.getInt("product_number");
                String productName = rs.getString("product_name");
                String fcategory = rs.getString("category");
                int price = rs.getInt("price");
                String thumbnailPath = rs.getString("thumbnail_path");
                String description = rs.getString("description");
                String saleWhether = rs.getString("sale_whether");

                Product product = new Product();
                product.setProductNum(fproductNum);
                product.setProductName(productName);
                product.setCategory(fcategory);
                product.setPrice(price);
                product.setThumbnailPath(thumbnailPath);
                product.setDescription(description);
                product.setSaleWhether(saleWhether);
                list.add(product);
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

    /**
     * 가격 정렬 제품 리스트
     * @param Connection 커넥션
     * @param String 정렬 조건
     * @return List<Product> 제품 리스트 
     */
    @Override
    public List<Product> findProductByPriceSort(Connection connection, String category, String sortSc) {
        List<Product> list = null;

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT *")
          .append(" FROM product")
          .append(" WHERE category = ? AND sale_whether = 'T'")
          .append(" ORDER BY price " + sortSc);

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setString(1, category);
            rs = pstmt.executeQuery();
            list = new ArrayList<Product>();

            while (rs.next()) {
                int fproductNum = rs.getInt("product_number");
                String productName = rs.getString("product_name");
                String fcategory = rs.getString("category");
                int price = rs.getInt("price");
                String thumbnailPath = rs.getString("thumbnail_path");
                String description = rs.getString("description");
                String saleWhether = rs.getString("sale_whether");

                Product product = new Product();
                product.setProductNum(fproductNum);
                product.setProductName(productName);
                product.setCategory(fcategory);
                product.setPrice(price);
                product.setThumbnailPath(thumbnailPath);
                product.setDescription(description);
                product.setSaleWhether(saleWhether);
                list.add(product);
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

    /**
     * 제품 리스트 전체 조회
     * @param Connection 커넥션
     * @return List<Product> 제품 리스트
     */
    @Override
    public List<Product> findByAll(Connection connection) {
        List<Product> list = null;
        StringBuilder sb = new StringBuilder();
            sb.append(" SELECT *")
              .append(" FROM product");

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            rs = pstmt.executeQuery();
            list = new ArrayList<Product>();
            while (rs.next()) {
                int fproductNum = rs.getInt("product_number");
                String productName = rs.getString("product_name");
                String fcategory = rs.getString("category");
                int price = rs.getInt("price");
                String thumbnailPath = rs.getString("thumbnail_path");
                String description = rs.getString("description");
                String saleWhether = rs.getString("sale_whether");
                Product product = new Product();
                product.setProductNum(fproductNum);
                product.setProductName(productName);
                product.setCategory(fcategory);
                product.setPrice(price);
                product.setThumbnailPath(thumbnailPath);
                product.setDescription(description);
                product.setSaleWhether(saleWhether);
                list.add(product);
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