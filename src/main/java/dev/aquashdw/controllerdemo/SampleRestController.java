package dev.aquashdw.controllerdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController // RestController를 이용해 (request와 get)매핑을 함께 사용가능 // 데이터를 주고받는데 사용
@RequestMapping("/rest") //rest가 만들어준 requestmapping (즉, 전체툴)
public class SampleRestController {
    private static final Logger logger = LoggerFactory.getLogger(SampleRestController.class);

    @GetMapping("/sample-payload")
    public SamplePayload samplePayload(){
        return new SamplePayload("seokimun", 10, "Developer");
    }
    // 결과적으로 url에 localhost:8080/rest/sample-payload 를 치면 결과값 출력

    @GetMapping(
            value= "/sample-image",
            produces = MediaType.IMAGE_PNG_VALUE // 2. 이미지라고 요청한 사람에게 돌려줌
    )
    public byte[] sampleImage() throws IOException { // 1. byte를 돌려줘서 responsebody에 들어간다
        InputStream inputStream = getClass().getResourceAsStream("/static/img.png"); //이미지가 있는 폴도로 경로설정

        return inputStream.readAllBytes();
    }
}
