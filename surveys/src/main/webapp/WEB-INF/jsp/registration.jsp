<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
	<meta charset="UTF-8">
	<title>Sign Up</title>
	<link rel = "icon" href =
        "https://library.kissclipart.com/20181213/uwe/kissclipart-lab-results-clipart-computer-icons-software-testin-444675676f2f188d.jpg"
                type = "image/x-icon">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body onLoad="javascript:checking()">
    <style>

        .text-block {
          position: absolute;
          text-align: center;
          top: 150px;
          right: 600px;
          background-color: rgba(255, 255, 255, 0.8);
          color: white;
          padding-left: 50px;
          padding-right: 50px;
          padding-bottom: 10px;
          padding-top: 70px;
          border-radius: 10px;
        }
        body {
          background-image: url("https://7themes.su/_ph/37/441883712.jpg");
          background-color: #cccccc;
          height: 720px;
          background-position: center;
          background-size: cover;
          position: relative;
        }

        .hero-text {
          text-align: center;
          position: absolute;
          top: 50%;
          left: 50%;
          transform: translate(-50%, -50%);
          color: black;
        }

        h1 {
            text-align: center;
            color: white;
        }

        #label{
            font-size: 22px;
            color: black;
        }

        #input {
          width: 100%;
          padding: 12px 20px;
          margin: 8px 0;
          box-sizing: border-box;
          border: 2px solid #98AFC7;
          border-radius: 7px;
          color: black;
        }
    </style>

    		<form action="/process" method="get">

		    <input type="hidden" name="exist" id="exist" value=${exist}>
            <br><br><br><br>
            <h1>Registration - Sign Up</h1>
            <div class="text-block">
                <div class="form-group">
                    <label for="nickname" class="form-label" id="label">Nickname</label>
                    <input type="text" name="nickname" class="form-control" id="input" placeholder="Enter a nickname" required />
                </div>
                <div class="form-group">
                    <label for="nickname" class="form-label" id="label">Password</label>
                    <input type="password" name="password" id="input" class="form-control" placeholder="password"
                                                required minlength="6" maxlength="20"/>
                </div>
                <br><br><br>
                    <a href="/", class="btn btn-danger">Back</a>
                    <a href="/login", class="btn btn-primary">Sign in</a>
                    <button type="submit" class="btn btn-success" name="signUp">Sign Up</button>
            </div>
        </form>

	<script type="text/javascript">
       function checking() {
            if(document.getElementById('exist').value == 'true'){
               alert("Sorry, but such user already exists. Sign in or come up with a new nickname");
            }
        }
    </script>
</body>
</html>