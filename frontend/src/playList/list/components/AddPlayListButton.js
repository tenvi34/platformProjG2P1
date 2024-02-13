import React, {useState} from 'react';
import { Button, Dialog, DialogTitle, DialogContent, DialogActions, Stack } from '@mui/material';
import AddIcon from '@mui/icons-material/Add';

import StyledTextField from '../../../_global/components/textField/StyledTextField';
import IconButton from '../../../_global/components/button/IconButton';

const AddPlayListButton = ({onClickSaveButton, ...props}) => {
  const [isDialogOpend, setIsDialogOpend] = useState(false);
  const [title, setTitle] = useState("")

  const onClickSaveButtonHandle = () => {
    onClickSaveButton(title);
  }

  return (
    <>
    <IconButton onClick={()=>{setIsDialogOpend(true);setTitle("");}} sx={{marginRight: "5px"}}>
        <AddIcon sx={{fontSize: 35, paddingTop: 0.3, paddingLeft: 0.3}}/>
    </IconButton>

    <Dialog open={isDialogOpend} onClose={()=>{setIsDialogOpend(false);}}>
      <DialogTitle sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>플레이 리스트 추가</DialogTitle>
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

export default AddPlayListButton;