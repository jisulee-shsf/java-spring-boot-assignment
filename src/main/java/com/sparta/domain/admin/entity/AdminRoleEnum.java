package com.sparta.domain.admin.entity;

public enum AdminRoleEnum {
    MANAGER(Authority.MANAGER),
    STAFF(Authority.STAFF);

    private final String authority;

    AdminRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String MANAGER = "ROLE_MANAGER";
        public static final String STAFF = "ROLE_STAFF";
    }
}