<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
	<meta charset="ISO-8859-1">
	<title>Sign Up</title>
	<link rel="stylesheet" type="text/css" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<script type="text/javascript" src="/webjars/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container text-center">
		<div>
			<h1>Registration - Sign Up</h1>
		</div>
		<form action="process_register" th:object="${user}"
			method="post" style="max-width: 600px; margin: 0 auto;">
		<div class="m-3">
			<div class="form-group row">
				<label class="col-4 col-form-label">Nickname: </label>
				<div class="col-8">
					<input type="nickname" th:field="*{nickname}" class="form-control" required />
				</div>
			</div>

			<div class="form-group row">
				<label class="col-4 col-form-label">Password: </label>
				<div class="col-8">
					<input type="password" th:field="*{password}" class="form-control"
							required minlength="6" maxlength="20"/>
				</div>
			</div>

			<div>
				<button type="submit" class="btn btn-success">Sign Up</button>
			</div>
		</div>
		</form>
	</div>
</body>
</html>