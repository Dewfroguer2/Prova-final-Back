package org.example.Service;

import org.example.entity.Artigos;
import org.example.repository.RepositoryAritigos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceArtigos {
    private final RepositoryAritigos repositoryAritigos;

    public ServiceArtigos(RepositoryAritigos repository) { repositoryAritigos = repository;}

    public List<Artigos> ListarAtigos(){
        List<Artigos> lista = repositoryAritigos.findAll();
        return lista;
    }

    public void DeleteArtigos(Long id) {
        Artigos artigo = repositoryAritigos.getReferenceById(id);
        repositoryAritigos.delete(artigo);
    }

    public Artigos cria(Artigos dados, String emailcriador ){
        Artigos newArtigo = new Artigos();
        newArtigo.setDescricao(dados.getDescricao());
        newArtigo.setEmail(emailcriador);
        newArtigo.setPrioridade(dados.getPrioridade());
        newArtigo.setTitulos(dados.getTitulos());
        return repositoryAritigos.save(newArtigo);
    }
}
