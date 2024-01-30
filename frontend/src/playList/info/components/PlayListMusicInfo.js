import React from 'react';
import { Card, Box } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';

import BoldText from '../../../_global/components/text/BoldText';
import IconButton from '../../../_global/components/button/IconButton';

const PlayListMusicInfo = ({playListMusicId, onClickTitle, sx, ...props}) => {

    return (
        <Card sx={{padding: 1.5, display: "flex", ...sx}} {...props} variant="outlined">
            <Box onClick={() => {onClickTitle(playListMusicId)}} sx={{cursor: "pointer", width: "100%"}}>
                <BoldText sx={{float: "left", marginTop: "3px"}}>Test Music Title</BoldText>
            </Box>

            <Box sx={{width: "200px"}}>
                <IconButton sx={{float: "right", marginLeft: "5px"}} buttonSx={{width: "35px", minWidth: "35px", height: "18px", minHeight: "18px"}} textSx={{fontSize: "12px", paddingBottom: "8px"}}>
                    <DeleteIcon sx={{width: "15px", height: "15px", float: "left"}}/>
                </IconButton>
                <IconButton sx={{float: "right", marginLeft: "5px"}} buttonSx={{width: "35px", minWidth: "35px", height: "18px", minHeight: "18px"}} textSx={{fontSize: "12px", paddingBottom: "8px"}}>
                    <EditIcon sx={{width: "15px", height: "15px", float: "left"}}/>
                </IconButton>
                <BoldText sx={{float: "right", color: "lightgray", fontSize: "10px", marginTop: "7px"}}>2024-01-30</BoldText>
            </Box>
        </Card>
    )
}

export default PlayListMusicInfo;