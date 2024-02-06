// package com.zzaug.member.persistence.auth;
//
// import com.zzaug.member.entity.auth.BlackTokenAuthEntity;
// import com.zzaug.member.entity.auth.TokenData;
// import com.zzaug.member.persistence.support.checker.DeletedFalse;
// import com.zzaug.member.persistence.support.checker.ZzuagRepository;
// import org.springframework.data.jpa.repository.JpaRepository;
//
// @ZzuagRepository
// public interface BlackTokenAuthRepository extends JpaRepository<BlackTokenAuthEntity, Long> {
//
//	@DeletedFalse
//	boolean existsByTokenAndDeletedFalse(TokenData token);
// }
