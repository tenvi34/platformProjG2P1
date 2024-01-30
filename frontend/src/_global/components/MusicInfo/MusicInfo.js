import React from 'react';
import { Stack, Card, Box } from '@mui/material';
import FavoriteIcon from '@mui/icons-material/Favorite';
import DeleteIcon from '@mui/icons-material/Delete';

import MusicPlayer from './MusicPlayer';
import AddToPlayListButton from './AddToPlayListButton';
import MusicUpdateButton from './MusicUpdateButton';

import IconButton from '../button/IconButton';
import ShareLinkButton from '../button/ShareLinkButton';
import YesNoButton from '../button/YesNoButton';

const MusicInfo = ({musicId, sx, ...props}) => {

    const onClickAddToPlayListButton = (playListId) => {
        alert(playListId);
    }


    const onClickMusicUpdateButton = (title, dataUrl) => {
        alert(title + " / " + dataUrl.length)
    }


    return (
        <Card variant="outlined" sx={{padding: 1.5, height: 87, ...sx}} {...props}>
            <Stack>
                <MusicPlayer
                    musicUrl={"http://localhost:8088/api/file/3dcbc900-cf8f-4a9a-a2da-a87ad091e479.mp3"}
                    musicSecTime={3}
                    musicTitle={"Test Music Title"}
                    musicCreater={"Test Music Creater"}
                    musicCreatedDate={"2022-01-01"}
                    navigateUrl={`/music/info/${musicId}`}
                />

                <Box>
                    <IconButton sx={{float: "left"}} buttonSx={{width: "35px", minWidth: "35px", height: "18px", minHeight: "18px"}} textSx={{fontSize: "10px", paddingBottom: "5px"}}>
                        <FavoriteIcon sx={{width: "13px", height: "13px", float: "left"}}/>1
                    </IconButton>
                    <ShareLinkButton title="음악 공유 링크" shareUrl="http://localhost:8088/music/info/1"/>
                    <AddToPlayListButton onClickSaveButton={onClickAddToPlayListButton}/>
                    
                    <YesNoButton onClickYes={()=>{alert("YES")}} title="정말로 삭제하시겠습니까?">
                        <IconButton sx={{float: "right", marginLeft: "5px"}} buttonSx={{width: "35px", minWidth: "35px", height: "18px", minHeight: "18px"}} textSx={{fontSize: "12px", paddingBottom: "8px"}}>
                            <DeleteIcon sx={{width: "15px", height: "15px", float: "left"}}/>
                        </IconButton>
                    </YesNoButton>
                    <MusicUpdateButton onClickSaveButton={onClickMusicUpdateButton}/>
                </Box>
            </Stack>
        </Card>
    )
}

export default MusicInfo;