package com.zzaug.member.domain.model.auth;

import java.util.Objects;
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
public class TryCountElement {
	private int tryCount;
	private Long emailAuthLogId;

	public boolean isNew() {
		return !Objects.isNull(emailAuthLogId);
	}

	public void plus() {
		this.tryCount = this.tryCount + 1;
	}
}
