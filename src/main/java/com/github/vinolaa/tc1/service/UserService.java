package com.github.vinolaa.tc1.service;

import com.github.vinolaa.tc1.dto.*;
import com.github.vinolaa.tc1.model.Cliente;
import com.github.vinolaa.tc1.model.Dono;
import com.github.vinolaa.tc1.model.Endereco;
import com.github.vinolaa.tc1.model.User;
import com.github.vinolaa.tc1.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
            .stream()
            .map(UserResponse::from)
            .toList();
    }

    public List<UserResponse> findByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name)
            .stream()
            .map(UserResponse::from)
            .toList();
    }

    @Transactional
    public UserResponse createDono(CreateDonoRequest request) {
        Endereco endereco = null;

        if (request.endereco() != null) {
            endereco = new Endereco(
                request.endereco().logradouro(),
                request.endereco().numero(),
                request.endereco().complemento(),
                request.endereco().bairro(),
                request.endereco().cidade(),
                request.endereco().uf(),
                request.endereco().cep()
            );
        }

        Dono dono = new Dono(
            request.name(), request.email(), request.login(),
            passwordEncoder.encode(request.password()),
            endereco, request.cnpj()
        );

        return UserResponse.from(userRepository.save(dono));
    }

    @Transactional
    public UserResponse createCliente(CreateClienteRequest request) {
        Endereco endereco = null;

        if (request.endereco() != null) {
            endereco = new Endereco(
                request.endereco().logradouro(),
                request.endereco().numero(),
                request.endereco().complemento(),
                request.endereco().bairro(),
                request.endereco().cidade(),
                request.endereco().uf(),
                request.endereco().cep()
            );
        }

        Cliente cliente = new Cliente(
            request.name(), request.email(), request.login(),
            passwordEncoder.encode(request.password()),
            endereco, request.cpf()
        );

        return UserResponse.from(userRepository.save(cliente));
    }

    @Transactional
    public void deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado: " + id);
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public UserResponse updateUser(UUID id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado: " + id));

        Endereco endereco = null;
        if (request.endereco() != null) {
            endereco = new Endereco(
                request.endereco().logradouro(),
                request.endereco().numero(),
                request.endereco().complemento(),
                request.endereco().bairro(),
                request.endereco().cidade(),
                request.endereco().uf(),
                request.endereco().cep()
            );
        }

        user.updateInfo(request.name(), request.email(), request.login(), endereco);
        return UserResponse.from(userRepository.save(user));
    }

    @Transactional
    public void updatePassword(UUID id, UpdatePasswordRequest request) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado: " + id));

        if (!passwordEncoder.matches(request.currentPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Senha atual incorreta");
        }

        if (!request.newPassword().equals(request.confirmPassword())) {
            throw new IllegalArgumentException("Nova senha e confirmação não coincidem");
        }

        user.updatePassword(passwordEncoder.encode(request.newPassword()));
        userRepository.save(user);
    }

}
