package com.gateway.business;

import com.gateway.data.access.objects.TypeDAO;
import com.gateway.data.objects.TypeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeBO {

    @Autowired
    private TypeDAO dao;

    public List<TypeDO> findAll(){
        return dao.findAll();
    }
}
