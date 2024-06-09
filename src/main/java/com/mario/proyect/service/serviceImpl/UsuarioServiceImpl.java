package com.mario.proyect.service.serviceImpl;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.entity.Usuario;
import com.mario.proyect.repository.RolDAO;
import com.mario.proyect.repository.UsuarioDAO;
import com.mario.proyect.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDao;

    @Autowired
    private RolDAO rolDao;

    @Autowired
    private BCryptPasswordEncoder encriptador;

    @Override
    public ModelAndView getUsuarios() {

        ModelAndView model = new ModelAndView();
        model.addObject("usuarios", usuarioDao.findAll());
        model.setViewName("usuarioHTML/usuarios");

        return model;
    }

    @Override
    public ModelAndView getUsuario(String nombre) {

        Usuario usuario = usuarioDao.findById(nombre).get();
        ModelAndView model = new ModelAndView();
        model.setViewName("usuarioHTML/usuario");
        model.addObject("usuario", usuario);

        return model;
    }

    @Override
    public ModelAndView deleteUsuario(String nombre) {

        ModelAndView model = new ModelAndView();

        Optional<Usuario> usuario = usuarioDao.findById(nombre);
        if (usuario.isPresent()) {
            usuarioDao.deleteById(nombre);
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
    public ModelAndView saveUsuario(Usuario usuarioNuevo, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();

        if (bindingResult.hasErrors()) {
            model.setViewName("usuarioHTML/usuarioForm");
            return model;
        }
        usuarioNuevo.setPassword(encriptador.encode(usuarioNuevo.getPassword()));
        usuarioDao.save(usuarioNuevo);
        model.setViewName("redirect:/usuarios");

        return model;
    }

    @Override
    public ModelAndView editUsuario(String nombre) {

        ModelAndView model = new ModelAndView();
        Optional<Usuario> jugOpt = usuarioDao.findById(nombre);

        if (jugOpt.isPresent()) {
            Usuario usuario = jugOpt.get();
            model.addObject("usuarioNuevo", usuario);
            model.addObject("roles", rolDao.findAll());
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
    public ModelAndView registrerUser(Usuario user) {
        ModelAndView modelAndView = new ModelAndView();

        Optional<Usuario> usuarioOptional = usuarioDao.findById(user.getEmail());

        if (!usuarioOptional.isPresent()) { 
            user.setRol(rolDao.findById((long) 3).get());
            user.setPassword(encriptador.encode(user.getPassword()));
            usuarioDao.save(user);
        } else {
            modelAndView.addObject("message", "El usuario ya existe");
            modelAndView.setViewName("generalHTML/register");
            return modelAndView;
        }

        modelAndView.addObject("message", "El usuario se ha registrado correctamente");
        modelAndView.setViewName("generalHTML/login");
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

        //Comprueba que el usuario autenticado no sea null
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
    public ModelAndView guardarCambioContrasenia(Usuario user,HttpServletRequest request) {
        ModelAndView model = new ModelAndView();

        model.setViewName("redirect:/");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario username = (Usuario) authentication.getPrincipal();

        username.setPassword(encriptador.encode(user.getPassword()));

        usuarioDao.save(username);

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return model;
    }
    

}
