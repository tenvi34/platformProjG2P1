import React, {useState} from 'react';
import { Button, Dialog, DialogTitle, DialogContent, DialogActions, Stack } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';

import StyledTextField from '../../../_global/components/textField/StyledTextField';
import IconButton from '../../../_global/components/button/IconButton';

const UpdatePlayListMusicButton = ({onClickSaveButton, defaultTitle, ...props}) => {
  const [isDialogOpend, setIsDialogOpend] = useState(false);
  const [title, setTitle] = useState(defaultTitle)

  const onClickSaveButtonHandle = () => {
    onClickSaveButton(title);
  }

  return (
    <>
    <IconButton onClick={()=>{setIsDialogOpend(true);setTitle(defaultTitle);}} sx={{float: "right", marginLeft: "5px"}} buttonSx={{width: "35px", minWidth: "35px", height: "18px", minHeight: "18px"}} textSx={{fontSize: "12px", paddingBottom: "8px"}}>
        <EditIcon sx={{width: "15px", height: "15px", float: "left"}}/>
    </IconButton>

    <Dialog open={isDialogOpend} onClose={()=>{setIsDialogOpend(false);}}>
      <DialogTitle sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>플레이 리스트 음악 편집</DialogTitle>
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

export default UpdatePlayListMusicButton;