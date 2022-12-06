package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// HttpServlet 을 이용해 http get 요청에 대해 json 데이터를 담아 응답하라.

// 2. @WebServlet 어노테이션으로 urlPatterns 지정.
// => @WebServlet 어노테이션이 등장하기 이전에는 web.xml 파일에서 일일이 컨트롤러 파일과 url 을 연결시켜 주어야 했다.
// https://gugbab2.tistory.com/21 참조
@WebServlet(name = "responseJsonServlet", urlPatterns = "response-json")
public class ResponseJsonServlet extends HttpServlet { // 1. servlet의 기능을 이용하기 위해 상속
    // http 요청이 호출되면 protected 접근제한자를 쓰는 service 메서드를 호출하기 때문에

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // [응답 헤더]
        // json 형식으로 응답하기 위해서는 content-type 을 application/json 으로 지정해주어야 한다.
        response.setContentType("application/json");

        // 문자 인코딩 : 한글을 비롯한 여러 문자들은 기본 인코딩으로 하면 깨진다.
        // HttpServletResponse 클래스를 까보면 우선순위 나오는데  default ISO-8859-1 으로되어 있고
        // setCharacterEncoding 을 지정하면 1순위로 해당 인코딩 형식이 반영된다.
        response.setCharacterEncoding("utf-8");

        // [응답 메시지]
        // HelloData 객체를 json으로 변환해서 메시지에 담기.

        HelloData helloData = new HelloData();
        helloData.setUsername("hello");
        helloData.setAge(20);

        // {"userName":"hello","age":20}
        String result = objectMapper.writeValueAsString(helloData);

        response.getOutputStream().println(result);
    }
}
