package com.cyzs.springbooticeentire.controller;

import com.cyzs.springbooticeentire.bean.IceObject;
import com.cyzs.springbooticeentire.icegen.person.Person;
import com.cyzs.springbooticeentire.icegen.person.PersonServicePrx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: xh
 * @create: 2020-05-26 13:08
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    IceObject iceObject;

    @PostMapping("/addPerson")
    public String addPerson(Person p){
        PersonServicePrx personServicePrx = iceObject.personServicePrx();
        if (personServicePrx != null){
           personServicePrx.addPerson(p);
        }
        return "success";
    }

    @GetMapping("/findPerson")
    public Person findPerson(String name){
        PersonServicePrx personServicePrx = iceObject.getPersonServicePrx();
        if (personServicePrx != null){
            Person person = personServicePrx.getPersonByName(name);
            return person;
        }
        return null;
    }
}
