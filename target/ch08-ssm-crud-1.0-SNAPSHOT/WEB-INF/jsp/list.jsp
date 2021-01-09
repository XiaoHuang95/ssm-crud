<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    pageContext.setAttribute("app_path",request.getContextPath());
%>
<html>
<head>
    <link rel="stylesheet" href="${app_path}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
    <script type="text/javascript" src="${app_path}/static/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="${app_path}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <title>员工列表</title>
</head>
<body>
    <!--搭建显示页面-->
    <div class="container">
        <!--标题-->
        <div class="row">
            <div class="col-md-12">
                <h1>SSM-CRUD</h1>
            </div>
        </div>
        <%--两个按钮--%>
        <div class="row">
            <div class="col-md-4 col-md-offset-8">
                <button class="btn btn-primary">新增</button>
                <button class="btn btn-danger">删除</button>
            </div>
        </div>
        <%--表格--%>
        <div class="row col-md-12">
            <table class="table table-hover">
                <tr>
                    <th>#</th>
                    <th>empName</th>
                    <th>gender</th>
                    <th>email</th>
                    <th>deptName</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${pageInfo.list}" var="emp">
                <tr>
                    <td>${emp.empId}</td>
                    <td>${emp.empName}</td>
                    <td>${emp.gender == "male"?"男":"女"}</td>
                    <td>${emp.email}</td>
                    <td>${emp.department.deptName}</td>
                    <td>
                        <button class="btn btn-primary btn-sm">
                            <span  class="glyphicon glyphicon-pencil"></span>
                            编辑
                        </button>
                        <button class="btn btn-danger btn-sm">
                            <span class="glyphicon glyphicon-trash"></span>
                            删除
                        </button>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
        <%--分页--%>
        <div class="row">
            <%--分页文字信息--%>
            <div class="col-md-6">
                当前第${pageInfo.pageNum}页,总共${pageInfo.pages}页,共有${pageInfo.total}条记录
            </div>
             <!--分页条信息-->
            <div class="col-md-6">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                            <li>
                                <a href="${app_path}/emps?pn=1">首页</a>
                            </li>
                        <c:if test="${pageInfo.hasPreviousPage}">
                            <li>
                                <a href="${app_path}/emps?pn=${pageInfo.pageNum-1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <c:forEach items="${pageInfo.navigatepageNums}" var="pageNum">
                            <c:if test="${pageNum==pageInfo.pageNum}">
                                <li class="active"><a href="#">${pageNum}</a></li>
                            </c:if>
                            <c:if test="${pageNum!=pageInfo.pageNum}" >
                                <li><a href="${app_path}/emps?pn=${pageNum}">${pageNum}</a></li>
                            </c:if>
                        </c:forEach>
                        <c:if test="${pageInfo.hasNextPage}">
                            <li>
                                <a href="${app_path}/emps?pn=${pageInfo.pageNum+1}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <li><a href="${app_path}/emps?pn=${pageInfo.pages}">末页</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</body>
</html>
