import React from 'react';
import { Stack, Card, Box } from '@mui/material';
import PlayArrowIcon from '@mui/icons-material/PlayArrow';
import FavoriteIcon from '@mui/icons-material/Favorite';
import LinkIcon from '@mui/icons-material/Link';
import PlaylistAddIcon from '@mui/icons-material/PlaylistAdd';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';

import IconButton from '../../../_global/components/button/IconButton';
import BoldText from '../../../_global/components/text/BoldText';
import StyledSlider from '../../../_global/components/slider/StyledSlider';

import ReactAudioPlayer from 'react-audio-player';

const MusicPlayer = ({musicId, sx, ...props}) => {
    // audio source
    console.log(musicId)

    const musicUrl = 'http://localhost:8088/api/file/5510a8d0-1b16-4ce6-8622-d1d39f0c3c36.mp3';

    return (
        <Card variant="outlined" sx={{padding: 1.5, height: 87, cursor: "pointer", ...sx}} {...props}>
            <Stack>
                <Box>
                    <IconButton sx={{float: "left"}} buttonSx={{width: "35px", minWidth: "35px", height: "35px", minHeight: "35px"}} textSx={{}}>
                        <PlayArrowIcon/>
                    </IconButton>

                    <Box sx={{float: "left", marginLeft: "5px"}}>
                        <BoldText sx={{color: "lightgray", fontSize: "10px"}}>Test Author</BoldText>
                        <BoldText>Test Title</BoldText>
                    </Box>

                    <BoldText sx={{float: "right", color: "lightgray", fontSize: "10px"}}>2022-01-05</BoldText>
                </Box>

                <Box>
                    <BoldText sx={{float: "left", fontSize: "10px", paddingTop: "8px"}}>03:00</BoldText>
                    <StyledSlider sx={{float: "left", width: "85%", marginX: "8px"}} defaultValue={50}></StyledSlider>
                    <BoldText sx={{float: "left", fontSize: "10px", paddingTop: "8px"}}>05:00</BoldText>
                </Box>

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

            <ReactAudioPlayer
                src={musicUrl}
            />
        </Card>
    )
}

export default MusicPlayer;