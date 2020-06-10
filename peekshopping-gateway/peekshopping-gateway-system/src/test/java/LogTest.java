
import org.junit.Test;
import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @date: 2020/6/10 15:05
 * @author: sumeng
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class LogTest {

    @Test
    public void Log() {

        Logger logger = LoggerFactory.getLogger("name");

        logger.info("this is info");

    }
}
