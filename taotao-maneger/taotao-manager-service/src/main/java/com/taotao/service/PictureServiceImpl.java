package com.taotao.service;

import com.taotao.utils.FtpUtil;
import com.taotao.utils.IDUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * 功能描述: 
 *      图片上传功能实现
 * @param: 
 * @return: 
 * @auther: superman
 * @date: 2018/12/17 14:31
 */
@Service
public class PictureServiceImpl implements PictureService {
//    用Value取值
    @Value("${FTP_ADDRESS}")
    private String  FTP_ADDRESS;
    @Value("${FTP_PORE}")
    private Integer  FTP_POST;
    @Value("${FTP_USERNAME}")
    private String  FTP_USERNAME;
    @Value("${FTP_PASSWORD}")
    private String  FTP_PASSWORD;
    @Value("${FTP_BASE_PATH}")
    private String FTP_BASE_PATH;
    @Value("${IMAGE_BASS_URL}")
    private String IMAGE_BASS_URL;
    /**
     *
     * 功能描述:  使用工具栏实现图片上传功能
     *需要在spring-mvc 配置部件解析器 不然会抛异常 添加如下内容
     * <bean id="portletMultipartResolver"
     *     class="org.springframework.web.portlet.multipart.CommonsPortletMultipartResolver">
     *
     *     <!-- 一个属性；以byte为单位的最大文件长度 -->
     *     <property name="maxUploadSize" value="100000"/>
     * </bean>
     * 需要引入 file-upload 和 common-io
     * @param:
     * @return:
     * @auther: superman
     * @date: 2018/12/17 15:24
     */
    public Map uploadPicture(MultipartFile uploadFile) {
        Map resultMap = new HashMap();
        try {
            //生成一个新的文件名
            //原始文件名
            String oldName = uploadFile.getOriginalFilename();
//        生成新的文件名称 生成一个字符串
//     第一种方法：   UUID.randomUUID()
//        第二种声生成方法 用毫秒和纳秒的工具类 IDUTtils
            String newName = IDUtils.genImageName();
            newName = newName + oldName.substring(oldName.lastIndexOf("."));
//           图片上传
            String imagePath = new DateTime().toString("/yyyy/MM/dd");
            boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_POST, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH, imagePath, newName, uploadFile.getInputStream());
            //返回结果
            if (result!=true) {
                resultMap.put("error", 1);
                resultMap.put("message", "文件上传失败");
                return resultMap;
            }
            resultMap.put("error", 0);
//                            上传图片的URL路径  新建图片的名称 图片名+ 时间+ 工具类随机事件数 组成一个新的图片地址
            resultMap.put("url", IMAGE_BASS_URL + imagePath + "/" + newName);
            return resultMap;

        } catch (IOException e) {
            resultMap.put("error", 1);
            resultMap.put("message", "文件上传发生异常");
            return resultMap;
        }
    }
}
