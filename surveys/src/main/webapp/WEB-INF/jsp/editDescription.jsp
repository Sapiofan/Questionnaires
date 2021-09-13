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
        <title>Edit description</title>
</head>
<body onLoad="javascript:disable(${minimum})">
   <style>
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

       body {
              background-image: url("https://www.pngkey.com/png/full/45-455323_picture-black-and-white-stock-blue-geometric-transprent.png");
              background-repeat: no-repeat;
              background-attachment: fixed;
              background-size: cover;
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
   </style>

    <form action="/addDescription" method="post">
        <input type="hidden" name="questionnaireId" value=${questionnaireId}>
        <input type="hidden" name="minimum" value=${minimum}>
        <input type="hidden" name="maximum" value=${maximum}>
        <input type="hidden" name="descriptionId" value=${descriptionId}>

        <div class="form-group">
            <label for="Description" class="form-label" id="label">Description</label>
            <input type="text" class="form-control" id="description" value="${description.description}" placeholder="Enter a description" name="description" required>
        </div>
        <p id="label">Low range</p>

        <div class="slidecontainer">
            <input type="range" min="${minimum}" max="${value_min}" value="${value_min}" class="slider" name="range1" id="myRange1">
            <p>Value: <span id="demo"></span></p>
        </div>

        <p id="label">High range</p>
        <div class="slidecontainer">
            <input type="range" min="${value_min}" max="${maximum}" value="${value}" class="slider" name="range2" id="myRange2">
            <p>Value: <span id="second"></span></p>
        </div>

        <button type="submit" class="btn btn-warning" name="back" onclick="notRequired()">Back</button>
        <button type="submit" class="btn btn-success" id="update" name="update" >Update</button>
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

       function disabled(x){
           if(x == 1){
                document.getElementById('myRange1').disabled=true;
           }
       }
   </script>
</body>
</html>