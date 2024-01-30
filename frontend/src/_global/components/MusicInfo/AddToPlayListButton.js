import React, {useState} from 'react';
import { Button, Dialog, DialogTitle, DialogContent, DialogActions, Stack, Select, MenuItem, InputLabel } from '@mui/material';
import PlaylistAddIcon from '@mui/icons-material/PlaylistAdd';

import StyledTextField from '../textField/StyledTextField';

import IconButton from '../../../_global/components/button/IconButton';

const AddToPlayListButton = ({onClickSaveButton, ...props}) => {
  const [isDialogOpend, setIsDialogOpend] = useState(false);
  const [title, setTitle] = useState("플레이 리스트 선택")
  const [playListId, setPlayListId] = useState("")

  const onClickSaveButtonHandle = () => {
    onClickSaveButton(title, playListId);
  }

  return (
    <>
    <IconButton onClick={()=>{setIsDialogOpend(true);setTitle("");setPlayListId("");}} sx={{float: "left", marginLeft: "5px"}} buttonSx={{width: "35px", minWidth: "35px", height: "18px", minHeight: "18px"}} textSx={{fontSize: "12px", paddingBottom: "8px"}}>
        <PlaylistAddIcon sx={{width: "15px", height: "15px", float: "left"}}/>
    </IconButton>

    <Dialog open={isDialogOpend} onClose={()=>{setIsDialogOpend(false);}}>
      <DialogTitle sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>플레이 리스트에 음악 추가</DialogTitle>
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

            <InputLabel sx={{marginTop: "5px"}}>플레이 리스트</InputLabel>
            <Select
                sx={{
                    height: "35px", paddingLeft: "10px", width: "400px"
                }}
                value={playListId}
                label="플레이 리스트"
                name="플레이 리스트"
                onChange={(e)=>{setPlayListId(e.target.value)}}
                variant="standard"
            >
                <MenuItem value={1}>PlayListTitle1</MenuItem>
                <MenuItem value={2}>PlayListTitle2</MenuItem>
            </Select>
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

export default AddToPlayListButton;