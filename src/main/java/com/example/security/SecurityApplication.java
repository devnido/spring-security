package com.example.security;

import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.security.persistence.entity.PermissionEntity;
import com.example.security.persistence.entity.RoleEntity;
import com.example.security.persistence.entity.RoleEnum;
import com.example.security.persistence.entity.UserEntity;
import com.example.security.persistence.repository.UserRepository;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
			/* CREATE PERMISSIONS */
			PermissionEntity createPermission = PermissionEntity
					.builder()
					.name("CREATE")
					.build();

			PermissionEntity readPermission = PermissionEntity
					.builder()
					.name("READ")
					.build();

			PermissionEntity updatePermission = PermissionEntity
					.builder()
					.name("UPDATE")
					.build();

			PermissionEntity deletePermission = PermissionEntity
					.builder()
					.name("DELETE")
					.build();

			PermissionEntity refactorPermission = PermissionEntity
					.builder()
					.name("REFACTOR")
					.build();

			/* CREATE ROLES */

			RoleEntity roleAdmin = RoleEntity
					.builder()
					.role(RoleEnum.ADMIN)
					.permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			RoleEntity roleUser = RoleEntity
					.builder()
					.role(RoleEnum.USER)
					.permissions(Set.of(createPermission, readPermission))
					.build();

			RoleEntity roleInvited = RoleEntity
					.builder()
					.role(RoleEnum.INVITED)
					.permissions(Set.of(readPermission))
					.build();

			RoleEntity roleDeveloper = RoleEntity
					.builder()
					.role(RoleEnum.DEVELOPER)
					.permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
					.build();

			/* CREATE USERS */
			UserEntity userSantiago = UserEntity
					.builder()
					.username("santiago")
					.password("$2a$10$16xoE47k3iSSj.9Mv0.S0.9O/Pk9lXsWDH9Fza5IzYKyQLy90Qp1S")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialsNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();

			UserEntity userDaniel = UserEntity
					.builder()
					.username("daniel")
					.password("$2a$10$16xoE47k3iSSj.9Mv0.S0.9O/Pk9lXsWDH9Fza5IzYKyQLy90Qp1S")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialsNoExpired(true)
					.roles(Set.of(roleUser))
					.build();

			UserEntity userAndrea = UserEntity
					.builder()
					.username("andrea")
					.password("$2a$10$16xoE47k3iSSj.9Mv0.S0.9O/Pk9lXsWDH9Fza5IzYKyQLy90Qp1S")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialsNoExpired(true)
					.roles(Set.of(roleInvited))
					.build();

			UserEntity userLuis = UserEntity
					.builder()
					.username("luis")
					.password("$2a$10$16xoE47k3iSSj.9Mv0.S0.9O/Pk9lXsWDH9Fza5IzYKyQLy90Qp1S")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialsNoExpired(true)
					.roles(Set.of(roleDeveloper))
					.build();

			userRepository.saveAll(List.of(userSantiago, userDaniel, userAndrea, userLuis));

		};
	}

}
