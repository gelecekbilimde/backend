package org.gelecekbilimde.scienceplatform.user.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gelecekbilimde.scienceplatform.common.model.request.PagingRequest;

@EqualsAndHashCode(callSuper = true)
@Data
public class RemoveFollower extends PagingRequest {

	public String followerId;

}
