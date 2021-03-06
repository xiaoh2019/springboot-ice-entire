package com.cyzs.springbooticeentire.controller;

import com.cyzs.springbooticeentire.bean.IceObject;
import com.cyzs.springbooticeentire.icegen.hello.HelloPrx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: xh
 * @create: 2020-05-26 13:45
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    IceObject iceObject;

    @GetMapping("/sayHello")
    public String sayHello(){
        HelloPrx helloPrx = iceObject.helloPrx();
        if (helloPrx != null){
            return helloPrx.SayHello("王二");
        }
        return "abc";
    }
}
