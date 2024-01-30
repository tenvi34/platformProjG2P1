import React from 'react';
import { Stack, Card, Box } from '@mui/material';
import { useNavigate } from "react-router-dom";
import LinkIcon from '@mui/icons-material/Link';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';

import IconButton from '../../../_global/components/button/IconButton';
import BoldText from '../../../_global/components/text/BoldText';

const PlayListInfo = ({playListId, sx, ...props}) => {
    const navigate = useNavigate();

    return (
        <Card sx={{padding: 1.5, ...sx}} {...props}>
            <Stack>
                <Box sx={{cursor: "pointer"}} onClick={()=>{navigate(`/playList/info/${playListId}`)}}>
                    <Box sx={{float: "left"}}>
                        <BoldText sx={{color: "lightgray", fontSize: "10px"}}>총 5개 음악</BoldText>
                        <BoldText>Test Music Play list</BoldText>
                    </Box>

                    <BoldText sx={{float: "right", color: "lightgray", fontSize: "10px"}}>2024-01-30</BoldText>
                </Box>

                <Box>
                    <IconButton sx={{float: "left"}} buttonSx={{width: "35px", minWidth: "35px", height: "18px", minHeight: "18px"}} textSx={{fontSize: "12px", paddingBottom: "8px"}}>
                        <LinkIcon sx={{width: "15px", height: "15px", float: "left"}}/>
                    </IconButton>

                    <IconButton sx={{float: "right", marginLeft: "5px"}} buttonSx={{width: "35px", minWidth: "35px", height: "18px", minHeight: "18px"}} textSx={{fontSize: "12px", paddingBottom: "8px"}}>
                        <DeleteIcon sx={{width: "15px", height: "15px", float: "left"}}/>
                    </IconButton>
                    <IconButton sx={{float: "right", marginLeft: "5px"}} buttonSx={{width: "35px", minWidth: "35px", height: "18px", minHeight: "18px"}} textSx={{fontSize: "12px", paddingBottom: "8px"}}>
                        <EditIcon sx={{width: "15px", height: "15px", float: "left"}}/>
                    </IconButton>
                </Box>
            </Stack>
        </Card>
    )
}

export default PlayListInfo;