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

    static async likeMusic(musicId, jwtTokenState) {
        console.log(`[EFFECT] likeMusic : <musicId:${musicId}>`)

        const requestHeader = {headers: {Authorization: jwtTokenState.jwtToken.Authorization}};
        const reqDto = {
            "musicId": musicId
        }
        const response = await axios.put(`http://${window.location.host}/api/music/musics/likeMusic`, reqDto, requestHeader);
        
        console.log(response)
    }

    static async updateMusicInfo(musicId, title, jwtTokenState) {
        console.log(`[EFFECT] updateMusicInfo : <musicId:${musicId}, title:${title}>`)

        const requestHeader = {headers: {Authorization: jwtTokenState.jwtToken.Authorization}};
        const reqDto = {
            "musicId": musicId,
            "title": title
        }
        const response = await axios.put(`http://${window.location.host}/api/music/musics/updateMusicInfo`, reqDto, requestHeader);
        
        console.log(response)
    }

    static async updateMusicFile(musicId, dataUrl, jwtTokenState) {
        console.log(`[EFFECT] updateMusicInfo : <musicId:${musicId}, dataUrlLength:${dataUrl.length}>`)

        const requestHeader = {headers: {Authorization: jwtTokenState.jwtToken.Authorization}};
        const reqDto = {
            "musicId": musicId,
            "dataUrl": dataUrl
        }
        const response = await axios.put(`http://${window.location.host}/api/music/musics/updateMusicFile`, reqDto, requestHeader);
        
        console.log(response)
    }


    static async searchMusicOneByMusicId(musicId, jwtTokenState) {
        console.log(`[EFFECT] searchMusicOneByMusicId : <musicId:${musicId}>`)

        const requestHeader = {headers: {Authorization: jwtTokenState.jwtToken.Authorization}};
        const response = await axios.get(`http://${window.location.host}/api/collectedData/musics/search/findByMusicId?musicId=${musicId}`, requestHeader);
        
        console.log(response)
        return response.data
    }

    static async searchMusicAll(jwtTokenState) {
        console.log(`[EFFECT] searchMusicAll`)

        const requestHeader = {headers: {Authorization: jwtTokenState.jwtToken.Authorization}};
        const response = await axios.get(`http://${window.location.host}/api/collectedData/musics`, requestHeader);
        
        console.log(response)
        return response.data._embedded.musics
    }

    static async searchMusicAllByTitle(title, jwtTokenState) {
        console.log(`[EFFECT] searchMusicAllByTitle : <title:${title}>`)

        const requestHeader = {headers: {Authorization: jwtTokenState.jwtToken.Authorization}};
        const response = await axios.get(`http://${window.location.host}/api/collectedData/musics/search/findByTitleContainingIgnoreCase?title=${title}`, requestHeader);
        
        console.log(response)
        return response.data._embedded.musics
    }

    static async searchMusicAllByCreater(creater, jwtTokenState) {
        console.log(`[EFFECT] searchMusicAllByCreater : <creater:${creater}>`)

        const requestHeader = {headers: {Authorization: jwtTokenState.jwtToken.Authorization}};
        const response = await axios.get(`http://${window.location.host}/api/collectedData/musics/search/findByCreaterContainingIgnoreCase?creater=${creater}`, requestHeader);
        
        console.log(response)
        return response.data._embedded.musics
    }
}

export default MusicProxy