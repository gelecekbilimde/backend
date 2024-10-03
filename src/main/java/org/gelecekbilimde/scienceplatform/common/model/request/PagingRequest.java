package org.gelecekbilimde.scienceplatform.common.model.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.gelecekbilimde.scienceplatform.common.model.BasePageable;
import org.gelecekbilimde.scienceplatform.common.model.BaseSort;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
public abstract class PagingRequest {

	@Valid
	@NotNull
	protected BasePageable pageable;

	public abstract boolean isOrderPropertyAccepted();

	@SuppressWarnings("all")
	public boolean isPropertyAccepted(final Set<String> acceptedProperties) {

		if (this.pageable == null || CollectionUtils.isEmpty(this.pageable.getOrders())) {
			return true;
		}

		for (BaseSort.BaseOrder order : this.pageable.getOrders()) {
			if (StringUtils.isBlank(order.getProperty()) || order.getDirection() == null) {
				return true;
			}
		}

		final List<BaseSort.BaseOrder> orders = this.pageable.getOrders();
		if (CollectionUtils.isEmpty(orders)) {
			return true;
		}

		final boolean isAnyDirectionEmpty = orders.stream().anyMatch(order -> order.getDirection() == null);
		final boolean isAnyPropertyEmpty = orders.stream().anyMatch(order -> order.getProperty() == null);
		if (isAnyDirectionEmpty || isAnyPropertyEmpty) {
			return true;
		}

		return orders.stream()
			.map(BaseSort.BaseOrder::getProperty)
			.allMatch(acceptedProperties::contains);
	}
}
