package home.cognizant.pm.api.mapper;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.mapstruct.Mapper;

import home.cognizant.pm.api.model.UserRequest;
import home.cognizant.pm.api.model.UserResponse;
import home.cognizant.pm.service.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
    UserResponse toUserResponse(User user);

    List<UserResponse> toUserResponse(Set<User> users);

    List<UserResponse> toUserResponse(List<User> users);

	User toUser(@Valid UserRequest userRequest);
}
