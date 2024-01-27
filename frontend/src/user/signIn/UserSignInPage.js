import React, { useContext, useState, useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import { Card, Stack } from '@mui/material';

import { AlertPopupContext } from '../../_global/provider/alertPopUp/AlertPopUpContext'
import { JwtTokenContext } from "../../_global/provider/jwtToken/JwtTokenContext";

import TopAppBar from '../../_global/components/TopAppBar';
import BoldText from '../../_global/components/text/BoldText';
import StyledTextField from '../../_global/components/textField/StyledTextField';
import StyledContainedButton from '../../_global/components/button/StyledContainedButton';
import StyledTextButton from '../../_global/components/button/StyledTextButton';

import UserProxy from '../../_global/proxy/UserProxy';
  
const UserSignInPage = () => {
    const { addAlertPopUp } = useContext(AlertPopupContext);
    const { registerTokenValue, jwtTokenState } = useContext(JwtTokenContext);
    const navigate = useNavigate();

    useEffect(() => {
        if(jwtTokenState.jwtToken !== null) {
            navigate("/music/search");
        }
    }, [jwtTokenState.jwtToken, navigate])


    const [signInInfo, setSignInInfo] = useState({
        "email": "", "password": ""
    })

    const handleSignInInfoChange = (event) => {
        const { name, value } = event.target;
        setSignInInfo((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    }


    const onSubmitSignIn = async () => {
        try {

            const jwtToken = await UserProxy.signIn(signInInfo.email, signInInfo.password);
            registerTokenValue(jwtToken);

        } catch(error) {
            addAlertPopUp("로그인 도중에 오류가 발생했습니다!", "error");
            console.error("로그인 도중에 오류가 발생했습니다!", error);
        }
    }

    return (
        <>
            <TopAppBar title="로그인"></TopAppBar>

            <Card variant="outlined" sx={{ marginTop: 3, textAlign: "center"}}>
                <Stack>
                    <BoldText sx={{fontSize: 25, marginTop: 5}}>로그인</BoldText>


                    <StyledTextField
                        label="이메일"
                        name="email"
                        type="email"
                        sx={{marginTop: 10, width: 400, marginX: "auto"}}

                        value={signInInfo.email}
                        onChange={handleSignInInfoChange}
                    />

                    <StyledTextField
                        label="비밀번호"
                        name="password"
                        type="password"
                        sx={{marginTop: 3, width: 400, marginX: "auto"}}

                        value={signInInfo.password}
                        onChange={handleSignInInfoChange}
                    />


                    <StyledContainedButton onClick={onSubmitSignIn} sx={{marginTop: 6, width: 400, marginX: "auto"}}>로그인</StyledContainedButton>
                    <StyledTextButton onClick={() => {navigate("/user/signUp")}} sx={{marginTop: 3, marginBottom: 5, width: 400, marginX: "auto"}}>회원가입</StyledTextButton>
                </Stack>
            </Card>
        </>
    )
}

export default UserSignInPage;