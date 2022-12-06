<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%
    // jsp가 결국 servlet으로 변경되어 service를 호출하는 방식이다.
    // request, response 사용 가능

    MemberRepository memberRepository = MemberRepository.getInstance();

    // application/x-www-form-urlencoded 형식 이나 url뒤에 붙는 쿼리 파라미터나
    // HttpServletRequest.getParameter("변수명")을 통해 값을 불러올 수 있다.
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    // 저장
    Member member = new Member(username, age);
    Member savedMember = memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>