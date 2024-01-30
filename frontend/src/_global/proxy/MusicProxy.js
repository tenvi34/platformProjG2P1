import axios from 'axios';

class MusicProxy {
    static async createMusic(title, creater, dataUrl, jwtTokenState) {
        console.log(`[EFFECT] createMusic : <title:${title}, creater:${creater}, dataUrlLength:${dataUrl.length}>`)

        const requestHeader = {headers: {Authorization: jwtTokenState.jwtToken.Authorization}};
        const reqDto = {
            "title": title,
            "creater": creater,
            "dataUrl": dataUrl
        }
        const response = await axios.put(`http://${window.location.host}/api/music/musics/createMusic`, reqDto, requestHeader);
        
        console.log(response)
    }
}

export default MusicProxy