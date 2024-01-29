import React, { useContext, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { useNavigate } from "react-router-dom";
import { Stack } from '@mui/material';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';

import { JwtTokenContext } from "../../_global/provider/jwtToken/JwtTokenContext";

import TopAppBar from '../../_global/components/TopAppBar';
import IconNavigationButton from '../../_global/components/button/IconNavigationButton';
import UserManageButton from '../../_global/components/button/UserManageButton';
import MusicInfo from '../../_global/components/MusicInfo/MusicInfo';

const MusicInfoPage = () => {
    const {musicId} = useParams();
    const {jwtTokenState} = useContext(JwtTokenContext);
    const navigate = useNavigate();

    useEffect(() => {
        if(jwtTokenState.jwtToken === null) {
            navigate("/user/signIn");
        }
    }, [jwtTokenState.jwtToken, navigate])

    console.log(musicId)

    return (
        <>
            <TopAppBar title="음악 정보">  
                <UserManageButton sx={{marginRight: "5px"}}/>
                <IconNavigationButton url="/music/list">
                    <ArrowBackIcon sx={{fontSize: 40}}/>
                </IconNavigationButton>
            </TopAppBar>

            <Stack>
                <MusicInfo sx={{width: "95.5%", marginTop: "5px"}} musicId={musicId}/>
            </Stack>
        </>
    )
}

export default MusicInfoPage;