package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.AbstractBaseEntity;

import static ru.javawebinar.topjava.util.UserUtil.DEFAULT_CALORIES_PER_DAY;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.javawebinar.topjava.AuthorizedUser;

import static java.util.Objects.requireNonNull;

public class SecurityUtil {
    private SecurityUtil() {
    }

    public static AuthorizedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public static AuthorizedUser get() {
        AuthorizedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public static int authUserId() {
        return get().getUserTo().id();
    }


    public static int authUserCaloriesPerDay() {
        return get().getUserTo().getCaloriesPerDay();
    }
}