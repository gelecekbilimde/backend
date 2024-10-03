package org.gelecekbilimde.scienceplatform.auth.controller;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.service.RoleApplicationService;
import org.gelecekbilimde.scienceplatform.common.model.response.SuccessResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class RoleSelfApplicationController {

    private final RoleApplicationService roleApplicationService;


    @PostMapping("/role-application/self/author")
    @PreAuthorize("hasAnyAuthority('role:application:create:self:author')")
    SuccessResponse<Void> createAuthorApplication() {

        roleApplicationService.createAuthorApplication();
        return SuccessResponse.success();
    }

    @PostMapping("/role-application/self/moderator")
    @PreAuthorize("hasAnyAuthority('role:application:create:self:moderator')")
    SuccessResponse<Void> createModeratorApplication() {

        roleApplicationService.createModeratorApplication();
        return SuccessResponse.success();
    }

}
