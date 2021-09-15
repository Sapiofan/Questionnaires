<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel = "icon" href =
        "https://library.kissclipart.com/20181213/uwe/kissclipart-lab-results-clipart-computer-icons-software-testin-444675676f2f188d.jpg"
                type = "image/x-icon">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Add question</title>
</head>
<body>
    <style>
        body {
              background-image: url("https://i.pinimg.com/originals/4f/2f/7d/4f2f7d2aa8582dcca07ca3eb600e922b.png");
              background-repeat: no-repeat;
              background-attachment: fixed;
              background-size: cover;
        }

        label{
            color: white;
            font-size: 24px;
            font-weight: bold;
        }
        form { display: inline; }
    </style>


    <div class="container mt-5">
        <form action="/listOfAnswers" method="post">

            <input type="hidden" name="surveyId" value=${surveyId}>
            <input type="hidden" name="questionId" value=${questionId}>

            <div class="form-group">
                <label for="question" class="form-label" id="label">Question</label>
                <input type="text" class="form-control" id="question" placeholder="Enter a question" value="${name}" name="question" required>
            </div>

            <button type="submit" class="btn btn-success" name="add" style="margin-left: 10px;">Add a question</button>
        </form>

        <form action="/listOfQuestions" method="post">
            <input type="hidden" name="surveyId" value=${surveyId}>
            <input type="hidden" name="questionId" value=${questionId}>
            <div style="float: left;">
                <button type="submit" class="btn btn-danger" name="returnToQuestions" onclick="requiredDisabled()">Back to question list</button>
            </div>
        </form>
    </div>

    <script type="text/javascript">
       function requiredDisabled(){
            document.getElementById("question").required = false;
       }
    </script>

</body>
</html>