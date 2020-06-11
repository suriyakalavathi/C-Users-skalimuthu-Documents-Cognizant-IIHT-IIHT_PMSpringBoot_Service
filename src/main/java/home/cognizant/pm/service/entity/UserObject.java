package home.cognizant.pm.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Entity
@Table(schema = "pm", name = "user")
@Data @NoArgsConstructor @AllArgsConstructor
public class UserObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;

    @Column(name = "employee_id", nullable = false, unique = true)
    private long employeeId;

    public UserObject() {		
	}
    
   	public UserObject(int i, int j, String fname, String lname) {
		this.userId=i;
		this.employeeId=j;
		this.firstName=fname;
		this.lastName=lname;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public ProjectObject getProject() {
		return project;
	}

	public void setProject(ProjectObject project) {
		this.project = project;
	}

	public TaskObject getTask() {
		return task;
	}

	public void setTask(TaskObject task) {
		this.task = task;
	}

	@Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @With
    private ProjectObject project;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @With
    private TaskObject task;

	public UserObject withProject(ProjectObject persistedProject) {
		// TODO Auto-generated method stub
		return null;
	}
}
