import axios from 'axios';

class PlayListMusicProxy {
    static async createPlayListMusic(playListId, musicId, title, jwtTokenState) {
        console.log(`[EFFECT] createPlayListMusic : <playListId:${playListId}, musicId:${musicId}, title:${title}>`)

        const requestHeader = {headers: {Authorization: jwtTokenState.jwtToken.Authorization}};
        const reqDto = {
            "playListId": playListId,
            "musicId": musicId,
            "title": title
        }
        const response = await axios.put(`http://${window.location.host}/api/playList/playListMusics/createPlayListMusic`, reqDto, requestHeader);
        
        console.log(response)
    }

    static async updatePlayListMusic(playListMusicId, title, jwtTokenState) {
        console.log(`[EFFECT] updatePlayListMusic : <playListMusicId:${playListMusicId}, title:${title}>`)

        const requestHeader = {headers: {Authorization: jwtTokenState.jwtToken.Authorization}};
        const reqDto = {
            "playListMusicId": playListMusicId,
            "title": title
        }
        const response = await axios.put(`http://${window.location.host}/api/playList/playListMusics/updatePlayListMusic`, reqDto, requestHeader);
        
        console.log(response)
    }


    static async searchPlayListMusicAllByPlayListId(playListId, jwtTokenState) {
        console.log(`[EFFECT] searchPlayListMusicAllByPlayListId : <playListId:${playListId}>`)

        const requestHeader = {headers: {Authorization: jwtTokenState.jwtToken.Authorization}};
        const response = await axios.get(`http://${window.location.host}/api/collectedData/playListMusics/search/findByPlayListId?playListId=${playListId}`, requestHeader);
        
        console.log(response)
        return response.data._embedded.playListMusics
    }
    

    static async deletePlayListMusicByPlayListMusicId(playListMusicId, jwtTokenState) {
        console.log(`[EFFECT] deletePlayListMusicByPlayListMusicId : <playListMusicId:${playListMusicId}>`)

        const requestHeader = {headers: {Authorization: jwtTokenState.jwtToken.Authorization}};
        const response = await axios.delete(`http://${window.location.host}/api/playList/playListMusics/${playListMusicId}`, requestHeader);
        
        console.log(response)
    }
}

export default PlayListMusicProxy