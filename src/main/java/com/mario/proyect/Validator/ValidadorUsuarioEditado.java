package com.mario.proyect.Validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mario.proyect.dto.RespuestaValidadorUsuarioDto;
import com.mario.proyect.entity.Usuario;
import com.mario.proyect.repository.UsuarioDAO;

@Service
public class ValidadorUsuarioEditado {

    @Autowired
    private UsuarioDAO usuarioDao;

    /*Metodo que sirve para validar la contraseña a partir de un pattern */
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{1,}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    private boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        return pattern.matcher(password).matches();
    }

    public RespuestaValidadorUsuarioDto validarUsuario(Usuario usuario){

        RespuestaValidadorUsuarioDto devolver = new RespuestaValidadorUsuarioDto();

        if(!isValidPassword(usuario.getPassword())){
            /* Mensaje de error en la contraseña */
            devolver.setError("La contraseña debe contener un caracter especial de esta lista:[ @ $ ! % * ? & ], una mayuscula, una minuscula y un numero como mínimo.");
            devolver.setValido(false);
        }else if(usuarioDao.existsById(usuario.getEmail())){
            /* Mensaje de error por que el email existe */
            devolver.setError("Este email ya existe, Pruebe con otro");
            devolver.setValido(false);
        }else {
            /* Todo bien, devuelve true */
            devolver.setValido(true);
        }

        return devolver;
    }
    
}
