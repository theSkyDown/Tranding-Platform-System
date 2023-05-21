package com.eccentric.tranding.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author eccentric
 */
public interface QiniuService {


    /**
     * 一些操作初始化
     */
    public void init();


    /**
     * 上传七牛图片
     * @param file
     * @param path
     * @return
     */
    public String uploadQiniuImg(MultipartFile file, String path) throws IOException;
}
