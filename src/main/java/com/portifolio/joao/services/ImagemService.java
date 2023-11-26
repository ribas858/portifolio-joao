package com.portifolio.joao.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Imagem create(Imagem objeto) {
        Admin admin = this.adminService.findById(objeto.getImagem_to_admin().getCod_admin());
        Categoria categoria = this.categoriaService.findById(objeto.getImagem_to_categoria().getCod_categoria());
        
        objeto.setCod_imagem(null);
        objeto.setImagem_to_admin(admin);
        objeto.setImagem_to_categoria(categoria);

        if (Objects.nonNull(objeto.getImagem_to_cliente())) {
            Cliente cliente = this.clienteService.findById(objeto.getImagem_to_cliente().getCod_cliente());
            objeto.setImagem_to_cliente(cliente);
        }

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
    @Transactional
    public Imagem update(Imagem objeto) {
        Imagem imagem = findById(objeto.getCod_imagem());
        imagem.setCaminho(objeto.getCaminho());
        imagem.setDescricao(objeto.getDescricao());
        
        imagem.setImagem_to_categoria(objeto.getImagem_to_categoria());
        imagem.setImagem_to_cliente(objeto.getImagem_to_cliente());
        imagem.setTitulo(objeto.getTitulo());
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

    // FIND ALL : COD CATEGORIA
    public List<Imagem> findAllImagemByCod_categoria(Long id) {
        this.categoriaService.findById(id);
        List<Imagem> imagens = this.imagemRepository.findAllImagensByCod_categoria(id);
        return imagens;
    }

    // FIND ALL : COD CLIENTE
    public List<Imagem> findAllImagemByCod_cliente(Long id) {
        this.clienteService.findById(id);
        List<Imagem> imagens = this.imagemRepository.findAllImagensByCod_cliente(id);
        return imagens;
    }

    // DELETE ALL
    // public void deleteAllByIds(List<Long> ids) {
    //     for (Long i : ids) {
    //         findById(i);
    //     }
    //     try {
    //         this.imagemRepository.deleteAllByIdInBatch(ids);
    //     } catch (Exception e) {
    //         throw new RuntimeException("[IMAGEM LISTA] Não é possivel excluir! Entidades relacionadas!!");
    //     }
    // }
}
