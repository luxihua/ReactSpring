
# Chap04. 리액트와 API 서버 통신

- 대부분의 프론트엔드 기술은 비동기 통신을 필요로 함.

- 리액트도 서버 데이터를 Ajax를 활용하여 기능을 구현함.

- 개발 목표 
    1. Axios 라이브러리를 이용한 서버와의 통신
    2. useEffect()을 활용한 비동기 처리와 상태 변경
    3. 커스텀 훅을 이용한 공통 코드 재사용하기
    4. 컴포넌트에서 모달창을 이용해서 결과 보여주기
    
    
-------------


## Ajax 통신 처리

- Axios 라이브러리 추가 : npm install axios



---------------

## useEffect(0 

 - 일반적으로, 컴포넌트 상태 변경 시 렌더링과 함께 Axios 함수를 재호출하여 무한한 반복 구조를 발생시킨다.
 
 - 이때, useEffect()을 사용하면 컴포넌트 내에서 특정 조건을 충족할 때 동작하는 방법을 제공 
    * 컴포넌트 실행 시 단 한번만 실행되는 비동기 처리
    * 컴포넌트의 여러 상태 중 특정 상태 변경 시 비동기 처리
    
- useEffect 사용
    1. 조회를 위한 ReadComponent 컴포넌트 생성

    ``` Javascript
    import {useEffect, useState } from "react"
    import {getOne} from "../../api/todoApi"

    const initState = {tno:0 , title: ' ', writer: ' ', dueDate: null, complete: false }

    const ReadComponent = ( {tno} ) => {

        const [todo, setTodo] = useState(initState) // 아직 todo는 사용하지 않음

        useEffect( () => {
            getOne(tno).then(data => {
                console.log(data)
                setTodo(data)
            })
        }, [tno])

        return (
            <div></div>
        )
    }

    export default ReadComponent
    ```
    
    
    2. ReadPage 컴포넌트에 ReadComponent import 시키기
    
    ```Javascript
    import { useCallback } from "react";
    import { createSearchParams, useNavigate, useParams, useSearchParams } from "react-router-dom";
    import ReadComponent from "../../components/todo/ReadComponent";

    const ReadPage = () => {

        const {tno} = useParams()
        const navigate = useNavigate()

        const [queryParams] = useSearchParams()

        const page = queryParams.get("page") ? parseInt(queryParams.get("page")) : 1
        const size = queryParams.get("size") ? parseInt(queryParams.get("size")) : 10
        const queryStr = createSearchParams({page, size}).toString()

        const moveToModify = useCallback( (tno) => {
            navigate({pathname: '/todo/modify/${tno}', search: queryStr})
        }, [tno, page, size])

        const moveToList = useCallback( () => {
            navigate({pathname:'/todo/list', search: queryStr})
        }, [page, size])

        return (
            <div className="font-extrabold w-full bg-white mt-6">
                <div className="text-2xl"> Todo Read Page Component {tno}  </div>
                <ReadComponent tno = {tno}></ReadComponent>
            </div>
        );
    }

    export default ReadPage;
    ```
    

    
    * 충돌점
    - 에러 내용 : 
    ``` Network Error
    AxiosError: Network Error
        at XMLHttpRequest.handleError (http://localhost:3000/static/js/vendors-node_modules_axios_lib_axios_js.chunk.js:244:14) 
    ```
    - npm start 시 FE쪽 http://localhost:3030 과 서버의 http://localhost:8080 이 CORS 충돌 문제로 실행되지 않음
    - 해결방법 : React쪽과 Server 두곳에 추가 설정 필요함.
        1. SpringBoot에서 해결하는 방법
            * CustomServletConfig.java
                ``` Java
                @Configuration
                public class CustomServletConfig implements WebMvcConfigurer{
                  @Override
                  public void addFormatters(FormatterRegistry registry) {
                        registry.addFormatter(new LocalDateFormatter()); }
                    @Override
                    public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/**").allowedOrigins("*") 
                    .allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "OPTIONS") 
                    .allowedOrigins("http://localhost:3000")
                    .maxAge(300)
                    .allowedHeaders("Authorization", "Cache-Control", "Content-Type");
                    }
                }
                ```
                
        2. React에서 해결하는 방법
          * package.json 
            ``` json
            "proxy": "http://localhost:8080" 
            ```
            
            
------------------------
            
            
## 네비게이션 관련 커스텀 훅

- 다시 목록 화면으로 이동하는 기능 추가(ReadComponent)

### 조회 화면의 버튼 처리
  
  - 페이지 컴포넌트
    1. React-Router를 이용해 내용 컴포넌트에 필요한 기능과 속성 설정.
    2. ReadPage에 라우팅 함수 정의 및 ReadComponent에 함수 속성 전달.
  
  - 내용 컴포넌트
    1. 실제 화면 구성, 버튼 등 내용 처리.
    2. ReadComponent에 전체 구성 요소 처리
    
    

    



