package com.ifsp.enumeration;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Status status) {
        if (status == null) {
            return null;
        }
        switch (status) {
            case TO_DO:
                return 0;
            case IN_PROGRESS:
                return 1;
            case DONE:
                return 2;
            default:
                throw new IllegalArgumentException("Status desconhecido: " + status);
        }
    }

    @Override
    public Status convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        switch (dbData) {
            case 0:
                return Status.TO_DO;
            case 1:
                return Status.IN_PROGRESS;
            case 2:
                return Status.DONE;
            default:
                throw new IllegalArgumentException("Valor desconhecido no banco de dados: " + dbData);
        }
    }
}
