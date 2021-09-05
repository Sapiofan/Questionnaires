<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
	<meta charset="UTF-8">
	<title>Sign Up</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body onLoad="javascript:checking()">
	<div class="container text-center">
		<div>
			<h1>Registration - Sign Up</h1>
		</div>
		<form action="/process" method="get">

		    <input type="hidden" name="exist" id="exist" value=${exist}>

            <div class="form-group">
                <label for="nickname" class="form-label">Nickname </label>
                <input type="text" name="nickname" class="form-control" placeholder="Enter a nickname" required />
            </div>
            <div class="form-group">
                <label for="nickname" class="form-label">Password </label>
                <input type="password" name="password" class="form-control" placeholder="password"
                  							required minlength="6" maxlength="20"/>
            </div>
            <div class="vertical-center">
                <a href="/", class="btn btn-danger">Back</a>
                <button type="submit" class="btn btn-success" name="signUp">Sign Up</button>
            </div>
        </form>
	</div>

	<script type="text/javascript">
       function checking() {
            if(document.getElementById('exist').value == 'true'){
               alert("Sorry, but such user already exists. Sign in or come up with a new nickname");
            }
        }
    </script>
</body>
</html>