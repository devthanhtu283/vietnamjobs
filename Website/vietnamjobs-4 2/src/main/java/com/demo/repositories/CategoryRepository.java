package com.demo.repositories;

import com.demo.entities.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Integer>{
	
	@Query("from Category where name = :name")
	public Category findbyname(@Param("name") String name);

	@Query("from Category where parentId = :id")
	public Category findbycategoryParentId(@Param("id") int id);

	@Query("from Category where status = :status")
	List<Category> findAllByStatus(@Param("status") Boolean status);

	@Query("from Category where parentId = :parentId")
	List<Category> getChildren(@Param("parentId") int parentId);

	@Query("from Category where parentId = 0")
	List<Category> getParent();

	@Query("SELECT COUNT(*) FROM Category WHERE name = :name AND id != :id")
	int exists(@Param("name") String name, @Param("id") int id);
	
//	@Query("SELECT c, COUNT(p) FROM Category c LEFT JOIN c.postingses p GROUP BY c")
	@Query(value = "SELECT c.id, c.name, COUNT(p.id) AS post_count " +
            "FROM category c " +
            "LEFT JOIN postings p ON c.id = p.category_id " +
            "GROUP BY c.id " +
            "ORDER BY post_count DESC " +
            "LIMIT 4", nativeQuery = true)
    List<Object[]> countPostingsByCategory();

	@Query("SELECT COUNT(*) FROM Category WHERE parentId = :id")
	int hasChildren(@Param("id") int id);
}
