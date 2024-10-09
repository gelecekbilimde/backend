package org.gelecekbilimde.scienceplatform.user.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.common.model.BaseDomainModel;
import org.gelecekbilimde.scienceplatform.user.model.enums.UserVerificationStatus;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class UserVerification extends BaseDomainModel {

    private String id;
    private User user;
    private UserVerificationStatus status;


    public void complete() {
        this.status = UserVerificationStatus.COMPLETED;
    }


    public boolean isCompleted() {
        return this.status == UserVerificationStatus.COMPLETED;
    }

}
