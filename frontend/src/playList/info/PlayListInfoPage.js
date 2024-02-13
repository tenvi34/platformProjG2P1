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
import PlayListMusicProxy from '../../_global/proxy/PlayListMusicProxy';

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
    
    const [playListMusicInfos, setPlayListMusicInfos] = useState([])
    useEffect(() => {
        (async () => {
            try {

                setPlayListMusicInfos((await PlayListMusicProxy.searchPlayListMusicAllByPlayListId(playListId, jwtTokenState)).filter((playListMusicInfo)=>{
                    return playListMusicInfo.status !== "PlayListMusicDeleted"
                }))

            } catch (error) {
                addAlertPopUp("플레이 리스트 음악 정보들을 가져오는 과정에서 오류가 발생했습니다!", "error");
                console.error("플레이 리스트 음악 정보들을 가져오는 과정에서 오류가 발생했습니다!", error);
            }
        })()
    }, [playListId, addAlertPopUp, jwtTokenState])

    const [currentMusicId, setCurrentMusicId] = useState(null);
    const onClickPlayListMusicTitle = (musicId) => {
        setCurrentMusicId(musicId)
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
                {
                    (currentMusicId) ? (<>
                        <MusicInfo sx={{width: "95%", marginTop: "5px"}} musicId={currentMusicId}/>
                    </>) : null
                }
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
                {
                    playListMusicInfos.map((playListMusicInfo) => (
                        <PlayListMusicInfo 
                            key={playListMusicInfo.playListMusicId}
                            playListMusicId={playListMusicInfo.playListMusicId}
                            playListMusicMusicId={playListMusicInfo.musicId}
                            playListMusicTitle={playListMusicInfo.title} 
                            playListMusicCreatedDate={TimeTool.prettyDateString(playListMusicInfo.createdDate)}

                            onClickTitle={onClickPlayListMusicTitle}/>
                    ))
                }
                </Stack>
            </Stack>
        </>
    )
}

export default PlayListInfoPage;