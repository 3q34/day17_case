<%@page contentType="text/html;charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>
        function deleteUser(id) {
            if (confirm("您确定删除该数据？"))
                location.href = " ${pageContext.request.contextPath}/deleteUserServlet?id=" + id;
        }

        window.onload = function () {
            document.getElementById("btnDelSelected").onclick = function () {
                var flag = false;
                var ids = document.getElementsByName("uid");
                for (var i = 0; i < ids.length; i++) {
                    if (ids[i].checked) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    if (confirm("您确定删除该数据？"))
                        document.getElementById("formId").submit();
                } else
                    alert("请选中删除对象！");

            };
            document.getElementById("checkAll").onclick = function () {

                var ids = document.getElementsByName("uid");
                for (var i = 0; i < ids.length; i++) {
                    ids[i].checked = this.checked;
                }
            }
        }

    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>
    <div style="float: left;">
        <form class="form-inline" role="form" action="${pageContext.request.contextPath}/findUserByPageServlet">
            <div class="form-group">
                <label for="name">姓名</label>
                <input type="text" value="${conditon.name[0]}" class="form-control" name="name" id="name" placeholder="请输入籍贯">
            </div>
            <div class="form-group">
                <label for="address">籍贯</label>
                <input type="text"  value="${conditon.address[0]}" class="form-control" id="address" name="address" placeholder="请输入籍贯">
            </div>
            <div class="form-group">
                <label for="email">邮箱</label>
                <input type="text" value="${conditon.email[0]}" class="form-control" id="email" name="email" placeholder="请输入邮箱">
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>

    <div style="float: right;margin:5px ">
        <a class="btn btn-primary" href="add.jsp">添加联系人</a>
        <a class="btn btn-primary" id="btnDelSelected" href="javascript:void(0)">删除联系人</a></div>
    <form id="formId" action="${pageContext.request.contextPath}/delSelectedUserServlet" method="post">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th><input type="checkbox" id="checkAll"></th>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>籍贯</th>
                <th>QQ</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${pageBean.list}" var="user" varStatus="s">
                <tr>
                    <td><input type="checkbox" name="uid" value="${user.id}"></td>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.gender}</td>
                    <td>${user.age}</td>
                    <td>${user.address}</td>
                    <td>${user.qq}</td>
                    <td>${user.email}</td>
                    <td><a class="btn btn-default btn-sm"
                           href="${pageContext.request.contextPath}/getUserServlet?id=${user.id}">修改</a>&nbsp;
                        <a class="btn btn-default btn-sm"
                           href="javascript:deleteUser(${user.id})">删除</a></td>
                </tr>
            </c:forEach>

        </table>
    </form>
    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">

                <c:if test="${pageBean.currentPage==1}">

                    <li class="disabled">
                        <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pageBean.currentPage-1<1?1:pageBean.currentPage-1}&pageSize=7&name=${conditon.name[0]}&address=${conditon.address[0]}&email=${conditon.email[0]}"
                           aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:if test="${pageBean.currentPage!=1}">

                    <li>
                        <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pageBean.currentPage-1<1?1:pageBean.currentPage-1}&pageSize=7&name=${conditon.name[0]}&address=${conditon.address[0]}&email=${conditon.email[0]}"
                           aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:forEach begin="1" var="i" varStatus="vst" end="${pageBean.totalPage}">

                    <c:if test="${pageBean.currentPage==i}">
                        <li class="active">
                            <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&pageSize=7&name=${conditon.name[0]}&address=${conditon.address[0]}&email=${conditon.email[0]}">${i}</a>
                        </li>
                    </c:if>
                    <c:if test="${pageBean.currentPage!=i}">
                        <li>
                            <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&pageSize=7&name=${conditon.name[0]}&address=${conditon.address[0]}&email=${conditon.email[0]}">${i}</a>
                        </li>
                    </c:if>
                </c:forEach>
                <c:if test="${pageBean.currentPage==pageBean.totalPage}">
                    <li class="disabled">
                        <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pageBean.currentPage+1>pageBean.totalPage?pageBean.totalPage:pageBean.currentPage+1}&pageSize=7&name=${conditon.name[0]}&address=${conditon.address[0]}&email=${conditon.email[0]}"
                           aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:if test="${pageBean.currentPage!=pageBean.totalPage}">
                    <li>
                        <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pageBean.currentPage+1>pageBean.totalPage?pageBean.totalPage:pageBean.currentPage+1}&pageSize=7&name=${conditon.name[0]}&address=${conditon.address[0]}&email=${conditon.email[0]}"
                           aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
                <span style="font-size: 25px;margin-left: 5px">共${pageBean.totalCount}条，共${pageBean.totalPage}页</span>
            </ul>
        </nav>
    </div>
</div>
</body>
</html>
