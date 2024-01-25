Start-Process powershell -ArgumentList "-Command", "cd ./user;./commands/kubernetes/deleteAll.ps1"
Start-Process powershell -ArgumentList "-Command", "cd ./music;./commands/kubernetes/deleteAll.ps1"
Start-Process powershell -ArgumentList "-Command", "cd ./file;./commands/kubernetes/deleteAll.ps1"
Start-Process powershell -ArgumentList "-Command", "cd ./playList;./commands/kubernetes/deleteAll.ps1"
Start-Process powershell -ArgumentList "-Command", "cd ./comment;./commands/kubernetes/deleteAll.ps1"

Start-Process powershell -ArgumentList "-Command", "cd ./collectedData;./commands/kubernetes/deleteAll.ps1"
Start-Process powershell -ArgumentList "-Command", "cd ./gateway;./commands/kubernetes/deleteAll.ps1"

Start-Process powershell -ArgumentList "-Command", "cd ./frontend;./commands/kubernetes/deleteAll.ps1"