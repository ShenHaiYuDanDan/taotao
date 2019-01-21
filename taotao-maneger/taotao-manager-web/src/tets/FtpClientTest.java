import com.taotao.utils.FtpUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FtpClientTest {
    @Test
    public void testFtp() throws IOException {
//        创建一个FtpCilent对象
        FTPClient ftpClient=new FTPClient();
//        创建FPT连接 默认端口是21
        ftpClient.connect("192.168.1.102",21);
//        登陆FTP服务器 使用用户名和密码
        boolean a=ftpClient.login("anonymous","");
        System.out.println(a);
//        上传文件
//        读取本地文件
        FileInputStream inputStream=new FileInputStream(new File("D:\\localhost\\123.jpg"));
//        设置上传路径
        ftpClient.changeWorkingDirectory("/img");
//        第一个参数 服务器端文档名
//        第二个参数 上传文档的inputStream
        ftpClient.storeFile("123.jpg",inputStream);
//        关闭连接
        ftpClient.logout();



    }
    public void tsstFtpUtli() throws FileNotFoundException {
        FileInputStream inputStream=new FileInputStream(new File("D:\\localhost\\home\\ftpuser\\123.jpg"));
        FtpUtil.uploadFile("192.168.2.102",21,"","","/home/ftpuser","2018/12/13","123.jpg",inputStream);
    }
}
