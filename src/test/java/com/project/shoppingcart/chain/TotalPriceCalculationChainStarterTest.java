package com.project.shoppingcart.chain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
public class TotalPriceCalculationChainStarterTest {

    @InjectMocks
    private TotalPriceCalculationChainStarter chainStarter;

    @Mock
    private CampaignForTotalPriceProcessor campaignForTotalPriceProcessor;

    @Mock
    private CouponForTotalPriceProcessor couponForTotalPriceProcessor;

    @Mock
    private DeliveryCostForTotalPriceProcessor deliveryCostForTotalPriceProcessor;

    @Test
    public void shouldStartChain() {
        ChainProcessorModel chainProcessorModel = new ChainProcessorModel();

        chainStarter.startChain(chainProcessorModel);

        verify(campaignForTotalPriceProcessor).setNext(couponForTotalPriceProcessor);
        verify(couponForTotalPriceProcessor).setNext(deliveryCostForTotalPriceProcessor);
        verify(campaignForTotalPriceProcessor).process(chainProcessorModel);
    }
}