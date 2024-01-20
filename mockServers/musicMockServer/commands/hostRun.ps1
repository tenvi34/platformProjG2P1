.\venv\Scripts\activate


cp envs/development.env .flaskenv


if (Test-Path "logs") {
    Remove-Item -Recurse -Force "logs"
}


python -m flask run