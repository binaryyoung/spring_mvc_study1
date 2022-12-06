package hello.servlet.web.servlet;


import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    // TODO 싱글톤 개념, 싱글톤 장단점 정리
    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // application/x-www-form-urlencoded 형식 이나 url뒤에 붙는 쿼리 파라미터나
        // HttpServletRequest.getParameter("변수명")을 통해 값을 불러올 수 있다.
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        // 저장
        Member member = new Member(username, age);
        Member savedMember = memberRepository.save(member);

        // 응답
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        //동적인 html을 제공해줄 필요가 있다.
        // PrintWriter에 html을 적는 방법은 오타날 확률이 너무 높다....
        PrintWriter w = response.getWriter();
        w.write("<html>\n" +
            "<head>\n" +
            " <meta charset=\"UTF-8\">\n" + "</head>\n" +
            "<body>\n" +
            "성공\n" +
            "<ul>\n" +
            "    <li>id="+member.getId()+"</li>\n" +
            "    <li>username="+member.getUsername()+"</li>\n" +
            " <li>age="+member.getAge()+"</li>\n" + "</ul>\n" +
            "<a href=\"/index.html\">메인</a>\n" + "</body>\n" +
            "</html>");
    }
}
