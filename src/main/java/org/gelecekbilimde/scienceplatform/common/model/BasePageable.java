package org.gelecekbilimde.scienceplatform.common.model;


import com.nimbusds.oauth2.sdk.util.CollectionUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BasePageable extends BaseSort {


	@Range(min = 1, max = 99999999)
	private int page;


	@Range(min = 1, max = 99999999)
	private int pageSize;


	public Pageable toPageable() {

		if (CollectionUtils.isNotEmpty(this.getOrders())) {
			return PageRequest.of(
				this.page - 1,
				this.pageSize,
				this.toSort()
			);
		}

		return PageRequest.of(
			this.page - 1,
			this.pageSize
		);
	}
}
