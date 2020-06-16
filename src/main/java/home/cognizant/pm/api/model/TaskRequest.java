package home.cognizant.pm.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class TaskRequest {

    @NotNull(message = "User ID is required")
    private long taskId;

    public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getParentTaskId() {
		return parentTaskId;
	}

	public void setParentTaskId(long parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	private long userId;

    @NotNull(message = "Parent Task ID is required")
    private long parentTaskId;

    @NotNull(message = "Project ID is required")
    private long projectId;

    @NotEmpty(message = "Enter Task Name")
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endDate;

    @Min(value = 1, message = "Priority between 1 and 30")
    @Max(value = 30, message = "Priority between 1 and 30")
    private int priority;

    private boolean status;
}
