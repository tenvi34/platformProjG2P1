import React from 'react';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';

import TopAppBar from '../../../_global/components/TopAppBar';
import IconNavigationButton from '../../../_global/components/button/IconNavigationButton';
import UserManageButton from '../../../_global/components/button/UserManageButton';

import AddPlayListButton from './AddPlayListButton';

const PlayListListTopAppBar = () => {

    const onClickAddPlayListButton = (title) => {
        alert(title);
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