package eu.kaszkowiak.demostore.exception;

import eu.kaszkowiak.demostore.common.AuditableEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class NotFoundException extends RuntimeException {
    private final AuditableEntity entity;
}
