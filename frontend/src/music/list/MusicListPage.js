import React, { useContext, useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import { Stack } from '@mui/material';
import LogoutIcon from '@mui/icons-material/Logout';
import PlaylistPlayIcon from '@mui/icons-material/PlaylistPlay';
import AddIcon from '@mui/icons-material/Add';

import { JwtTokenContext } from "../../_global/provider/jwtToken/JwtTokenContext";

import MusicSearchForm from './MusicSearchForm';

import TopAppBar from '../../_global/components/TopAppBar';
import IconButton from '../../_global/components/button/IconButton';
import IconNavigationButton from '../../_global/components/button/IconNavigationButton';
import UserManageButton from '../../_global/components/button/UserManageButton';
import MusicInfo from '../../_global/components/MusicInfo/MusicInfo';

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


    const onSubmitSearch = (searchText, searchType) => {
        console.log(searchText, searchType)
    }

    return (
        <>
            <TopAppBar title="음악 목록">  
                <IconButton sx={{marginRight: "5px"}} onClick={() => {}}>
                    <AddIcon sx={{fontSize: 35, paddingTop: 0.3, paddingLeft: 0.3}}/>
                </IconButton>

                <IconNavigationButton sx={{marginRight: "5px"}} url="/playList/list">
                    <PlaylistPlayIcon sx={{fontSize: 35, paddingTop: 0.3, paddingLeft: 0.3}}/>
                </IconNavigationButton>
                <UserManageButton sx={{marginRight: "5px"}}/>
                <IconButton onClick={() => {deleteTokenValue();}}>
                    <LogoutIcon sx={{fontSize: 35, paddingTop: 0.3, paddingLeft: 0.3}}/>
                </IconButton>
            </TopAppBar>
            
            <Stack sx={{marginTop: 3, alignItems: "center"}}>
                <MusicSearchForm onSubmit={onSubmitSearch}/>

                <Stack sx={{width: "100%"}}>
                    <MusicInfo sx={{width: "95.5%", marginTop: "5px"}} musicId={1}/>
                </Stack>
            </Stack>
        </>
    )
}

export default MusicListPage;