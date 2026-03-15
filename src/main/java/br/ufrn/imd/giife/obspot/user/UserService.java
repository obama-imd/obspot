package br.ufrn.imd.giife.obspot.user;

import br.ufrn.imd.giife.obspot.common.service.exception.EntityAlreadyExistsException;
import br.ufrn.imd.giife.obspot.common.service.exception.EntityNotFoundException;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    public static final String USER_ID_NOT_FOUND_MESSAGE = "Usuário de ID %s não encontrado";
    public static final String USER_EMAIL_NOT_FOUND_MESSAGE = "Usuário %s não encontrado";
    public static final String USER_ALREADY_EXISTS_MESSAGE = "Já existe um usuário com o email %s";

    private final UserRepository userRepository;

    UserService(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    public UserEntity save(@NonNull UserEntity user) {
        checkExistenceByEmail(user.getEmail());
        return userRepository.save(user);
    }

    public UserEntity findById(@NonNull UUID id){
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(USER_ID_NOT_FOUND_MESSAGE.formatted(id)));
    }

    public Set<UserEntity> findAllById(@NonNull Collection<UUID> ids){
        return ids.stream().map(this::findById).collect(Collectors.toSet());
    }

    public UserEntity findByEmail(@NonNull String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(USER_EMAIL_NOT_FOUND_MESSAGE.formatted(email)));
    }
    
    public void checkExistenceByEmail(@NonNull String email) {
        userRepository.findByEmail(email).ifPresent(entity -> {
            throw new EntityAlreadyExistsException(USER_ALREADY_EXISTS_MESSAGE.formatted(email));
        });
    }

}
