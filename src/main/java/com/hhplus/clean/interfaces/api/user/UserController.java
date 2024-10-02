package com.hhplus.clean.interfaces.api.user;

import com.hhplus.clean.application.UserFacade;
import com.hhplus.clean.domain.user.UserEntity;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    //사용자 생성
    @PostMapping
    public UserResponseDTO createUser(@RequestBody UserRequestDTO requestDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(requestDTO.getUsername());

        UserEntity createUser = userFacade.saveUser(userEntity);
        return  UserResponseDTO.from(createUser);

    }

    //단일 사용자 조회
    @GetMapping("/{id}")
    public UserResponseDTO getUser(@PathVariable long id) {
        UserEntity userEntity = userFacade.getUser(id);
        return UserResponseDTO.from(userEntity);
    }

    // 사용자 목록 조회 API
    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        List<UserEntity> users = userFacade.getAllUsers();
        return users.stream()
                .map(UserResponseDTO::from)
                .collect(Collectors.toList());
    }
}
