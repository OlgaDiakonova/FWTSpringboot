package com.go2it.springboot.util.dtoEntityConverter;

import com.go2it.springboot.entity.User;
import com.go2it.springboot.entity.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    private static ModelMapper modelMapper;

    public UserConverter() {
    }

    @Autowired
    public UserConverter(ModelMapper modelMapper) {
        UserConverter.modelMapper = modelMapper;
        modelMapper.addMappings(userToDTOMapping);
        modelMapper.addMappings(DTOToUserMapping);
    }

    PropertyMap<User, UserDTO> userToDTOMapping = new PropertyMap<User, UserDTO>() {
        @Override
        protected void configure() {
            map().setFirst_name(source.getFirst_name());
            map().setLast_name(source.getLast_name());
        }
    };

    PropertyMap<UserDTO, User> DTOToUserMapping = new PropertyMap<UserDTO, User>() {
        @Override
        protected void configure() {
            map().setFirst_name(source.getFirst_name());
            map().setLast_name(source.getLast_name());
        }
    };

    public static UserDTO convertUserToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public static User convertDTOToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
