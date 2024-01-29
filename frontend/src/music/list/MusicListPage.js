import React, { useContext, useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import LogoutIcon from '@mui/icons-material/Logout';

import { JwtTokenContext } from "../../_global/provider/jwtToken/JwtTokenContext";

import TopAppBar from '../../_global/components/TopAppBar';
import IconButton from '../../_global/components/button/IconButton';

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

    return (
        <>
            <TopAppBar title="음악 목록">           
                <IconButton sx={{marginRight: 1}} onClick={() => {deleteTokenValue();}}>
                    <LogoutIcon sx={{fontSize: 35, paddingTop: 0.3, paddingLeft: 0.3}}/>
                </IconButton>
            </TopAppBar>
        </>
    )
}

export default MusicListPage;