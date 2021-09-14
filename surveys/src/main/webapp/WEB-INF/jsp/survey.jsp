<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel = "icon" href =
        "https://library.kissclipart.com/20181213/uwe/kissclipart-lab-results-clipart-computer-icons-software-testin-444675676f2f188d.jpg"
                type = "image/x-icon">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Survey</title>
</head>
<body>
    <style>
        body {
              background-image: url("https://i.pinimg.com/originals/4f/2f/7d/4f2f7d2aa8582dcca07ca3eb600e922b.png");
              background-repeat: no-repeat;
              background-attachment: fixed;
              background-size: cover;
        }
        #description {
          width: 100%;
          height: 150px;
          padding: 12px 20px;
          box-sizing: border-box;
          border: 2px solid #ccc;
          border-radius: 4px;
          background-color: #f8f8f8;
          font-size: 16px;
          resize: none;
        }

        label{
            color: white;
            font-size: 24px;
            font-weight: bold;
        }
    </style>

    <div class="container mt-5">
        <form action="/createSurvey" method="post">
            <input type="hidden" name="surveyId" value=${surveyId}>
            <div class="form-group">
                <label for="SurveyName" class="form-label" id="label">Survey name</label>
                <input type="text" class="form-control" id="name" placeholder="Enter a name" name="name" value="${name}" required>
            </div>
            <div class="form-group">
                <label for="description" class="form-label" id="label">Description</label>
                <textarea class="form-control" id="description" placeholder="Enter a description" name="description" required>${description}</textarea>
            </div>
            <input type="submit" class="btn btn-danger" value="Back" name="backToMain" onclick="requiredDisabled()" />
            <input type="submit" class="btn btn-success" value="Continue" />
        </form>
    </div>

    <script>
        function requiredDisabled(){
                document.getElementById("name").required = false;
                document.getElementById("description").required = false;
        }
    </script>
  </body>
</html>