package com.mario.proyect.service.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.entity.Usuario;
import com.mario.proyect.repository.RolDAO;
import com.mario.proyect.repository.UsuarioDAO;
import com.mario.proyect.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

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
    
}
