package home.cognizant.pm.api.mapper;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;

import home.cognizant.pm.api.model.UserRequest;
import home.cognizant.pm.api.model.UserResponse;
import home.cognizant.pm.service.entity.UserObject;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserObject toUser(UserRequest userRequest);

    UserResponse toUserResponse(UserObject user);

    List<UserResponse> toUserResponse(Set<UserObject> users);

    List<UserResponse> toUserResponse(List<UserObject> users);
}
