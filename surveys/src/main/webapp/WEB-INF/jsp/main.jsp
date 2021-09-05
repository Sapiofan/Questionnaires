<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
    <h3>Hello, here you can create simple surveys and questionnaires.</h3>
    <form action="/logout" method="get">
        <input type="submit" class="btn btn-danger" value="Sign out" />
        <a href="/survey", class="btn btn-primary">Create survey</a>
        <a href="/createQuestionnaire", class="btn btn-primary">Create questionnaire</a>
        <a href="/list", class="btn btn-primary">List of surveys</a>
        <a href="/listOfQuestionnaires", class="btn btn-primary">List of questionnaires</a>
    </form>
</body>
</html>