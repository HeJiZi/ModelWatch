package util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class SendMailUtil {
    public final static String ACCOUT_NAME="modelwatch@hejizi.cn";
    public final static String ACCESS_KEY_ID="LTAI4rsg2BogQiYf";
    public final static String SECRET="QXfEJuLaiM0HHiuV6iTuvEdrYBfGrU";
    public final static String FROM_NAME="ModelWatch";
    public final static String TAG_NAME = FROM_NAME;

    public static void SendMail(String subject,String toAddress,String htmlBody){
        // 如果是除杭州region外的其它region（如新加坡、澳洲Region），需要将下面的"cn-hangzhou"替换为"ap-southeast-1"、或"ap-southeast-2"。
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESS_KEY_ID, SECRET);
        // 如果是除杭州region外的其它region（如新加坡region）， 需要做如下处理
        //try {
        //DefaultProfile.addEndpoint("dm.ap-southeast-1.aliyuncs.com", "ap-southeast-1", "Dm",  "dm.ap-southeast-1.aliyuncs.com");
        //} catch (ClientException e) {
        //e.printStackTrace();
        //}
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        try {
            //request.setVersion("2017-06-22");// 如果是除杭州region外的其它region（如新加坡region）,必须指定为2017-06-22
            request.setAccountName(ACCOUT_NAME);
            request.setFromAlias(FROM_NAME);
            request.setAddressType(1);
            request.setTagName(TAG_NAME);
            request.setReplyToAddress(true);
            request.setToAddress(toAddress);
            //可以给多个收件人发送邮件，收件人之间用逗号分开，批量发信建议使用BatchSendMailRequest方式
            //request.setToAddress("邮箱1,邮箱2");
            request.setSubject(subject);
            request.setHtmlBody(htmlBody);
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
        }
        catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
