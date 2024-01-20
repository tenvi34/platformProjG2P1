from flask import Blueprint, request
from http import HTTPStatus

from .._global.logger import CustomLogger
from .._global.logger import CustomLoggerType

from . import MusicService

from .reqDtos.GetDataUrlReqDto import GetDataUrlReqDto
from .resDtos.GetDataUrlResDto import GetDataUrlResDto


bp = Blueprint("music", __name__, url_prefix="/musics")

# MOCK: 주어진 DataUrlCode에 해당하는 DataUrl을 반환하기 위해서
@bp.route("/getDataUrl", methods=("PUT",))
def getDataUrl() -> GetDataUrlReqDto :
    try :

        getDataUrlReqDto:GetDataUrlReqDto = GetDataUrlReqDto(request)
        CustomLogger.debug(CustomLoggerType.ENTER, "", "<getDataUrlReqDto: {}>".format(getDataUrlReqDto))

        getDataUrlResDto:GetDataUrlResDto = MusicService.getDataUrl(getDataUrlReqDto)

        CustomLogger.debug(CustomLoggerType.EXIT, "", "<getDataUrlResDto: {}>".format(getDataUrlResDto))
        return (getDataUrlResDto.json(), HTTPStatus.OK)

    except Exception as e :
        jsonData = request.get_json()
        dataUrlCode = jsonData["dataUrlCode"] or ""
        CustomLogger.error(e, "", "<dataUrlCode: {}>".format(dataUrlCode))
        return ("", HTTPStatus.BAD_REQUEST)