import com.example.demo.common.CommonResponse
import com.example.demo.exception.KhErrorCode
import com.example.demo.login.LoginRequest
import com.example.demo.login.mapper.LoginMapper
import com.example.demo.login.service.impl.LoginServiceImpl
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify;
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@ExtendWith(MockitoExtension::class)
class LoginServiceImplTest {
    @Mock
    private lateinit var mockLoginMapper: LoginMapper

    @Mock
    private lateinit var mockPasswordEncoder : BCryptPasswordEncoder

    @InjectMocks
    private lateinit var loginServiceImpl : LoginServiceImpl

    @BeforeEach
    fun before(){
        println("==================== test start ====================")
    }

    @AfterEach
    fun after(){
        println("==================== test end ======================")
    }

    @Test
    @DisplayName("비밀번호가 일치하면, 성공 응답(data=true, code=0000)을 반환한다")
    fun `checkPw should return success when password matches`() {
        // given
        val loginRequest = LoginRequest(id = "testuser", password = "password123")
        val savedHashedPassword = "a_very_long_hashed_password_from_db"

        // 1. mockLoginMapper의 getPassword("testuser")가 호출되면, 위에서 정의한 암호화된 비밀번호를 반환하도록 설정합니다.
        `when`(mockLoginMapper.getPassword(loginRequest.id)).thenReturn(savedHashedPassword)

        // 2. mockPasswordEncoder의 matches 메서드가 호출되면, true를 반환하도록 설정합니다. (비밀번호 일치 상황)
        `when`(mockPasswordEncoder.matches(loginRequest.password, savedHashedPassword)).thenReturn(true)

        // When
        val response = loginServiceImpl.checkPw(loginRequest)
        println(response)

        // Then
        Assertions.assertThat(true).isEqualTo(response.data)
        Assertions.assertThat("0000").isEqualTo(response.code)

        // loginMapper.getPassword와 passwordEncoder.matches가 각각 1번씩 호출되었는지 검증
        verify(mockLoginMapper).getPassword(loginRequest.id)
        verify(mockPasswordEncoder).matches(loginRequest.password, savedHashedPassword)
    }

    @Test
    @DisplayName("비밀번호가 불일치하면, 실패 응답(data=false, code=1001)을 반환한다")
    fun `checkPw should return failure when password does not match`() {
        // Given
        val loginRequest = LoginRequest(id = "testuser", password = "wrong_password")
        val savedHashedPassword = "a_very_long_hashed_password_from_db"

        `when`(mockLoginMapper.getPassword(loginRequest.id)).thenReturn(savedHashedPassword)
        // 비밀번호가 불일치하는 상황을 시뮬레이션하기 위해 false를 반환하도록 설정합니다.
        `when`(mockPasswordEncoder.matches(loginRequest.password, savedHashedPassword)).thenReturn(false)

        // When
        val response = loginServiceImpl.checkPw(loginRequest)

        // Then
        Assertions.assertThat(false).isEqualTo(response.data)
        Assertions.assertThat("1001").isEqualTo(response.code)
    }

    @Test
    @DisplayName("사용자 조회 중 예외가 발생하면, 에러 응답(INVALID_INPUT)을 반환한다")
    fun `checkPw should return error response when mapper throws exception`() {
        // Given
        val loginRequest = LoginRequest(id = "non_existent_user", password = "any_password")

        // loginMapper.getPassword가 호출되면 RuntimeException을 던지도록 설정합니다. (사용자 없음, DB 오류 등 시뮬레이션)
        `when`(mockLoginMapper.getPassword(loginRequest.id)).thenThrow(RuntimeException("Simulated DB Error"))

        // When
        val response = loginServiceImpl.checkPw(loginRequest)

        // Then
        Assertions.assertThat(false).isEqualTo(response.data)
        Assertions.assertThat(KhErrorCode.INVALID_INPUT.code).isEqualTo(response.code)
    }
}