import React, { useRef, useState } from 'react';
import { Stack, Box } from '@mui/material';
import PlayArrowIcon from '@mui/icons-material/PlayArrow';
import PauseIcon from '@mui/icons-material/Pause';
import ReactAudioPlayer from 'react-audio-player';

import IconButton from '../../../../_global/components/button/IconButton';
import BoldText from '../../../../_global/components/text/BoldText';
import StyledSlider from '../../../../_global/components/slider/StyledSlider';

import TimeTool from '../../../../_global/tool/TimeTool';

const MusicPlayer = ({musicUrl, musicSecTime, musicTitle, musicCreater, musicCreatedDate, ...props}) => {
    const audioPlayerRef = useRef();
    const [isPaused, setIsPaused] = useState(true)
    const [currentTimeSec, setCurrentTimeSec] = useState(0)


    const onPlayButtonClicked = () => {
        const audioRef = audioPlayerRef.current.audioEl.current;

        if(audioRef.paused) audioRef.play()
        else audioRef.pause()
    }

    const onSliderDragEnded = (e) => {
        const audioRef = audioPlayerRef.current.audioEl.current;

        audioRef.currentTime = currentTimeSec
        audioRef.play()
    }
    

    return (
    <>
    
        <Stack {...props}>
            <Box>
                <IconButton onClick={onPlayButtonClicked} sx={{float: "left"}} buttonSx={{width: "35px", minWidth: "35px", height: "35px", minHeight: "35px"}}>
                    {isPaused ? <PlayArrowIcon/> : <PauseIcon/>}
                </IconButton>

                <Box sx={{float: "left", marginLeft: "5px"}}>
                    <BoldText sx={{color: "lightgray", fontSize: "10px"}}>{musicCreater}</BoldText>
                    <BoldText>{musicTitle}</BoldText>
                </Box>

                <BoldText sx={{float: "right", color: "lightgray", fontSize: "10px"}}>{musicCreatedDate}</BoldText>
            </Box>

            <Box>
                <BoldText sx={{float: "left", fontSize: "10px", paddingTop: "9px", width:"30px"}}>{TimeTool.SecondToAudioTime(currentTimeSec)}</BoldText>
                <StyledSlider sx={{float: "left", marginX: "15px", width: "82%"}}
                            defaultValue={0} step={1} min={0} max={musicSecTime} value={currentTimeSec}
                            onChange={(e)=>{setCurrentTimeSec(e.target.value)}}
                            onMouseDown={()=>{audioPlayerRef.current.audioEl.current.pause()}}
                            onMouseUp={onSliderDragEnded}
                ></StyledSlider>
                <BoldText sx={{float: "left", fontSize: "10px", paddingTop: "9px", width:"30px"}}>{TimeTool.SecondToAudioTime(musicSecTime)}</BoldText>
            </Box>
        </Stack>

        <ReactAudioPlayer
            src={musicUrl}
            ref={audioPlayerRef}
            listenInterval={1000}
            
            onEnded={()=>{setCurrentTimeSec(0)}}
            onListen={()=>{setCurrentTimeSec(Math.floor(audioPlayerRef.current.audioEl.current.currentTime));}}
        
            onPlay={()=>{setIsPaused(false)}}
            onPause={()=>{setIsPaused(true)}}
        />
    </>
    )
}

export default MusicPlayer;