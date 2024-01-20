import os

from .._global.logger import CustomLogger
from .._global.logger import CustomLoggerType

from .reqDtos.GetDataUrlReqDto import GetDataUrlReqDto
from .resDtos.GetDataUrlResDto import GetDataUrlResDto

# 주어진 DataUrl을 기반으로 파일을 S3에 업로드시키고, 관련 URL을 반환하기 위해서
def getDataUrl(getDataUrlReqDto:GetDataUrlReqDto) -> GetDataUrlResDto :
    targetSamplePath = ""
    if getDataUrlReqDto.dataUrlCode == "SampleMusicDataUrl1" : 
        targetSamplePath = "./sampleDatas/SampleMusicDataUrl1.txt"
    elif getDataUrlReqDto.dataUrlCode == "SampleMusicDataUrl2" : 
        targetSamplePath = "./sampleDatas/SampleMusicDataUrl2.txt"
    elif getDataUrlReqDto.dataUrlCode == "SampleMusicDataUrl3" : 
        targetSamplePath = "./sampleDatas/SampleMusicDataUrl3.txt"
    else :
        return GetDataUrlResDto("[dataUrlCode에 해당하는 파일이 없음]")

    sampleDataUrl = ""
    with open(targetSamplePath, "r") as file:
        sampleDataUrl = "".join(file.readlines())

    return GetDataUrlResDto(sampleDataUrl)