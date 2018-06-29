package com.lazy.ant.common.tests.logger;

import com.lazy.ant.common.logger.Logger;
import com.lazy.ant.common.logger.LoggerFactory;
import com.lazy.ant.common.logger.jcl.JclLoggerAdapter;
import com.lazy.ant.common.logger.jdk.JdkLoggerAdapter;
import com.lazy.ant.common.logger.log4j.Log4jLoggerAdapter;
import com.lazy.ant.common.logger.slf4j.Slf4jLoggerAdapter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * <p></p>
 *
 * @author laizhiyuan
 * @date 2018/6/28.
 */

@RunWith(Parameterized.class)
public class LoggerTests {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {JclLoggerAdapter.class},
                {JdkLoggerAdapter.class},
                {Log4jLoggerAdapter.class},
                {Slf4jLoggerAdapter.class}
        });
    }

    private Class cls;
    public LoggerTests(Class cls) {
        this.cls = cls;
    }

    @Test
    public void test(){
        System.out.println(cls.getName());
    }

    @Test
    public void testLoggerFactory(){
        Logger logger = LoggerFactory.getLogger(LoggerTests.class);
        logger.info("test info");
        logger.debug("test debug");
        logger.warn("test warn");
        logger.error("test error");
    }
}
