<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
    </style>
    <div class="container mt-5">
        <form action="/survey/${surveyId}/${question.number}" method="get">
            <input type="hidden" name="resultId" value=${resultId}>
            <h3>Q${question.number}. ${question.description}</h3>
            <table>
            <c:forEach  items="${answers}" var ="answer">
                    <tr>
                        <div class="form-check">
                          <input class="form-check-input" type="radio" name="radio" id="radio" value="${answer.number}">
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