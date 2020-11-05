package server.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile[] files, @RequestParam String nodeId) throws IOException {
        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            System.out.println(originalFilename);
            System.out.println(nodeId);
            File out = new File("f:\\" + originalFilename);
            file.transferTo(out);
        }
        return "upload ok";
    }
}
