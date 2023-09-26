package ezen.call.web.work.store.controller;

import java.util.Map;

import ezen.call.web.mvc.controller.WebController;
/**
 * 매장 요청 처리 세부 컨트롤러
 * @author 이한솔
 * /index
 */
public class StoreController implements WebController{
    
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String viewName = "store/store";
        return viewName;
    }
}