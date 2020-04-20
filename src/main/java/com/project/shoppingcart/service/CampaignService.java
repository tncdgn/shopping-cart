package com.project.shoppingcart.service;

import com.project.shoppingcart.dao.CampaignDao;
import com.project.shoppingcart.entity.Campaign;
import com.project.shoppingcart.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampaignService extends BaseService<Campaign> {

    @Autowired
    private CampaignDao campaignDao;

    @Override
    public void save(Campaign campaign) {
        campaignDao.save(campaign);
    }

    @Override
    public Optional<Campaign> findById(long id) {
        return campaignDao.findById(id);
    }

    public List<Campaign> findByCategory(Category category) {
        return campaignDao.findByCategory(category);
    }
}
