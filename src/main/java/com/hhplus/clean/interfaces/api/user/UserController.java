package com.hhplus.clean.interfaces.api.user;

import com.hhplus.clean.application.UserFacade;
import com.hhplus.clean.interfaces.api.dto.UserRequestDTO;
import com.hhplus.clean.interfaces.api.dto.UserResponseDTO;
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
        return  UserResponseDTO.from(userFacade.saveUser(requestDTO.toCommand())) ;
    }

    //단일 사용자 조회
    @GetMapping("/{id}")
    public UserResponseDTO getUser(@PathVariable long id) {
        return UserResponseDTO.from(userFacade.getUser(id));
    }

    // 사용자 목록 조회 API
    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userFacade.getAllUsers().stream()
                .map(UserResponseDTO::from)
                .collect(Collectors.toList());
    }
}
