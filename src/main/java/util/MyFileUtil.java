package util;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class MyFileUtil {
    public static String getResourcesUrl(){
        String path=MyFileUtil.class.getResource("/").getPath();
        int tempindex=path.substring(0,path.length()-2).lastIndexOf("/");
        tempindex=path.substring(0,tempindex).lastIndexOf("/");
        String relaPath=path.substring(0,tempindex)+"/resources";
        return relaPath;
    }
    public static MultipartFile getFile(HttpServletRequest request){
        MultipartFile file=null;
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        // 获取上传的文件
        Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();
        for(Map.Entry<String, MultipartFile> entry : fileMap.entrySet()){
            // 对文件进处理
            file=entry.getValue();
        }
        return file;
    }
}
