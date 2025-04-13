package com.devmarrima.dscommerce_list.projections;

public interface UserDetailsProjection {
    String getUsername();

    String getPassword();

    Long getRoleId();

    String getAuthority();

}
