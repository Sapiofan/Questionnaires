<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>${answer} answer</title>
</head>
<h1>Answer info</h1>
<body>
    <table>
        <tr>
            <td>number</td>
            <td>${answer.number}</td>
        </tr>
        <tr>
            <td>answer</td>
            <td>${answer.answer}</td>
        </tr>
        <tr>
            <td>correctness</td>
            <td>${answer.correctness}</td>
        </tr>
    </table>
    <form action="/listOfAnswers" method="get">
       <input type="hidden" name="questionId" value=${questionId}>
       <input type="submit" class="btn btn-danger" value="Back"/>
    </form>
</body>