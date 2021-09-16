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
    <title>List of answers</title>
</head>
<body onLoad="javascript:checking()">
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

      #label{
           color: white;
           font-size: 20px;
           font-weight: bold;
      }

      h3{
            color: white;
           font-size: 28px;
           font-weight: bold;
      }

      input[type=text] {
            width: 30%;
           box-sizing: border-box;
           border: 2px solid #ccc;
           border-radius: 4px;
           font-size: 16px;
           background-color: white;
           background-position: 10px 10px;
           background-repeat: no-repeat;
         }

    .custom-radio {
        position: absolute;
        z-index: -1;
        opacity: 0;
      }

      /* для элемента label связанного с .custom-radio */
      .custom-radio+label {
        display: inline-flex;
        align-items: center;
        user-select: none;
        color: white;
        font-weight: bold;
        font-size: 18px;
      }

      /* создание в label псевдоэлемента  before со следующими стилями */
      .custom-radio+label::before {
        content: '';
        display: inline-block;
        width: 1em;
        height: 1em;
        flex-shrink: 0;
        flex-grow: 0;
        border: 1px solid #adb5bd;
        border-radius: 50%;
        margin-right: 0.5em;
        background-repeat: no-repeat;
        background-position: center center;
        background-size: 50% 50%;
      }

      /* стили при наведении курсора на радио */
      .custom-radio:not(:disabled):not(:checked)+label:hover::before {
        border-color: #b3d7ff;
      }

      /* стили для активной радиокнопки (при нажатии на неё) */
      .custom-radio:not(:disabled):active+label::before {
        background-color: #800517;
        border-color: #800517;
      }

      /* стили для радиокнопки, находящейся в фокусе */
      .custom-radio:focus+label::before {
        box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
      }

      /* стили для радиокнопки, находящейся в фокусе и не находящейся в состоянии checked */
      .custom-radio:focus:not(:checked)+label::before {
        border-color: #80bdff;
      }

      /* стили для радиокнопки, находящейся в состоянии checked */
      .custom-radio:checked+label::before {
        border-color: #800517;
        background-color: #800517;
        background-image: url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' viewBox='-4 -4 8 8'%3e%3ccircle r='3' fill='%23fff'/%3e%3c/svg%3e");
      }

      /* стили для радиокнопки, находящейся в состоянии disabled */
      .custom-radio:disabled+label::before {
        background-color: #e9ecef;
      }

    .radio {
      margin-bottom: 0.4em;
    }
    form { display: inline; }
   </style>

   <form action="/addQuestion" method="post">
       <input type="hidden" name="questionId" value=${questionId}>
       <button type="submit" class="btn btn-danger" name="changeQuestionName" onclick="notRequired()">Change question name</button>
   </form>

   <form action="/listOfQuestions" method="post">
       <input type="hidden" name="questionId" value=${questionId}>
       <button type="submit" class="btn btn-success" id="addQuestion" style="margin-left: 10px;" name="addQuestion" onclick="notRequired()">Submit a question</button>
   </form>

    <form action="/listOfAnswers" method="post">
       <input type="hidden" name="questionId" value=${questionId}>
       <input type="hidden" id="size" name="size" value=${size}>
       <input type="hidden" id="input" name="input" value=${input}>

        <button type="submit" class="btn btn-primary" name="saveAnswer"style="margin-left: 10px;">Add an answer</button>

        <br>
        <label for="answer" class="form-label" id="label">Answer</label>
        <input type="text" class="form-control" id="answer" placeholder="Enter an answer" name="answer" required>

        <div class="form-check form-check-inline">
            <input class="custom-radio" type="radio" name="correctAnswer" id="inlineRadio1" value="1" required">
            <label class="form-check-label" for="inlineRadio1">Correct</label>
          </div>

          <div class="form-check form-check-inline">
            <input class="custom-radio" type="radio" name="correctAnswer" id="inlineRadio2" value="2" required>
            <label class="form-check-label" for="inlineRadio2">Incorrect</label>
          </div>
    </form>

        <br><br><br>
        <h3>Question: ${question}<br>Answers:</h3>
        <table border="1" id="table">
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
                     <td><a href="/deleteAnswer/${answer.id}?questionId=${questionId}"
                     onclick="if (confirm('Are you sure you want to delete the answer?')) form.action='/deleteAnswer'; else return false;">Delete</a></td>
                     <td><a href="/editAnswer/${answer.number}?questionId=${questionId}">Edit</a></td>
                 </tr>
            </c:forEach>
       </table>

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