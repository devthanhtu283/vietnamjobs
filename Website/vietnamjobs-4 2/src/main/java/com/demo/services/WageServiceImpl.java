package com.demo.services;

import com.demo.entities.*;
import com.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service("wageService")
public class WageServiceImpl implements WageService{

	@Autowired
	private WageRepository wageRepository;

	@Override
	public Wage find(int id) {
		return wageRepository.findById(id).get();
	}

	@Override
	public Iterable<Wage> findAll() {
		return wageRepository.findAll();
	}

	@Override
	public boolean save(Wage local) {
		try {
			wageRepository.save(local);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		try {
			wageRepository.delete(wageRepository.findById(id).get());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean exists(int min,int max, int id) {
		return wageRepository.exists(min,max, id) > 0;
	}

	@Override
	public List<Wage> findAllByStatus(Boolean status) {
		return wageRepository.findAllByStatus(status);
	}

	//    @Override
//    public boolean existsPost(int id) {
//        return wageRepository.countByPost(id) > 0;
//    }
}
