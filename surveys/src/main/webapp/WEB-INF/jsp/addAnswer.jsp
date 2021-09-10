<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel = "icon" href =
        "https://library.kissclipart.com/20181213/uwe/kissclipart-lab-results-clipart-computer-icons-software-testin-444675676f2f188d.jpg"
                type = "image/x-icon">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Add answer</title>
</head>
<body onLoad="javascript:checked(${value})>

    <style>
        .vertical-center {
          margin: 0;
          position: absolute;
          top: 90%;
          -ms-transform: translateY(-50%);
          transform: translateY(-50%);
        }
    </style>
    <div class="container mt-5">
        <form action="/listOfAnswers" method="post">
            <div class="form-group">

                <input type="hidden" name="questionId" value=${questionId}>

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

            </div>
            <div class="vertical-center">
                <button type="submit" class="btn btn-danger" name="answers" onclick="notRequired()">Back to answers list</button>
                <button type="submit" class="btn btn-success" name="saveAnswer">Submit</button>
            </div>
        </form>
    </div>

    <script type="text/javascript">
       function notRequired() {
            document.getElementById('answer').required=false;
            document.getElementById('inlineRadio1').required=false;
            document.getElementById('inlineRadio2').required=false;
        }
    </script>
</body>
</html>