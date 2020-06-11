package home.cognizant.pm.api.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor
public class ParentTaskRequest {

    @NotNull(message = "Parent Task ID is required")
    private long parentTaskId;

    @NotEmpty(message = "Enter the Name")
    private String name;
}
