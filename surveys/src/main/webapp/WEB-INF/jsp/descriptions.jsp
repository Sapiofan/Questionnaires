<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel = "icon" href =
        "https://library.kissclipart.com/20181213/uwe/kissclipart-lab-results-clipart-computer-icons-software-testin-444675676f2f188d.jpg"
                type = "image/x-icon">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>List of descriptions</title>
</head>
<body onLoad="javascript:disableSubmit(${minimum}, ${maximum})">
   <style>
       body {
           background-image: url("https://www.pngkey.com/png/full/45-455323_picture-black-and-white-stock-blue-geometric-transprent.png");
           background-repeat: no-repeat;
           background-attachment: fixed;
           background-size: cover;
     }
     #table {
       font-family: Arial, Helvetica, sans-serif;
       border-collapse: collapse;
       width: 50%;
     }

     #table td, #table th {
       border: 1px solid #ddd;
       padding: 8px;
     }

     #table tr:nth-child(even){background-color: #f2f2f2;}

     #table tr:nth-child(odd){background-color: #C0C0C0;}

     #table tr:hover {background-color: #ddd;}

     #table th {
       padding-top: 12px;
       padding-bottom: 12px;
       text-align: left;
       background-color: #157DEC;
       color: white;
     }

     input[type=number] {
      float:right;
       box-sizing: border-box;
       border: 2px solid #ccc;
       border-radius: 4px;
       font-size: 16px;
       background-color: white;
       background-position: 10px 10px;
       background-repeat: no-repeat;
     }

     #label{
          color: white;
          font-size: 20px;
          font-weight: bold;
     }

     input[type=text]{
         width: 30%;
        box-sizing: border-box;
        border: 2px solid #ccc;
        border-radius: 4px;
        font-size: 16px;
        background-color: white;
        background-position: 10px 10px;
        background-repeat: no-repeat;
      }

       .slider {
         -webkit-appearance: none;
         width: 50%;
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
         background: #00FF00;
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

         <button type="submit" class="btn btn-warning" name="addQuestions" onclick="notRequired()">Add questions</button>
        <button type="submit" class="btn btn-danger" name="deleteQuestionnaire" onclick="if (confirm('Are you sure you want to delete the questionnaire?')) form.action='/addDescription'; else { return false;}; notRequired();">Delete questionnaire</button>
        <button type="submit" class="btn btn-primary" id="add" name="addDescription" >Add a description</button>
        <button type="submit" class="btn btn-success" id="save" name="saveQuestionnaire" onclick="notRequired()" disabled>Save a questionnaire</button>


        <div class="form-group">
            <label for="Description" class="form-label" id="label">Description</label>
            <input type="text" class="form-control" id="description" placeholder="Enter a description" name="description" required>
        </div>
        <p id="label">Enter a number of points</p>

        <div class="slidecontainer">
            <input type="range" min="${minimum}" max="${maximum}" value="${minimum}" class="slider" name="range" id="myRange">
            <p id="label">Value: <span id="demo"></span></p>
        </div>
    </form>

    <table border="1" id="table">
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
               <td><a href="/deleteDescription/${description.id}?questionnaireId=${questionnaireId}"
               onclick="if (confirm('Are you sure you want to delete the description?')) form.action='/deleteDescription'; else return false;">Delete</a></td>
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