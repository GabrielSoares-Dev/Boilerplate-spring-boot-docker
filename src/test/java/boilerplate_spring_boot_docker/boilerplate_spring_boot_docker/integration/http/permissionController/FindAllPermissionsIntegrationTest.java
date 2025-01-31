package boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.integration.http.permissionController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.integration.helpers.BaseAuthenticatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.ResultActions;

public class FindAllPermissionsIntegrationTest extends BaseAuthenticatedTest {
  private String path = "/v1/permission";

  @Test
  @Sql(
      value = "classpath:insert-permissions.sql",
      executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(value = "classpath:reset-permissions.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
  public void testFounded() throws Exception {

    ResultActions output =
        this.request.perform(get(this.path).header("Authorization", this.tokenFormatted));

    output.andExpect(status().isOk());
    output.andExpect(jsonPath("$.message").value("Found permissions"));
    output.andExpect(jsonPath("$.content[0].id").value(300));
  }
}
