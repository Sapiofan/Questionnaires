<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>List of surveys</title>
</head>
<body>
<h1 style="text-align: center">Hello, here will be list of surveys!</h1>
<h2>Surveys</h2>
<style>
table {
    border-collapse: collapse;
}

table, td, th {
    border: 1px solid #999;
    padding: 5px;
}
</style>
<table border="1">
    <tr>
        <th>Survey number</th>
        <th>Survey name</th>
        <th>Creator</th>
        <th>Number of questions</th>
    </tr>
    <c:forEach  items="${surveys}" var ="survey">
        <tr>
            <td>${survey.id}</td>
            <td>${survey.name}</td>
            <td>${survey.size}</td>
            <td>${survey.user.nickname}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>