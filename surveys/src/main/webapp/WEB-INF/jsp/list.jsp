<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>List of surveys</title>
<<<<<<< HEAD
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

=======
>>>>>>> origin/main
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
<<<<<<< HEAD

<a href="/main", class="btn btn-danger">Back to main</a>

=======
>>>>>>> origin/main
<table border="1">
    <tr>
        <th>Survey number</th>
        <th>Survey name</th>
        <th>Creator</th>
        <th>Number of questions</th>
    </tr>
    <c:forEach  items="${surveys}" var ="survey">
        <tr>
<<<<<<< HEAD
            <td><a href="/survey/${survey.id}">${survey.id}</a></td>
            <td>${survey.name}</td>
            <td>${survey.user.nickname}</td>
            <td>${survey.size}</td>
=======
            <td>${survey.id}</td>
            <td>${survey.name}</td>
            <td>${survey.size}</td>
            <td>${survey.user.nickname}</td>
>>>>>>> origin/main
        </tr>
    </c:forEach>
</table>

<<<<<<< HEAD
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>


=======
>>>>>>> origin/main
</body>
</html>