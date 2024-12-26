import React from "react";
import { Container, Input, RowContainer } from "./LoginPage.style";

function LoginPage() {
  return (
    <>
      <Container>
        <h1>로그인 페이지</h1>
        <RowContainer>
          <div>로그인 아이디 :</div>
          <Input />
        </RowContainer>
        <RowContainer>
          <div>비밀번호 :</div>
          <Input />
        </RowContainer>
        <button>로그인</button>
        <button>회원가입</button>
      </Container>
    </>
  );
}

export default LoginPage;
