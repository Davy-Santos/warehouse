package br.com.dio.warehouse.dto;

import br.com.dio.warehouse.entity.StockStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record StockStatusMessage(
        @JsonProperty("id")
        UUID id,
        @JsonProperty("status")
        StockStatus status) {
}