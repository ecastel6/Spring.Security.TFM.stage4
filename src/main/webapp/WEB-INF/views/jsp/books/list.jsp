<%--<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>--%>

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
            <table class="table table-stripped" style="table-layout: fixed">
                <thead>
                <tr>
                    <th class="col-md-2">Author</th>
                    <th class="col-md-3">Title</th>
                    <th class="col-md-1">Price</th>
                    <th class="col-md-4">Description</th>
                    <th class="col-md-2">Available operations</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${books}" var="book">
                    <tr>
                        <td class="col-md-2">${book.author}</td>
                        <td class="col-md-3">${book.title}</td>
                        <td class="col-md-1">${book.price}</td>
                        <td class="col-md-4">${book.description}</td>
                        <td class="col-md-2">
                            <sec:authorize access="hasRole('ADMIN')">
                                    <spring:url value="/books/${book.id}/delete?${_csrf.parameterName}=${_csrf.token}" var="deleteUrl" />
                                    <spring:url value="/books/${book.id}/update" var="updateUrl" />
                                <button class="btn btn-primary custom-width" onclick="location.href='${updateUrl}'">Update</button>
                                <button class="btn btn-danger custom-width" onclick="this.disabled=true;post('${deleteUrl}')">Delete</button>
                            </sec:authorize>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <sec:authorize access="hasRole('ADMIN')">
                <a href="<c:url value='/books/add' />">Add Book</a>
            </sec:authorize>
        </div>
        <jsp:include page="../fragments/footer.jsp"/>
    </div>
</div>

</body>
</html>