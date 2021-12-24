<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/customtaglib.tld" prefix="ctl"%>
<html>
<head>
    <title>Title</title>
    <style>
        .xml-check-warning {
            color: red;
        }
        table, th, td {
            border: outset;
            border-collapse: collapse;
        }
        th {
            background-color: darkgray;
        }
    </style>
</head>
<body>
<c:if test="${not_checked_xml==true}">
    <div class="xml-check-warning">
        <p>XML файл не был проверен с помощью XSD схемы!</p>
    </div>
</c:if>
<h2>Result list </h2>
<c:out value="(${parser} парсер)"/>
<br/>
<ctl:list-viewer list="${result_list}"/>
<br/>
</body>
</html>
