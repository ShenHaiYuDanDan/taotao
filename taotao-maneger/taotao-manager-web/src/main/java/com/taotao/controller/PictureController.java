package com.taotao.controller;

import com.taotao.service.PictureService;
import com.taotao.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
/**
 *
 * 功能描述:
 *         上传图片处理
 * @param:
 * @return:
 * @auther: superman
 * @date: 2018/12/18 15:19
 */
@Controller
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping("/pic/upload")
    @ResponseBody
//                                      MultipartFile 参数名要与js文件下的common一致
//        为了兼容性 改成String类型
    public String pictureUpload(MultipartFile uploadFile){
        Map result= pictureService.uploadPicture(uploadFile);
//      为了保证兼容性 。需要把result转换成json格式的字符串 使用后JSonUtils工具类
        String json = JsonUtils.objectToJson(result);
        return json;

    }

}
