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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>

    <h2>Questionnaires</h2>
    <style>
        body {
                background-image: url("https://i.pinimg.com/originals/4f/2f/7d/4f2f7d2aa8582dcca07ca3eb600e922b.png");
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: cover;
          }
          #table {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 50%;
          }

          #table td, #table th {
            border: 1px solid #ddd;
            padding: 8px;
          }

          #table tr:nth-child(even){background-color: #f2f2f2;}

          #table tr:nth-child(odd){background-color: #C0C0C0;}

          #table tr:hover {background-color: #ddd;}

          #table th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #157DEC;
            color: white;
          }




        form.search input[type=text] {
          padding: 10px;
          font-size: 17px;
          border: 1px solid grey;
          float: left;
          width: 40%;
          background: #f1f1f1;
        }

        #button {
          float: left;
          width: 10%;
          padding: 14px;
          background: #2196F3;
          color: white;
          font-size: 17px;
          border: 1px solid grey;
          border-left: none;
          cursor: pointer;
        }

        form.search button:hover {
          background: #0b7dda;
        }

        form.search::after {
          content: "";
          clear: both;
          display: table;
        }
        #label{
             color: white;
             font-size: 24px;
             font-weight: bold;
        }
        h2{
            color: white;
            text-align: center;
        }
    </style>

    <form class="search" action="/searchQuestionnaire" method="post">
         <label for="search" class="form-label" id="label">Search by survey/user</label>
         <br>
        <input type="text" class="form-control" id="search" placeholder="Search..." name="search" required>
        <button type="submit" id="button" class="fa fa-search" name="searchRows">Search</button>
        <select id="type" name="type">
          <option value="questionnaire">Questionnaire</option>
          <option value="user">User</option>
        </select>
        <br><br><br>

        <a href="/main", class="btn btn-danger">Back to main</a>
        <button type="submit" class="btn btn-primary" name="backToList" onclick="disable()">Back to all list</button>
    </form>

    <table border="1" id="table">
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