package org.emerald.apifirst.apifirstserver.mappers;

import org.emerald.apifirst.apifirstserver.domain.PaymentMethod;
import org.emerald.apifirst.model.PaymentMethodDto;
import org.mapstruct.Mapper;

@Mapper
public interface PaymentMethodMapper {

    PaymentMethodDto paymentMethodToDto(PaymentMethod paymentMethod);
}
