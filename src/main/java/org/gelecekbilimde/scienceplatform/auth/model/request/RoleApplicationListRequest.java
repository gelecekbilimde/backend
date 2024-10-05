package org.gelecekbilimde.scienceplatform.auth.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.auth.model.RoleApplicationFilter;
import org.gelecekbilimde.scienceplatform.common.model.request.PagingRequest;

import java.util.Set;

@Getter
@Setter
public class RoleApplicationListRequest extends PagingRequest {

	@Valid
	private RoleApplicationFilter filter;


	@JsonIgnore
	@AssertTrue
	@Override
	public boolean isOrderPropertyAccepted() {
		final Set<String> acceptedFilterFields = Set.of("createdAt");
		return this.isPropertyAccepted(acceptedFilterFields);
	}

}
