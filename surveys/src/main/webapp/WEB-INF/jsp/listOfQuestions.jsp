<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>List of questions</title>
</head>
<body>
   <style>
       table {
           border-collapse: collapse;
       }

       table, td, th {
           border: 1px solid #999;
           padding: 5px;
       }
   </style>

    <form action="/addQuestion" method="get">
       <input type="hidden" name="surveyId" value=${surveyId}>
       <input type="hidden" name="questionId" value=${questionId}>
        <button type="submit" class="btn btn-danger" name="changeSurveyName" >Change survey name</button>
        <button type="submit" class="btn btn-primary" name="add" >Add a question</button>
        <button type="submit" class="btn btn-success" name="saveSurvey" >Save a survey</button>

    </form>
   <table border="1">
       <tr>
           <th>number</th>
           <th>description of question</th>
       </tr>
       <c:forEach  items="${questions}" var ="question">
           <tr>
               <td><a href="/listOfAnswers/${question.number}?surveyId=${surveyId}">${question.number}</a></td>
               <td>${question.description}</td>
           </tr>
       </c:forEach>
   </table>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>