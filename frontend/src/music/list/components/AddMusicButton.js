import React, {useState} from 'react';
import { TextField, Button, Dialog, DialogTitle, DialogContent, DialogActions, Stack } from '@mui/material';
import AddIcon from '@mui/icons-material/Add';

import IconButton from '../../../_global/components/button/IconButton';

const AddMusicButton = ({...props}) => {
  const [isDialogOpend, setIsDialogOpend] = useState(false);
  const [title, setTitle] = useState("")
  const [creater, setCreater] = useState("")
  const [dataUrl, setDataUrl] = useState("")

  const onClickSaveButton = () => {
    alert(title + "/" + creater);
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
            <TextField
                name="title"
                label="제목"

                margin="normal"
                fullWidth

                sx={{width: 400}}
                value={title}

                onChange={(e)=>{setTitle(e.target.value)}}
            />
          <TextField
              name="creater"
              label="제작자명"

              margin="normal"
              fullWidth

              sx={{width: 400}}
              value={creater}

              onChange={(e)=>{setCreater(e.target.value)}}
          />
        </Stack>
      </DialogContent>

      <DialogActions>
          <Button onClick={() => {
            setIsDialogOpend(false);
          }} sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>닫기</Button>
          <Button onClick={() => {
            onClickSaveButton();
            setIsDialogOpend(false);
          }} sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>저장</Button>
      </DialogActions>
    </Dialog>
    </>
  )
}

export default AddMusicButton;