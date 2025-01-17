package spring_boot_to_do_list.spring_boot_to_do_list.integration.http.taskController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class FindTaskByIdIntegrationTest {
  @Autowired private MockMvc mockMvc;

  private String path = "/v1/tasks";

  @Test
  @Sql(value = "classpath:insert-task.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(value = "classpath:reset-tasks.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
  public void testFound() throws Exception {
    ResultActions output = this.mockMvc.perform(get(this.path + "/1"));

    output.andExpect(status().isOk());
    output.andExpect(jsonPath("$.message").value("Task found successfully"));
  }
}
