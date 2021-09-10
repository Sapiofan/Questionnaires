<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>List of questionnaires</title>
    <link rel = "icon" href =
        "https://library.kissclipart.com/20181213/uwe/kissclipart-lab-results-clipart-computer-icons-software-testin-444675676f2f188d.jpg"
                type = "image/x-icon">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>

    <h2>Questionnaires</h2>
    <style>
        table {
            border-collapse: collapse;
        }

        table, td, th {
            border: 1px solid #999;
            padding: 5px;
        }
    </style>

    <form action="/searchQuestionnaire" method="get">
         <label for="search" class="form-label">Search by survey/user</label>
        <input type="text" class="form-control" id="search" placeholder="Search..." name="search" required>

        <select id="type" name="type">
          <option value="questionnaire">Questionnaire</option>
          <option value="user">User</option>
        </select>

        <a href="/main", class="btn btn-danger">Back to main</a>
        <button type="submit" class="btn btn-primary" name="backToList" onclick="disable()">Back to all list</button>
        <button type="submit" class="btn btn-primary" name="searchRows">Search</button>

    </form>

    <table border="1">
        <tr>
            <th>Questionnaire number</th>
            <th>Questionnaire name</th>
            <th>Creator</th>
            <th>Number of questions</th>
        </tr>
        <c:forEach items="${questionnaires}" var ="questionnaire">
            <tr>
                <td><a href="/questionnaire/${questionnaire.id}">${questionnaire.number}</a></td>
                <td>${questionnaire.name}</td>
                <td>${questionnaire.user.nickname}</td>
                <td>${questionnaire.size}</td>
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