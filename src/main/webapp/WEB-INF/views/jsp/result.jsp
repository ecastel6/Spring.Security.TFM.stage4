<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>

<h2>Submitted Book Information</h2>
<c:if test="${not empty message}"><div class="message green">${message}</div></c:if>
<table>
    <tr>
        <td>Author</td>
        <td>${author}</td>
    </tr>
    <tr>
        <td>Title</td>
        <td>${title}</td>
    </tr>
    <tr>
        <td>ID</td>
        <td>${id}</td>
    </tr>
</table>
</body>
</html>