serviceNames=("user" "music" "file" "playList" "comment" "collectedData")
errorLogs=""
for serviceName in "${serviceNames[@]}"
do

    echo "[*] Searching for '$serviceName' error log..."
    errorLogs+=`http GET http://localhost:8088/api/$serviceName/sanityCheck/logs?regFilter=.*ERROR.* | grep ERROR`
    errorLogs+="\n"
done

echo "[*] Total error logs..."
echo -e $errorLogs