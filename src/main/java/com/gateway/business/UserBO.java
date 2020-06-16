package com.gateway.business;

import com.gateway.data.access.objects.UserDAO;
import com.gateway.data.objects.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBO {

    @Autowired
    private UserDAO dao;

    public UserDO findByNameAndPassword(UserDO userDO){
        final UserDO newUserDO = dao.findByNameAndPassword(userDO.getName(), userDO.getPassword());

        if(newUserDO == null){
            throw new RuntimeException();
        }

        return newUserDO;
    }

    public void save(UserDO userDO){
        try{
            dao.save(userDO.getName(), userDO.getPassword());
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
