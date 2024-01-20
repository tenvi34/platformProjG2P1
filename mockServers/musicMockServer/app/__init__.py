import os
from flask import Flask

from ._global.logger import LoggingConfig

from .sanityCheck import SanityCheckController


dirPathsToCreate = ["./logs"]

def create_app():
    for dirPathToCreate in dirPathsToCreate :
        if not os.path.exists(dirPathToCreate):
            os.makedirs(dirPathToCreate)


    app = Flask(__name__)
    LoggingConfig.setupLoggingConfig()

    app.register_blueprint(SanityCheckController.bp)

    return app
