<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>List of surveys</title>
    <link rel = "icon" href =
        "https://library.kissclipart.com/20181213/uwe/kissclipart-lab-results-clipart-computer-icons-software-testin-444675676f2f188d.jpg"
                type = "image/x-icon">
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
        h2 {
            text-align: center;
            font-size: 26px;
        }


        input[type=text] {
          width: 130px;
          box-sizing: border-box;
          border: 2px solid #ccc;
          border-radius: 4px;
          font-size: 16px;
          background-color: white;
          background-position: 10px 10px;
          background-repeat: no-repeat;
          padding: 12px 20px 12px 40px;
          transition: width 0.4s ease-in-out;
        }

        input[type=text]:focus {
          width: 50%;
        }
    </style>

    <form action="/search" method="get">
         <label for="search" class="form-label">Search by survey/user</label>
        <input type="search" class="input-box" id="search" placeholder="Search..." name="search" required>
        <select id="type" name="type">
          <option value="survey">Survey</option>
          <option value="user">User</option>
        </select>
        <br>

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