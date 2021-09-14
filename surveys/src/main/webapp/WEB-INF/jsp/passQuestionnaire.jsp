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
    <title>Questionnaire passing</title>
</head>
<body>

    <style>
           body {
                  background-image: url("https://i.pinimg.com/originals/4f/2f/7d/4f2f7d2aa8582dcca07ca3eb600e922b.png");
                  background-repeat: no-repeat;
                  background-attachment: fixed;
                  background-size: cover;
            }

            #label{
                 color: white;
                 font-size: 32px;
                 font-weight: bold;
            }
            p {
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

    <div class="container mt-5">
        <form action="/questionnaire/${questionnaireId}/${question.number}" method="get">
            <input type="hidden" name="resultId" value=${resultId}>
            <input type="hidden" name="flag" value=${flag}>
            <h3 id="label">Q${question.number}. ${question.name}</h3>
            <div class="slidecontainer">
                <input type="range" min="${minimum}" max="${maximum}" value="${middle}" class="slider" name="range" id="myRange">
                <p>Degree of agreement : <span id="demo"></span></p>
            </div>
            <input type="submit" class="btn btn-danger" value="back" name="back" />
            <input type="submit" class="btn btn-primary" value="next" name="next" />
        </form>
    </div>

    <script>
        var slider = document.getElementById("myRange");
        var output = document.getElementById("demo");
        output.innerHTML = slider.value;

        slider.oninput = function() {
         output.innerHTML = this.value;
        }
   </script>
</body>
</html>