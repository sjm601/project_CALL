package ezen.call.web.work.home;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ezen.call.domain.common.factory.ServiceFactory;
import ezen.call.domain.member.dto.MemberCombined;
import ezen.call.domain.member.service.MemberService;
import ezen.call.domain.product.dto.Product;
import ezen.call.domain.product.service.ProductService;
import ezen.call.web.mvc.controller.HttpController;
/**
 * /index 요청에 대한 세부 핸들러
 * @author 이희영
 */
public class HomeController implements HttpController{
    
    private ProductService productService = ServiceFactory.getInstance().getProductService();
    private MemberService memberService = ServiceFactory.getInstance().getMemberService();

	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) {
		String viewName = "index";
        
        List<Product> glassesList = productService.indexProductList("glasses");
        model.put("glassesList", glassesList);
        
        List<Product> sunglassesList = productService.indexProductList("sunglasses");
        model.put("sunglassesList", sunglassesList);
        
        if (request.getQueryString() != null) {
			if (request.getQueryString().equalsIgnoreCase("quit")) {
				// 회원 탈퇴(상태값 업데이트)
				MemberCombined memberCombined = (MemberCombined) request.getSession().getAttribute("loginMember");
				int memberNum = memberCombined.getMember().getMemberNum();

				if (memberCombined != null) {
					memberNum = memberCombined.getMember().getMemberNum();
					memberService.updateMemberDisabled(memberNum);
					// 세션삭제
					request.getSession().invalidate();
					viewName = "redirect:/mall/index";
				}
			}
		}
        
        return viewName;
	}
}