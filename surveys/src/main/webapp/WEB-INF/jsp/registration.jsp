<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
	<meta charset="ISO-8859-1">
	<title>Sign Up</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
	<div class="container text-center">
		<div>
			<h1>Registration - Sign Up</h1>
		</div>
		<form action="/process_register" method="post">
		<div class="m-3">
			<div class="form-group row">
				<label class="col-4 col-form-label">Nickname: </label>
				<div class="col-8">
					<input type="text" name="nickname" class="form-control" placeholder="Enter a nickname" required />
				</div>
			</div>

			<div class="form-group row">
				<label class="col-4 col-form-label">Password: </label>
				<div class="col-8">
					<input type="password" name="password" class="form-control" placeholder="password"
							required minlength="6" maxlength="20"/>
				</div>
			</div>

			<div>
			    <a href="/", class="btn btn-danger">Back</a>
				<button type="submit" class="btn btn-success" name="signUp">Sign Up</button>
			</div>
		</div>
		</form>
	</div>
</body>
</html>