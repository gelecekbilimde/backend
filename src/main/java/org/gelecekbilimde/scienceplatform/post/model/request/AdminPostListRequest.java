package org.gelecekbilimde.scienceplatform.post.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.AssertTrue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gelecekbilimde.scienceplatform.common.model.request.PagingRequest;
import org.gelecekbilimde.scienceplatform.post.model.AdminPostFilter;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdminPostListRequest extends PagingRequest {

	private AdminPostFilter filter;

	@JsonIgnore
	@AssertTrue
	@Override
	public boolean isOrderPropertyAccepted() {
		final Set<String> acceptedFilterFields = Set.of();
		return this.isPropertyAccepted(acceptedFilterFields);
	}

}
