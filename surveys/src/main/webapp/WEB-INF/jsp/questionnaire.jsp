<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Questionnaire</title>
</head>
<body onLoad="javascript:checked(${value})">
<h1 style="text-align: center">Hello, here you will be able to create questionnaire!</h1>

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
        <form action="/createQuestionnaire" method="post">

            <input type="hidden" name="questionnaireId" value=${questionnaireId}>

            <div class="form-group">
                <label for="Questionnaire" class="form-label">Questionnaire name</label>
                <input type="text" class="form-control" id="name" placeholder="Enter a name" name="name" value="${name}" required>
            </div>

            <div class="form-group">
                <label for="description" class="form-label">Description</label>
                <input type="text" class="form-control" id="description" placeholder="Enter a name" value="${description}" name="description" required>
            </div>

            <p>Choose the scale</p>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="scale" id="inlineRadio1" value="5" required>
                <label class="form-check-label" for="inlineRadio1">5-point scale</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="scale" id="inlineRadio2" value="10" required>
                <label class="form-check-label" for="inlineRadio2">10-point scale</label>
            </div>

            <div class="vertical-center">
                <input type="submit" class="btn btn-danger" value="Back" name="backToMain" onclick="requiredDisabled()" />
                <input type="submit" class="btn btn-success" value="Continue" name"continue" />
            </div>
        </form>
    </div>
    <script>
        function requiredDisabled(){
                document.getElementById("name").required = false;
                document.getElementById("description").required = false;
                document.getElementById("inlineRadio1").required = false;
                document.getElementById("inlineRadio2").required = false;
        }

        function checked(value){
            if(value == 10){
                document.getElementById("inlineRadio2").checked = true;
                document.getElementById("inlineRadio1").disabled = true;
            }
            else{
                document.getElementById("inlineRadio1").checked = true;
            }
        }
    </script>
</body>
</html>