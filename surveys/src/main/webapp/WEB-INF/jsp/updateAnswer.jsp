<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel = "icon" href =
        "https://library.kissclipart.com/20181213/uwe/kissclipart-lab-results-clipart-computer-icons-software-testin-444675676f2f188d.jpg"
                type = "image/x-icon">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Update answer</title>
</head>
<body onLoad="javascript:checked(${value})">

    <style>
        body {
            background-image: url("https://www.pngkey.com/png/full/45-455323_picture-black-and-white-stock-blue-geometric-transprent.png");
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: cover;
      }

      #label{
           color: white;
           font-size: 20px;
           font-weight: bold;
      }

      input[type=text], input[type=number] {
            width: 30%;
           box-sizing: border-box;
           border: 2px solid #ccc;
           border-radius: 4px;
           font-size: 16px;
           background-color: white;
           background-position: 10px 10px;
           background-repeat: no-repeat;
         }

    .custom-radio {
        position: absolute;
        z-index: -1;
        opacity: 0;
      }

      /* для элемента label связанного с .custom-radio */
      .custom-radio+label {
        display: inline-flex;
        align-items: center;
        user-select: none;
        color: white;
        font-weight: bold;
        font-size: 18px;
      }

      /* создание в label псевдоэлемента  before со следующими стилями */
      .custom-radio+label::before {
        content: '';
        display: inline-block;
        width: 1em;
        height: 1em;
        flex-shrink: 0;
        flex-grow: 0;
        border: 1px solid #adb5bd;
        border-radius: 50%;
        margin-right: 0.5em;
        background-repeat: no-repeat;
        background-position: center center;
        background-size: 50% 50%;
      }

      /* стили при наведении курсора на радио */
      .custom-radio:not(:disabled):not(:checked)+label:hover::before {
        border-color: #b3d7ff;
      }

      /* стили для активной радиокнопки (при нажатии на неё) */
      .custom-radio:not(:disabled):active+label::before {
        background-color: #800517;
        border-color: #800517;
      }

      /* стили для радиокнопки, находящейся в фокусе */
      .custom-radio:focus+label::before {
        box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
      }

      /* стили для радиокнопки, находящейся в фокусе и не находящейся в состоянии checked */
      .custom-radio:focus:not(:checked)+label::before {
        border-color: #80bdff;
      }

      /* стили для радиокнопки, находящейся в состоянии checked */
      .custom-radio:checked+label::before {
        border-color: #800517;
        background-color: #800517;
        background-image: url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' viewBox='-4 -4 8 8'%3e%3ccircle r='3' fill='%23fff'/%3e%3c/svg%3e");
      }

      /* стили для радиокнопки, находящейся в состоянии disabled */
      .custom-radio:disabled+label::before {
        background-color: #e9ecef;
      }

    .radio {
      margin-bottom: 0.4em;
    }
    </style>

    <div class="container mt-5">
        <form action="/updateAnswer" method="post">
            <div class="form-group">

                <input type="hidden" name="questionId" value=${questionId}>
                <input type="hidden" name="answerId" value=${answerId}>

                <label for="number" class="form-label" id="label">Number of answer</label>
                <input type="number" min="1", max="${size}" class="form-control" id="number" placeholder="Enter a number" name="number" value="${answer.number}" required>


                <label for="answer" class="form-label" id="label">Answer</label>
                <input type="text" class="form-control" id="answer" placeholder="Enter a answer" name="answer" value="${answer.answer}" required>

                <div class="form-check form-check-inline">
                    <input class="custom-radio" type="radio" name="correctAnswer" id="true" value="1" required">
                    <label class="form-check-label" for="inlineRadio1">Correct</label>
                  </div>

                  <div class="form-check form-check-inline">
                    <input class="custom-radio" type="radio" name="correctAnswer" id="false" value="2" required>
                    <label class="form-check-label" for="inlineRadio2">Incorrect</label>
                  </div>
          </div>
        <button type="submit" class="btn btn-danger" name="backToAnswersList" onclick="notRequired()">Back</button>
        <button type="submit" class="btn btn-success" name="saveAnswer">Submit</button>
        </form>
    </div>

    <script>
        function notRequired() {
            document.getElementById('answer').required=false;
            document.getElementById('true').required=false;
            document.getElementById('false').required=false;
        }

        function checked(value){
            if(value == 2){
                document.getElementById('false').checked=true;
            }
            else{
                document.getElementById('true').checked=true;
            }
        }
    </script>

   </body>
</html>