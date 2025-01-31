package boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.integration.http.roleController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.integration.helpers.BaseAuthenticatedTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.ResultActions;

public class CreateRoleIntegrationTest extends BaseAuthenticatedTest {
  private String path = "/v1/role";

  @Test
  @Sql(value = "classpath:reset-roles.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
  public void testCreated() throws Exception {
    Map<String, String> input = new HashMap<>();
    input.put("name", "test-name");
    input.put("description", "test-description");

    String inputJson = new ObjectMapper().writeValueAsString(input);

    ResultActions output =
        this.request.perform(
            post(this.path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson)
                .header("Authorization", this.tokenFormatted));

    output.andExpect(status().isCreated());
    output.andExpect(jsonPath("$.message").value("Role created successfully"));
  }

  @Test
  @Sql(value = "classpath:insert-roles.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(value = "classpath:reset-roles.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
  public void testAlreadyExists() throws Exception {
    Map<String, String> input = new HashMap<>();
    input.put("name", "test-name");
    input.put("description", "test-description");

    String inputJson = new ObjectMapper().writeValueAsString(input);

    ResultActions output =
        this.request.perform(
            post(this.path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson)
                .header("Authorization", this.tokenFormatted));

    output.andExpect(status().isBadRequest());
    output.andExpect(jsonPath("$.message").value("Role already exists"));
  }

  @Test
  public void testEmptyFields() throws Exception {
    Map<String, String> input = new HashMap<>();
    input.put("name", null);
    input.put("description", null);

    String inputJson = new ObjectMapper().writeValueAsString(input);

    ResultActions output =
        this.request.perform(
            post(this.path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson)
                .header("Authorization", this.tokenFormatted));

    output.andExpect(status().isUnprocessableEntity());
  }
}
