package com.demo.services;

import com.demo.entities.*;
import com.demo.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dtos.SeekerDTO;

import java.util.List;

@Service("typeService")
public class TypeServiceImpl implements TypeService{

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public Type find(int id) {
        return typeRepository.findById(id).get();
    }

    @Override
    public Iterable<Type> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public boolean save(Type local) {
        try {
            typeRepository.save(local);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            typeRepository.delete(typeRepository.findById(id).get());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean exists(String name, int id) {
        return typeRepository.exists(name, id) > 0;
    }

    @Override
    public List<Type> findAllByStatus(Boolean status) {
        return typeRepository.findAllByStatus(status);
    }

    //    @Override
//    public boolean existsPost(int id) {
//        return rankRepository.countByPost(id) > 0;
//    }
	
}
