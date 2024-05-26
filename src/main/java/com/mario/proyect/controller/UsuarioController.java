package com.mario.proyect.usuario;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioDAO usuarioDao;
	
	private UsuarioHelper helper = new UsuarioHelper();

	@GetMapping("/usuarios")
	public ModelAndView getUsuarios() {
		
		ModelAndView model = new ModelAndView();
		model.addObject("usuarios", usuarioDao.findAll());
		model.setViewName("usuarioHTML/usuarios");
		return model;
	}
    @SuppressWarnings("null")
    @GetMapping("/usuario/{nombre}")
    public ModelAndView getUsuario(@PathVariable String dni) {
        Usuario usuario = usuarioDao.findById(dni).get();
        ModelAndView model = new ModelAndView();
        model.setViewName("usuarioHTML/Usuario");
        model.addObject("usuario", usuario);
        return model;
    }

    @SuppressWarnings("null")
    @GetMapping("/usuario/del/{nombre}")
    public ModelAndView deleteUsuario(@PathVariable String nombre) {

        ModelAndView model = new ModelAndView();
        Optional<Usuario> usuario = usuarioDao.findById(nombre);
        if (usuario.isPresent()) {
            usuarioDao.deleteById(nombre);
        }
        model.setViewName("redirect:/usuarios");

        return model;
    }

    @GetMapping("/usuario/add")
    public ModelAndView addUsuario() {
        ModelAndView model = new ModelAndView();
        model.addObject("usuarioNuevo", new Usuario());
        model.setViewName("usuarioHTML/usuarioForm");

        return model;
    }

    @PostMapping("/usuario/save")
    public ModelAndView saveUsuario(@ModelAttribute("usuarioNuevo") @Valid Usuario usuarioNuevo,
            BindingResult bindingResult) {
        return helper.helperSaveUsuario(usuarioNuevo, bindingResult, usuarioDao);
    }

    @SuppressWarnings("null")
    @GetMapping("/usuario/edit/{dni}")
    public ModelAndView editUsuario(@PathVariable String dni) {
        ModelAndView model = new ModelAndView();
        Optional<Usuario> jugOpt = usuarioDao.findById(dni);

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
