package ezen.call.domain.product.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import ezen.call.domain.product.dao.ProductDao;
import ezen.call.domain.product.dto.Product;

/**
 * 제품 관련 비즈니스 로직 처리 및 트랜잭션 관리 구현체
 * @author 김종원
 */
public class ProductServiceImpl implements ProductService {
    
    private DataSource dataSource;
    private ProductDao productDao;

    public ProductServiceImpl(DataSource dataSource, ProductDao productDao) {
        this.dataSource = dataSource;
        this.productDao = productDao;
    }
    
    /**
     * 상품 등록
     * @param product 등록할 상품 정보
     */
    @Override
    public void createProduct(Product product) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            productDao.create(connection, product);
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new RuntimeException(e.getMessage());
            }
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e1) {}
        }
    }
    
    /**
     * 상품 활성화
     * @param Int 제품 번호
     */
    @Override
    public void enabledProduct(int productNum) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            productDao.enabled(connection, productNum);
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new RuntimeException(e.getMessage());
            }
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e1) {}
        }
    }
    
    /**
     * 상품 비활성화
     * @param Int 제품 번호
     */
    @Override
    public void disabledProduct(int productNum) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            productDao.disabled(connection, productNum);
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new RuntimeException(e.getMessage());
            }
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e1) {}
        }
    }
    
    /**
     * 단일 상품 조회 (상품 번호)
     * @param Int 제품 번호
     * @return 조회된 제품
     */
    @Override
    public Product readProductByNum(int productNum) {
        Product product = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            product = productDao.findProductByNum(connection, productNum);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e1) {}
        }
        return product;
    }
    
    /**
     * 단일 상품 조회 (상품 이름)
     * @param String 제품 이름
     * @return 조회된 제품
     */
    @Override
    public Product readProductByName(String productName) {
        Product product = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            product = productDao.findProductByName(connection, productName);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e1) {}
        }
        return product;
    }
    
    /**
     * 상품 조회 (카테고리 별, 정렬조건 추가)
     * @param String 카테고리
     * @param String 정렬 조건
     * @return 정렬된 제품 리스트
     */
    @Override
    public List<Product> readProductListCategory(String category, String sortCondition) {
        List<Product> productList = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            productList = productDao.findProductByCategory(connection, category, sortCondition);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e1) {}
        }
        return productList;
    }

    /**
     * 상품 조회 (카테고리 별, 가격순, 내림차순/오름차순 조건 추가)
     * @param String 카테고리
     * @param String 내림차순/오름차순 조건
     * @return 정렬된 제품 리스트
     */
    @Override
    public List<Product> readProductListPriceSort(String category, String sortSc) {
         List<Product> productList = null;
         Connection connection = null;
         try {
             connection = dataSource.getConnection();
             productList = productDao.findProductByPriceSort(connection, category, sortSc);
         } catch (Exception e) {
             throw new RuntimeException(e.getMessage());
         } finally {
             try {
                 if (connection != null) connection.close();
             } catch (SQLException e1) {}
         }
         return productList;
    }

    /**
     * 메인 페이지 상품 조회(3)
     * @param String 카테고리
     * @return 제품 리스트
     */
    @Override
    public List<Product> indexProductList(String category) {
        List<Product> productList = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            productList = productDao.indexProduct(connection, category, 3);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e1) {}
        }
        return productList;
    }

    /**
     * 디테일 페이지 추천상품 조회(4)
     * @param String 카테고리
     * @return 제품 리스트
     */
    @Override
    public List<Product> detailProductList(String category) {
        List<Product> productList = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            productList = productDao.indexProduct(connection, category, 4);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e1) {}
        }
        return productList;
    }
    
    /**
     * 제품 리스트 조회
     * @return 제품 리스트
     */
    @Override
    public List<Product> findByAll() {
        List<Product> productList = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            productList = productDao.findByAll(connection);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e1) {}
        }
        return productList;
    }
    
    public static void main(String[] args) {
//        ServiceFactory serviceFactory = ServiceFactory.getInstance();
//        ProductService productService = serviceFactory.getProductService();
//        
//        Product product = new Product(100, "테스트1", "glasses", 15000, "주소",
//                "테스트1입니다.", "T");
//        product = productService.createProduct(product);
//        System.out.println(product);
//        
//        Product product1 = productService.readProductByName("안경1");
//        System.out.println(product1);
//        
//        List<Product> productList = productService.readProductListCategory("glasses","price");
//        for (Product product2 : productList) {
//            System.out.println(product2);
//        }
//        List<Product> productList = productService.readProductListPriceSort("glasses","desc");
//        for (Product product2 : productList) { 
//            System.out.println(product2);
//        }
//        
//        Product product2 = productService.disabledProduct(25);
//        System.out.println(product2);
//        
//        Product product2 = productService.enabledProduct(25);
//        System.out.println(product2);
    }
}