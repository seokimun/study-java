package dev.aquashdw.controllerdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


// 이 컨트롤로서 실행된다
@Controller // Controller은 일반적으로 뷰를 제공하거나 넓은범위에서 사용
public class SampleController {
    private static final Logger logger
            = LoggerFactory.getLogger(SampleController.class);

    @RequestMapping (
            value="hello",      // url의 path로서 정의 (hello라는 곳에 들어가면 이렇게 나온다) [경로가 hello 로 설정]
            method = RequestMethod.GET  // 브라우저에서 주소를 넣음으로서 페이지를 불러올때는 GET 메소드를 사용
    )      //어떠한 함수를 썼을때 반환되는 값을 사용할때 쓰는 구문

    public String hello(@RequestParam(name = "id", required = false, defaultValue = "") String id){ // Query의 내용을 불러오는데 사용
        logger.info("Path: Hello"); //로그값
        logger.info("Query Param id: " + id); //로그값
        return "hello.html";
    } // string을 기본적으로 반환값을 넣어줌 (string은 어떠한 경로로 간다~)
    // 즉, hello.html이 아니라 hello만 넣었을때 계속 반복되는 구조로 오류가 생긴다 (무한루프의 일종)

    @GetMapping(
            value = "/hello/{id}" // id 부분에 Path 부분이 들어간다(url 부분에 아이디값을 넣으면 됨)
    )
    public String helloPath(@PathVariable("id") String id){ // id 속성을 string 값으로 설정
        logger.info("Path Variable is: " + id); // 샐행로그를 보면 어떤아이디의 값으로 매핑이 되었는지 확인가능
        return "/hello.html";  // hello.html 로 데이터를 반환하여 응답

    }

    @GetMapping(
            "/get-profile" // get-profile 이라는 경로로 설정
    )
    public @ResponseBody SamplePayload getProfile() { // getprofile 경로로 접속해서 꼭 @ResponseBody 명령어를 통해 데이터를 http에 응답을 불러와야함
        return new SamplePayload("seokimun", 20, "Developer");
    }
}
/*
* 요청 경로를 설정하기 위해 Controller Annotation을 사용
* RequestMapping을 이용해 경로에 따라 실행될 함수를 지정할 수 있음
* Method 별로 별도의 Annotation이 존재(GetMapping, PostMapping 등)
* HTML 외에 데이터 전송을 위해 Body와 MediaType을 설정가능
* RestController => Controller + ResponseBody
 */