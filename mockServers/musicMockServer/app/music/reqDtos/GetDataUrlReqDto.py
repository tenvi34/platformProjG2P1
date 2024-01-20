from flask import request as flaskRequest

class GetDataUrlReqDto:
    def __init__(self, request:flaskRequest) :
        self.__jsonData = request.get_json()
        self.__dataUrlCode:str = self.__jsonData["dataUrlCode"] or ""

    def __str__(self) :
        return "<GetDataUrlReqDto dataUrlCode: {}>".format(len(self.__dataUrlCode))


    @property
    def dataUrlCode(self) -> str :
        return self.__dataUrlCode