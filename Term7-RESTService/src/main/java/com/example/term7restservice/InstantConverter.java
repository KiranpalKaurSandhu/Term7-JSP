package com.example.term7restservice;

import jakarta.persistence.AttributeConverter;
import java.sql.Timestamp;
import java.time.Instant;

public class InstantConverter implements AttributeConverter<Instant, Timestamp> {
    @Override
    public Timestamp convertToDatabaseColumn(Instant instant) {
        return instant == null ? null : Timestamp.from(instant);
    }

    @Override
    public Instant convertToEntityAttribute(Timestamp timestamp) {
        return timestamp == null ? null : timestamp.toInstant();
    }
}