import React, { useContext, useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import LogoutIcon from '@mui/icons-material/Logout';

import { JwtTokenContext } from "../../_global/provider/jwtToken/JwtTokenContext";

import TopAppBar from '../../_global/components/TopAppBar';
import IconButton from '../../_global/components/button/IconButton';


import ReactAudioPlayer from 'react-audio-player';




const MusicListPage = () => {
    const {jwtTokenState, deleteTokenValue} = useContext(JwtTokenContext);
    const navigate = useNavigate();

    useEffect(() => {
        if(jwtTokenState.jwtToken === null) {
            navigate("/user/signIn");
        }
    }, [jwtTokenState.jwtToken, navigate])

    if(jwtTokenState.jwtToken !== null) {
        console.log(jwtTokenState.jwtToken)
        console.log(jwtTokenState.jwtToken.role === "Admin")
    }


    // audio source
    const musicUrl = 'http://localhost:8088/api/file/5510a8d0-1b16-4ce6-8622-d1d39f0c3c36.mp3';

    return (
        <>
            <TopAppBar title="음악 목록">           
                <IconButton sx={{marginRight: 1}} onClick={() => {deleteTokenValue();}}>
                    <LogoutIcon sx={{fontSize: 35, paddingTop: 0.3, paddingLeft: 0.3}}/>
                </IconButton>
            </TopAppBar>

            <ReactAudioPlayer
                src={musicUrl}
                controls
            />
        </>
    )
}

export default MusicListPage;