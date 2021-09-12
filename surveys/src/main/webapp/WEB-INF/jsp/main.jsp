<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel = "icon" href =
    "https://library.kissclipart.com/20181213/uwe/kissclipart-lab-results-clipart-computer-icons-software-testin-444675676f2f188d.jpg"
            type = "image/x-icon">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
    <style>
        div.scrollmenu {
          background-color:#2554C7;
          overflow: auto;
          white-space: nowrap;
        }

        div.scrollmenu a {
          display: inline-block;
          color: white;
          text-align: center;
          padding: 14px;
          text-decoration: none;
        }

        div.scrollmenu a:hover {
          background-color: #1E90FF;
        }

        #input{
            position:relative;
            left:770px;
            top: -3px;
        }
        body {
              background-image: url("http://www.trainerize.com/blog/wp-content/uploads/2017/12/Client-Questionnaire.jpg");
              background-repeat: no-repeat;
              background-attachment: fixed;
              background-size: cover;
        }

        .container {
          position: relative;
          text-align: center;
          color: white;
        }

        .centered {
          position: relative;

          left: 50%;
          transform: translate(-50%, 10%);
        }

    </style>
    <form action="/logout" method="get">
    <div class="scrollmenu">
      <b><a href="/survey">Create survey</a></b>
      <b><a href="/createQuestionnaire">Create questionnaire</a></b>
      <b><a href="/list">Surveys list</a></b>
      <b><a href="/listOfQuestionnaires">Questionnaires list</a></b>
      <input type="submit" id="input" class="btn btn-danger" value="Sign out" />
    </div>

    <div class="container">
        <div class="centered">
            <h1>Now you are on homepage!</h1>
            <h2>Here you can start creating of survey or questionnaire,</h2>
            <h2>go to list of surveys or questionnaires and pass any one from the list.</h2>
        </div>
    </div>

    </form>
</body>
</html>