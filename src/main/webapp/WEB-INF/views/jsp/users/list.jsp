
<%@include file="../fragments/header.jsp" %>
<body>

<div class="panel panel-default">
    <div class="panel-heading">
        <strong>Seguridad Web aplicando Spring Security</strong>
        <ul class="glyphicon glyphicon-education"> Autor: Emilio Castellano</ul>
        <ul class="glyphicon">&diams; Tutor: Juan Alberto de Frutos</ul>
    </div>
    <div class="panel-body">
        <div class="container">
            <table class="table table-stripped table-fixed">
                <thead>
                <tr>
                    <th class="col-md-2">First Name</th>
                    <th class="col-md-3">Last Name</th>
                    <th class="col-md-2">Email</th>
                    <th class="col-md-2">Username</th>
                    <th class="col-md-3">Available operations</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td class="col-md-2">${user.firstName}</td>
                        <td class="col-md-3">${user.lastName}</td>
                        <td class="col-md-2">${user.email}</td>
                        <td class="col-md-2">${user.username}</td>
                        <td class="col-md-3">
                            <sec:authorize access="hasRole('ADMIN')">
                            <a href="<c:url value='/users/edit-user-${user.username}' />"
                               class="btn btn-warning custom-width">Edit</a>
                            <a href="<c:url value='/users/delete-user-${user.username}' />"
                               class="btn btn-danger custom-width">Delete</a>
                            </sec:authorize>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <sec:authorize access="hasRole('ADMIN')">
                <a href="<c:url value='/users/add' />">Add New User</a>
            </sec:authorize>
        </div>
        <%--  <jsp:include page="../fragments/footer.jsp"/>--%>
    </div>
</div>

</body>
</html>