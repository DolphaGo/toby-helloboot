package tobyspring

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

class HelloApiTest {

    @Test
    fun helloApi() {
        // http localhost:8080/hello?name=DolphaGo
        val rest = TestRestTemplate() // 응답 자체를 가져와서 상태코드는 무엇이고 이런 것들을 순수하게 받아오고 싶다면, RestTemplate 보다 TestRestTemplate 권장

        val name = "DolphaGo"
        val response = rest.getForEntity("http://localhost:8080/hello?name=${name}", String::class.java)
        // 웹 응답의 3가지 요소, statusCode, headers, body

        // status: code 200
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)

        // header: (content-type) text/plain
        assertThat(response.headers.getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE) // ;charset=ISO-8859-1 인코딩 정보 제외

        // body: Hello DolphaGo
        assertThat(response.body).isEqualTo("Hello DolphaGo")
    }

    @Test
    fun `helloApi_실패`() {
        // http localhost:8080/hello?name=DolphaGo
        val rest = TestRestTemplate() // 응답 자체를 가져와서 상태코드는 무엇이고 이런 것들을 순수하게 받아오고 싶다면, RestTemplate 보다 TestRestTemplate 권장

        val name = ""
        val response = rest.getForEntity("http://localhost:8080/hello?name=${name}", String::class.java)
        // 웹 응답의 3가지 요소, statusCode, headers, body

        // status: code 500
        assertThat(response.statusCode).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
