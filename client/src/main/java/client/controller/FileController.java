package client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * file
 *
 * @author wangwenjie
 * @date 2020-11-05
 */
@RestController
@RequestMapping("/file")
public class FileController {

    private static final String SERVER_URL = "http://localhost:8000/file/upload";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file, @RequestParam String nodeId) throws IOException {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        //使用byteArrayResource
        ByteArrayResource resource = new ByteArrayResource(file.getBytes()) {
            //必须重写getFilename方法，否则restTemplate请求报错
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };
        map.add("file", resource);
        map.add("nodeId", nodeId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(map,headers);
        String result = restTemplate.postForObject(SERVER_URL, entity, String.class);
        return "ok";
    }
}
