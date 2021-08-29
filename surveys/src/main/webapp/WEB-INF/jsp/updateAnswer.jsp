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
</style>
    <div class="container mt-5">
        <form action="/updateAnswer" method="post">
          <div class="form-group">

             <input type="hidden" name="questionId" value=${questionId}>
             <input type="hidden" name="answerId" value=${answerId}>

            <label for="number" class="form-label">Number of answer</label>
            <input type="text" class="form-control" id="number" placeholder="Enter a number" name="number" value="${answer.number}" required>


            <label for="answer" class="form-label">Answer</label>
            <input type="text" class="form-control" id="answer" placeholder="Enter a answer" name="answer" value="${answer.answer}" required>

            <div class="form-check form-check-inline">
              <input class="form-check-input" type="radio" name="correctAnswer" id="true" value="Correct" required>
              <label class="form-check-label" for="inlineRadio1">Correct</label>
            </div>
            <div class="form-check form-check-inline">
              <input class="form-check-input" type="radio" name="correctAnswer" id="false" value="Incorrect" checked required>
              <label class="form-check-label" for="inlineRadio2">Incorrect</label>
            </div>

          </div>
          <div class="vertical-center">
            <a href="/listOfAnswers", class="btn btn-danger">Back</a>
            <button type="submit" class="btn btn-success" name="saveAnswer">Submit</button>
          </div>
        </form>

    </div>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>