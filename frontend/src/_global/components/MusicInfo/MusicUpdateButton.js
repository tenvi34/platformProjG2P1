import React, {useState} from 'react';
import { Button, Dialog, DialogTitle, DialogContent, DialogActions, Stack } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import MusicNoteIcon from '@mui/icons-material/MusicNote';

import IconButton from '../button/IconButton';
import StyledTextField from '../textField/StyledTextField';
import FileUploadButton from '../button/FileUploadButton';
import StyledTextButton from '../button/StyledTextButton';

const MusicUpdateButton = ({onClickSaveButton, defaultTitle, ...props}) => {
  const [isDialogOpend, setIsDialogOpend] = useState(false);
  const [title, setTitle] = useState(defaultTitle)
  const [dataUrl, setDataUrl] = useState("")

  const onClickSaveButtonHandle = () => {
    onClickSaveButton(title, dataUrl);
  }

  const onUploadMusicFile = (_, musicDataUrl) => {
    setDataUrl(musicDataUrl);
  }

  return (
    <>
    <IconButton onClick={()=>{setTitle(defaultTitle);setDataUrl("");setIsDialogOpend(true);}} sx={{float: "right", marginLeft: "5px"}} buttonSx={{width: "35px", minWidth: "35px", height: "18px", minHeight: "18px"}} textSx={{fontSize: "12px", paddingBottom: "8px"}}>
        <EditIcon sx={{width: "15px", height: "15px", float: "left"}}/>
    </IconButton>

    <Dialog open={isDialogOpend} onClose={()=>{setIsDialogOpend(false);}}>
      <DialogTitle sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>음악 수정</DialogTitle>
      <DialogContent>
        <Stack>
            <StyledTextField
                name="title"
                label="제목"

                margin="normal"
                fullWidth

                sx={{width: 400}}
                value={title}

                onChange={(e)=>{setTitle(e.target.value)}}
            />

            <FileUploadButton accept="*.mp3" onUploadFile={onUploadMusicFile}>
              <StyledTextButton
                  variant="text"
                  color="primary"
                  startIcon={<MusicNoteIcon />}
                  sx={{"&&&": ((dataUrl.length > 0) ? {} : {"color": "gray"})}}
              >
                    음악 파일 수정
              </StyledTextButton>
            </FileUploadButton>
        </Stack>
      </DialogContent>

      <DialogActions>
          <Button onClick={() => {
            setIsDialogOpend(false);
          }} sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>닫기</Button>
          <Button onClick={() => {
            onClickSaveButtonHandle();
            setIsDialogOpend(false);
          }} sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>저장</Button>
      </DialogActions>
    </Dialog>
    </>
  )
}

export default MusicUpdateButton;