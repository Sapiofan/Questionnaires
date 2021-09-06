<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Add question</title>
</head>
<body>
    <style>
        .vertical-center {
          margin: 0;
          position: absolute;
          top: 90%;
          -ms-transform: translateY(-50%);
          transform: translateY(-50%);
        }

        table {
            border-collapse: collapse;
        }

        table, td, th {
            border: 1px solid #999;
            padding: 5px;
        }
    </style>


    <div class="container mt-5">
        <form action="/addQuestion" method="post">

            <input type="hidden" name="surveyId" value=${surveyId}>
            <input type="hidden" name="questionId" value=${questionId}>



            <div class="form-group">
                <label for="question" class="form-label">Question</label>
                <input type="text" class="form-control" id="question" placeholder="Enter a question" value="${name}" name="question" required>
            </div>
            <div class="vertical-center">
                <button type="submit" class="btn btn-danger" name="returnToQuestions" onclick="requiredDisabled()">Back to question list</button>
                <button type="submit" class="btn btn-success" name="add" >Add a question</button>
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