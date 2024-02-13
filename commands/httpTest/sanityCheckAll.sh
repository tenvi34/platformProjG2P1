serviceNames=("user" "music" "file" "playList" "comment" "collectedData")
for serviceName in "${serviceNames[@]}"
do

    http --check-status -q GET http://localhost:8088/api/$serviceName/sanityCheck
    if [ $? -ne 0 ]; then
        echo "[!] '$serviceName' sanity check... > FAIL..."
        exit 1
    fi
    echo "[*] '$serviceName' sanity check... > OK !"

done