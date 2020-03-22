package eu.kaszkowiak.demostore.product;

import eu.kaszkowiak.demostore.common.AuditableEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductE extends AuditableEntity {

    @Size(max = 50)
    @NotBlank
    private String name;

    private BigDecimal price;

    @NotNull
    private boolean isDeleted;
}
