import React, { useContext } from 'react';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';

import { AlertPopupContext } from '../../../_global/provider/alertPopUp/AlertPopUpContext';
import { JwtTokenContext } from '../../../_global/provider/jwtToken/JwtTokenContext';

import TopAppBar from '../../../_global/components/TopAppBar';
import IconNavigationButton from '../../../_global/components/button/IconNavigationButton';
import UserManageButton from '../../../_global/components/button/UserManageButton';

import AddPlayListButton from './AddPlayListButton';

import PlayListProxy from '../../../_global/proxy/PlayListProxy';

const PlayListListTopAppBar = () => {
    const {addAlertPopUp} = useContext(AlertPopupContext);
    const {jwtTokenState} = useContext(JwtTokenContext);


    const onClickAddPlayListButton = async (title) => {
        try {

            await PlayListProxy.createPlayList(title, jwtTokenState)
            addAlertPopUp("플레이 리스트를 생성했습니다.", "success");

        } catch (error) {
            addAlertPopUp("플레이 리스트를 생성하는 과정에서 오류가 발생했습니다!", "error");
            console.error("플레이 리스트를 생성하는 과정에서 오류가 발생했습니다!", error);
        }
    }

    return (
        <TopAppBar title="플레이 리스트 목록">
            <AddPlayListButton onClickSaveButton={onClickAddPlayListButton}/>
        
            <UserManageButton sx={{marginRight: "5px"}}/>
            <IconNavigationButton url="/music/list">
                <ArrowBackIcon sx={{fontSize: 40}}/>
            </IconNavigationButton>
        </TopAppBar>
    )
}

export default PlayListListTopAppBar;