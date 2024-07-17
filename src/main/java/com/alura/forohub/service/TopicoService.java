package com.alura.forohub.service;

import com.alura.forohub.model.StatusTopico;
import com.alura.forohub.model.Topico;
import com.alura.forohub.model.Usuario;
import com.alura.forohub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    public List<Topico> listarTopicos() {
        return topicoRepository.findAll();
    }

    public Optional<Topico> obtenerTopico(Long id) {
        return topicoRepository.findById(id);
    }

    public Topico guardarTopico(Topico topico) {
        return topicoRepository.save(topico);
    }

    @Transactional
    public Topico actualizarTopico(Long id, Topico topicoActualizado) {
        Optional<Topico> topicoOpt = topicoRepository.findById(id);
        if (topicoOpt.isPresent()) {
            Topico topico = topicoOpt.get();
            topico.setTitulo(topicoActualizado.getTitulo());
            topico.setMensaje(topicoActualizado.getMensaje());
            topico.setStatus(topicoActualizado.getStatus());
            topico.setAutor(topicoActualizado.getAutor());
            return topico;
        }
        return null;
    }

    public void eliminarTopico(Long id) {
        topicoRepository.deleteById(id);
    }
}
