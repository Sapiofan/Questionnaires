<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Survey</title>
</head>
<body>
<h1 style="text-align: center">Hello, here you will be able to create survey!</h1>

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
<<<<<<< HEAD
        <form action="/createSurvey" method="post">
          <input type="hidden" name="surveyId" value=${surveyId}>
          <div class="form-group">
            <label for="SurveyName" class="form-label">Survey name</label>
            <input type="text" class="form-control" id="name" placeholder="Enter a name" name="name" required>
          </div>
          <div class="vertical-center">
            <a href="/main", class="btn btn-danger">Back</a>
            <input type="submit" class="btn btn-success" value="Continue" />
=======
        <form action="survey/creating" method="post">
          <div class="form-group">
            <label for="SurveyName" class="form-label">Survey name</label>
            <input type="text" class="form-control" id="SurveyName" placeholder="Enter a name" name="survey">
          </div>
          <div class="vertical-center">
            <a href="/main", class="btn btn-danger">Back</a>
            <a href="/survey/creating", class="btn btn-success">Continue</a>
>>>>>>> origin/main
          </div>
        </form>

    </div>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>