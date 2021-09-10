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

        div.scrollmenu {
          background-color: #333;
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
          background-color: #777;
        }
    </style>

    <div class="container mt-5">
        <form action="/survey/${surveyId}/${question.number}" method="get">
            <input type="hidden" name="resultId" value=${resultId}>
            <div class="scrollmenu">
                <c:forEach  items="${questions}" var ="question">
                    <tr>
                        <td><a href="/survey/${surveyId}/${question.number}?resultId=${resultId}">${question.number}</a></td>
                    </tr>
                </c:forEach>
            </div>

            <h3>Q${question.number}. ${question.description}</h3>
            <table>
                <c:forEach  items="${answers}" var ="answer">
                    <tr>
                        <div class="form-check">
                          <input class="form-check-input" type="radio" name="radio" id="radio" value="${answer.number}" required>
                          <label class="form-check-label" for="radio">
                            ${answer.number}. ${answer.answer}
                          </label>
                        </div>
                    </tr>
                </c:forEach>
            </table>
            <div class="vertical-center">
                <input type="submit" class="btn btn-danger" value="back" name="back" />
                <input type="submit" class="btn btn-primary" value="next" name="next" />
            </div>
        </form>
    </div>
</body>
</html>