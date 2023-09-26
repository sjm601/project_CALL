package ezen.call.web.mvc.controller;

import java.util.Map;

/**
 * 세부 컨트롤러 명세(역할)
 * @author 이희영
 */
public interface BasicController {
    public ModelAndView process(Map<String, String> paramMap);
}