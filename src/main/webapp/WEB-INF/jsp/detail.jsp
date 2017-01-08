<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 引入jstl --%>
<%@include file="common/tag.jsp" %>
<!DOCTYPE html>
<html lang="zh-cn">

<head>
    <title>电影票详情页</title>
    <%@include file="common/head.jsp" %>
</head>

<body>
<!-- 页面显示部分 -->
<div class="container">
    <div class="panel panel-default text-center">
        <div class="panel-heading">
            <h2>${ticket.name}</h2>
        </div>
        <div class="panel-body">
            <!--  显示time图标 -->
            <span class="glyphicon glyphicon-time"></span>
            <!--  展示倒计时  -->
            <span class="glyphicon" id="ticket-box"></span>
        </div>
    </div>
</div>
<!--  抢票状态层  -->
<div id="grapTicketStatus" class="modal fade">
    <div class="panel panel-default text-center">
        <div class="panel-body">
            <div class="panel-content">
                <!-- 验证信息 -->
                <span id="status" class="glyphicon"></span>
            </div>
        </div>
    </div>
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<!-- jquery cookie  plugin -->
<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<!-- jquery countdown plugin  -->
<script src="http://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>
<!--   导入交互逻辑 -->
<script src="/resources/js/ticket.js" type="text/javascript"></script>
<script type="text/javascript">

    $(function () {
        // 使用EL表达式传入参数
        grapTicketModel.init({
            ticketId: ${ticket.ticketId},
            startTime: ${ticket.startTime.time},
            endTime: ${ticket.endTime.time}
        });
    });
</script>
</body>

</html>
