package com.eccentric.tranding.controller;

import com.eccentric.tranding.pojo.common.Ret;
import com.eccentric.tranding.service.QiniuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author eccentric
 */
@RestController
@RequestMapping("/qiniu")
public class QiniuController {

    @Autowired
    private QiniuService qiniuService;

    /**
     * 上传头像至七牛
     * @param file
     * @return
     */
    @RequestMapping("/avatar")
    @ResponseBody
    public Ret avatarUpload(@RequestParam("file") MultipartFile file) throws IOException {
        //拦截空文件
        if (file.isEmpty()){
            return Ret.fail("图片为空，请重新上传");
        }
        String fileUrl = qiniuService.uploadQiniuImg(file,"avatar/");
        return Ret.ok(fileUrl==null?"上传失败":"上传成功", fileUrl);
    }


    /**
     * 上传商品图片至七牛
     * @param file
     * @return
     */
    @RequestMapping("/good")
    @ResponseBody
    public Ret goodImgUpload(@RequestParam("file") MultipartFile file) throws IOException {
        //拦截空文件
        if (file.isEmpty()){
            return Ret.fail("图片为空，请重新上传");
        }
        String fileUrl = qiniuService.uploadQiniuImg(file,"good/");
        return Ret.ok(fileUrl==null?"上传失败":"上传成功", fileUrl);
    }
}
