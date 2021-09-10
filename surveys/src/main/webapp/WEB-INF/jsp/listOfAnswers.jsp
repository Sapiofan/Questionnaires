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
<body onLoad="javascript:checking()">
   <style>
       table {
           border-collapse: collapse;
       }

       table, td, th {
           border: 1px solid #999;
           padding: 5px;
       }
       .vertical-center {
         margin: 0;
         position: absolute;
         top: 90%;
         -ms-transform: translateY(-50%);
         transform: translateY(-50%);
       }
   </style>

    <form action="/addAnswer" method="get">
       <input type="hidden" name="questionId" value=${questionId}>
       <input type="hidden" id="size" name="size" value=${size}>
       <input type="hidden" id="input" name="input" value=${input}>

        <button type="submit" class="btn btn-danger" name="changeQuestionName" onclick="notRequired()">Change question name</button>
        <button type="submit" class="btn btn-primary" name="saveAnswer">Add answer</button>
        <button type="submit" class="btn btn-success" id="addQuestion" name="addQuestion" onclick="notRequired()">Submit a question</button>

        <label for="answer" class="form-label">Answer</label>
        <input type="text" class="form-control" id="answer" placeholder="Enter an answer" name="answer" required>

        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="correctAnswer" id="inlineRadio1" value="1" required>
            <label class="form-check-label" for="inlineRadio1">Correct</label>
        </div>

        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="correctAnswer" id="inlineRadio2" value="2" required>
            <label class="form-check-label" for="inlineRadio2">Incorrect</label>
        </div>

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
       function notRequired() {
           document.getElementById('answer').required=false;
           document.getElementById('inlineRadio1').required=false;
           document.getElementById('inlineRadio2').required=false;
       }
    </script>
</body>
</html>