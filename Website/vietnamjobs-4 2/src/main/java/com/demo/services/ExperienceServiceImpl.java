package com.demo.services;

import com.demo.entities.Experience;
import com.demo.repositories.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("experienceService")
public class ExperienceServiceImpl implements ExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;

    @Override
    public Experience find(int id) {
        return experienceRepository.findById(id).get();
    }

    @Override
    public Iterable<Experience> findAll() {
        return experienceRepository.findAll();
    }

    @Override
    public boolean save(Experience local) {
        try {
            experienceRepository.save(local);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            experienceRepository.delete(experienceRepository.findById(id).get());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean exists(String name, int id) {
        return experienceRepository.exists(name, id) > 0;
    }

    @Override
    public List<Experience> findAllByStatus(Boolean status) {
        return experienceRepository.findAllByStatus(status);
    }

    //    @Override
//    public boolean existsPost(int id) {
//        return experienceRepository.countByPost(id) > 0;
//    }


}
