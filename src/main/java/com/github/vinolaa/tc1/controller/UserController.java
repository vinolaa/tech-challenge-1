package com.github.vinolaa.tc1.controller;

import com.github.vinolaa.tc1.dto.*;
import com.github.vinolaa.tc1.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "Usuários", description = "Gerenciamento de usuários")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Lista todos os usuários")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Operation(summary = "Busca usuários pelo nome")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    @GetMapping("/search")
    public ResponseEntity<List<UserResponse>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(userService.findByName(name));
    }

    @Operation(summary = "Cria um novo Dono")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Dono criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
        @ApiResponse(responseCode = "409", description = "Email ou login já cadastrado",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PostMapping("/dono")
    public ResponseEntity<UserResponse> createDono(@RequestBody @Valid CreateDonoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createDono(request));
    }

    @Operation(summary = "Cria um novo Cliente")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
        @ApiResponse(responseCode = "409", description = "Email ou login já cadastrado",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PostMapping("/cliente")
    public ResponseEntity<UserResponse> createCliente(@RequestBody @Valid CreateClienteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createCliente(request));
    }

    @Operation(summary = "Atualiza dados do usuário")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID id,
                                                   @RequestBody @Valid UpdateUserRequest request) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    @Operation(summary = "Atualiza senha do usuário")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Senha atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
        @ApiResponse(responseCode = "422", description = "Senha atual incorreta ou confirmação não confere",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable UUID id,
                                               @RequestBody @Valid UpdatePasswordRequest request) {
        userService.updatePassword(id, request);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Remove um usuário")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
