package uz.lee.news_app.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.lee.news_app.custom_responses.exceptions.ForbiddenException;
import uz.lee.news_app.user.Users;

@Component
@Aspect
public class CheckPermissionExecutor {

    @Before(value = "@annotation(checkPermissions)")
    public void checkUserPermission(CheckPermissions checkPermissions){
        String permission = checkPermissions.permission();
        Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals(permission))){
            return;
        }

        throw new ForbiddenException("You do not have permission to access this resource to " + checkPermissions.permission());
    }
}
