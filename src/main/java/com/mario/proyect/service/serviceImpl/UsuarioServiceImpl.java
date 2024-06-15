package com.mario.proyect.service.serviceImpl;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scripting.bsh.BshScriptUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.Validator.BCryptUtils;
import com.mario.proyect.Validator.ValidadorUsuarioEditado;
import com.mario.proyect.dto.RespuestaValidadorUsuarioDto;
import com.mario.proyect.entity.Usuario;
import com.mario.proyect.repository.RolDAO;
import com.mario.proyect.repository.UsuarioDAO;
import com.mario.proyect.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDao;

    @Autowired
    private RolDAO rolDao;

    @Autowired
    private ValidadorUsuarioEditado validador;

    @Autowired
    private BCryptPasswordEncoder encriptador;

    @Override
    public ModelAndView getUsuarios(boolean hayError) {

        ModelAndView model = new ModelAndView();
        model.addObject("usuarios", usuarioDao.findAll());
        if(hayError){
            model.addObject("error", "No puedes eliminar ese usuario ya que es con el que tienes iniciado sesión.");
        }
        
        model.setViewName("usuarioHTML/usuarios");

        return model;
    }

    @Override
    public ModelAndView getUsuario(String nombre, boolean hayError) {

        Usuario usuario = usuarioDao.findById(nombre).get();
        ModelAndView model = new ModelAndView();
        model.setViewName("usuarioHTML/usuario");
        model.addObject("usuario", usuario);
        if(hayError){
            model.addObject("error", "No puedes editar ese usuario ya que es con el que tienes iniciado sesión.");
        }
        
        return model;
    }

    @Override
    public ModelAndView deleteUsuario(String email) {

        ModelAndView model = new ModelAndView();

        Optional<Usuario> usuario = usuarioDao.findById(email);
        /*Obtengo el email del usuario logueado */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object logeado = authentication.getPrincipal();
        String emailLogueado = ((Usuario) logeado).getEmail();

        if (emailLogueado.equals(email)) {
            model.setViewName("redirect:/usuarios/true");
            return model;
        }
        if (usuario.isPresent()) {
            usuarioDao.deleteById(email);
        }
        model.setViewName("redirect:/usuarios");

        return model;
    }

    @Override
    public ModelAndView addUsuario() {

        ModelAndView model = new ModelAndView();
        model.addObject("usuarioNuevo", new Usuario());
        model.addObject("roles", rolDao.findAll());
        model.setViewName("usuarioHTML/usuarioForm");

        return model;
    }


    @Override
    public ModelAndView saveUsuario(Usuario usuarioNuevo, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        Usuario usuarioCreado = new Usuario();
         // Validación manual de la contraseña
        String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{1,}$";
        if (!usuarioNuevo.getPassword().matches(passwordPattern)) {
            bindingResult.rejectValue("password", "error.usuarioNuevo", "La contraseña debe contener al menos una letra mayúscula, una letra minúscula, un número y un carácter especial.");
        }
        Optional<Usuario> userOptional = usuarioDao.findById(usuarioNuevo.getEmailAntiguo());
        if(userOptional.isPresent()){
            usuarioDao.delete(userOptional.get());
        }
        if (bindingResult.hasErrors()) {
            RespuestaValidadorUsuarioDto response = validador.validarUsuario(usuarioNuevo);
            if (response.isValido()) {
                usuarioCreado.setEmail(usuarioNuevo.getEmail());
                if (BCryptUtils.isBCryptHash(usuarioNuevo.getPassword())) {
                    usuarioCreado.setPassword(usuarioNuevo.getPassword());
                } else if (!BCryptUtils.isBCryptHash(usuarioNuevo.getPassword())) {
                    usuarioCreado.setPassword(encriptador.encode(usuarioNuevo.getPassword()));
                }
                usuarioCreado.setRol(usuarioCreado.getRol());
                usuarioCreado.setUsuario(usuarioCreado.getUsuario());

                usuarioDao.save(usuarioCreado);
                model.setViewName("redirect:/usuarios");
            }else {
                /* si no es valido, redirige al form pero con el mensaje de error */
                usuarioDao.save(usuarioNuevo);
                model.setViewName("usuarioHTML/usuarioForm");
                model.addObject("usuarioNuevo", usuarioNuevo);
                model.addObject("roles", rolDao.findAll());
                model.addObject("error", response.getError());
            }
        } else {
            /*TODO Revisar al crear un usuario qeu si conincide el email con uno existente lo edita */
            usuarioCreado.setEmail(usuarioNuevo.getEmail());
            usuarioCreado.setPassword(encriptador.encode(usuarioNuevo.getPassword()));
            usuarioCreado.setRol(usuarioNuevo.getRol());
            usuarioCreado.setUsuario(usuarioNuevo.getUsuario());

            usuarioDao.save(usuarioCreado);
            model.setViewName("redirect:/usuarios");
        }
        return model;
    }

    @Override
    public ModelAndView editUsuario(String email, HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
        Optional<Usuario> userOpt = usuarioDao.findById(email);
        /*Obtengo el email del usuario logueado */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object logeado = authentication.getPrincipal();
        String emailLogueado = ((Usuario) logeado).getEmail();

        if (emailLogueado.equals(email)) {
            Usuario usuario = usuarioDao.findById(email).get();
            model.setViewName("redirect:/usuario/"+usuario.getEmail()+"/true");
            return model;
        }

        if (userOpt.isPresent()) {
            Usuario usuario = userOpt.get();
            usuario.setEmailAntiguo(usuario.getEmail());
            usuarioDao.save(usuario);
            model.addObject("usuarioNuevo", usuario);
            model.addObject("roles", rolDao.findAll());
            model.addObject("emailViejo", email);
            model.setViewName("usuarioHTML/usuarioForm");
        } else {
            model.setViewName("redirect:/usuarios");
        }

        return model;
    }

    @Override
    public Usuario findUserByUsuario(String usuario) {
        return usuarioDao.findById(usuario).get();
    }

    @Override
    public void saveUser(Usuario user) {
        user.setPassword(encriptador.encode(user.getPassword()));
        usuarioDao.save(user);
    }

    @Override
    public ModelAndView login() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("generalHTML/login");
        modelAndView.addObject("user", new Usuario());
        return modelAndView;
    }

    @Override
    public ModelAndView loginUser(Usuario user, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Usuario> existingUserOptional = usuarioDao.findById(user.getEmail());
        if (existingUserOptional.isPresent()) {
            Usuario existingUser = existingUserOptional.get();
            if (encriptador.matches(user.getPassword(), existingUser.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("user", existingUser);
                modelAndView.setViewName("redirect:/home");
            } else {
                modelAndView.addObject("message", "Invalid username or password");
                modelAndView.setViewName("generalHTML/login");
            }
        } else {
            modelAndView.addObject("message", "User not found");
            modelAndView.setViewName("generalHTML/login");
        }

        return modelAndView;
    }

    @Override
    public ModelAndView registrer() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("generalHTML/register");
        modelAndView.addObject("user", new Usuario());
        return modelAndView;
    }

    @Override
    public ModelAndView registrerUser(@Valid Usuario user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("generalHTML/register");
            modelAndView.addObject("user", user);
            return modelAndView;
        }

        Optional<Usuario> usuarioOptional = usuarioDao.findById(user.getEmail());

        if (!usuarioOptional.isPresent()) {
            user.setRol(rolDao.findById((long) 3).get());
            user.setPassword(encriptador.encode(user.getPassword()));
            usuarioDao.save(user);
            modelAndView.addObject("message", "El usuario se ha registrado correctamente");
            modelAndView.setViewName("generalHTML/login");
        } else {
            modelAndView.addObject("message", "El usuario ya existe");
            modelAndView.addObject("user", user);
            modelAndView.setViewName("generalHTML/register");
        }
        return modelAndView;
    }

    @Override
    public ModelAndView logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/login");
        return modelAndView;
    }

    @Override
    public ModelAndView home(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            modelAndView.setViewName("index");
        } else {
            modelAndView.setViewName("redirect:/login");
        }
        return modelAndView;
    }

    @Override
    public ModelAndView cambioContrasenia() {
        // Obtener el usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario username = null;

        // Comprueba que el usuario autenticado no sea null
        if (authentication != null) {
            username = (Usuario) authentication.getPrincipal();
        }
        ModelAndView model = new ModelAndView();
        if (username != null) {
            model.setViewName("redirect:/cambioContraseniaUsuario");
            return model;
        }
        throw new IllegalArgumentException("El usuario no existe o no esta bien las credenciales");
    }

    @Override
    public ModelAndView formCambioContrasenia() {
        ModelAndView model = new ModelAndView();
        model.setViewName("usuarioHTML/formCambioContrasenia");

        model.addObject("user", new Usuario());

        return model;
    }

    @Override
    public ModelAndView guardarCambioContrasenia(@Valid Usuario user, BindingResult bindingResult,
            HttpServletRequest request) {
        ModelAndView model = new ModelAndView();

        if (bindingResult.hasErrors()) {
            model.setViewName("usuarioHTML/formCambioContrasenia");
            model.addObject("user", user);
            return model;
        }

        model.setViewName("redirect:/emailConfirmacion");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario username = (Usuario) authentication.getPrincipal();

        username.setPassword(encriptador.encode(user.getPassword()));

        usuarioDao.save(username);

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        model.addObject("usuario", username);
        return model;
    }

}
