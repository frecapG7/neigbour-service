package com.neigbour.service.neigbourservice.model.repository;

import com.neigbour.service.neigbourservice.model.entity.Category;
import com.neigbour.service.neigbourservice.model.entity.District;
import com.neigbour.service.neigbourservice.model.entity.PointOfInterest;
import com.neigbour.service.neigbourservice.model.entity.SubCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointOfInterestRepository extends CrudRepository<PointOfInterest, Long> {


    List<PointOfInterest> findByCategory(Category category);

    List<PointOfInterest> findByCategoryAndSubCategoriesContains(Category category, List<SubCategory> subCategories);

    List<PointOfInterest> findByDistrict(District district);


}
