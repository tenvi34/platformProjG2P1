import React, {useState} from 'react';
import { Button, Dialog, DialogTitle, DialogContent, DialogActions, Stack } from '@mui/material';
import AddIcon from '@mui/icons-material/Add';
import MusicNoteIcon from '@mui/icons-material/MusicNote';

import IconButton from '../../../_global/components/button/IconButton';
import StyledTextField from '../../../_global/components/textField/StyledTextField';
import StyledTextButton from '../../../_global/components/button/StyledTextButton';
import FileUploadButton from '../../../_global/components/button/FileUploadButton';

const AddMusicButton = ({onClickSaveButton, ...props}) => {
  const [isDialogOpend, setIsDialogOpend] = useState(false);
  const [title, setTitle] = useState("")
  const [creater, setCreater] = useState("")
  const [dataUrl, setDataUrl] = useState("")

  const onClickSaveButtonHandle = () => {
    onClickSaveButton(title, creater, dataUrl);
  }

  const onUploadMusicFile = (_, musicDataUrl) => {
    setDataUrl(musicDataUrl);
  }

  return (
    <>
    <IconButton onClick={()=>{setTitle("");setCreater("");setIsDialogOpend(true);}} {...props}>
        <AddIcon sx={{fontSize: 35, paddingTop: 0.3, paddingLeft: 0.3}}/>
    </IconButton>

    <Dialog open={isDialogOpend} onClose={()=>{setIsDialogOpend(false);}}>
      <DialogTitle sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>음악 추가</DialogTitle>
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
            <StyledTextField
                name="creater"
                label="제작자명"

                margin="normal"
                fullWidth

                sx={{width: 400}}
                value={creater}

                onChange={(e)=>{setCreater(e.target.value)}}
            />

            <FileUploadButton accept="*.mp3" onUploadFile={onUploadMusicFile}>
                <StyledTextButton
                    variant="text"
                    color="primary"
                    startIcon={<MusicNoteIcon />}
                    sx={{"&&&": ((dataUrl.length > 0) ? {} : {"color": "gray"})}}
                >
                    음악 파일 업로드
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

export default AddMusicButton;