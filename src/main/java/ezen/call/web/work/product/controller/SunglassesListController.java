package ezen.call.web.work.product.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ezen.call.domain.common.factory.ServiceFactory;
import ezen.call.domain.product.dto.Product;
import ezen.call.domain.product.service.ProductService;
import ezen.call.web.mvc.controller.HttpController;

/**
 * 선글라스 리스트 요청 처리 세부 컨트롤러
 * @author 강소영
 * /category/category_sg
 */
public class SunglassesListController implements HttpController {

    private ProductService productService = ServiceFactory.getInstance().getProductService();
    
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model, HttpServletRequest request,
            HttpServletResponse response) {
        
        String viewName = "category/category_sg";
        
        // 이름순정렬 (GET)
        if (request.getQueryString().equalsIgnoreCase("sortName")) {
             List<Product> productList = productService.readProductListCategory("sunglasses", "product_name");
             model.put("productList", productList);
             
        // 높은가격순정렬 (GET)
        } else if(request.getQueryString().equalsIgnoreCase("sortHighprice")) {
            List<Product> productList = productService.readProductListPriceSort("sunglasses", "DESC");
            model.put("productList", productList);
            
        // 낮은가격순정렬 (GET)
        } else if(request.getQueryString().equalsIgnoreCase("sortLowprice")) {
             List<Product> productList = productService.readProductListPriceSort("sunglasses", "ASC");
             model.put("productList", productList);
             
        //기본정렬-등록순서대로 보여주기 (GET)
        } else if(request.getQueryString().equalsIgnoreCase("sg")){
            List<Product> productList = productService.readProductListCategory("sunglasses","product_number");
            model.put("productList", productList);
        }
         return viewName;
    }
}