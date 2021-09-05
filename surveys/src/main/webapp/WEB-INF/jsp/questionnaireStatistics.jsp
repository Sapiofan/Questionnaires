<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Results</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
    <input type="hidden" name="questionnaireId" value=${questionnaireId}>
    <input type="hidden" name="question" value=${question}>
    <h2>You have passed the test</h2>

    <h5>Lost time: hours: ${hours}, minutes: ${minutes} seconds: ${seconds}</h5>
    <h5>Description: ${description}</h5>

    <form action="/listOfQuestionnaires" method="post">
        <input type="hidden" name="resultId" value=${resultId}>
        <button type="submit" class="btn btn-primary" name="back" >Back to list of questionnaires</button>
    </form>
</body>
</html>