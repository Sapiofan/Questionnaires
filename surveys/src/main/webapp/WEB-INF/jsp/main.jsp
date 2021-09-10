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
          background-color: #E5E4E2;
          overflow: auto;
          white-space: nowrap;
        }

        div.scrollmenu a {
          display: inline-block;
          color: black;
          text-align: center;
          padding: 14px;
          text-decoration: none;
        }

        div.scrollmenu a:hover {
          background-color: #767;
        }
    </style>
    <h3>Hello, here you can create simple surveys and questionnaires.</h3>
    <form action="/logout" method="get">
    <div class="scrollmenu">
      <a href="/survey">Create survey</a>
      <a href="/createQuestionnaire">Create questionnaire</a>
      <a href="/list">Surveys list</a>
      <a href="/listOfQuestionnaires">Questionnaires list</a>
      <input type="submit" class="btn btn-danger" value="Sign out" />
    </div>

    </form>
</body>
</html>