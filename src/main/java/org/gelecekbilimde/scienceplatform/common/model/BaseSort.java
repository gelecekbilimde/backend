package org.gelecekbilimde.scienceplatform.common.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Sort;

import java.util.List;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseSort {

	@Valid
	protected List<BaseOrder> orders;

	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class BaseOrder {

		@NotBlank
		private String property;

		@NotNull
		private Direction direction;
	}

	public enum Direction {

		ASC,
		DESC;

		public Sort.Direction toDirection() {
			return Sort.Direction.valueOf(this.name());
		}
	}

	protected org.springframework.data.domain.Sort toSort() {
		return org.springframework.data.domain.Sort.by(
			this.orders.stream()
				.map(order -> Sort.Order.by(order.getProperty()).with(order.getDirection().toDirection()))
				.toList()
		);
	}

	public static BaseSort of(final org.springframework.data.domain.Sort sorts) {

		List<BaseOrder> orders = sorts.stream()
			.map(order -> BaseOrder.builder()
				.property(order.getProperty())
				.direction(Direction.valueOf(order.getDirection().toString()))
				.build())
			.toList();

		return BaseSort.builder()
			.orders(orders)
			.build();
	}
}
