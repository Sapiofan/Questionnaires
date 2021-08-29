<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Add question</title>
</head>
<body>

<style>
.vertical-center {
  margin: 0;
  position: absolute;
  top: 90%;
  -ms-transform: translateY(-50%);
  transform: translateY(-50%);
}

table {
    border-collapse: collapse;
}

table, td, th {
    border: 1px solid #999;
    padding: 5px;
}
</style>

<script type="text/javascript">
   var clicks = 0;
   function add() {
     onClick();
     if (typeof(Storage) !== "undefined") {
         if (localStorage.clickcount) {
           localStorage.clickcount = document.getElementById("question");
         } else {
           localStorage.clickcount = document.getElementById("question");
         }
         document.getElementById("result").innerHTML = "You have clicked the button " + localStorage.clickcount + " time(s).";
     }
   }
   function onClick() {
     clicks += 1;
     if(clicks > 9){
        document.getElementById("clicks").disabled = true;
     }
     if(clicks >= 2){
        document.getElementById("save").disabled = false;
     }

   };

   function requiredDisabled(){
        document.getElementById("question").required = false;
   }

   function addForm(){var element = document.createElement("input");
                           var label = document.createElement("label");
                           label.setAttribute("for","answer");
                           label.setAttribute("class","form-label");
                           element.setAttribute("type", "text");
                           element.setAttribute("name", "answer");
                           element.setAttribute("class", "form-control");
                           element.setAttribute("placeholder", "Enter an answer");
                           element.setAttribute("required", "true")
                           var spanvar = document.getElementById("myspan");
                           label.textContent="Answer " + clicks;
                           spanvar .appendChild(label);
                               spanvar .appendChild(element);};
</script>


    <div class="container mt-5">
        <form action="/addQuestion" method="post">

        <input type="hidden" name="surveyId" value=${surveyId}>
           <input type="hidden" name="questionId" value=${questionId}>



          <div class="form-group">
            <label for="question" class="form-label">Question</label>
            <input type="text" class="form-control" id="question" placeholder="Enter a question" name="question" required>
            <span id="myspan"></span>

          </div>
          <div class="vertical-center">
            <button type="submit" class="btn btn-danger" name="returnToQuestions" onclick="requiredDisabled()">Back</button>
            <button type="submit" class="btn btn-success" name="add" >Add a question</button>
          </div>
        </form>

    </div>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>