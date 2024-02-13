import React, { useContext, useEffect, useState } from 'react';
import { Stack } from '@mui/material';
import { useNavigate } from "react-router-dom";

import { AlertPopupContext } from '../../_global/provider/alertPopUp/AlertPopUpContext';
import { JwtTokenContext } from "../../_global/provider/jwtToken/JwtTokenContext";

import PlayListInfo from '../__global/components/playListInfo/PlayListInfo';

import PlayListListTopAppBar from './components/PlayListListTopAppBar';

import PlayListProxy from '../../_global/proxy/PlayListProxy';
import TimeTool from '../../_global/tool/TimeTool';

const PlayListListPage = () => {
    const {addAlertPopUp} = useContext(AlertPopupContext);
    const {jwtTokenState} = useContext(JwtTokenContext);
    const navigate = useNavigate();

    useEffect(() => {
        if(jwtTokenState.jwtToken === null) {
            navigate("/user/signIn");
        }
    }, [jwtTokenState.jwtToken, navigate])


    const [playListInfos, setPlayListInfos] = useState([])

    useEffect(() => {
        (async () => {
            try {
  
                setPlayListInfos((await PlayListProxy.searchPlayListAllByCreaterId(jwtTokenState.jwtToken.id, jwtTokenState)).filter((playListInfo)=>{
                  return playListInfo.status !== "PlayListDeleted"
                }));
          
            } catch (error) {
                addAlertPopUp("플레이 리스트 정보들을 불러오는 과정에서 오류가 발생했습니다!", "error");
                console.error("플레이 리스트 정보들을 불러오는 과정에서 오류가 발생했습니다!", error);
            }
        })()
    }, [addAlertPopUp, jwtTokenState])


    return (
        <>
            <PlayListListTopAppBar/>

            <Stack sx={{marginTop: "5px"}} spacing={1}>
                {
                    playListInfos.map((playListInfo) => (
                        <PlayListInfo playListId={playListInfo.playListId} playListTitle={playListInfo.title}
                            playListMusicCount={playListInfo.musicCount} playListCreatedDate={TimeTool.prettyDateString(playListInfo.createdDate)}
                            key={playListInfo.playListId}
                        />
                    ))
                }
            </Stack>
        </>
    )
}

export default PlayListListPage;