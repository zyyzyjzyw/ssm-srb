package com.tedu.java.sms;

import com.tedu.java.util.SmsProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author： zyy
 * @date： 2022/12/15 10:00
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class UtilsTest {


    @Test
    public void testProperties(){
        System.out.println(SmsProperties.KEY_ID);
        System.out.println(SmsProperties.KEY_SECRET);
        System.out.println(SmsProperties.REGION_Id);
        System.out.println(SmsProperties.SIGN_NAME);
        System.out.println(SmsProperties.TEMPLATE_CODE);
    }
}
