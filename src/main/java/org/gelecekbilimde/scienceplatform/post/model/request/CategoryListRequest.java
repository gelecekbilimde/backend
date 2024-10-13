package org.gelecekbilimde.scienceplatform.post.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.AssertTrue;
import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.common.model.request.PagingRequest;

import java.util.Set;

@Getter
@Setter
public class CategoryListRequest extends PagingRequest {

	@JsonIgnore
	@AssertTrue
	@Override
	public boolean isOrderPropertyAccepted() {
		final Set<String> acceptedFilterFields = Set.of("createdAt");
		return this.isPropertyAccepted(acceptedFilterFields);
	}

}
