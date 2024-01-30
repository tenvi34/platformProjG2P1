import React, {useState} from 'react';
import { Button, Dialog, DialogTitle, DialogContent, DialogActions, Stack, Select, MenuItem } from '@mui/material';
import PlaylistAddIcon from '@mui/icons-material/PlaylistAdd';

import IconButton from '../../../_global/components/button/IconButton';

const AddToPlayListButton = ({onClickSaveButton, ...props}) => {
  const [isDialogOpend, setIsDialogOpend] = useState(false);
  const [playListId, setPlayListId] = useState("")

  const onClickSaveButtonHandle = () => {
    onClickSaveButton(playListId);
  }

  return (
    <>
    <IconButton onClick={()=>{setIsDialogOpend(true);setPlayListId("");}} sx={{float: "left", marginLeft: "5px"}} buttonSx={{width: "35px", minWidth: "35px", height: "18px", minHeight: "18px"}} textSx={{fontSize: "12px", paddingBottom: "8px"}}>
        <PlaylistAddIcon sx={{width: "15px", height: "15px", float: "left"}}/>
    </IconButton>

    <Dialog open={isDialogOpend} onClose={()=>{setIsDialogOpend(false);}}>
      <DialogTitle sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>플레이 리스트에 음악 추가</DialogTitle>
      <DialogContent>
        <Stack>
            <Select
                sx={{
                    height: "35px", paddingLeft: "10px", width: "400px"
                }}
                value={playListId}
                label="플레이 리스트"
                name="플레이 리스트"
                onChange={(e)=>{setPlayListId(e.target.value)}}
                disableUnderline={true}
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