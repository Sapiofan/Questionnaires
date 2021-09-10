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
       table {
           border-collapse: collapse;
       }

       table, td, th {
           border: 1px solid #999;
           padding: 5px;
       }
   </style>

    <form action="/addQQuestion" method="get">
       <input type="hidden" name="questionnaireId" value=${questionnaireId}>


       <div class="form-group">
               <label for="Question" class="form-label">Question</label>
               <input type="text" class="form-control" id="question" placeholder="Enter a question" name="question" required>
       </div>

        <button type="submit" class="btn btn-warning" name="changeQuestionnaireFields" onclick="notRequired()">Change questionnaire fields</button>
        <button type="submit" class="btn btn-danger" name="deleteQuestionnaire"onclick="if (confirm('Are you sure you want to delete the questionnaire?')) form.action='/addQQuestion'; else {return false;}; notRequired();">Delete questionnaire</button>
        <button type="submit" class="btn btn-primary" name="add" >Add a question</button>
        <button type="submit" class="btn btn-success" name="addDescriptions" onclick="notRequired()">Add descriptions</button>
    </form>
    <table border="1">
       <tr>
           <th>number</th>
           <th>description of question</th>
       </tr>
       <c:forEach  items="${questions}" var ="question">
           <tr>
               <td>${question.number}</td>
               <td>${question.name}</td>
               <td><a href="/deleteQQuestion/${question.number}?questionnaireId=${questionnaireId}"
               onclick="if (confirm('Are you sure you want to delete the question?')) form.action='/deleteQQuestion'; else return false;">Delete</a></td>
           </tr>
       </c:forEach>
   </table>

   <form action="/changedNumber" method="post">
       <input type="hidden" name="questionnaireId" value=${questionnaireId}>

       <label for="from" class="form-label">Current question number</label>
       <input type="number" class="form-control" id="from" placeholder="from" name="from">

       <label for="to" class="form-label">New question number</label>
       <input type="number" class="form-control" id="to" placeholder="to" name="to">

        <button type="submit" class="btn btn-primary" name="changeQuestionNumber" onclick="checkSize(${size})">Change question number</button>
   </form>
   <script>
       function checkSize(size){
           var from = document.getElementById('from').value;
           var to = document.getElementById('to').value;
           if(size == 0 && from > size && from <= 0 && to > size && to <= 0){
               alert("You inputted not existed question number or tried to input number that is out of questions range")
           }
       }
       function notRequired() {
          document.getElementById('question').required=false;
      }
   </script>
</body>
</html>