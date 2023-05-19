package com.eccentric.tranding;

import com.eccentric.tranding.utils.Md5Util;
import com.eccentric.tranding.utils.UserHolder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TrandingPlatformApplicationTests {

    @Test
    void contextLoads() throws Exception {
        UserHolder.saveUser(null);
        System.out.println(UserHolder.getUser());
    }

}
