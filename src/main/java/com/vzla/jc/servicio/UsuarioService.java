package com.vzla.jc.servicio;

import com.vzla.jc.dao.UsuarioDao;
import com.vzla.jc.domain.Rol;
import com.vzla.jc.domain.Usuario;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@Slf4j
public class UsuarioService implements UserDetailsService{

    
    @Autowired
    private UsuarioDao usuarioDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Usuario usuario = usuarioDao.findByUsername(username);
            
            if(usuario == null){
                throw new UsernameNotFoundException(username);
            }
            var roles = new ArrayList<GrantedAuthority>();
            
            for(Rol rol: usuario.getRoles()){
                roles.add(new SimpleGrantedAuthority(rol.getNombre()));
            }
            
            return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
    
}
