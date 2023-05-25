package com.eccentric.tranding.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author eccentric
 */
@Controller
public class LogController {


    /**
     * 日志文件导出功能
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping("/log/export")
    public  ResponseEntity<InputStreamResource> exportLog(HttpSession session) throws IOException {

        //读取文件
        String path = System.getProperty("user.dir");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String filePath = path + "/logs/log_"+ LocalDateTime.now().format(format) +".log";
        FileSystemResource file = new FileSystemResource(filePath);
        //设置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }



}
