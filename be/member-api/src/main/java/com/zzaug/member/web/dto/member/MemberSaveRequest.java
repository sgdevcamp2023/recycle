package com.zzaug.member.web.dto.member;

import com.zzaug.member.web.dto.validator.Certification;
import com.zzaug.member.web.dto.validator.Password;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class MemberSaveRequest {

	@Certification private String certification;
	@Password private String password;
}
