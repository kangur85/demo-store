package eu.kaszkowiak.demostore.common;

import java.util.Collection;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public interface DtoMapper<ENTITY, DTO> {
    DTO toDto(ENTITY entity);

    ENTITY toEntity(DTO dto);

    default DTO mapExecuteAndMapBack(DTO dto, UnaryOperator<ENTITY> function) {
        ENTITY entity = toEntity(dto);
        entity = function.apply(entity);
        return toDto(entity);
    }

    default List<DTO> toDto(Collection<ENTITY> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }
}
