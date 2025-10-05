package br.com.dio.warehouse.service;

import br.com.dio.warehouse.entity.ProductEntity;

import java.util.UUID;

public interface IProductQueryService {

    ProductEntity findById(final UUID id);


}
