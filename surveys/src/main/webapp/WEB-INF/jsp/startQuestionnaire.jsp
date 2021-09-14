<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel = "icon" href =
        "https://library.kissclipart.com/20181213/uwe/kissclipart-lab-results-clipart-computer-icons-software-testin-444675676f2f188d.jpg"
                type = "image/x-icon">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Start passing</title>
</head>
<body>
    <h1 style="text-align: center; color:white;">${name}</h1>

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
                     font-size: 48px;
                     font-weight: bold;
                }
          h3 {
               color: white;
               font-size: 32px;
               font-weight: bold;
          }
    </style>

    <div class="container mt-5">
        <form action="/questionnaire/${questionnaireId}" method="post">
            <h3><p>Description: ${description}</p></h3>
            <div class="vertical-center">
                <a href="/listOfQuestionnaires", class="btn btn-danger">Back to list</a>
                <input type="submit" class="btn btn-success" value="Start" name="start" />
            </div>
        </form>
    </div>
</body>
</html>