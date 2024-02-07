package org.gelecekbilimde.scienceplatform.common.mail.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ConfirmAccountRequest {

	@NotNull(message = "Confirmation Token mustn't be empty")
	private String confirmationToken;
}
