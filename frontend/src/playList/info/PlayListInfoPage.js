import React, { useContext, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { useNavigate } from "react-router-dom";
import { Stack, Divider } from '@mui/material';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import { JwtTokenContext } from "../../_global/provider/jwtToken/JwtTokenContext";

import PlayListInfo from '../__global/components/PlayListInfo';

import TopAppBar from '../../_global/components/TopAppBar';
import IconNavigationButton from '../../_global/components/button/IconNavigationButton';
import UserManageButton from '../../_global/components/button/UserManageButton';
import MusicInfo from '../../_global/components/MusicInfo/MusicInfo';
import PlayListMusicInfo from './PlayListMusicInfo';

const PlayListInfoPage = () => {
    const {playListId} = useParams();
    const {jwtTokenState} = useContext(JwtTokenContext);
    const navigate = useNavigate();

    useEffect(() => {
        if(jwtTokenState.jwtToken === null) {
            navigate("/user/signIn");
        }
    }, [jwtTokenState.jwtToken, navigate])

    console.log(playListId)

    const onClickPlayListMusicTitle = (playListMusicId) => {
        alert(playListMusicId)
    }

    return (
        <>
            <TopAppBar title="플레이 리스트 정보">  
                <UserManageButton sx={{marginRight: "5px"}}/>
                <IconNavigationButton url="/playList/list">
                    <ArrowBackIcon sx={{fontSize: 40}}/>
                </IconNavigationButton>
            </TopAppBar>

            <Stack>
                <MusicInfo sx={{width: "95%", marginTop: "5px"}} musicId={1}/>
                <Divider sx={{marginTop: "5px"}}/>

                <PlayListInfo sx={{marginTop: "5px"}} playListId={1}/>
                <Divider sx={{marginTop: "5px"}}/>

                <Stack spacing={1} sx={{marginTop: "5px", width: "100%"}}>
                    <PlayListMusicInfo playListMusicId={1} onClickTitle={onClickPlayListMusicTitle}/>
                </Stack>
            </Stack>
        </>
    )
}

export default PlayListInfoPage;