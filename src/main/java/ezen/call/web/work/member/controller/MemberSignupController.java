package ezen.call.web.work.member.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ezen.call.domain.common.factory.ServiceFactory;
import ezen.call.domain.member.dto.Member;
import ezen.call.domain.member.dto.MemberHistory;
import ezen.call.domain.member.service.MemberService;
import ezen.call.web.mvc.controller.HttpController;

/**
 * 회원 가입 화면 및 가입 요청 처리 세부 컨트롤러
 * @author 이한솔
 * /member/signup
 */
public class MemberSignupController implements HttpController {

    private MemberService memberService = ServiceFactory.getInstance().getMemberService();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model, HttpServletRequest request,
            HttpServletResponse response) {
        String viewName = null;
        // 회원 가입 화면 요청 (Get)
        if (request.getMethod().equalsIgnoreCase("get")) {
            viewName = "member/signup";
            
        // 회원 가입 요청 (POST)
        } else {
            String email = paramMap.get("email");
            String password = paramMap.get("password");
            String memberName = paramMap.get("member_name");
            String phoneNum = paramMap.get("phone_number");
            String birthday = paramMap.get("birthday");
            String gender = paramMap.get("gender");

            boolean confirmEmail = memberService.confirmEmail(email);
            boolean confirmPhoneNum = memberService.confirmPhoneNum(phoneNum);
            if (confirmEmail || confirmPhoneNum) {
                String alertScript = "<script>alert('Email or phone number is already registered.'); window.location.href='/mall/member/signup';</script>";
                try {
                    response.getWriter().write(alertScript);
                } catch (IOException e) {}
            } else {
                viewName = "index";
                Member member = new Member(email, password, memberName, phoneNum, birthday, gender);
                MemberHistory memberHistory = new MemberHistory();
                // 반환받은 member 객체에는 가입일자 정보 포함
                memberService.registerMember(member, memberHistory);
            }
        }
        return viewName;
    }
}