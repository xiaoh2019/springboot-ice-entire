package com.cyzs.springbooticeentire.config;

import com.cyzs.springbooticeentire.icegen.HelloPrx;
import com.cyzs.springbooticeentire.icegen.PersonServicePrx;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectPrx;
import com.zeroc.Ice.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @description:
 * @author: xh
 * @create: 2020-05-11 10:14
 */
@Configuration
public class IceConfig {

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Bean
    public Communicator communicator(){
        Communicator communicator = Util.initialize();
        Runnable r = new Runnable(){
            @Override
            public void run() {
                try {
                    communicator.waitForShutdown();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    communicator.destroy();
                }
            }
        };
        threadPoolTaskExecutor.execute(r);
        return communicator;
    }

    @Bean
    public HelloPrx helloPrx(Communicator ic){
        ObjectPrx proxy = ic.stringToProxy("HelloIce:tcp -h 10.0.0.123 -p 8899");
        return HelloPrx.checkedCast(proxy);
    }

    @Bean
    public PersonServicePrx personServicePrx(Communicator ic){
        ObjectPrx proxy = ic.stringToProxy("PersonService:tcp -h 10.0.0.123 -p 8899");
        return PersonServicePrx.checkedCast(proxy);
    }
}
