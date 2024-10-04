package org.gelecekbilimde.scienceplatform.post.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryCreateRequest {

	@NotBlank(message = "Category name cannot be blank")
	@Size(max = 36, message = "Category name must be less than 36 characters")
	private String name;

	@NotBlank(message = "Description cannot be blank")
	@Size(max = 255, message = "Description must be less than 255 characters")
	private String description;

	@NotNull(message = "Order number cannot be null")
	@PositiveOrZero(message = "Order number must be zero or a positive integer")
	private Integer order;

	@NotBlank(message = "Slug cannot be blank")
	@Size(max = 36, message = "Slug must be less than 36 characters")
	private String slug;

	@Size(max = 36, message = "Icon must be less than 36 characters")
	private String icon;

	@PositiveOrZero(message = "Parent ID must be zero or a positive number")
	private Long parentId;

}
