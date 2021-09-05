<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>List of descriptions</title>
</head>
<body onLoad="javascript:disableSubmit(${minimum}, ${maximum})">
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


        <button type="submit" class="btn btn-warning" name="addQuestions" onclick="notRequired()">Add questions</button>
        <button type="submit" class="btn btn-danger" name="deleteQuestionnaire" onclick="notRequired()">Delete questionnaire</button>
        <button type="submit" class="btn btn-primary" id="add" name="addDescription" >Add a description</button>
        <button type="submit" class="btn btn-success" id="save" name="saveQuestionnaire" onclick="notRequired()" disabled>Save a questionnaire</button>
    </form>

    <table border="1">
       <tr>
           <th>number</th>
           <th>description</th>
           <th>start of range</th>
           <th>end of range</th>
       </tr>
       <c:forEach  items="${descriptions}" var ="description">
           <tr>
               <td>${description.number}</td>
               <td>${description.description}</td>
               <td>${description.start_scale}</td>
               <td>${description.end_scale}</td>
               <td><a href="/deleteDescription/${description.id}?questionnaireId=${questionnaireId}">Delete</a></td>
               <td><a href="/editDescription/${description.id}?questionnaireId=${questionnaireId}">Edit</a></td>
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

       function disableSubmit(min, max) {
            if(min > max){
                document.getElementById('add').disabled=true;
                document.getElementById('save').disabled=false;
            }
       }
   </script>
</body>
</html>