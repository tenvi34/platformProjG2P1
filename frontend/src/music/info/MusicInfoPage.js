import React, { useContext, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { useNavigate } from "react-router-dom";
import { Stack, Divider, Box, Paper, InputBase } from '@mui/material';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import ChatBubbleIcon from '@mui/icons-material/ChatBubble';
import SendIcon from '@mui/icons-material/Send';

import { AlertPopupContext } from '../../_global/provider/alertPopUp/AlertPopUpContext';
import { JwtTokenContext } from "../../_global/provider/jwtToken/JwtTokenContext";

import CommentInfo from './components/CommentInfo';

import TopAppBar from '../../_global/components/TopAppBar';
import IconNavigationButton from '../../_global/components/button/IconNavigationButton';
import UserManageButton from '../../_global/components/button/UserManageButton';
import MusicInfo from '../../_global/components/MusicInfo/MusicInfo';
import BoldText from '../../_global/components/text/BoldText';
import IconButton from '../../_global/components/button/IconButton';

import CommentProxy from '../../_global/proxy/CommentProxy';

const MusicInfoPage = () => {
    const {musicId} = useParams();
    const {addAlertPopUp} = useContext(AlertPopupContext);
    const {jwtTokenState} = useContext(JwtTokenContext);
    const navigate = useNavigate();

    useEffect(() => {
        if(jwtTokenState.jwtToken === null) {
            navigate("/user/signIn");
        }
    }, [jwtTokenState.jwtToken, navigate])



    const [commentToSend, setCommentToSend] = useState("")
    
    const onClickCreateComment = async () => {
        try {

            await CommentProxy.createComment(musicId, commentToSend, jwtTokenState);
            addAlertPopUp("댓글을 생성했습니다.", "success");
            setCommentToSend("");

        } catch (error) {
            addAlertPopUp("댓글을 생성하는 과정에서 오류가 발생했습니다!", "error");
            console.error("댓글을 생성하는 가져오는 과정에서 오류가 발생했습니다!", error);
        }
    }


    return (
        <>
            <TopAppBar title="음악 정보">  
                <UserManageButton sx={{marginRight: "5px"}}/>
                <IconNavigationButton url="/music/list">
                    <ArrowBackIcon sx={{fontSize: 40}}/>
                </IconNavigationButton>
            </TopAppBar>

            <Stack>
                <MusicInfo sx={{width: "95%", marginTop: "5px"}} musicId={musicId}/>
                <Divider sx={{marginTop: "5px"}}/>
                
                <Box sx={{marginTop: "10px"}}>
                    <ChatBubbleIcon sx={{color: "gray", float: "left"}}/>
                    <BoldText sx={{color: "gray", float: "left", marginLeft: "5px", paddingTop: "3px"}}>댓글: 5개</BoldText>
                </Box>


                <Paper sx={{marginTop: "3px", width: "100%", display: "flex"}}>
                    <InputBase value={commentToSend} onChange={(e)=>{setCommentToSend(e.target.value)}}
                               multiline maxRows={2} sx={{float:"left", width: "100%", padding: "5px"}}/>
                    <IconButton onClick={onClickCreateComment} buttonSx={{float: "right", borderRadius: "0px 5px 5px 0px", width: "50px", minWidth: "50px", height: "56px", minHeight: "56px"}}>
                        <SendIcon sx={{fontSize: 35, paddingTop: 0.3, paddingLeft: 0.3}}/>
                    </IconButton>
                </Paper>


                <Stack spacing={1} sx={{marginTop: "10px"}} divider={<Divider flexItem />}>
                    <CommentInfo/>
                </Stack>
            </Stack>
        </>
    )
}

export default MusicInfoPage;