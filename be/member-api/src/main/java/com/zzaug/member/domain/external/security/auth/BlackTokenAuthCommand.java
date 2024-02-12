package com.zzaug.member.domain.external.security.auth;

public interface BlackTokenAuthCommand {

	void execute(String accessToken, String refreshToken);
}
