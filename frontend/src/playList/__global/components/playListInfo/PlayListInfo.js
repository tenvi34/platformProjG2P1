import React, { useContext } from 'react';
import { Stack, Card, Box } from '@mui/material';
import { useNavigate } from "react-router-dom";
import LinkIcon from '@mui/icons-material/Link';
import DeleteIcon from '@mui/icons-material/Delete';

import { AlertPopupContext } from '../../../../_global/provider/alertPopUp/AlertPopUpContext';
import { JwtTokenContext } from '../../../../_global/provider/jwtToken/JwtTokenContext';

import UpdatePlayListButton from './UpdatePlayListButton';

import IconButton from '../../../../_global/components/button/IconButton';
import BoldText from '../../../../_global/components/text/BoldText';
import YesNoButton from '../../../../_global/components/button/YesNoButton';
import ShareLinkButton from '../../../../_global/components/button/ShareLinkButton';

import PlayListProxy from '../../../../_global/proxy/PlayListProxy';

const PlayListInfo = ({playListId, playListTitle, playListMusicCount, playListCreatedDate, sx, ...props}) => {
    const {addAlertPopUp} = useContext(AlertPopupContext);
    const {jwtTokenState} = useContext(JwtTokenContext);
    const navigate = useNavigate();

    const onClickUpdatePlayListButton = async (title) => {
        try {
            
            await PlayListProxy.updatePlayList(playListId, title, jwtTokenState)
            addAlertPopUp("플레이 리스트 정보를 수정했습니다.", "success");
      
        } catch (error) {
            addAlertPopUp("플레이 리스트 정보들을 수정하는 과정에서 오류가 발생했습니다!", "error");
            console.error("플레이 리스트 정보들을 수정하는 과정에서 오류가 발생했습니다!", error);
        }
    }

    return (
        <Card sx={{padding: 1.5, ...sx}} {...props} variant="outlined">
            <Stack>
                <Box sx={{cursor: "pointer"}} onClick={()=>{navigate(`/playList/info/${playListId}`)}}>
                    <Box sx={{float: "left"}}>
                        <BoldText sx={{color: "lightgray", fontSize: "10px"}}>총 {playListMusicCount}개 음악</BoldText>
                        <BoldText>{playListTitle}</BoldText>
                    </Box>

                    <BoldText sx={{float: "right", color: "lightgray", fontSize: "10px"}}>{playListCreatedDate}</BoldText>
                </Box>

                <Box>
                    <ShareLinkButton title="음악 공유 링크" shareUrl={`http://${window.location.host}/playList/info/${playListId}`}>
                        <IconButton sx={{float: "left"}} buttonSx={{width: "35px", minWidth: "35px", height: "18px", minHeight: "18px"}} textSx={{fontSize: "12px", paddingBottom: "8px"}}>
                            <LinkIcon sx={{width: "15px", height: "15px", float: "left"}}/>
                        </IconButton>
                    </ShareLinkButton>

                    <YesNoButton onClickYes={()=>{alert("YES")}} title="정말로 삭제하시겠습니까?">
                        <IconButton sx={{float: "right", marginLeft: "5px"}} buttonSx={{width: "35px", minWidth: "35px", height: "18px", minHeight: "18px"}} textSx={{fontSize: "12px", paddingBottom: "8px"}}>
                            <DeleteIcon sx={{width: "15px", height: "15px", float: "left"}}/>
                        </IconButton>
                    </YesNoButton>
                    <UpdatePlayListButton onClickSaveButton={onClickUpdatePlayListButton} defaultTitle={playListTitle}/>
                </Box>
            </Stack>
        </Card>
    )
}

export default PlayListInfo;