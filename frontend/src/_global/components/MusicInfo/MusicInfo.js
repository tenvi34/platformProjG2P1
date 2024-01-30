import React, { useState, useEffect, useContext } from 'react';
import { Stack, Card, Box } from '@mui/material';
import FavoriteIcon from '@mui/icons-material/Favorite';
import DeleteIcon from '@mui/icons-material/Delete';
import LinkIcon from '@mui/icons-material/Link';

import MusicPlayer from './MusicPlayer';
import AddToPlayListButton from './AddToPlayListButton';
import MusicUpdateButton from './MusicUpdateButton';

import { AlertPopupContext } from '../../provider/alertPopUp/AlertPopUpContext';
import { JwtTokenContext } from '../../provider/jwtToken/JwtTokenContext';

import IconButton from '../button/IconButton';
import ShareLinkButton from '../button/ShareLinkButton';
import YesNoButton from '../button/YesNoButton';

import MusicProxy from '../../proxy/MusicProxy';
import FileProxy from '../../proxy/FileProxy';
import PlayListMusicProxy from '../../proxy/PlayListMusicProxy';
import TimeTool from '../../tool/TimeTool';

const MusicInfo = ({musicId, sx, ...props}) => {
    const {addAlertPopUp} = useContext(AlertPopupContext);
    const {jwtTokenState} = useContext(JwtTokenContext);
    const [musicInfo, setMusicInfo] = useState(null)

    useEffect(() => {
        (async () => {
            try {

                let musicInfoToUpdate = await MusicProxy.searchMusicOneByMusicId(musicId, jwtTokenState)
                let fileInfoToUpdate = await FileProxy.searchFileOneByFileId(musicInfoToUpdate.fileId, jwtTokenState)
                
                musicInfoToUpdate.filePath = `http://${window.location.host}/api/file/${fileInfoToUpdate.path}`
                musicInfoToUpdate.prettyCreatedDate = TimeTool.prettyDateString(musicInfoToUpdate.createdDate)

                setMusicInfo(musicInfoToUpdate)

            } catch (error) {
                addAlertPopUp("단일 음악 정보를 가져오는 과정에서 오류가 발생했습니다!", "error");
                console.error("단일 음악 정보를 가져오는 과정에서 오류가 발생했습니다!", error);
            }
        })()
    }, [musicId, addAlertPopUp, jwtTokenState])


    const onClickLikeMusicButton = async () => {
        try {

            await MusicProxy.likeMusic(musicId, jwtTokenState)
            addAlertPopUp("좋아요를 눌렀습니다.", "success");

        } catch (error) {
            addAlertPopUp("좋아요를 누르는 과정에서 오류가 발생했습니다!", "error");
            console.error("좋아요를 누르는 과정에서 오류가 발생했습니다!", error);
        }
    }

    const onClickAddToPlayListButton = async (title, playListId) => {
        try {

            await PlayListMusicProxy.createPlayListMusic(playListId, musicId, title, jwtTokenState);
            addAlertPopUp("플레이 리스트에 추가했습니다.", "success");

        } catch (error) {
            addAlertPopUp("플레이 리스트에 추가하는 과정에서 오류가 발생했습니다!", "error");
            console.error("플레이 리스트에 추가하는 과정에서 오류가 발생했습니다!", error);
        }
    }

    const onClickMusicUpdateButton = async (title, dataUrl) => {
        try {

            if((title.length > 0) && (musicInfo.title !== title))
                await MusicProxy.updateMusicInfo(musicId, title, jwtTokenState)

            if(dataUrl.length > 0)
                await MusicProxy.updateMusicFile(musicId, dataUrl, jwtTokenState)

            addAlertPopUp("음악 정보를 업데이트했습니다.", "success");

        } catch (error) {
            addAlertPopUp("음악 정보를 업데이트하는 과정에서 오류가 발생했습니다!", "error");
            console.error("음악 정보를 업데이트하는 과정에서 오류가 발생했습니다!", error);
        }
    }


    const onClickDeleteMusic = async () => {
        try {

            await MusicProxy.deleteMusicByMusicId(musicId, jwtTokenState)
            addAlertPopUp("음악 정보를 삭제했습니다.", "success");

        } catch (error) {
            addAlertPopUp("음악 정보를 삭제하는 과정에서 오류가 발생했습니다!", "error");
            console.error("음악 정보를 삭제하는 과정에서 오류가 발생했습니다!", error);
        }
    }


    return (
        <Card variant="outlined" sx={{padding: 1.5, height: 87, ...sx}} {...props}>
            <Stack>
                {
                    (musicInfo) ? (
                    <>
                    <MusicPlayer
                        musicUrl={musicInfo.filePath}
                        musicSecTime={musicInfo.totalSeconds}
                        musicTitle={musicInfo.title}
                        musicCreater={musicInfo.creater}
                        musicCreatedDate={musicInfo.prettyCreatedDate}
                        navigateUrl={`/music/info/${musicId}`}
                    />

                    <Box>
                        <IconButton onClick={onClickLikeMusicButton} sx={{float: "left"}} buttonSx={{width: "40px", minWidth: "35px", height: "18px", minHeight: "18px"}} textSx={{fontSize: "10px", paddingBottom: "5px"}}>
                            <FavoriteIcon sx={{width: "13px", height: "13px", float: "left"}}/>{musicInfo.likes}
                        </IconButton>
                        <ShareLinkButton title="음악 공유 링크" shareUrl={`http://${window.location.host}/music/info/${musicId}`}>
                            <IconButton sx={{float: "left", marginLeft: "5px"}} buttonSx={{width: "35px", minWidth: "35px", height: "18px", minHeight: "18px"}} textSx={{fontSize: "12px", paddingBottom: "8px"}}>
                                <LinkIcon sx={{width: "15px", height: "15px", float: "left"}}/>
                            </IconButton>
                        </ShareLinkButton>
                        <AddToPlayListButton onClickSaveButton={onClickAddToPlayListButton} defaultTitleValue={musicInfo.title}/>
                        
                        <YesNoButton onClickYes={onClickDeleteMusic} title="정말로 삭제하시겠습니까?">
                            <IconButton sx={{float: "right", marginLeft: "5px"}} buttonSx={{width: "35px", minWidth: "35px", height: "18px", minHeight: "18px"}} textSx={{fontSize: "12px", paddingBottom: "8px"}}>
                                <DeleteIcon sx={{width: "15px", height: "15px", float: "left"}}/>
                            </IconButton>
                        </YesNoButton>
                        <MusicUpdateButton defaultTitle={musicInfo.title} onClickSaveButton={onClickMusicUpdateButton}/>
                    </Box>
                    </>) : null
                }
            </Stack>
        </Card>
    )
}

export default MusicInfo;