import React, { useContext, useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import { Stack } from '@mui/material';

import { JwtTokenContext } from "../../_global/provider/jwtToken/JwtTokenContext";

import MusicSearchForm from './components/MusicSearchForm';
import PlayListTopAppBar from './components/PlayListTopAppBar';

import MusicInfo from '../../_global/components/MusicInfo/MusicInfo';

const MusicListPage = () => {
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


    const onSubmitSearch = (searchText, searchType) => {
        console.log(searchText, searchType)
    }

    
    return (
        <>
            <PlayListTopAppBar/>

            <Stack sx={{marginTop: 3, alignItems: "center"}}>
                <MusicSearchForm onSubmit={onSubmitSearch}/>

                <Stack spacing={1} sx={{width: "100%", marginTop: "5px"}}>
                    <MusicInfo sx={{width: "95%"}} musicId={1}/>
                </Stack>
            </Stack>
        </>
    )
}

export default MusicListPage;