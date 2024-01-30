import React from 'react';
import { Stack, Box} from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';

import CommentUpdateButton from './CommentUpdateButton';

import BoldText from '../../../_global/components/text/BoldText';
import NormalText from '../../../_global/components/text/NormalText';
import IconButton from '../../../_global/components/button/IconButton';
import YesNoButton from '../../../_global/components/button/YesNoButton';

const CommentInfo = ({commentId, userName, createdDate, content}) => {
    const onClickCommentUpdateButton = (content) => {
        alert(content)
    }

    return (
        <Stack>
            <Box>
                <BoldText sx={{float: "left"}}>{userName}</BoldText>
                <BoldText sx={{float: "left", marginX: "5px"}}>·</BoldText>
                <BoldText sx={{float: "left", color: "lightgray", fontSize: "10px", marginTop: "2px"}}>{createdDate}</BoldText>

                <YesNoButton onClickYes={()=>{alert("YES")}} title="정말로 삭제하시겠습니까?">
                    <IconButton sx={{float: "right", marginLeft: "5px"}} buttonSx={{width: "20px", minWidth: "20px", height: "18px", minHeight: "18px"}} textSx={{fontSize: "12px", paddingBottom: "8px"}}>
                        <DeleteIcon sx={{width: "15px", height: "15px", float: "left"}}/>
                    </IconButton>
                </YesNoButton>
                <CommentUpdateButton onClickSaveButton={onClickCommentUpdateButton}/>
            </Box>
            <NormalText style={{fontWeight: "1px"}}>
                {content}
            </NormalText>
        </Stack>
    )
}

export default CommentInfo;