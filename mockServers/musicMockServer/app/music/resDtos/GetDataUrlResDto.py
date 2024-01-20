from flask import jsonify

class GetDataUrlResDto:
    def __init__(self, dataUrl:str="") :
        self.__dataUrl:str = dataUrl

    def __str__(self) :
        return "<GetDataUrlResDto dataUrlLen: {}>".format(len(self.__dataUrl))


    @property
    def dataUrl(self) -> str :
        return self.__dataUrl


    @dataUrl.setter
    def dataUrl(self, dataUrl) :
        self.__dataUrl = dataUrl


    def json(self) -> str :
        return jsonify({
            "dataUrl": self.__dataUrl
        })
