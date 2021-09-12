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
              background-image: url("https://www.pngkey.com/png/full/45-455323_picture-black-and-white-stock-blue-geometric-transprent.png");
              background-repeat: no-repeat;
              background-attachment: fixed;
              background-size: cover;
        }

        label{
            color: black;
            font-size: 24px;
            font-weight: bold;
        }
    </style>


    <div class="container mt-5">
        <form action="/addQuestion" method="post">

            <input type="hidden" name="surveyId" value=${surveyId}>
            <input type="hidden" name="questionId" value=${questionId}>



            <div class="form-group">
                <label for="question" class="form-label" id="label">Question</label>
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