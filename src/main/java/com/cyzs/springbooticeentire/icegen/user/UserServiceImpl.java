package com.cyzs.springbooticeentire.icegen.user;

import com.zeroc.Ice.Current;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:  服务端实现接口
 * @author: xh
 * @create: 2020-05-25 17:56
 */
@Slf4j
public class UserServiceImpl implements UserService {

    private List<User> userList = new ArrayList<>();

    @Override
    public User getUserByName(String name, Current current) {
        log.info("收到getUserByName请求");
        for (User user : userList) {
            if (name == user.name){
                return user;
            }
        }
        return null;
    }

    @Override
    public void addUser(User user, Current current) {
        log.info("收到addUser请求");
        userList.add(user);
    }

    @Override
    public void deleteUserById(int id, Current current) {
        log.info("收到deleteUserById请求");
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            if (id == user.id){
                userList.remove(i);
            }
        }
    }

    @Override
    public void updateUser(User user, Current current) {
        log.info("收到updateUser请求");
        for (int i = 0; i < userList.size(); i++) {
            User userTemp = userList.get(i);
            if (user.id == userTemp.id){
                userList.remove(i);
                userList.add(user);
            }
        }
    }
}
