import React, { useContext, useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import AddIcon from '@mui/icons-material/Add';

import { JwtTokenContext } from "../../_global/provider/jwtToken/JwtTokenContext";

import TopAppBar from '../../_global/components/TopAppBar';
import IconButton from '../../_global/components/button/IconButton';
import IconNavigationButton from '../../_global/components/button/IconNavigationButton';
import UserManageButton from '../../_global/components/button/UserManageButton';

const PlayListListPage = () => {
    const {jwtTokenState} = useContext(JwtTokenContext);
    const navigate = useNavigate();

    useEffect(() => {
        if(jwtTokenState.jwtToken === null) {
            navigate("/user/signIn");
        }
    }, [jwtTokenState.jwtToken, navigate])

    return (
        <>
            <TopAppBar title="플레이 리스트 목록">
                <IconButton sx={{marginRight: "5px"}} onClick={() => {}}>
                    <AddIcon sx={{fontSize: 35, paddingTop: 0.3, paddingLeft: 0.3}}/>
                </IconButton>
            
                <UserManageButton sx={{marginRight: "5px"}}/>
                <IconNavigationButton url="/music/list">
                    <ArrowBackIcon sx={{fontSize: 40}}/>
                </IconNavigationButton>
            </TopAppBar>
        </>
    )
}

export default PlayListListPage;