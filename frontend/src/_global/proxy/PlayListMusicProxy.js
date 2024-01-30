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
}

export default PlayListMusicProxy