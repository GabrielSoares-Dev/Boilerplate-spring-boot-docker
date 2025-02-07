package boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.integration.http.permissionController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.helpers.BaseAuthenticatedTest;
import boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.helpers.UserEmail;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.domain.enums.PermissionEnum;

public class CreatePermissionIntegrationTest extends BaseAuthenticatedTest {
  private String path = "/v1/permission";

  @BeforeEach
  public void setupEach() throws IllegalArgumentException, UnsupportedEncodingException {
    this.userEmail = UserEmail.ADMIN();
    this.generateAuthorizationToken();
  }

  @Test
  public void testCreated() throws Exception {
    Map<String, String> input = new HashMap<>();
    input.put("name", "test-name");
    input.put("description", "test-description");

    String inputJson = new ObjectMapper().writeValueAsString(input);

    ResultActions output = this.request.perform(
        post(this.path)
            .contentType(MediaType.APPLICATION_JSON)
            .content(inputJson)
            .header("Authorization", this.tokenFormatted));

    output.andExpect(status().isCreated());
    output.andExpect(jsonPath("$.message").value("Permission created successfully"));
  }

  @Test
  public void testAlreadyExists() throws Exception {
  Map<String, String> input = new HashMap<>();
  input.put("name", PermissionEnum.CREATE_PERMISSION.toString());
  input.put("description", "test-description");

  String inputJson = new ObjectMapper().writeValueAsString(input);

  ResultActions output = this.request.perform(
  post(this.path)
  .contentType(MediaType.APPLICATION_JSON)
  .content(inputJson)
  .header("Authorization", this.tokenFormatted));

  output.andExpect(status().isBadRequest());
  output.andExpect(jsonPath("$.message").value("Permission already exists"));
  }

  @Test
  public void testEmptyFields() throws Exception {
  Map<String, String> input = new HashMap<>();
  input.put("name", null);
  input.put("description", null);

  String inputJson = new ObjectMapper().writeValueAsString(input);

  ResultActions output = this.request.perform(
  post(this.path)
  .contentType(MediaType.APPLICATION_JSON)
  .content(inputJson)
  .header("Authorization", this.tokenFormatted));

  output.andExpect(status().isUnprocessableEntity());
  }
}
