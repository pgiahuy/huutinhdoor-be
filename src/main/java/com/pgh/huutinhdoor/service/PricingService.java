package com.pgh.huutinhdoor.service;

import com.pgh.huutinhdoor.entity.Ticket;
import com.pgh.huutinhdoor.entity.TicketItem;
import com.pgh.huutinhdoor.enums.PricingType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PricingService {

    private static final int SCALE = 2;

    public BigDecimal calculateItemTotal(TicketItem item) {

        if (item.getPriceAtTime() == null) {
            throw new IllegalStateException("PriceAtTime is null");
        }

        if (item.getPricingType() == null) {
            throw new IllegalStateException("PricingType is null");
        }

        BigDecimal basePrice = item.getPriceAtTime();
        BigDecimal result;

        switch (item.getPricingType()) {

            case UNIT:
                result = basePrice.multiply(
                        BigDecimal.valueOf(defaultIfNull(item.getQuantity()))
                );
                break;

            case METER:
                result = basePrice.multiply(
                        BigDecimal.valueOf(defaultIfNull(item.getLength()))
                );
                break;

            case SQUARE_METER:
                BigDecimal width = BigDecimal.valueOf(defaultIfNull(item.getWidth()));
                BigDecimal height = BigDecimal.valueOf(defaultIfNull(item.getHeight()));
                BigDecimal area = width.multiply(height);
                result = basePrice.multiply(area);
                break;

            default:
                throw new IllegalArgumentException("Unsupported pricing type");
        }

        result = result.setScale(SCALE, RoundingMode.HALF_UP);

        item.setTotalPrice(result);

        return result;
    }

    public BigDecimal calculateTicketTotal(Ticket ticket) {

        return ticket.getItems()
                .stream()
                .map(this::calculateItemTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(SCALE, RoundingMode.HALF_UP);
    }

    private double defaultIfNull(Double value) {
        return value == null ? 0.0 : value;
    }

    private int defaultIfNull(Integer value) {
        return value == null ? 0 : value;
    }
}