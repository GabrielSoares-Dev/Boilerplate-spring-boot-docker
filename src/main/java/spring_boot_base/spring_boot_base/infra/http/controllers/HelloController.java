package spring_boot_base.spring_boot_base.infra.http.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring_boot_base.spring_boot_base.application.dtos.useCases.hello.HelloUseCaseInputDto;
import spring_boot_base.spring_boot_base.application.services.LoggerServiceInterface;
import spring_boot_base.spring_boot_base.application.useCases.hello.HelloUseCase;
import spring_boot_base.spring_boot_base.infra.helpers.BaseResponse;

@RestController
@RequestMapping("/v1/hello")
@Tag(name = "Hello API", description = "Endpoints for Hello operations")
public class HelloController {
  @Autowired private LoggerServiceInterface loggerService;

  private String logContext = "HelloController";

  @Autowired private HelloUseCase helloUseCase;

  @Operation(summary = "Say hello", description = "Returns a greeting message")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Success",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Map.class),
                    examples =
                        @ExampleObject(
                            value = "{\"message\": \"Hello Example!\", \"statusCode\": 200}"))),
      })
  @GetMapping("/{name}")
  public ResponseEntity<Map<String, Object>> hello(@PathVariable String name) {
    this.loggerService.debug(String.format("Start %s hello with input: ", this.logContext), name);
    String output = this.helloUseCase.run(new HelloUseCaseInputDto(name));
    this.loggerService.debug("output: ", output);

    return BaseResponse.success(output, HttpStatus.OK);
  }
}
