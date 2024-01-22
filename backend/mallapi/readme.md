# 2장. React-Router 설정

## IA(Information Architecture) 기획에 맞는 메뉴 구성

### React-Router
 - 정부 구조 기획이 웹 사이트 개발 중요 단계.
 - IA는 '메뉴 경로' 정리로 페이지 간 링크 및 <form> 설계.
 - 리액트는 SPA로 동작, 여러 컴포넌트로 하나의 페이지에 표시함.
 - React-Router로 브라우저 주소창에 따라 다른 화면을 표시함.
 
 ### React-Router 개발 목표
 - React-Router를 적용하여 페이지의 이동이 가능하도록 컴포넌트들을 구성
 - Tailwind CSS를 적용하여 공통의 레이아웃을 구성하고, 이를 통해 페이지 기반의 어플리케이션을 구성
 
 
 ----------------------------------------
 
 1.  React- Router 설정
 
 ####  기본 라우팅 설정
 
  - root.js 파일 : 기본 라우팅 설정 추가
  
  ``` Javascript
  const { createBrowserRouter } = require("react-router-dom"); const root = createBrowserRouter([
  ])
  export default root;
```


  - App.js 파일 수정
  
   ``` Javascript
   import {RouterProvider} from "react-router-dom"; import root from "./router/root";
   function App() {
     return (
   <RouterProvider router={root}></RouterProvider> );
   }
   export default App;
```

- 페이지용 컴포넌트 추가와 설정(MainPage.js 추가)

``` Javascript
    const MainPage = () => { return (
        <div className=" text-3xl">
          <div>Main Page</div>
    </div>
    ); }
    export default MainPage;
```

- MainPage 컴포넌트를 root.js 에 추가

``` Javascript
    import { Suspense, lazy } from "react";
    const { createBrowserRouter } = require("react-router-dom");
    const Loading = <div>Loading....</div>
    const Main = lazy(() => import("../pages/MainPage"))
    const root = createBrowserRouter([
      {
    path: "",
    element: <Suspense fallback={Loading}><Main/></Suspense> }
    ])
    export default root;
```



8.  URL Params 사용하기

- URL Params : Todo 목록 페이지에서 조회 페이지로 이동 시 경로 변경



 
 
