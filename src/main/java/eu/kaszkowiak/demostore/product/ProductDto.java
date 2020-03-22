package eu.kaszkowiak.demostore.product;

import eu.kaszkowiak.demostore.common.AuditableDto;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ApiModel("Product")
public class ProductDto extends AuditableDto {

    @NotBlank
    @Size(max = 50)
    private String name;

    private BigDecimal price;
}
