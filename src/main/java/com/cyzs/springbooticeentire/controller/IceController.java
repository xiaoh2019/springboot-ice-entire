package com.cyzs.springbooticeentire.controller;

import com.cyzs.springbooticeentire.icegen.HelloPrx;
import com.cyzs.springbooticeentire.icegen.Person;
import com.cyzs.springbooticeentire.icegen.PersonServicePrx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @description:
 * @author: xh
 * @create: 2020-05-11 10:18
 */
@RestController
@RequestMapping("/ice")
public class IceController {

    @Autowired
    private HelloPrx helloPrx;

    @Autowired
    private PersonServicePrx personServicePrx;

    @GetMapping("/SayHello")
    public String hello(String name){
        String s = helloPrx.SayHello(name);
        return s;
    }

    @PostMapping("/addPerson")
    public String addPerson(String name, Long id, int age, String email, String address){
        Person person = new Person();
        person.name = name;
        person.id = id;
        person.age = age;
        person.email = email;
        person.address = address;
        personServicePrx.addPerson(person);
        return "success";
    }

    @GetMapping("/getPersonByName")
    public Person getPersonByName(String name){
        //Person personByName = personServicePrx.getPersonByName(name);
        HashMap<String, String> map = new HashMap<>();
        map.put("aa","aa");
        //Person personByName = personServicePrx.getPersonByName(name, map);
        CompletableFuture<Person> personByNameAsync = personServicePrx.getPersonByNameAsync(name);
        Person person = null;
        try {

            person = personByNameAsync.get();
        }catch (Exception e){
            e.printStackTrace();
        }
        return person;
    }

}
