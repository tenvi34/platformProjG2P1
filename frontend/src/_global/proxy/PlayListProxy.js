import axios from 'axios';

class PlayListProxy {
    static async searchPlayListAll(jwtTokenState) {
        console.log(`[EFFECT] searchPlayListAll`)

        const requestHeader = {headers: {Authorization: jwtTokenState.jwtToken.Authorization}};
        const response = await axios.get(`http://${window.location.host}/api/collectedData/playLists`, requestHeader);
        
        console.log(response)
        return response.data._embedded.playLists
    }
}

export default PlayListProxy