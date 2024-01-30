import React, { useContext, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { useNavigate } from "react-router-dom";
import { Stack, Divider } from '@mui/material';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';

import { AlertPopupContext } from '../../_global/provider/alertPopUp/AlertPopUpContext';
import { JwtTokenContext } from "../../_global/provider/jwtToken/JwtTokenContext";

import PlayListMusicInfo from './components/PlayListMusicInfo';
import PlayListInfo from '../__global/components/playListInfo/PlayListInfo';

import TopAppBar from '../../_global/components/TopAppBar';
import IconNavigationButton from '../../_global/components/button/IconNavigationButton';
import UserManageButton from '../../_global/components/button/UserManageButton';
import MusicInfo from '../../_global/components/MusicInfo/MusicInfo';

import PlayListProxy from '../../_global/proxy/PlayListProxy';

import TimeTool from '../../_global/tool/TimeTool';

const PlayListInfoPage = () => {
    const {playListId} = useParams();
    const {addAlertPopUp} = useContext(AlertPopupContext);
    const {jwtTokenState} = useContext(JwtTokenContext);
    const navigate = useNavigate();

    useEffect(() => {
        if(jwtTokenState.jwtToken === null) {
            navigate("/user/signIn");
        }
    }, [jwtTokenState.jwtToken, navigate])


    const [playListInfo, setPlayListInfo] = useState(null)
    useEffect(() => {
        (async () => {
            try {

                setPlayListInfo(await PlayListProxy.searchPlayListOneByPlayListId(playListId, jwtTokenState))

            } catch (error) {
                addAlertPopUp("플레이 리스트 정보를 가져오는 과정에서 오류가 발생했습니다!", "error");
                console.error("플레이 리스트 정보를 가져오는 과정에서 오류가 발생했습니다!", error);
            }
        })()
    }, [playListId, addAlertPopUp, jwtTokenState])


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

                {
                    (playListInfo) ? (<>
                        <PlayListInfo sx={{marginTop: "5px"}}
                            playListId={playListInfo.playListId} playListTitle={playListInfo.title}
                            playListMusicCount={playListInfo.musicCount} playListCreatedDate={TimeTool.prettyDateString(playListInfo.createdDate)}
                            key={playListInfo.playListId}
                        />   
                    </>) : null
                }
                <Divider sx={{marginTop: "5px"}}/>

                <Stack spacing={1} sx={{marginTop: "5px", width: "100%"}}>
                    <PlayListMusicInfo playListMusicId={1} onClickTitle={onClickPlayListMusicTitle}/>
                </Stack>
            </Stack>
        </>
    )
}

export default PlayListInfoPage;