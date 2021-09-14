<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Statistics</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel = "icon" href =
        "https://library.kissclipart.com/20181213/uwe/kissclipart-lab-results-clipart-computer-icons-software-testin-444675676f2f188d.jpg"
                type = "image/x-icon">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
    <style>
        .container{
            text-align: center;
        }
        body {
            background-image: url("https://i.pinimg.com/originals/4f/2f/7d/4f2f7d2aa8582dcca07ca3eb600e922b.png");
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: cover;
      }
      #label{
                 color: white;
                 font-size: 48px;
                 font-weight: bold;
            }
      h3 {
           color: white;
           font-size: 32px;
           font-weight: bold;
      }

    </style>
    <input type="hidden" name="surveyId" value=${surveyId}>
    <input type="hidden" name="questions" value=${questions}>
    <input type="hidden" name="question" value=${question}>
    <input type="hidden" name="answers" value=${answers}>
    <div class="container">
        <h2 id="label">You have passed the survey</h2>
        <h3>Your results:</h3>
        <h3>Lost time: hours: ${hours}, minutes: ${minutes} seconds: ${seconds}</h3>
        <h3>Right answers/all answers: ${counter}/${allAnswers}</h3>
        <a href="/list", class="btn btn-danger">Back to list of surveys</a>
    </div>
</body>
</html>