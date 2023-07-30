package com.internal.service.template.dto;

import java.util.*;
import java.util.stream.Collectors;

import com.internal.service.template.model.UserV2;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthOutput {

    private long userId;

    private String name;

    private String password;

    private String email;

    private boolean active;

    private String editedBy;

    private Date modifiedOn;

	private String jwt;

    private String userName;

    private List<GrantedAuthority> authorities;


    public AuthOutput(UserV2 user, String jWT_STRING) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.userName=user.getUserName();
        this.active = user.isActive();
        this.editedBy = user.getEditedBy();
        this.modifiedOn = user.getModifiedOn();
        this.jwt = jWT_STRING;
        setAuthorities(Arrays.stream(user.getRole().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()));
    }

}
