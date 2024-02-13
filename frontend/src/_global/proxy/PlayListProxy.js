import axios from 'axios';

class PlayListProxy {
    static async createPlayList(title, jwtTokenState) {
        console.log(`[EFFECT] createPlayList : <title:${title}>`)

        const requestHeader = {headers: {Authorization: jwtTokenState.jwtToken.Authorization}};
        const reqDto = {
            "title": title
        }
        const response = await axios.put(`http://${window.location.host}/api/playList/playLists/createPlayList`, reqDto, requestHeader);
        
        console.log(response)
    }

    static async updatePlayList(playListId, title, jwtTokenState) {
        console.log(`[EFFECT] updatePlayList : <playListId:${playListId}, title:${title}>`)

        const requestHeader = {headers: {Authorization: jwtTokenState.jwtToken.Authorization}};
        const reqDto = {
            "playListId": playListId,
            "title": title
        }
        const response = await axios.put(`http://${window.location.host}/api/playList/playLists/updatePlayList`, reqDto, requestHeader);
        
        console.log(response)
    }


    static async searchPlayListAllByCreaterId(createrId, jwtTokenState) {
        console.log(`[EFFECT] searchPlayListAllByCreaterId : <createrId:${createrId}>`)

        const requestHeader = {headers: {Authorization: jwtTokenState.jwtToken.Authorization}};
        const response = await axios.get(`http://${window.location.host}/api/collectedData/playLists/search/findByCreaterId?createrId=${createrId}`, requestHeader);
        
        console.log(response)
        return response.data._embedded.playLists
    }

    static async searchPlayListOneByPlayListId(playListId, jwtTokenState) {
        console.log(`[EFFECT] searchPlayListOneByPlayListId : <playListId:${playListId}>`)

        const requestHeader = {headers: {Authorization: jwtTokenState.jwtToken.Authorization}};
        const response = await axios.get(`http://${window.location.host}/api/collectedData/playLists/search/findByPlayListId?playListId=${playListId}`, requestHeader);
        
        console.log(response)
        return response.data
    }


    static async deletePlayListByPlayListId(playListId, jwtTokenState) {
        console.log(`[EFFECT] deletePlayListByPlayListId : <playListId:${playListId}>`)

        const requestHeader = {headers: {Authorization: jwtTokenState.jwtToken.Authorization}};
        const response = await axios.delete(`http://${window.location.host}/api/playList/playLists/${playListId}`, requestHeader);
        
        console.log(response)
    }
}

export default PlayListProxy