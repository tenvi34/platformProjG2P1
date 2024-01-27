import { Button } from '@mui/material';
import styled from 'styled-components';

const StyledContainedButton = styled(Button)({
    "&&&": {
        backgroundColor: "blueviolet",
        color: "white"
    }
});

export default StyledContainedButton;