package com.demo.services;

import com.demo.entities.Rank;
import com.demo.repositories.RankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("rankService")
public class RankServiceImpl implements RankService{


    @Autowired
    private RankRepository rankRepository;

    @Override
    public Rank find(int id) {
        return rankRepository.findById(id).get();
    }

    @Override
    public Iterable<Rank> findAll() {
        return rankRepository.findAll();
    }

    @Override
    public boolean save(Rank local) {
        try {
            rankRepository.save(local);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            rankRepository.delete(rankRepository.findById(id).get());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean exists(String name, int id) {
        return rankRepository.exists(name, id) > 0;
    }

    @Override
    public List<Rank> findAllByStatus(Boolean status) {
        return rankRepository.findAllByStatus(status);
    }

    //    @Override
//    public boolean existsPost(int id) {
//        return rankRepository.countByPost(id) > 0;
//    }

}
