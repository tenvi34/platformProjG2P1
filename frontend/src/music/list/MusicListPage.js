import React, { useContext, useEffect, useState } from 'react';
import { useNavigate } from "react-router-dom";
import { Stack } from '@mui/material';

import { AlertPopupContext } from '../../_global/provider/alertPopUp/AlertPopUpContext';
import { JwtTokenContext } from "../../_global/provider/jwtToken/JwtTokenContext";

import MusicSearchForm from './components/MusicSearchForm';
import PlayListTopAppBar from './components/PlayListTopAppBar';

import MusicInfo from '../../_global/components/MusicInfo/MusicInfo';

import MusicProxy from '../../_global/proxy/MusicProxy';

const MusicListPage = () => {
    const {addAlertPopUp} = useContext(AlertPopupContext);
    const {jwtTokenState} = useContext(JwtTokenContext);
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


    const [musicInfos, setMusicInfos] = useState([])

    useEffect(() => {
        (async () => {
            try {

                setMusicInfos(await MusicProxy.searchMusicAll(jwtTokenState));

            } catch (error) {
                addAlertPopUp("음악 정보들을 가져오는 과정에서 오류가 발생했습니다!", "error");
                console.error("음악 정보들을 가져오는 과정에서 오류가 발생했습니다!", error);
            }
        })()
    }, [addAlertPopUp, jwtTokenState])


    const onSubmitSearch = async (searchType, searchText) => {
        if(searchText.length === 0) {
            setMusicInfos(await MusicProxy.searchMusicAll(jwtTokenState));
            return;
        }
        
        switch(searchType) {
            case "musicTitle":
                setMusicInfos(await MusicProxy.searchMusicAllByTitle(searchText, jwtTokenState));
                break;
            case "musicCreater":
                setMusicInfos(await MusicProxy.searchMusicAllByCreater(searchText, jwtTokenState));
                break;
            default:
                break;
        }
    }

    
    return (
        <>
            <PlayListTopAppBar/>

            <Stack sx={{marginTop: 3, alignItems: "center"}}>
                <MusicSearchForm onSubmit={onSubmitSearch}/>

                <Stack spacing={1} sx={{width: "100%", marginTop: "5px"}}>
                    {
                        musicInfos.map((musicInfo)=>(
                            <MusicInfo key={musicInfo.musicId} sx={{width: "95%"}} musicId={musicInfo.musicId}/>
                        ))
                    }
                </Stack>
            </Stack>
        </>
    )
}

export default MusicListPage;