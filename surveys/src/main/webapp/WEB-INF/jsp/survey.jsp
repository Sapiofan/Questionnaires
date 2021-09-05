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
            <input type="hidden" name="surveyId" value=${surveyId}>
            <div class="form-group">
                <label for="SurveyName" class="form-label">Survey name</label>
                <input type="text" class="form-control" id="name" placeholder="Enter a name" name="name" required>
            </div>
            <div class="vertical-center">
                <input type="submit" class="btn btn-danger" value="Back" name="backToMain" onclick="requiredDisabled()" />
                <input type="submit" class="btn btn-success" value="Continue" />
            </div>
        </form>
    </div>

    <script>
        function requiredDisabled(){
                document.getElementById("name").required = false;
        }
    </script>
  </body>
</html>