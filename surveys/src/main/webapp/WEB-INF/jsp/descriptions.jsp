<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>List of descriptions</title>
</head>
<body>
   <style>
       table {
           border-collapse: collapse;
       }

       table, td, th {
           border: 1px solid #999;
           padding: 5px;
       }
       .slidecontainer {
         width: 100%;
       }

       .slider {
         -webkit-appearance: none;
         width: 100%;
         height: 15px;
         border-radius: 5px;
         background: #d3d3d3;
         outline: none;
         opacity: 0.7;
         -webkit-transition: .2s;
         transition: opacity .2s;
       }

       .slider:hover {
         opacity: 1;
       }

       .slider::-webkit-slider-thumb {
         -webkit-appearance: none;
         appearance: none;
         width: 25px;
         height: 25px;
         border-radius: 50%;
         background: #04AA6D;
         cursor: pointer;
       }

       .slider::-moz-range-thumb {
         width: 25px;
         height: 25px;
         border-radius: 50%;
         background: #04AA6D;
         cursor: pointer;
       }
   </style>

    <form action="/addDescription" method="get">
       <input type="hidden" name="questionnaireId" value=${questionnaireId}>
       <input type="hidden" name="minimum" value=${minimum}>
        <input type="hidden" name="maximum" value=${maximum}>

       <div class="form-group">
               <label for="Description" class="form-label">Description</label>
               <input type="text" class="form-control" id="description" placeholder="Enter a description" name="description" required>
       </div>
       <h1>Enter a number of points</h1>

       <div class="slidecontainer">
         <input type="range" min="${minimum}" max="${maximum}" value="${minimum}" class="slider" name="range" id="myRange">
         <p>Value: <span id="demo"></span></p>
       </div>


        <button type="submit" class="btn btn-warning" name="addQuestions" >Add questions</button>
        <button type="submit" class="btn btn-danger" name="deleteQuestionnaire">Delete questionnaire</button>
        <button type="submit" class="btn btn-primary" name="add" >Add a description</button>
        <button type="submit" class="btn btn-success" name="saveQuestionnaire" >Save a questionnaire</button>
    </form>
   <table border="1">
       <tr>
           <th>number</th>
           <th>description</th>
       </tr>
       <c:forEach  items="${descriptions}" var ="description">
           <tr>
               <td>${question.number}</td>
               <td>${question.name}</td>
               <td><a href="/deleteDescription/${description.number}?questionnaireId=${questionnaireId}">Delete</a></td>
           </tr>
       </c:forEach>
   </table>

   <script>
        var slider = document.getElementById("myRange");
        var output = document.getElementById("demo");
        output.innerHTML = slider.value;

        slider.oninput = function() {
         output.innerHTML = this.value;
        }

        function notRequired() {
           document.getElementById('description').required=false;
       }
   </script>


    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>