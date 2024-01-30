import React, { useContext } from 'react';
import LogoutIcon from '@mui/icons-material/Logout';
import PlaylistPlayIcon from '@mui/icons-material/PlaylistPlay';

import { JwtTokenContext } from "../../../_global/provider/jwtToken/JwtTokenContext";

import AddMusicButton from './AddMusicButton';

import TopAppBar from '../../../_global/components/TopAppBar';
import IconButton from '../../../_global/components/button/IconButton';
import IconNavigationButton from '../../../_global/components/button/IconNavigationButton';
import UserManageButton from '../../../_global/components/button/UserManageButton';

const PlayListTopAppBar = () => {
    const {deleteTokenValue} = useContext(JwtTokenContext);

    const onClickMusicCreateButton = (title, creater, dataUrl) => {
        alert(title + " / " + creater + " / " + dataUrl.length)
    }

    return (
        <TopAppBar title="음악 목록">  
            <AddMusicButton onClickSaveButton={onClickMusicCreateButton} sx={{marginRight: "5px"}}/>

            <IconNavigationButton sx={{marginRight: "5px"}} url="/playList/list">
                <PlaylistPlayIcon sx={{fontSize: 35, paddingTop: 0.3, paddingLeft: 0.3}}/>
            </IconNavigationButton>
            <UserManageButton sx={{marginRight: "5px"}}/>
            <IconButton onClick={() => {deleteTokenValue();}}>
                <LogoutIcon sx={{fontSize: 35, paddingTop: 0.3, paddingLeft: 0.3}}/>
            </IconButton>
        </TopAppBar>
    )
}

export default PlayListTopAppBar;