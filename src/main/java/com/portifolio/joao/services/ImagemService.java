package com.portifolio.joao.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portifolio.joao.models.Admin;
import com.portifolio.joao.models.Categoria;
import com.portifolio.joao.models.Cliente;
import com.portifolio.joao.models.Imagem;
import com.portifolio.joao.repositories.ImagemRepository;

@Service
public class ImagemService {
    
    @Autowired
    private ImagemRepository imagemRepository;

    @Autowired
    private AdminService adminService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ClienteService clienteService;

    // CREATE
    public Imagem create(Imagem objeto) {
        Admin admin = this.adminService.findById(objeto.getImagem_to_admin().getCod_admin());
        Categoria categoria = this.categoriaService.findById(objeto.getImagem_to_categoria().getCod_categoria());
        Cliente cliente = this.clienteService.findById(objeto.getImagem_to_cliente().getCod_cliente());

        objeto.setCod_imagem(null);
        objeto.setImagem_to_admin(admin);
        objeto.setImagem_to_categoria(categoria);
        objeto.setImagem_to_cliente(cliente);

        return this.imagemRepository.save(objeto);
    }
    
    // READ
    public Imagem findById(Long id) {
        Optional<Imagem> imagem = this.imagemRepository.findById(id);
        return imagem.orElseThrow(() -> new RuntimeException(
            "Imagem não encontrada!! id:" + id + ", tipo: " + Imagem.class.getName()
        ));
    }
    
    // UPDATE
    public Imagem update(Imagem objeto) {
        Imagem imagem = findById(objeto.getCod_imagem());
        imagem = objeto;
        return this.imagemRepository.save(imagem);
    }

    // DELETE
    public void delete(Long id) {
        findById(id);
        try {
            this.imagemRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("[IMAGEM] Não é possivel excluir! Entidades relacionadas!!");
        }
    }
}
