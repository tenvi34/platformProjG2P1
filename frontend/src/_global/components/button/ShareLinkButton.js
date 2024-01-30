import React, {useState} from 'react';
import { Button, Dialog, DialogTitle, DialogContent, DialogActions, Stack, TextField } from '@mui/material';
import LinkIcon from '@mui/icons-material/Link';

import IconButton from './IconButton';

const ShareLinkButton = ({title, shareUrl, ...props}) => {
    const [isSharedLinkDialogOpend, setIsSharedLinkDialogOpend] = useState(false);

    return (
    <>
    <IconButton onClick={()=>{setIsSharedLinkDialogOpend(true);}} sx={{float: "left", marginLeft: "5px"}} buttonSx={{width: "35px", minWidth: "35px", height: "18px", minHeight: "18px"}} textSx={{fontSize: "12px", paddingBottom: "8px"}}>
        <LinkIcon sx={{width: "15px", height: "15px", float: "left"}}/>
    </IconButton>

    <Dialog open={isSharedLinkDialogOpend} onClose={()=>{setIsSharedLinkDialogOpend(false);}} {...props}>
        <DialogTitle sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>{title}</DialogTitle>
        <DialogContent>
            <Stack>
                <TextField
                    name="sharedLink"

                    margin="normal"
                    fullWidth

                    sx={{width: 400}}
                    value={shareUrl}
                />
            </Stack>
        </DialogContent>

        <DialogActions>
            <Button onClick={() => {
                setIsSharedLinkDialogOpend(false);
            }} sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>닫기</Button>
        </DialogActions>
    </Dialog>
    </>
    )
}

export default ShareLinkButton;