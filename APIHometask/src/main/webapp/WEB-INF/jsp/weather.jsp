<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home Page</title>
</head>
<body>
<h3>Weather ${action}: ${city}</h3>
<table>
    <tr style="outline: thin solid">
        <td style="width: 96px; outline: thin solid">Time</td>
        <td style="width: 96px; outline: thin solid">Feels Like, C</td>
        <td style="width: 96px; outline: thin solid">Temperature, C</td>
        <td style="width: 96px; outline: thin solid">Pressure, MB</td>
        <td style="width: 96px; outline: thin solid">Humidity, %</td>
        <td style="width: 96px; outline: thin solid">Wind Speed, kph</td>
        <td style="width: 96px; outline: thin solid">Wind Direction</td>
    </tr>
    <c:forEach items="${weather}" var="w">
        <tr style="outline: thin solid">
            <td style="width: 96px; outline: thin solid">${w.getTime()}
            </td>
            <td style="width: 96px; outline: thin solid">${w.getFeelsLike()}
            </td>
            <td style="width: 96px; outline: thin solid">${w.getTemp()}
            </td>
            <td style="width: 96px; outline: thin solid">${w.getPressure()}
            </td>
            <td style="width: 96px; outline: thin solid">${w.getHumidity()}
            </td>
            <td style="width: 96px; outline: thin solid">${w.getWindKph()}
            </td>
            <td style="width: 96px; outline: thin solid">${w.getWindDir()}
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>