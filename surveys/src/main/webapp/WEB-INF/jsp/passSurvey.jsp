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
    <title>Survey passing</title>
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
        body {
                background-image: url("https://i.pinimg.com/originals/4f/2f/7d/4f2f7d2aa8582dcca07ca3eb600e922b.png");
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: cover;
          }

          #label{
               color: white;
               font-size: 26px;
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
        <form action="/survey/${surveyId}/${question.number}" method="get">
            <input type="hidden" name="resultId" value=${resultId}>

            <h3 id="label">Q${question.number}. ${question.description}</h3>
            <table>
                <c:forEach  items="${answers}" var ="answer">
                    <tr>
                        <div class="form-check">
                          <input class="custom-radio" type="radio" name="radio" id="${answer.number}" value="${answer.number}" required>
                          <label class="form-check-label" for="${answer.number}">
                            ${answer.number}. ${answer.answer}
                          </label>
                        </div>
                    </tr>
                </c:forEach>
            </table>
            <div class="vertical-center">
                <input type="submit" class="btn btn-danger" value="back" onclick="notRequired(${size})" name="back" />
                <input type="submit" class="btn btn-primary" value="next" name="next" />
            </div>
        </form>
    </div>

    <script>
        function notRequired(size) {
            for(let id = 1; id <= size; id++){
                let string = "" + id;
                document.getElementById(string).required=false;
            }
        }
    </script>
</body>
</html>