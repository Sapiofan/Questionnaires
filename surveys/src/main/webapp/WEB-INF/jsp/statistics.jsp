<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Statistics</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
    <input type="hidden" name="surveyId" value=${surveyId}>
    <input type="hidden" name="questions" value=${questions}>
    <input type="hidden" name="question" value=${question}>
    <input type="hidden" name="answers" value=${answers}>
    <h2>You have passed the test</h2>
    <h3>Your results:</h3>
    <h5>Lost time: hours: ${hours}, minutes: ${minutes} seconds: ${seconds}</h5>
    <h5>Right answers/all answers: ${counter}/${allAnswers}</h5>

    <a href="/list", class="btn btn-danger">Back to list of surveys</a>
</body>
</html>