package com.cyzs.springbooticeentire.icegen;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectPrx;
import com.zeroc.Ice.Util;

/**
 * @Description:
 * @Author xh
 * @create 2020-03-18 14:43
 */
public class IceClient {
    public static void main(String[] args) {
        // 通信器
        Communicator ic = null;
        // 初始化这个通信器
        ic = Util.initialize(args);
        //根据地址生成一个服务代理对象  HelloIce -- 服务端那边自己定义的唯一标识符
        ObjectPrx proxy = ic.stringToProxy("HelloIce:tcp -h 10.0.0.129 -p 8899");
        HelloPrx helloPrx = HelloPrx.checkedCast(proxy);
        //调用方法
        String str = helloPrx.SayHello("jack");
        System.out.println(str);

    }
}
