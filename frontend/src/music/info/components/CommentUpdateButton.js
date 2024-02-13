import React, {useState} from 'react';
import { Button, Dialog, DialogTitle, DialogContent, DialogActions, Stack } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';

import IconButton from '../../../_global/components/button/IconButton';
import StyledTextField from '../../../_global/components/textField/StyledTextField';

const CommentUpdateButton = ({onClickSaveButton, defaultComment, ...props}) => {
  const [isDialogOpend, setIsDialogOpend] = useState(false);
  const [content, setContent] = useState(defaultComment)

  const onClickSaveButtonHandle = () => {
    onClickSaveButton(content);
  }

  return (
    <>
    <IconButton onClick={()=>{setContent(defaultComment);setIsDialogOpend(true);}} sx={{float: "right", marginLeft: "5px"}} buttonSx={{width: "20px", minWidth: "20px", height: "18px", minHeight: "18px"}} textSx={{fontSize: "12px", paddingBottom: "8px"}}>
        <EditIcon sx={{width: "15px", height: "15px", float: "left"}}/>
    </IconButton>

    <Dialog open={isDialogOpend} onClose={()=>{setIsDialogOpend(false);}}>
      <DialogTitle sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>댓글 수정</DialogTitle>
      <DialogContent>
        <Stack>
            <StyledTextField
                name="content"
                label="내용"

                margin="normal"
                fullWidth

                sx={{width: 400}}
                value={content}

                onChange={(e)=>{setContent(e.target.value)}}
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

export default CommentUpdateButton;