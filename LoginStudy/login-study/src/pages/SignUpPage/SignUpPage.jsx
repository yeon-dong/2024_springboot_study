import React from "react";
import { ErrorMsg } from "./SignUpPage.style";

function SignUpPage() {
  return (
    <>
      <Container>
        <h1>회원가입 페이지</h1>
        <RowContainer>
          <div>로그인 아이디 :</div>
          <Input />
        </RowContainer>
        <ErrorMsg>로그인 아이디가 중복됩니다.</ErrorMsg>
        <RowContainer>
          <div>비밀번호 :</div>
          <Input />
        </RowContainer>
        <RowContainer>
          <div>닉네임 :</div>
          <Input />
        </RowContainer>
        <ErrorMsg>닉네임을 입력하세요.</ErrorMsg>
        <button>회원가입</button>
      </Container>
    </>
  );
}

export default SignUpPage;
