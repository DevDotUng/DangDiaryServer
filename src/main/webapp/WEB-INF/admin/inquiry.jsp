<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Admin Inquiry</title>
</head>
    <body>
    <h1>답변 준비중</h1>
    <table border="1">
        <th>문의 id</th>
        <th>유저 id</th>
        <th>닉네임</th>
        <th>문의 날짜</th>
        <th>문의 유형</th>
        <th>제목</th>
        <th>내용</th>
        <th>진행 상황</th>
        <th>답변</th>
        <th>답변 날짜</th>
        <th>고마워요 여부</th>
        <th>접수 완료 버튼</th>
        <c:forEach var="inquiryHistory" items="${preparingForAnswerList}" >
            <tr>
                <td>${inquiryHistory.inquiryId}</td>
                <td>${inquiryHistory.userId}</td>
                <td>${inquiryHistory.nickname}</td>
                <td>${inquiryHistory.registerDate}</td>
                <td>${inquiryHistory.type}</td>
                <td>${inquiryHistory.title}</td>
                <td>${inquiryHistory.content}</td>
                <td>${inquiryHistory.progress}</td>
                <td>${inquiryHistory.answer}</td>
                <td>${inquiryHistory.answerDate}</td>
                <td>${inquiryHistory.isLike}</td>
                <td>
                    <form action="/admin/inquiry/receive" method="get">
                        <input type="text" name="inquiryId" value="${inquiryHistory.inquiryId}" style="width:50px;" readonly/>
                        <input type="submit" value="답변하기">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br><br>
    <h1>접수 완료</h1>
    <table border="1">
        <th>문의 id</th>
        <th>유저 id</th>
        <th>닉네임</th>
        <th>문의 날짜</th>
        <th>문의 유형</th>
        <th>제목</th>
        <th>내용</th>
        <th>진행 상황</th>
        <th>답변</th>
        <th>답변 날짜</th>
        <th>고마워요 여부</th>
        <th>답변하기 버튼</th>
        <c:forEach var="inquiryHistory" items="${receivedList}" >
            <tr>
                <td>${inquiryHistory.inquiryId}</td>
                <td>${inquiryHistory.userId}</td>
                <td>${inquiryHistory.nickname}</td>
                <td>${inquiryHistory.registerDate}</td>
                <td>${inquiryHistory.type}</td>
                <td>${inquiryHistory.title}</td>
                <td>${inquiryHistory.content}</td>
                <td>${inquiryHistory.progress}</td>
                <td>${inquiryHistory.answer}</td>
                <td>${inquiryHistory.answerDate}</td>
                <td>${inquiryHistory.isLike}</td>
                <td>
                    <form action="/admin/inquiry/answer" method="post">
                        <input type="text" name="answer" style="width:300px;height:200px;">
                        <input type="text" name="inquiryId" value="${inquiryHistory.inquiryId}" style="width:50px;" readonly/>
                        <input type="submit" value="답변하기">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br><br>
    <h1>답변 완료</h1>
    <table border="1">
        <th>문의 id</th>
        <th>유저 id</th>
        <th>닉네임</th>
        <th>문의 날짜</th>
        <th>문의 유형</th>
        <th>제목</th>
        <th>내용</th>
        <th>진행 상황</th>
        <th>답변</th>
        <th>답변 날짜</th>
        <th>고마워요 여부</th>
        <c:forEach var="inquiryHistory" items="${answerCompleteList}" >
            <tr>
                <td>${inquiryHistory.inquiryId}</td>
                <td>${inquiryHistory.userId}</td>
                <td>${inquiryHistory.nickname}</td>
                <td>${inquiryHistory.registerDate}</td>
                <td>${inquiryHistory.type}</td>
                <td>${inquiryHistory.title}</td>
                <td>${inquiryHistory.content}</td>
                <td>${inquiryHistory.progress}</td>
                <td>${inquiryHistory.answer}</td>
                <td>${inquiryHistory.answerDate}</td>
                <td>${inquiryHistory.isLike}</td>
            </tr>
        </c:forEach>
    </table>
    </body>
</html>