<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>List of questions</title>
</head>
<body onLoad="javascript:checking()">
   <style>
       table {
           border-collapse: collapse;
       }

       table, td, th {
           border: 1px solid #999;
           padding: 5px;
       }
   </style>

    <form action="/addAnswer" method="get">
       <input type="hidden" name="questionId" value=${questionId}>
       <input type="hidden" id="size" name="size" value=${size}>
       <input type="hidden" id="input" name="input" value=${input}>

        <button type="submit" class="btn btn-danger" name="changeQuestionName" >Change question name</button>
        <button type="submit" class="btn btn-primary" id="addAnswer" name="addAnswer">Add an answer</button>
        <button type="submit" class="btn btn-success" id="addQuestion" name="addQuestion">Submit a question</button>

        <h3>Question: ${question}</h3>
        <table border="1">
            <tr>
                <th>number</th>
                <th>answer</th>
                <th>correctness</th>
            </tr>
            <c:forEach  items="${answers}" var ="answer">
                 <tr>
                     <td>${answer.number}</td>
                     <td>${answer.answer}</td>
                     <td>${answer.correctness}</td>
                     <td><a href="/deleteAnswer/${answer.number}?questionId=${questionId}"
                     onclick="if (confirm('Are you sure you want to delete the answer?')) form.action='/deleteAnswer'; else return false;">Delete</a></td>
                     <td><a href="/editAnswer/${answer.number}?questionId=${questionId}">Edit</a></td>
                 </tr>
            </c:forEach>
       </table>
    </form>

    <script type="text/javascript">
       function checking() {
            if(document.getElementById('size').value > 9){
               document.getElementById('addAnswer').disabled = true;
            }
            else if(document.getElementById('size').value > 1 && document.getElementById('input').value == 'true'){
               document.getElementById("addQuestion").disabled = false;
            }
            else{
                document.getElementById("addQuestion").disabled = true;
                document.getElementById('addAnswer').disabled = false;
            }
          }
    </script>
</body>
</html>