package eu.kaszkowiak.demostore.common


import com.fasterxml.jackson.databind.ObjectMapper
import eu.kaszkowiak.demostore.DemoStoreApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@SpringBootTest(classes = DemoStoreApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTest extends Specification {

    @Autowired
    MockMvc client

    @Autowired
    ObjectMapper objectMapper

    def get(map = [:]) {
        perform(HttpMethod.GET, map)
    }

    def post(map = [:]) {
        perform(HttpMethod.POST, map)
    }

    def put(map = [:]) {
        perform(HttpMethod.PUT, map)
    }

    def delete(map = [:]) {
        perform(HttpMethod.DELETE, map)
    }

    private perform(HttpMethod httpMethod, map = [:]) {
        def request = MockMvcRequestBuilders.request(httpMethod, map.url as String)

        if (map.content) {
            request.content(objectMapper.writeValueAsString(map.content))
            request.contentType(MediaType.APPLICATION_JSON)
        }

        int statusCode = map.status ?: 200
        def response = client.perform(request)
                .andExpect(status().is(statusCode))
                .andReturn()
                .response

        if (map.resultClass) {
            return objectMapper.readValue(response.getContentAsString(), map.resultClass)
        }
        response
    }
}