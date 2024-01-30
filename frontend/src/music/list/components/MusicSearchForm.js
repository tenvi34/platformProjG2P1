import React, { useState } from 'react';
import { Paper, InputBase, MenuItem, Select } from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import IconButton from '../../../_global/components/button/IconButton';

const MusicSearchForm = ({onSubmit, sx, ...props}) => {
    const [searchText, setSearchText] = useState("")
    const [searchType, setSearchType] = useState("musicTitle")

    const handleOnSubmit = () => {
        onSubmit(searchType, searchText)
    }

    return (
        <Paper component="form" sx={{width: "397px", height: "35px", ...sx}} {...props}>
            <Select
                sx={{
                    height: "35px", width: "125px", paddingLeft: "10px"
                }}
                value={searchType}
                label="검색 대상"
                name="검색 대상"
                onChange={(e)=>{setSearchType(e.target.value)}}
                disableUnderline={true}
                variant="standard"
            >
                <MenuItem value="musicTitle">음악 제목</MenuItem>
                <MenuItem value="musicCreater">음악 제작자</MenuItem>
            </Select>

            <InputBase sx={{marginLeft: "10px"}} value={searchText} onChange={(e)=>{setSearchText(e.target.value)}}/>
            <IconButton onClick={handleOnSubmit} buttonSx={{
                    marginLeft: "10px", float:"right", height: "35px", minHeight: "35px", width: "35px", minWidth: "35px",
                    borderRadius: "0px 5px 5px 0px"
                }}>
                <SearchIcon/>
            </IconButton>
        </Paper>
    )
}

export default MusicSearchForm;