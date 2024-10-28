import React from "react";
import { Container } from "./MainPage.style";

function MainPage() {
  return (
    <>
      <Container>
        <h1>쿠키 로그인 메인화면</h1>
        <hr></hr>
        <div>로그인 되어있지 않습니다!</div>
        <button>로그인</button>
        <br />
        <button>회원가입</button>
      </Container>
    </>
  );
}

export default MainPage;
