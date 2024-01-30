import React, {useState} from 'react';
import { Button, Dialog, DialogTitle, DialogContent, DialogActions, Stack } from '@mui/material';

import ManageAccountsIcon from '@mui/icons-material/ManageAccounts';

import StyledTextField from '../textField/StyledTextField';
import IconButton from './IconButton';

const UserManageButton = ({...props}) => {
  const [isDialogOpend, setIsDialogOpend] = useState(false);
  const [userName, setUserName] = useState("")
  
  const onClickSaveButton = () => {
    alert(userName);
  }

  return (
    <>
    <IconButton onClick={()=>{setUserName("");setIsDialogOpend(true);}} {...props}>
      <ManageAccountsIcon sx={{fontSize: 35, paddingTop: 0.3, paddingLeft: 0.3}}/>
    </IconButton>

    <Dialog open={isDialogOpend} onClose={()=>{setIsDialogOpend(false);}}>
      <DialogTitle sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>사용자 정보 수정</DialogTitle>
      <DialogContent>
        <Stack>
          <StyledTextField
                name="userName"
                label="유저명"

                margin="normal"
                fullWidth

                sx={{width: 400}}
                value={userName}

                onChange={(e)=>{setUserName(e.target.value)}}
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

export default UserManageButton;