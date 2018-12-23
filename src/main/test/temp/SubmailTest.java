package temp;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.junit.Test;
import util.SendMailUtil;

public class SubmailTest {
    @Test
    public void  sendMail(){
        SendMailUtil.SendMail("您收到了一封来自黄睿的邀请邮件","1759197828@qq.com","<h1>这是标题</h1><a href=\"\">hhhh</a><h1>");
    }
}
