<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<style>
    .error {
        color: #ff0000;
    }

    .errorblock {
        color: #000;
        background-color: #ffEEEE;
        border: 3px solid #ff0000;
        padding: 8px;
        margin: 16px;
    }
</style>
<body>

<h2>Book Information</h2>
<c:if test="${not empty message}"><div class="message green">${message}</div></c:if>
<form:form method = "POST" action = "/addBook" commandName = "book">
    <%--<form:errors path = "*" cssClass = "errorblock" element = "div" />--%>
    <table>
        <tr>
            <td><form:label path = "author">Autor</form:label></td>
            <td><form:input path = "author" /></td>
            <td><form:errors path = "author" cssClass = "error" /></td>
        </tr>
        <tr>
            <td><form:label path = "title">Titulo</form:label></td>
            <td><form:input path = "title" /></td>
            <td><form:errors path = "title" cssClass = "error" /></td>
        </tr>
        <tr>
            <td><form:label path = "price">price</form:label></td>
            <td><form:input path = "price" id="price"/></td>
            <td><form:errors path = "price" cssClass = "error" /></td>
        </tr>
        <tr>
            <td><form:label path = "description">description</form:label></td>
            <td><form:input path = "description" /></td>
            <td><form:errors path = "description" cssClass = "error" /></td>
        </tr>
        <tr>
            <td colspan = "2">
                <input type = "submit" value = "Submit"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>