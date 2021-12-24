<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h1>Внутренняя ошибка сервера: ${pageContext.errorData.statusCode}</h1>
<p>Сообщение исключения: <br/>
${pageContext.exception} </p>
</body>
</html>
