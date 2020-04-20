package com.project.shoppingcart.service;

import com.project.shoppingcart.dao.CampaignDao;
import com.project.shoppingcart.entity.Campaign;
import com.project.shoppingcart.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
public class CampaignServiceTest {

    @InjectMocks
    private CampaignService campaignService;

    @Mock
    private CampaignDao campaignDao;

    @Test
    public void shouldSave() {
        Campaign campaign = new Campaign();

        campaignService.save(campaign);
        verify(campaignDao).save(campaign);
    }

    @Test
    public void shouldFindById() {
        campaignService.findById(1l);

        verify(campaignDao).findById(1l);
    }

    @Test
    public void shouldFindByCategory() {
        Category category = new Category();

        campaignService.findByCategory(category);

        verify(campaignDao).findByCategory(category);
    }
}