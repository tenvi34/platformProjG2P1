import React, { useContext } from 'react';
import LogoutIcon from '@mui/icons-material/Logout';
import PlaylistPlayIcon from '@mui/icons-material/PlaylistPlay';

import { AlertPopupContext } from '../../../_global/provider/alertPopUp/AlertPopUpContext';
import { JwtTokenContext } from "../../../_global/provider/jwtToken/JwtTokenContext";

import AddMusicButton from './AddMusicButton';

import TopAppBar from '../../../_global/components/TopAppBar';
import IconButton from '../../../_global/components/button/IconButton';
import IconNavigationButton from '../../../_global/components/button/IconNavigationButton';
import UserManageButton from '../../../_global/components/button/UserManageButton';
import YesNoButton from '../../../_global/components/button/YesNoButton';

import MusicProxy from '../../../_global/proxy/MusicProxy';

const PlayListTopAppBar = () => {
    const {addAlertPopUp} = useContext(AlertPopupContext);
    const {jwtTokenState, deleteTokenValue} = useContext(JwtTokenContext);

    const onClickMusicCreateButton = async (title, creater, dataUrl) => {
        try {

            await MusicProxy.createMusic(title, creater, dataUrl, jwtTokenState)
            addAlertPopUp("음악 정보 추가가 정상적으로 수행되었습니다.", "success");
    
        } catch(error) {
            addAlertPopUp("음악 정보 추가 도중에 오류가 발생했습니다!", "error");
            console.error("음악 정보 추가 도중에 오류가 발생했습니다!", error);
        }
    }

    return (
        <TopAppBar title="음악 목록">  
            <AddMusicButton onClickSaveButton={onClickMusicCreateButton} sx={{marginRight: "5px"}}/>

            <IconNavigationButton sx={{marginRight: "5px"}} url="/playList/list">
                <PlaylistPlayIcon sx={{fontSize: 35, paddingTop: 0.3, paddingLeft: 0.3}}/>
            </IconNavigationButton>
            <UserManageButton sx={{marginRight: "5px"}}/>
            <YesNoButton onClickYes={() => {deleteTokenValue();}} title="정말로 로그아웃 하시겠습니까?">
                <IconButton>
                    <LogoutIcon sx={{fontSize: 35, paddingTop: 0.3, paddingLeft: 0.3}}/>
                </IconButton>
            </YesNoButton>
        </TopAppBar>
    )
}

export default PlayListTopAppBar;