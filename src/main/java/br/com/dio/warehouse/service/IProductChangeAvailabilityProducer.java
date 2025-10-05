package br.com.dio.warehouse.service;

import br.com.dio.warehouse.dto.StockStatusMessage;
import br.com.dio.warehouse.entity.StockStatus;

public interface IProductChangeAvailabilityProducer {

    void notifyStatusChange(final StockStatusMessage message);
}
