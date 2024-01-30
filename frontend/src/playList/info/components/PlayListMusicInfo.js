import React from 'react';
import { Card, Box } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';

import BoldText from '../../../_global/components/text/BoldText';
import IconButton from '../../../_global/components/button/IconButton';
import YesNoButton from '../../../_global/components/button/YesNoButton';

import UpdatePlayListMusicButton from './UpdatePlayListMusicButton';

const PlayListMusicInfo = ({playListMusicId, playListMusicTitle, playListMusicCreatedDate, onClickTitle, sx, ...props}) => {

    const onClickUpdatePlayListMusicButton = (title) => {
        alert(title)
    }

    return (
        <Card sx={{padding: 1.5, display: "flex", ...sx}} {...props} variant="outlined">
            <Box onClick={() => {onClickTitle(playListMusicId)}} sx={{cursor: "pointer", width: "100%"}}>
                <BoldText sx={{float: "left", marginTop: "3px"}}>{playListMusicTitle}</BoldText>
            </Box>

            <Box sx={{width: "200px"}}>
                <YesNoButton onClickYes={()=>{alert("YES")}} title="정말로 삭제하시겠습니까?">
                    <IconButton sx={{float: "right", marginLeft: "5px"}} buttonSx={{width: "35px", minWidth: "35px", height: "18px", minHeight: "18px"}} textSx={{fontSize: "12px", paddingBottom: "8px"}}>
                        <DeleteIcon sx={{width: "15px", height: "15px", float: "left"}}/>
                    </IconButton>
                </YesNoButton>
                <UpdatePlayListMusicButton onClickSaveButton={onClickUpdatePlayListMusicButton}/>

                <BoldText sx={{float: "right", color: "lightgray", fontSize: "10px", marginTop: "7px"}}>{playListMusicCreatedDate}</BoldText>
            </Box>
        </Card>
    )
}

export default PlayListMusicInfo;