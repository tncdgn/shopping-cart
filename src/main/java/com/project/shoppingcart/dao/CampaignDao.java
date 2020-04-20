package com.project.shoppingcart.dao;

import com.project.shoppingcart.entity.Campaign;
import com.project.shoppingcart.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignDao extends JpaRepository<Campaign, Long> {
    List<Campaign> findByCategory(Category category);
}
