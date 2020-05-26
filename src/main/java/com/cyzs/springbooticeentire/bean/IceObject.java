package com.cyzs.springbooticeentire.bean;

import com.cyzs.springbooticeentire.icegen.hello.HelloPrx;
import com.cyzs.springbooticeentire.icegen.person.PersonServicePrx;
import com.cyzs.springbooticeentire.icegen.user.UserServiceImpl;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.ObjectPrx;
import com.zeroc.Ice.Util;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: xh
 * @create: 2020-05-26 14:40
 */
@Component
public class IceObject implements InitializingBean {

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private Communicator communicator = Util.initialize();

    public HelloPrx helloPrx = null;

    public PersonServicePrx personServicePrx = null;

    /**
     * 启动本地服务
     */
    public void startServer(){
        Communicator communicator = Util.initialize();
        Runnable r = new Runnable(){
            @Override
            public void run() {
                try {
                    ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("adapter", "8899");
                    com.zeroc.Ice.Object userService = new UserServiceImpl();
                    adapter.add(userService, Util.stringToIdentity("UserService"));
                    adapter.activate();
                    communicator.waitForShutdown();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    communicator.destroy();
                }
            }
        };
        threadPoolTaskExecutor.execute(r);
    }

    public HelloPrx helloPrx(){
        if (helloPrx == null){
            ObjectPrx proxy = communicator.stringToProxy("HelloService:tcp -h 10.0.0.122 -p 8899");
            helloPrx = HelloPrx.checkedCast(proxy);
            return helloPrx;
        }else {
            return helloPrx;
        }
    }

    public void personServicePrx(){
        ObjectPrx proxy = communicator.stringToProxy("PersonService:tcp -h 10.0.0.122 -p 8899");
        personServicePrx =  PersonServicePrx.checkedCast(proxy);
    }

    public HelloPrx getHelloPrx() {
        return helloPrx;
    }

    public void setHelloPrx(HelloPrx helloPrx) {
        this.helloPrx = helloPrx;
    }

    public PersonServicePrx getPersonServicePrx() {
        return personServicePrx;
    }

    public void setPersonServicePrx(PersonServicePrx personServicePrx) {
        this.personServicePrx = personServicePrx;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        startServer();
    }
}
