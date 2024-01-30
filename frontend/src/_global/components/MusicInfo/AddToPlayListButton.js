import React, { useState, useContext } from 'react';
import { Button, Dialog, DialogTitle, DialogContent, DialogActions, Stack, Select, MenuItem, InputLabel } from '@mui/material';
import PlaylistAddIcon from '@mui/icons-material/PlaylistAdd';

import { AlertPopupContext } from '../../provider/alertPopUp/AlertPopUpContext';
import { JwtTokenContext } from '../../provider/jwtToken/JwtTokenContext';

import StyledTextField from '../textField/StyledTextField';

import IconButton from '../../../_global/components/button/IconButton';

import PlayListProxy from '../../proxy/PlayListProxy';

const AddToPlayListButton = ({onClickSaveButton, defaultTitleValue, ...props}) => {
  const {addAlertPopUp} = useContext(AlertPopupContext);
  const {jwtTokenState} = useContext(JwtTokenContext);

  const [isDialogOpend, setIsDialogOpend] = useState(false);
  const [title, setTitle] = useState(defaultTitleValue)
  const [playListId, setPlayListId] = useState("")


  const onClickSaveButtonHandle = () => {
    onClickSaveButton(title, playListId);
  }


  const [playListInfos, setPlayListInfos] = useState([])

  const onClickDialogOpenButton = async () => {
    try {

      setPlayListInfos((await PlayListProxy.searchPlayListAllByCreaterId(jwtTokenState.jwtToken.id, jwtTokenState)).filter((playListInfo)=>{
        return playListInfo.status !== "PlayListDeleted"
      }));

      setIsDialogOpend(true);
      setTitle(defaultTitleValue);
      setPlayListId("");

    } catch (error) {
        addAlertPopUp("플레이 리스트 정보들을 불러오는 과정에서 오류가 발생했습니다!", "error");
        console.error("플레이 리스트 정보들을 불러오는 과정에서 오류가 발생했습니다!", error);
    }
  }


  return (
    <>
    <IconButton onClick={onClickDialogOpenButton} sx={{float: "left", marginLeft: "5px"}} buttonSx={{width: "35px", minWidth: "35px", height: "18px", minHeight: "18px"}} textSx={{fontSize: "12px", paddingBottom: "8px"}}>
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
              {
                playListInfos.map((playListInfo)=>(
                  <MenuItem key={playListInfo.playListId} value={playListInfo.playListId}>{playListInfo.title}</MenuItem>
                ))
              }
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