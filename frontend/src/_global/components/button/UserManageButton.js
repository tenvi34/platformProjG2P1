import { React } from 'react';

import ManageAccountsIcon from '@mui/icons-material/ManageAccounts';

import IconButton from './IconButton';

const UserManageButton = ({...props}) => {

    return (
      <IconButton onClick={() => {}} {...props}>
        <ManageAccountsIcon sx={{fontSize: 35, paddingTop: 0.3, paddingLeft: 0.3}}/>
      </IconButton>
    )
}

export default UserManageButton;