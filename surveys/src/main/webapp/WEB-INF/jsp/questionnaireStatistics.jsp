<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Results</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel = "icon" href =
        "https://library.kissclipart.com/20181213/uwe/kissclipart-lab-results-clipart-computer-icons-software-testin-444675676f2f188d.jpg"
                type = "image/x-icon">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>

    <style>
        .container{
            text-align: center;
        }
        body {
            background-image: url("https://www.pngkey.com/png/full/45-455323_picture-black-and-white-stock-blue-geometric-transprent.png");
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: cover;
      }
      #label{
                 color: white;
                 font-size: 48px;
                 font-weight: bold;
                 text-align: center;
            }
      h3 {
           color: white;
           font-size: 32px;
           font-weight: bold;
      }

    </style>

    <input type="hidden" name="questionnaireId" value=${questionnaireId}>
    <input type="hidden" name="question" value=${question}>
    <h2 id="label">You have passed the questionnaire</h2>

    <h3>Lost time: hours: ${hours}, minutes: ${minutes} seconds: ${seconds}</h3>
    <h3>Description: ${description}</h3>

    <form action="/listOfQuestionnaires" method="post">
        <input type="hidden" name="resultId" value=${resultId}>
        <button type="submit" class="btn btn-primary" name="back" >Back to list of questionnaires</button>
    </form>
</body>
</html>