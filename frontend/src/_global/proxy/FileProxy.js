import axios from 'axios';

class FileProxy {
    static async searchFileOneByFileId(fileId, jwtTokenState) {
        console.log(`[EFFECT] searchFileOneByFileId : <fileId:${fileId}>`)

        const requestHeader = {headers: {Authorization: jwtTokenState.jwtToken.Authorization}};
        const response = await axios.get(`http://${window.location.host}/api/collectedData/files/search/findByFileId?fileId=${fileId}`, requestHeader);
        
        console.log(response)
        return response.data
    }
}

export default FileProxy