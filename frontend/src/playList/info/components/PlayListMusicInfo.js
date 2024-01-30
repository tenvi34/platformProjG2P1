import React, { useContext } from 'react';
import { Card, Box } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';

import { AlertPopupContext } from '../../../_global/provider/alertPopUp/AlertPopUpContext';
import { JwtTokenContext } from '../../../_global/provider/jwtToken/JwtTokenContext';

import BoldText from '../../../_global/components/text/BoldText';
import IconButton from '../../../_global/components/button/IconButton';
import YesNoButton from '../../../_global/components/button/YesNoButton';

import UpdatePlayListMusicButton from './UpdatePlayListMusicButton';

import PlayListMusicProxy from '../../../_global/proxy/PlayListMusicProxy';

const PlayListMusicInfo = ({playListMusicId, playListMusicMusicId, playListMusicTitle, playListMusicCreatedDate, onClickTitle, sx, ...props}) => {
    const {addAlertPopUp} = useContext(AlertPopupContext);
    const {jwtTokenState} = useContext(JwtTokenContext);


    const onClickUpdatePlayListMusicButton = async (title) => {
        try {

            await PlayListMusicProxy.updatePlayListMusic(playListMusicId, title, jwtTokenState);
            addAlertPopUp("플레이 리스트 음악 정보를 수정했습니다.", "success");

        } catch (error) {
            addAlertPopUp("플레이 리스트 음악 정보를 수정하는 과정에서 오류가 발생했습니다!", "error");
            console.error("플레이 리스트 음악 정보를 수정하는 과정에서 오류가 발생했습니다!", error);
        }
    }

    const onClickPlayListMusicDeleteButton = async () => {
        try {

            await PlayListMusicProxy.deletePlayListMusicByPlayListMusicId(playListMusicId, jwtTokenState);
            addAlertPopUp("플레이 리스트 음악 정보를 삭제했습니다.", "success");

        } catch (error) {
            addAlertPopUp("플레이 리스트 음악 정보를 삭제하는 과정에서 오류가 발생했습니다!", "error");
            console.error("플레이 리스트 음악 정보를 삭제하는 과정에서 오류가 발생했습니다!", error);
        }
    }


    return (
        <Card sx={{padding: 1.5, display: "flex", ...sx}} {...props} variant="outlined">
            <Box onClick={() => {onClickTitle(playListMusicMusicId)}} sx={{cursor: "pointer", width: "100%"}}>
                <BoldText sx={{float: "left", marginTop: "3px"}}>{playListMusicTitle}</BoldText>
            </Box>

            <Box sx={{width: "200px"}}>
                <YesNoButton onClickYes={onClickPlayListMusicDeleteButton} title="정말로 삭제하시겠습니까?">
                    <IconButton sx={{float: "right", marginLeft: "5px"}} buttonSx={{width: "35px", minWidth: "35px", height: "18px", minHeight: "18px"}} textSx={{fontSize: "12px", paddingBottom: "8px"}}>
                        <DeleteIcon sx={{width: "15px", height: "15px", float: "left"}}/>
                    </IconButton>
                </YesNoButton>
                <UpdatePlayListMusicButton 
                    onClickSaveButton={onClickUpdatePlayListMusicButton}
                    defaultTitle={playListMusicTitle}
                />

                <BoldText sx={{float: "right", color: "lightgray", fontSize: "10px", marginTop: "7px"}}>{playListMusicCreatedDate}</BoldText>
            </Box>
        </Card>
    )
}

export default PlayListMusicInfo;