package com.ipinyou.base.freemarker.shiro;
import freemarker.template.SimpleHash;

/**
 * Shortcut for injecting the tags into Freemarker
 *
 * <p>Usage: cfg.setSharedVeriable("shiro", new ShiroTags());</p>
 * @see https://github.com/jagregory/shiro-freemarker-tags
 */
@SuppressWarnings("serial")
public class ShiroTags extends SimpleHash {
    public ShiroTags() {
        put("authenticated", new AuthenticatedTag());
        put("guest", new GuestTag());
//        put("hasAnyRoles", new HasAnyRolesTag());
        put("hasPermission", new HasPermissionTag());
//        put("hasRole", new HasRoleTag());
        put("lacksPermission", new LacksPermissionTag());
//        put("lacksRole", new LacksRoleTag());
        put("notAuthenticated", new NotAuthenticatedTag());
        put("principal", new PrincipalTag());
        put("user", new UserTag());
        put("hasPermissionOne",new HasPermissionOneTag());
        put("lackPermissionOne",new LacksPermissionOneTag());
    }
}