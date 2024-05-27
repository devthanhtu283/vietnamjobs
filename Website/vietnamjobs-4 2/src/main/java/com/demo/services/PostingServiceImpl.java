package com.demo.services;

import java.util.Date;
import java.util.List;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dtos.AccountDTO;
import com.demo.dtos.PostingDTO;
import com.demo.entities.Admin;
import com.demo.entities.Postings;
import com.demo.repositories.PostingRepository;
@Service("postingService")
public class PostingServiceImpl implements PostingService{
	
	@Autowired
	private PostingRepository postingRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<PostingDTO> findAll() {
		return mapper.map(postingRepository.findAll(), new TypeToken<List<PostingDTO>>() {}.getType());
	}
	@Override
	public boolean save(PostingDTO postingDTO) {
		// TODO Auto-generated method stub
		try {
			Postings postings =  mapper.map(postingDTO, Postings.class);
			postingRepository.save(postings);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public List<PostingDTO> findByEmployerId(int empid) {
		// TODO Auto-generated method stub
		return mapper.map(postingRepository.findByEmployerId(empid), new TypeToken<List<PostingDTO>>() {}.getType());
	}
	@Override
	public List<PostingDTO> findByEmployerName(String name) {
		// TODO Auto-generated method stub
		return mapper.map(postingRepository.findbyemployername(name), new TypeToken<List<PostingDTO>>() {}.getType());
	}
	@Override
	public List<PostingDTO> findByEmployerIdlimit(int employerid, int limit){
		// TODO Auto-generated method stub
		return mapper.map(postingRepository.limit3(employerid,limit), new TypeToken<List<PostingDTO>>() {}.getType());
	}
	@Override
	public PostingDTO findById(int id) {
		// TODO Auto-generated method stub
		return mapper.map(postingRepository.findById(id).get(), new PostingDTO().getClass());
	}
	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		try {
			postingRepository.delete(postingRepository.findById(id).get());
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public List<PostingDTO> findByStatus(boolean status) {
		// TODO Auto-generated method stub
		return mapper.map(postingRepository.findbystatus(status), new TypeToken<List<PostingDTO>>() {}.getType());
	}
	@Override
	public List<PostingDTO> findByEmployerIdStatus(int employerid, boolean status) {
		// TODO Auto-generated method stub
		return mapper.map(postingRepository.findbyemployerstatus(employerid, status), new TypeToken<List<PostingDTO>>() {}.getType());
	}
	@Override
	public List<PostingDTO> findByDeadline() {
		// TODO Auto-generated method stub
		return mapper.map(postingRepository.findbydeadline(new Date()), new TypeToken<List<PostingDTO>>() {}.getType());
	}
	@Override
	public List<PostingDTO> findByEmployerIdDeadline(int employerid) {
		return mapper.map(postingRepository.findbyemployeriddeadline(employerid, new Date()), new TypeToken<List<PostingDTO>>() {}.getType());
	}
	@Override
	public List<PostingDTO> search(Integer localId, Integer wageId, Integer typeId, Integer categoryId, Integer experienceId, String title) {
		return mapper.map(postingRepository.search(localId, wageId, typeId, categoryId, experienceId, title), new TypeToken<List<PostingDTO>>() {}.getType()); 
	}
	
	@Override
	public List<PostingDTO> findByTittle(String name) {
		return mapper.map(postingRepository.findbyemployertitle(name), new TypeToken<List<PostingDTO>>() {}.getType());
	}
	
	@Override
	public List<PostingDTO> findAllLimit(int limit) {
		return mapper.map(postingRepository.findAllLimit(limit), new TypeToken<List<PostingDTO>>() {}.getType());
	}

	@Override
	public Iterable<Postings> getByEmployerId(int id) {
		return postingRepository.getByEmployerId(id);
	}
	

	@Override
	public Postings getDetail(int id) {
		return postingRepository.findById(id).get();
	}
	
	@Override
	public boolean saveDB(Postings item) {
		try {
			postingRepository.save(item);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean existPostByCategoryId(int id) {
		return postingRepository.countByCategoryId(id) > 0;
	}

	@Override
	public boolean existPostByLocalId(int id) {
		return postingRepository.countByLocalId(id) > 0;
	}

	@Override
	public boolean existPostByExperienceId(int id) {
		return postingRepository.countByExperienceId(id) > 0;
	}

	@Override
	public boolean existPostByRankId(int id) {
		return postingRepository.countByRankId(id) > 0;
	}

	@Override
	public boolean existPostByTypeId(int id) {
		return postingRepository.countByTypeId(id) > 0;
	}

	@Override
	public boolean existPostByWageId(int id) {
		return postingRepository.countByWageId(id) > 0;
	}

	@Override
	public int countByEmployerId(int id) {
		if (postingRepository.countByEmployerId(id) > 0) {
			return postingRepository.countByEmployerId(id);
		} else {
			return 0;
		}
	}

	@Override
	public Postings find(int id) {
		return postingRepository.findById(id).get();
	}

	@Transactional
	@Override
	public Boolean updateStatusById(int id, Boolean status) {
		return postingRepository.updateStatusById(id, status) > 0;
	}

	@Override
	public int countByMonthAndYear(int month, int year) {
		if(postingRepository.countByMonthAndYear(month, year) > 0){
			return postingRepository.countByMonthAndYear(month,year);
		}else {
			return 0;
		}
	}

	@Override
	public Iterable<Postings> getAll() {
		return postingRepository.findAll();
	}


}
