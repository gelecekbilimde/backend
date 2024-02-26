package org.gelecekbilimde.scienceplatform.post.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gelecekbilimde.scienceplatform.common.PagingRequest;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdminPostListRequest extends PagingRequest {

	public Boolean isActive;

}
