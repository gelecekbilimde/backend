package org.gelecekbilimde.scienceplatform.post.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.AssertTrue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gelecekbilimde.scienceplatform.common.model.request.PagingRequest;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdminPostListRequest extends PagingRequest {

	public Boolean isActive;

	@JsonIgnore
	@AssertTrue
	@Override
	public boolean isOrderPropertyAccepted() {
		final Set<String> acceptedFilterFields = Set.of();
		return this.isPropertyAccepted(acceptedFilterFields);
	}

}
