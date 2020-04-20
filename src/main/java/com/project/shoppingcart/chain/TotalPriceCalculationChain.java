package com.project.shoppingcart.chain;

public interface TotalPriceCalculationChain {

    void setNext(TotalPriceCalculationChain processor);

    void process(ChainProcessorModel chainProcessorModel);
}
