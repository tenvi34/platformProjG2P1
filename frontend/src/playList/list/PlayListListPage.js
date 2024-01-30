import React, { useContext, useEffect } from 'react';
import { Stack } from '@mui/material';
import { useNavigate } from "react-router-dom";

import { JwtTokenContext } from "../../_global/provider/jwtToken/JwtTokenContext";

import PlayListInfo from '../__global/components/playListInfo/PlayListInfo';

import PlayListListTopAppBar from './components/PlayListListTopAppBar';

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
            <PlayListListTopAppBar/>

            <Stack sx={{marginTop: "5px"}} spacing={1}>
                <PlayListInfo playListId={1}/>
            </Stack>
        </>
    )
}

export default PlayListListPage;