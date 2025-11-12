package com.app.backend.Service;

import com.app.backend.dto.UserCreateRequest;   
import com.app.backend.dto.UserUpdateRequest;
import com.app.backend.model.User;
import com.app.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframwework.security.core.context.SecurityContextHolder;
import org.spring.framework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public class userService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(()) -> new RuntimeException("Usuario no encontrado papi");
    }

    public User create(UserCreateRequest request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(paswordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        user.setActive(request.getActive() != null ? request.getActive() : true);
        return userRepository.save(user);       
    }

    public User update(Long id, UserUpdateRequest request){
        User user = findById(id);

        //Esto lo que hace es que validara que el coordinador no pueda modificar el admin principal//

        if (id == 1L && isCoordinador()){
            throw new RuntimeException("No tienes permiso para modificar el admin mi rey tratame serio");
        }

        user.setUsername(request.getUsername());
        user.setEmaiil(request.getEmail());
        user.setRole(request.getRole());
        user.setActive(request.getActive());

        if (request.getPassword() != null && !request.getPassword().isEmpty()){
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        return userRepository.save(user);
    }

    private boolean isCoordinador(){
        Authemtication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authetication != null && authentication.getAuthorities() != null){
            return authenttication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("Role_COORDINADOR"));
        }
        return false;
    }

    public  void delete(Long id){
        //Esto lo que hace ES QUE EL COORDINADOR NO SE PONGA DE CHISTOSO A BORRAR EL ADMIN //

        if (id == 1L && isCoordinador()){
            throw new RuntimeException("No tienes permiso para eliminar el admin mi rey tratame serio");
        }

            if (user == null){
        throw new RuntimeException("Usuario no encontrado")

        
    }
        userRepository.delete(user);
    }
}