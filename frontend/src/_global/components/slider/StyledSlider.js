import { Slider } from '@mui/material';
import { styled } from '@mui/system';

const StyledSlider = styled(Slider)({
    "&&&": {
        "color": "blueviolet"
    },

    "& .MuiSlider-thumb:hover": {
        "box-shadow": "0 0 0 10px #8a2be21e !important"
    },

    "& .MuiSlider-thumb.Mui-focusVisible": {
        "box-shadow": "0 0 0 8px #8a2be21e !important"
    },

    "& .MuiSlider-thumb.Mui-active": {
        "box-shadow": "0 0 0 8px #8a2be21e !important"
    }
});

export default StyledSlider;