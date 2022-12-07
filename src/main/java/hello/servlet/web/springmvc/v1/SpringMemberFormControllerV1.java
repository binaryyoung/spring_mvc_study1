package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// 1. 컴포넌트 스캔 대상이 된다, 스프링이 자동으로 스프링 빈으로 등록한다.
// 2. 스프링 MVC에서 애노테이션 기반 컨트롤러로 인식한다. =>
// (RequestMappingHandlerMapping은 스프링 빈 중에서 @RequestMapping 또는
// @Controller가 클래스 레벨에 붙어있는 경우 매핑 정보로 인식한다)
@Controller // @Component + @RequestMapping
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}
