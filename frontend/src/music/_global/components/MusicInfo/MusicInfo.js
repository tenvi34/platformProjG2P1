import React from 'react';
import { Stack, Card, Box } from '@mui/material';
import FavoriteIcon from '@mui/icons-material/Favorite';
import LinkIcon from '@mui/icons-material/Link';
import PlaylistAddIcon from '@mui/icons-material/PlaylistAdd';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';

import MusicPlayer from './MusicPlayer';

import IconButton from '../../../../_global/components/button/IconButton';

const MusicInfo = ({musicId, sx, ...props}) => {

    return (
        <Card variant="outlined" sx={{padding: 1.5, height: 87, ...sx}} {...props}>
            <Stack>
                <MusicPlayer
                    musicUrl={"http://localhost:8088/api/file/5510a8d0-1b16-4ce6-8622-d1d39f0c3c36.mp3"}
                    musicSecTime={3}
                    musicTitle={"Test Music Title"}
                    musicCreater={"Test Music Creater"}
                    musicCreatedDate={"2022-01-01"}
                />

                <Box>
                    <IconButton sx={{float: "left"}} buttonSx={{width: "35px", minWidth: "35px", height: "18px", minHeight: "18px"}} textSx={{fontSize: "10px", paddingBottom: "5px"}}>
                        <FavoriteIcon sx={{width: "13px", height: "13px", float: "left"}}/>1
                    </IconButton>
                    <IconButton sx={{float: "left", marginLeft: "5px"}} buttonSx={{width: "35px", minWidth: "35px", height: "18px", minHeight: "18px"}} textSx={{fontSize: "12px", paddingBottom: "8px"}}>
                        <LinkIcon sx={{width: "15px", height: "15px", float: "left"}}/>
                    </IconButton>
                    <IconButton sx={{float: "left", marginLeft: "5px"}} buttonSx={{width: "35px", minWidth: "35px", height: "18px", minHeight: "18px"}} textSx={{fontSize: "12px", paddingBottom: "8px"}}>
                        <PlaylistAddIcon sx={{width: "15px", height: "15px", float: "left"}}/>
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

export default MusicInfo;