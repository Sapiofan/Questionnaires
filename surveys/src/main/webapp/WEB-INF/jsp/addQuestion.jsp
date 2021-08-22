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
<script type="text/javascript">
   function add() {
     var element = document.createElement("input");
     var label = document.createElement("label")
     label.setAttribute("for","answer")
     label.setAttribute("class","form-label")
     element.setAttribute("type", "text");
     element.setAttribute("name", "mytext");
     element.setAttribute("class", "form-control")
     element.setAttribute("placeholder", "Enter an answer")
     var spanvar = document.getElementById("myspan");
     label.textContent="Answer "
     spanvar .appendChild(label)
         spanvar .appendChild(element)

   }
</script>
    <div class="container mt-5">
        <form>
          <div class="form-group">
            <label for="question" class="form-label">Question</label>
            <input type="text" class="form-control" id="question" placeholder="Enter a question" name="question">
            <span id="myspan"></span>
          </div>
          <div class="vertical-center">
            <button type="button" class="btn btn-primary" action="/survey/">Add an answer</button>
            <button type="button" class="btn btn-primary" action="/survey/addQuestion">Next question</button>
            <button type="submit" class="btn btn-success" action="/survey/processSurvey" method="post">Submit</button>
          </div>
        </form>

    </div>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>