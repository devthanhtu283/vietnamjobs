package com.demo.services;

import com.demo.entities.Local;
import com.demo.repositories.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("localService")
public class LocalServiceImpl implements LocalService {

    @Autowired
    private LocalRepository localRepository;

    @Override
    public Local find(int id) {
        return localRepository.findById(id).get();
    }

    @Override
    public Iterable<Local> findAll() {
        return localRepository.findAll();
    }

    @Override
    public boolean save(Local local) {
        try {
            localRepository.save(local);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            localRepository.delete(localRepository.findById(id).get());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean exists(String name, int id) {
        return localRepository.exists(name, id) > 0;
    }

//    @Override
//    public boolean existsPost(int id) {
//        return localRepository.countByPost(id) > 0;
//    }

    @Override
    public List<Local> findAllByStatus(Boolean status) {
        return localRepository.findAllByStatus(status);
    }

}
