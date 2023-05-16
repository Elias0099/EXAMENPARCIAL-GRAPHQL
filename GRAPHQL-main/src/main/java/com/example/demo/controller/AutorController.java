package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.demo.entity.Autor;
import com.example.demo.repository.AutorRepository;

@Controller
public class AutorController {

	@Autowired
    private AutorRepository autorRepository;

    @QueryMapping
    public Iterable<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    @QueryMapping
    public Autor obtenerAutor(@Argument("id") Long id) {
        return autorRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Autor guardarAutor(@Argument("autor") Autor autor) {
        return autorRepository.save(autor);
    }

    @MutationMapping
    public Autor actualizarAutor(@Argument("id") Long id, @Argument("autor") Autor autorInput) {
        Optional<Autor> optionalAutor = autorRepository.findById(id);
        if (optionalAutor.isPresent()) {
            Autor autor = optionalAutor.get();
            autor.setNombres(autorInput.getNombres());
            autor.setApellidos(autorInput.getApellidos());
            autor.setPais(autorInput.getPais());

            return autorRepository.save(autor);
        }
        return null;
    }

    @MutationMapping
    public boolean eliminarAutor(@Argument("id") Long id) {
        autorRepository.deleteById(id);
        return true;
    }
	
}
