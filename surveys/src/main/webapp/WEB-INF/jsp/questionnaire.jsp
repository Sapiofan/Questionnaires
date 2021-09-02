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
        <form action="/createSurvey" method="post">
          <input type="hidden" name="questionnaireId" value=${questionnaireId}>
          <div class="form-group">
            <label for="Questionnaire" class="form-label">Questionnaire name</label>
            <input type="text" class="form-control" id="name" placeholder="Enter a name" name="name" required>
          </div>
          <div class="form-group">
              <label for="description" class="form-label">Description</label>
              <input type="text" class="form-control" id="description" placeholder="Enter a name" name="description" required>
            </div>
            <p>Choose the scale</p>
            <div class="form-check form-check-inline">
              <input class="form-check-input" type="radio" name="scale" id="inlineRadio1" value="1" required>
              <label class="form-check-label" for="inlineRadio1">5-point scale</label>
            </div>
            <div class="form-check form-check-inline">
              <input class="form-check-input" type="radio" name="scale" id="inlineRadio2" value="2" required>
              <label class="form-check-label" for="inlineRadio2">10-point scale</label>
            </div>
          <div class="vertical-center">
            <input type="submit" class="btn btn-danger" value="Back" name="backToMain" onclick="requiredDisabled()" />
            <input type="submit" class="btn btn-success" value="Continue" />
          </div>
        </form>
    <script>
    function requiredDisabled(){
            document.getElementById("name").required = false;
            document.getElementById("description").required = false;
            document.getElementById("inlineRadio1").required = false;
            document.getElementById("inlineRadio2").required = false;
    }
    </script>

    </div>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>