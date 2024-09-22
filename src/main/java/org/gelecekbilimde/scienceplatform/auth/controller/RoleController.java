package org.gelecekbilimde.scienceplatform.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.model.UserRole;
import org.gelecekbilimde.scienceplatform.auth.model.request.RoleChangeRequest;
import org.gelecekbilimde.scienceplatform.auth.service.RoleService;
import org.gelecekbilimde.scienceplatform.common.model.response.SuccessResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PutMapping("/author-requests")
    @PreAuthorize("hasRole('USER')")
    public SuccessResponse<Void> requestToBeAuthor() {
        roleService.userRoleToAuthorRoleRequest();
        return SuccessResponse.success();
    }

    @GetMapping("/author-requests")
    @PreAuthorize("hasRole('ADMIN')")
    public SuccessResponse<List<UserRole>> getAllRequestToBeAuthor() {
        return SuccessResponse.success(roleService.getAllUserRoleToAuthorRoleRequest());
    }

    @DeleteMapping("/author-requests")
    @PreAuthorize("hasRole('ADMIN')")
    public SuccessResponse<Void> deleteRequestToBeAuthor(@RequestBody @Valid RoleChangeRequest request) {
        roleService.deleteUserRoleToAuthorRoleRequest(request);
        return SuccessResponse.success();
    }

    @PutMapping("/moderator-requests")
    @PreAuthorize("hasRole('AUTHOR')")
    public SuccessResponse<Void> requestToBeModerator() {
        roleService.authorRoleToModeratorRoleRequest();
        return SuccessResponse.success();
    }

    @PutMapping("/role-assigments")
    @PreAuthorize("hasRole('ADMIN')")
    public SuccessResponse<Void> makeUserAuthor(@RequestBody @Valid RoleChangeRequest request) {
        roleService.changeUserRole(request);
        return SuccessResponse.success();
    }

}
