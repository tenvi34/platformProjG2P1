from .._global.logger import CustomLogger
from .._global.logger import CustomLoggerType

from .reqDtos.GetDataUrlReqDto import GetDataUrlReqDto
from .resDtos.GetDataUrlResDto import GetDataUrlResDto

# 주어진 DataUrl을 기반으로 파일을 S3에 업로드시키고, 관련 URL을 반환하기 위해서
def getDataUrl(getDataUrlReqDto:GetDataUrlReqDto) -> GetDataUrlResDto :
    getDataUrlResDto:GetDataUrlResDto = GetDataUrlResDto(getDataUrlReqDto.dataUrl)
    return getDataUrlResDto