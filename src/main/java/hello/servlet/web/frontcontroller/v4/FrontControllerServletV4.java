package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormContorllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormContorllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // URI 조회
        String requestURI = request.getRequestURI();

        ControllerV4 controller = controllerMap.get(requestURI);

        // URI가 등록되지 않았다면, NOT_FOUND 명시적으로 반환
        if (controller == null) {
            // 그냥 숫자 코드를 쓰는 것보다, 상수로 표현하는 게 낫다.
            // 변하지 않고, 숫자보다 의미가 있다.
//            response.setStatus(404);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // controller에 넘겨줄 paramMap과 model 생성
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();

        // controller 로직 처리
        String viewName = controller.process(paramMap, model);

        // viewname 조합해서 MyView 생성.
        MyView myView = viewResolver(viewName);
        myView.render(model, request, response);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
            .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
