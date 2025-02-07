package boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.integration.http.userController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.domain.enums.RoleEnum;
import boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.infra.models.Role;
import boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.infra.repositories.role.RoleJpaRepository;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class CreateUserIntegrationTest {
  @Autowired
  private RoleJpaRepository roleRepository;

  @Autowired
  private MockMvc request;

  private ObjectMapper objectMapper;

  private String path = "/v1/user";

  private void resetRoles() {
    this.roleRepository.deleteAll();
  }

  private void createAdminRole() {
    Role role = new Role();
    role.setName(RoleEnum.ADMIN.toString());
    role.setDescription("test-role");
    this.roleRepository.save(role);
  }

  @BeforeEach
  public void setUp() {
    this.resetRoles();
    this.createAdminRole();
    objectMapper = new ObjectMapper();
  }

  @Test
  public void testCreated() throws Exception {
    Map<String, String> input = new HashMap<>();
    input.put("name", "Boilerplate");
    input.put("email", "boilerplate@gmail.com");
    input.put("phoneNumber", "11942421224");
    input.put("password", "Boilerplate@2023");

    String inputJson = objectMapper.writeValueAsString(input);
    ResultActions output = this.request.perform(
        post(this.path).contentType(MediaType.APPLICATION_JSON).content(inputJson));

    output.andExpect(status().isCreated());
    output.andExpect(jsonPath("$.message").value("User created successfully"));
  }

  @Test
  @Sql(value = "classpath:insert-users.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
  public void testNotCreatedIfUserAlreadyExists() throws Exception {
    Map<String, String> input = new HashMap<>();
    input.put("name", "Boilerplate");
    input.put("email", "john.doe@example.com");
    input.put("phoneNumber", "11942421224");
    input.put("password", "Boilerplate@2023");

    String inputJson = objectMapper.writeValueAsString(input);
    ResultActions output = this.request.perform(
        post(this.path).contentType(MediaType.APPLICATION_JSON).content(inputJson));

    output.andExpect(status().isBadRequest());
    output.andExpect(jsonPath("$.message").value("User already exists"));
  }

  @Test
  public void testEmptyFields() throws Exception {
    Map<String, String> input = new HashMap<>();
    input.put("name", null);
    input.put("email", null);
    input.put("phoneNumber", null);
    input.put("password", null);

    String inputJson = objectMapper.writeValueAsString(input);
    ResultActions output = this.request.perform(
        post(this.path).contentType(MediaType.APPLICATION_JSON).content(inputJson));

    output.andExpect(status().isUnprocessableEntity());
  }
}
