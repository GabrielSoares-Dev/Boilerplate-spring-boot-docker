package spring_boot_base.spring_boot_base.integration.http.helloController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class HelloIntegrationTest {

  @Autowired private MockMvc mockRequest;

  private String path = "/v1/hello";

  @Test
  public void testHello() throws Exception {
    ResultActions output = this.mockRequest.perform(get(this.path + "/test-name"));

    output.andExpect(status().isOk());
    output.andExpect(jsonPath("$.message").value("Hello test-name!"));
  }
}
