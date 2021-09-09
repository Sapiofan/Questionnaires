<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>List of surveys</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>

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

    <form action="/search" method="get">
         <label for="search" class="form-label">Search by survey/user</label>
        <input type="text" class="form-control" id="search" placeholder="Search..." name="search" required>

        <select id="type" name="type">
          <option value="survey">Survey</option>
          <option value="user">User</option>
        </select>

        <a href="/main", class="btn btn-danger">Back to main</a>
        <button type="submit" class="btn btn-primary" name="backToList" onclick="disable()">Back to all list</button>
        <button type="submit" class="btn btn-primary" name="searchRows">Search</button>

    </form>

    <table border="1">
        <tr>
            <th>Survey number</th>
            <th>Survey name</th>
            <th>Creator</th>
            <th>Number of questions</th>
        </tr>
        <c:forEach  items="${surveys}" var ="survey">
            <tr>
                <td><a href="/survey/${survey.id}">${survey.number}</a></td>
                <td>${survey.name}</td>
                <td>${survey.user.nickname}</td>
                <td>${survey.size}</td>
            </tr>
        </c:forEach>
    </table>

    <script>
        function disable(){
            document.getElementById('search').required=false;
        }
    </script>

</body>
</html>