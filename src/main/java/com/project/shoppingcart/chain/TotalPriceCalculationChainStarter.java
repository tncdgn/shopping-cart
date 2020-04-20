package com.project.shoppingcart.chain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TotalPriceCalculationChainStarter {

    @Autowired
    private CampaignForTotalPriceProcessor campaignForTotalPriceProcessor;

    @Autowired
    private CouponForTotalPriceProcessor couponForTotalPriceProcessor;

    @Autowired
    private DeliveryCostForTotalPriceProcessor deliveryCostForTotalPriceProcessor;

    public void startChain(ChainProcessorModel chainProcessorModel) {
        campaignForTotalPriceProcessor.setNext(couponForTotalPriceProcessor);
        couponForTotalPriceProcessor.setNext(deliveryCostForTotalPriceProcessor);

        campaignForTotalPriceProcessor.process(chainProcessorModel);
    }
}
