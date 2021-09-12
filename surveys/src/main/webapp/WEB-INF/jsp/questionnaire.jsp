<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel = "icon" href =
        "https://library.kissclipart.com/20181213/uwe/kissclipart-lab-results-clipart-computer-icons-software-testin-444675676f2f188d.jpg"
                type = "image/x-icon">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Questionnaire</title>
</head>
<body onLoad="javascript:checked(${value})">
    <style>
        body {
              background-image: url("https://www.pngkey.com/png/full/45-455323_picture-black-and-white-stock-blue-geometric-transprent.png");
              background-repeat: no-repeat;
              background-attachment: fixed;
              background-size: cover;
        }
        #description {
          width: 100%;
          height: 150px;
          padding: 12px 20px;
          box-sizing: border-box;
          border: 2px solid #ccc;
          border-radius: 4px;
          background-color: #f8f8f8;
          font-size: 16px;
          resize: none;
        }

        label, p{
            color: black;
            font-size: 24px;
            font-weight: bold;
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
        <form action="/createQuestionnaire" method="post">

            <input type="hidden" name="questionnaireId" value=${questionnaireId}>

            <div class="form-group">
                <label for="Questionnaire" class="form-label">Questionnaire name</label>
                <input type="text" class="form-control" id="name" placeholder="Enter a name" name="name" value="${name}" required>
            </div>

            <div class="form-group">
                <label for="description" class="form-label">Description</label>
                <textarea class="form-control" id="description" placeholder="Enter a description" name="description" required>${description}</textarea>
            </div>

            <p>Choose the scale</p>

            <div class="form-check form-check-inline">
                <input class="custom-radio" type="radio" name="scale" id="inlineRadio1" value="5" required">
                <label class="form-check-label" for="inlineRadio1">5-point scale</label>
              </div>

              <div class="form-check form-check-inline">
                <input class="custom-radio" type="radio" name="scale" id="inlineRadio2" value="10" required>
                <label class="form-check-label" for="inlineRadio2">10-point scale</label>
              </div>
            <br><br>
            <input type="submit" class="btn btn-danger" value="Back" name="backToMain" onclick="requiredDisabled()" />
            <input type="submit" class="btn btn-success" value="Continue" name"continue" />
        </form>
    </div>
    <script>
        function requiredDisabled(){
                document.getElementById("name").required = false;
                document.getElementById("description").required = false;
                document.getElementById("inlineRadio1").required = false;
                document.getElementById("inlineRadio2").required = false;
        }

        function checked(value){
            if(value == 10){
                document.getElementById("inlineRadio2").checked = true;
                document.getElementById("inlineRadio1").disabled = true;
            }
            else{
                document.getElementById("inlineRadio1").checked = true;
            }
        }
    </script>
</body>
</html>