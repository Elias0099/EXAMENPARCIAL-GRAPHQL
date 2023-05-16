package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.demo.entity.Editorial;
import com.example.demo.repository.EditorialRepository;

@Controller
public class EditorialController {

	@Autowired
    private EditorialRepository editorialRepository;

    @QueryMapping
    public Iterable<Editorial> listarEditoriales() {
        return editorialRepository.findAll();
    }

    @QueryMapping
    public Editorial obtenerEditorial(@Argument("id") Long id) {
        return editorialRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Editorial guardarEditorial(@Argument("editorial") Editorial editorial) {
        return editorialRepository.save(editorial);
    }

    @MutationMapping
    public Editorial actualizarEditorial(@Argument("id") Long id, @Argument("editorial") Editorial editorialInput) {
        Optional<Editorial> optionalEditorial = editorialRepository.findById(id);
        if (optionalEditorial.isPresent()) {
            Editorial editorial = optionalEditorial.get();
            editorial.setNombre(editorialInput.getNombre());
            editorial.setPais(editorialInput.getPais());

            return editorialRepository.save(editorial);
        }
        return null;
    }

    @MutationMapping
    public boolean eliminarEditorial(@Argument("id") Long id) {
        editorialRepository.deleteById(id);
        return true;
    }
	
}
