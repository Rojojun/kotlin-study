package com.rojojun.kopring.service

import com.rojojun.kopring.dto.SignUpDto
import com.rojojun.kopring.entity.User
import com.rojojun.kopring.repository.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootTest
class UserServiceTest {
    @Autowired
    lateinit var userService: UserService
    @MockBean
    lateinit var userRepository: UserRepository
    @MockBean
    lateinit var passwordEncoder: PasswordEncoder

    @Test
    fun `유저 회원가입 테스트`() {
        // given
        val signUpDto = SignUpDto(
            email = "test@test.com",
            nickname = "test",
            password = "test_password",
            checkPassword = "test_password"
        );

        val encodedPassword = "encoded_test_password"
        val user = User(
            email = "test@test.com",
            nickname = "test",
            password = encodedPassword,
        )
        whenever(passwordEncoder.encode(signUpDto.password)).thenReturn(encodedPassword)
        whenever(userRepository.save(signUpDto.toDomain())).thenReturn(user)

        // when
        val savedUser = userService.signUp(signUpDto);

        // then
        assertThat(savedUser.email).isEqualTo(user.email)
        println(savedUser.password)
    }

    @Test
    fun `User 중복 체크`() {

    }
}