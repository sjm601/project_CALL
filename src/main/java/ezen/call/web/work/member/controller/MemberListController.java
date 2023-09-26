package ezen.call.web.work.member.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ezen.call.domain.common.factory.ServiceFactory;
import ezen.call.domain.member.dto.MemberCombined;
import ezen.call.domain.member.service.MemberService;
import ezen.call.web.common.page.PageParams;
import ezen.call.web.common.page.Pagination;
import ezen.call.web.mvc.controller.HttpController;

/**
 * 회원 목록 요청 처리 세부 컨트롤러
 * @author 강소영
 * /member
 */
public class MemberListController implements HttpController{
    
    // 한 페이지에 보여지는 목록 갯수 설정
    private static final int ELEMENT_SIZE = 5;
    // 한페이지에 보여지는 페이지 갯수 설정
    private static final int PAGE_SIZE = 5;
    
    private MemberService memberService = ServiceFactory.getInstance().getMemberService();
    
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model, HttpServletRequest request,
            HttpServletResponse response) {
        String viewName = "member/memberpage";
        
        // 사용자 선택페이지
        String requestPage = paramMap.get("page");
        if (requestPage == null || requestPage.equals("")) {
            requestPage = "1";
        }
        int selectPage = Integer.parseInt(requestPage);

        // 페이징 계산을 위한 게시글 전체 갯수 조회
        int rowCount = memberService.getMemberCount();

        // 전체 페이지수 계산
        PageParams params = new PageParams(ELEMENT_SIZE, PAGE_SIZE, selectPage, rowCount);
        Pagination pagination = new Pagination(params);
        List<MemberCombined> memberList = memberService.getMembersPaginationCombined(params);
        
        // 모델에 게시글 목록 설정
        model.put("memberList", memberList);
        // 모델에 페이징 정보 설정
        model.put("pagination", pagination);
        
        return viewName;
    }
}