package home.cognizant.pm.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data @NoArgsConstructor @AllArgsConstructor
public class UserRequest {

    @NotNull(message = "User ID Required")
    private long userId;

    @NotNull(message = "Employee ID Required")
    private long employeeId;

    @NotEmpty(message = "Enter First Name")
    private String firstName;

    @NotEmpty(message = "Enter Last Name")
    private String lastName;

    @NotNull(message = "Project ID Required")
    private long projectId;

    @NotNull(message = "Task ID Required")
    private long taskId;
}
