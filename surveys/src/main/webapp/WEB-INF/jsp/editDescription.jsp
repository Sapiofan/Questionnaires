<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>Edit description</title>
</head>
<body>
   <style>
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

    <form action="/addDescription" method="post">
       <input type="hidden" name="questionnaireId" value=${questionnaireId}>
       <input type="hidden" name="minimum" value=${minimum}>
        <input type="hidden" name="maximum" value=${maximum}>

       <div class="form-group">
               <label for="Description" class="form-label">Description</label>
               <input type="text" class="form-control" id="description" value="${description.description}" placeholder="Enter a description" name="description" required>
       </div>
           <h1>Low range</h1>

       <div class="slidecontainer">
         <input type="range" min="${minimum}" max="${value_min}" value="${value_min}" class="slider" name="range1" id="myRange1">
         <p>Value: <span id="demo"></span></p>
       </div>

        <h1>High range</h1>
       <div class="slidecontainer">
        <input type="range" min="${value_min}" max="${maximum}" value="${value}" class="slider" name="range2" id="myRange2">
        <p>Value: <span id="second"></span></p>
      </div>


        <button type="submit" class="btn btn-warning" name="addQuestions" onclick="notRequired()">Back</button>
        <button type="submit" class="btn btn-primary" id="add" name="addDescription" >Update</button>
    </form>

   <script>
        var slider = document.getElementById("myRange1");
        var output = document.getElementById("demo");
        var slider1 = document.getElementById("myRange2");
        var output1 = document.getElementById("second");

        output.innerHTML = slider.value;

        output1.innerHTML = slider1.value;

        slider.oninput = function() {
         output.innerHTML = this.value;
        }

        slider1.oninput = function() {
            output1.innerHTML = this.value;
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