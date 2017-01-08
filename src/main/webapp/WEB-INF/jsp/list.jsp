<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 引入jstl -->
<%@include file="common/tag.jsp" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>选票页面</title>
    <%@include file="common/head.jsp" %>
</head>
<body>
<!-- 页面显示部分 -->
<div class="container">
    <div class="panel panel-default text-right">
            <c:if test="${sessionScope.username!=null}">
            <span><span class="text-center">${sessionScope.username},欢迎你登录</span><a id="logout-btn" class="btn btn-danger">[退出]</a></span>
            </c:if>
            <c:if test="${sessionScope.username==null}">
                <span><a href="/login/login" class="btn btn-primary">欢迎登录</a></span>
            </c:if>

    </div>
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h2>秒杀列表</h2>
        </div>
        <div class="panel-body">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>名称</th>
                    <th>库存</th>
                    <th>开始时间</th>
                    <th>结束时间</th>
                    <th>创建时间</th>
                    <th>详情页</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="ticket" items="${list}">
                    <tr>
                        <td>${ticket.name}</td>
                        <td>${ticket.number}</td>
                        <td>
                            <fmt:formatDate value="${ticket.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>
                            <fmt:formatDate value="${ticket.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>
                            <fmt:formatDate value="${ticket.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>
                            <a class="btn btn-info" href="/ticket/${ticket.ticketId}/detail" target="_blank">link</a>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script>
    $('#logout-btn').on('click', function () {
            $.post('/login/logout', {}, function(result) {
                if (result && result['success']) {
                    //alert("登出成功，请直接登录");
                    // 刷新页面
                    location.reload();
                    $.cookie('userId', null, {path: '/'});
                } else {
                    var error = result['error'];
                    alert(error);
                }
            });
    });


</script>
</body>
</html>
