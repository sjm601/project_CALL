package ezen.call.web.work.member.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ezen.call.domain.common.factory.ServiceFactory;
import ezen.call.domain.member.dto.Member;
import ezen.call.domain.member.dto.MemberCombined;
import ezen.call.domain.member.service.MemberService;
import ezen.call.web.mvc.controller.HttpController;

/**
 * 회원 로그인 및 로그아웃 처리 세부 컨트롤러
 * 
 * @author 박상훈 /member/signin
 */
public class MemberSigninController implements HttpController {

	private MemberService memberService = ServiceFactory.getInstance().getMemberService();

	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) {
		String viewName = "member/signin";

		// 로그인 요청 (POST)
		if (request.getMethod().equalsIgnoreCase("post")) {
			String loginEmail = paramMap.get("email");
			String loginPassword = paramMap.get("password");
			String saveEmail = paramMap.get("saveEmail");

			Member loginMember = memberService.isMember(loginEmail, loginPassword);

			// 회원이 아닐경우 오류 메세지
			if (loginMember == null) {
				model.put("loginError", "아이디 또는 비밀번호가 올바르지 않습니다.");
				return viewName;
			}

			int memberNumber = loginMember.getMemberNum();
			MemberCombined memberCombined = memberService.readMemberByNumCombined(memberNumber);
			// 회원인 경우
			if (loginMember != null) {
				// 세션에 로그인정보 저장
				request.getSession().setAttribute("loginMember", memberCombined);
				// 아이디 저장 체크 시 쿠키 생성
				if (saveEmail != null) {
					Cookie saveCookie;
					saveCookie = new Cookie("saveEmail", loginMember.getEmail());
					saveCookie.setPath("/");
					saveCookie.setMaxAge(60 * 60 * 24 * 100);
					response.addCookie(saveCookie);
				} else if (saveEmail == null) {
					Cookie saveCookie;
					saveCookie = new Cookie("saveEmail", loginMember.getEmail());
					saveCookie.setPath("/");
					saveCookie.setMaxAge(0);
					response.addCookie(saveCookie);
				}
				viewName = "redirect:/mall/index";
			}

			// 로그아웃 요청 (GET)
		} else if (request.getQueryString().equalsIgnoreCase("logout")) {
			// 세션 삭제
			request.getSession().invalidate();
			viewName = "redirect:/mall/index";

			// 로그인 화면 요청 (GET)
		} else if (request.getQueryString().equalsIgnoreCase("login") && request.getMethod().equalsIgnoreCase("get")) {
			viewName = "member/signin";
		}
		return viewName;
	}
}