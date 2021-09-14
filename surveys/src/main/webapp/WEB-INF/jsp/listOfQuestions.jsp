<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel = "icon" href =
        "https://library.kissclipart.com/20181213/uwe/kissclipart-lab-results-clipart-computer-icons-software-testin-444675676f2f188d.jpg"
                type = "image/x-icon">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>List of questions</title>
</head>
<body>
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

       input[type=number] {
        float:left;
         box-sizing: border-box;
         border: 2px solid #ccc;
         border-radius: 4px;
         font-size: 16px;
         background-color: white;
         background-position: 10px 10px;
         background-repeat: no-repeat;
       }

       #label{
            color: white;
            font-size: 20px;
            font-weight: bold;
       }

   </style>

    <br>
    <form action="/addQuestion" method="get">
       <input type="hidden" name="surveyId" value=${surveyId}>
       <input type="hidden" name="questionId" value=${questionId}>
        <button type="submit" class="btn btn-warning" name="changeSurveyName" >Change survey name</button>
        <button type="submit" class="btn btn-danger" name="deleteSurvey"
        onclick="if (confirm('Are you sure you want to delete the survey?')) form.action='/addQuestion'; else return false;">Delete survey</button>
        <button type="submit" class="btn btn-primary" name="add" >Add a question</button>
        <button type="submit" class="btn btn-success" name="saveSurvey" >Save a survey</button>
    </form>
    <br>

    <form action="/changedQuestionNumber" method="post">
        <input type="hidden" name="surveyId" value=${surveyId}>
        <input type="hidden" name="questionId" value=${questionId}>

        <input type="number" class="form-control" min="1" max="${size}" id="from" placeholder="from" style="width: 100px;" name="from">
        <input type="number" class="form-control" min="1" max="${size}" id="to" placeholder="to" name="to" style="width: 100px;">

         <button type="submit" class="btn btn-warning" name="changeQuestionNumber" onclick="checkSize(${size})">Change question number</button>
    </form>
    <br><br>

    <table border="1" id="table">
        <tr>
            <th>number</th>
            <th>description of question</th>
        </tr>
        <c:forEach  items="${questions}" var ="question">
             <tr>
                 <td><a href="/listOfAnswers/${question.number}?surveyId=${surveyId}">${question.number}</a></td>
                 <td>${question.description}</td>
                 <td><a href="/deleteQuestion/${question.id}?surveyId=${surveyId}"
                 onclick="if (confirm('Are you sure you want to delete the question?')) form.action='/deleteQuestion'; else return false;">Delete</a></td>
             </tr>
        </c:forEach>
    </table>

    <script>
        function checkSize(size){
           var from = document.getElementById('from').value;
           var to = document.getElementById('to').value;
           if(size == 0 && from > size && from <= 0 && to > size && to <= 0){
               alert("You inputted not existed question number or tried to input number that is out of questions range")
           }
       }
    </script>
</body>
</html>