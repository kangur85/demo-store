package eu.kaszkowiak.demostore.util;

import eu.kaszkowiak.demostore.common.AuditableDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ControllerUtils {
    public static void validateId(AuditableDto dto, Long id) {
        if (dto == null) {
            throw new IllegalArgumentException("cannot update with null object");
        }
        if (!dto.getId().equals(id)) {
            throw new IllegalArgumentException("Id mismatch");
        }
    }
}
