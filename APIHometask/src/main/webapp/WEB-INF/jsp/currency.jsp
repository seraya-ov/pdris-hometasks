<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home Page</title>
</head>
<body>
<h3>Currency</h3>
<table>
    <tr style="outline: thin solid">
        <td style="width: 96px; outline: thin solid">Date</td>
        <td style="width: 96px; outline: thin solid">AUD</td>
        <td style="width: 96px; outline: thin solid">GBR</td>
        <td style="width: 96px; outline: thin solid">USD</td>
        <td style="width: 96px; outline: thin solid">EUR</td>
        <td style="width: 96px; outline: thin solid">CAD</td>
        <td style="width: 96px; outline: thin solid">SGD</td>
        <td style="width: 96px; outline: thin solid">CHF</td>
    </tr>
    <c:forEach items="${currency}" var="c">
        <tr style="outline: thin solid">
            <td style="width: 96px; outline: thin solid">${c.getDate()}
            </td>
            <td style="width: 96px; outline: thin solid">${c.getAud()}
            </td>
            <td style="width: 96px; outline: thin solid">${c.getGbr()}
            </td>
            <td style="width: 96px; outline: thin solid">${c.getUsd()}
            </td>
            <td style="width: 96px; outline: thin solid">${c.getEur()}
            </td>
            <td style="width: 96px; outline: thin solid">${c.getCad()}
            </td>
            <td style="width: 96px; outline: thin solid">${c.getSgd()}
            </td>
            <td style="width: 96px; outline: thin solid">${c.getChf()}
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>