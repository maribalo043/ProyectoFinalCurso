package com.mario.proyect.service.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.entity.Usuario;
import com.mario.proyect.repository.UsuarioDAO;
import com.mario.proyect.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    	@Autowired
	    private UsuarioDAO usuarioDao;

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
        model.setViewName("usuarioHTML/usuarioForm");

        return model;
    }

    @Override
    public ModelAndView saveUsuario(Usuario usuarioNuevo, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("usuarioHTML/usuarioForm");
            return modelAndView;
        }

        usuarioDao.save(usuarioNuevo);

        if (usuarioNuevo.getUsername() == null) {
            modelAndView.setViewName("redirect:/usuarios");
        }

        return modelAndView;
    }

    @Override
    public ModelAndView editUsuario(String nombre) {

        ModelAndView model = new ModelAndView();
        Optional<Usuario> jugOpt = usuarioDao.findById(nombre);

        if (jugOpt.isPresent()) {
            Usuario usuario = jugOpt.get();
            model.addObject("usuarioNuevo", usuario);
            model.setViewName("usuarioHTML/usuarioForm");
        } else {
            model.setViewName("redirect:/usuarios");
        }

        return model;
    }
    
}
