package com.zzaug.security.authentication.authority;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

@Getter
@ToString
public enum Roles {
	ROLE_USER("ROLE_USER") {
		@Override
		public GrantedAuthority getAuthority() {
			return UserAuthority.builder().build();
		}
	};

	Roles(String role) {
		this.role = role;
	}

	private final String role;

	public abstract GrantedAuthority getAuthority();
}
