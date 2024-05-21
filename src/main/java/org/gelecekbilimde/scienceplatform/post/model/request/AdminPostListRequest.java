package org.gelecekbilimde.scienceplatform.post.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gelecekbilimde.scienceplatform.common.model.request.PagingRequest;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdminPostListRequest extends PagingRequest {

	public Boolean isActive;

}
