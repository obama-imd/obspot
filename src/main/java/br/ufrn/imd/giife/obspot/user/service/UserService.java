package br.ufrn.imd.giife.obspot.user.service;

import br.ufrn.imd.giife.obspot.common.service.exception.EntityAlreadyExistsException;
import br.ufrn.imd.giife.obspot.user.UserEntity;
import br.ufrn.imd.giife.obspot.user.UserRepository;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private static final String USER_NOT_FOUND_MESSAGE = "User %s not found";
    private static final String USER_ALREADY_EXISTS_MESSAGE = "There's already a user with the email %s";

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

    public UserEntity findByEmail(@NonNull String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE.formatted(email)));
    }
    
    public void checkExistenceByEmail(@NonNull String email) {
        userRepository.findByEmail(email).ifPresent(entity -> {
            throw new EntityAlreadyExistsException(USER_ALREADY_EXISTS_MESSAGE.formatted(email));
        });
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        return (UserDetails) findByEmail(email);
    }

}
