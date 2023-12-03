package com.portifolio.joao.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.portifolio.joao.models.Admin;
import com.portifolio.joao.models.Categoria;
import com.portifolio.joao.models.Cidade;
import com.portifolio.joao.models.Endereco;
import com.portifolio.joao.models.Estado;
import com.portifolio.joao.models.Imagem;
import com.portifolio.joao.models.Pais;
import com.portifolio.joao.models.Telefone;
import com.portifolio.joao.repositories.AdminRepository;
import com.portifolio.joao.repositories.CategoriaRepository;
import com.portifolio.joao.repositories.CidadeRepository;
import com.portifolio.joao.repositories.EnderecoRepository;
import com.portifolio.joao.repositories.EstadoRepository;
import com.portifolio.joao.repositories.ImagemRepository;
import com.portifolio.joao.repositories.PaisRepository;
import com.portifolio.joao.repositories.TelefoneRepository;

import jakarta.annotation.PostConstruct;

@Service
public class SeedService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private PaisRepository paisRepository;

    
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private TelefoneRepository telefoneRepository;

    @Autowired
    private EnderecoRepository enderecorRepository;

    @Autowired
    private CategoriaRepository categoriarRepository;

    @Autowired
    private ImagemRepository imagemrRepository;

    @Value("${data.seed}")
    private Boolean dataSeed;


    @PostConstruct
    public final void seedData() {        
        if (dataSeed) {

            

            String arq1 = "data/cidades.txt";
            List<Cidade> cidades = new ArrayList<Cidade>();

            try (BufferedReader cid = new BufferedReader(new FileReader(arq1))) {
                String linha;
                while ((linha = cid.readLine()) != null) {
                    Cidade cidade = new Cidade();
                    String [] parts = linha.split(";");
                    cidade.setNome_cidade(parts[0]);
                    cidade.setEstado_cidade(parts[1]);
                    cidades.add(cidade);
                }
            } catch (IOException e) {
                throw new RuntimeException("[CIDADES] Arquivo inexistente...");
            }

            
            String arq2 = "data/estados.txt";
            List<Estado> estados = new ArrayList<Estado>();

            try (BufferedReader est = new BufferedReader(new FileReader(arq2))) {
                String linha;
                while ((linha = est.readLine()) != null) {
                    Estado estado = new Estado();
                    String [] parts = linha.split(";");
                    estado.setNome_estado(parts[0]);
                    estado.setUf(parts[1]);
                    estados.add(estado);
                }
            } catch (IOException e) {
                throw new RuntimeException("[ESTADOS] Arquivo inexistente...");
            }

            Pais pais = new Pais();
            pais.setNome_pais("Brasil");
            
            
            //System.out.printf("===========================================----------------------\n\n");
            //System.out.print("size: " + cidades.get(2283).getNome_cidade() + " " + cidades.get(2283).getEstado_cidade() + " " + dataSeed + "\n");


            this.cidadeRepository.saveAll(cidades);
            this.estadoRepository.saveAll(estados);
            this.paisRepository.save(pais);
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            List<Telefone> telefones = new ArrayList<Telefone>();
            Telefone telefone = new Telefone();
            telefone.setFone("123456789");
            telefones.add(telefone);
            telefoneRepository.saveAll(telefones);
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            List<Endereco> enderecos = new ArrayList<Endereco>();
            Endereco endereco = new Endereco();
            endereco.setBairro("Centro");
            endereco.setCep("12345-678");
            endereco.setComplemento("Ap. 302");
            endereco.setNome_rua("Rua Principal");
            endereco.setNumero("123");
            endereco.setQuadra("A");
            endereco.setEndereco_to_cidade(cidades.get(5569));
            endereco.setEndereco_to_estado(estados.get(26));
            endereco.setEndereco_to_pais(pais);
            enderecos.add(endereco);
            enderecorRepository.saveAll(enderecos);
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            Admin admin = new Admin();

            admin.setNome("João Vitor");
            admin.setSobrenome("Romano");
            admin.setEmail("joao.silva@gmail.com");
            admin.setSenha("senha123");

            admin.setTelefones(telefones);

            admin.setEnderecos(enderecos);

            this.adminRepository.save(admin);
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            List<Categoria> categorias = new ArrayList<Categoria>();

            Categoria categoria1 = new Categoria();
            categoria1.setNome("Fotos");
            categoria1.setCategoria_to_admin(admin);
            categorias.add(categoria1);

            Categoria categoria2 = new Categoria();
            categoria2.setNome("Videos");
            categoria2.setCategoria_to_admin(admin);
            categorias.add(categoria2);

            Categoria categoria3 = new Categoria();
            categoria3.setNome("Capas");
            categoria3.setCategoria_to_admin(admin);
            categorias.add(categoria3);

            Categoria categoria4 = new Categoria();
            categoria4.setNome("Blog");
            categoria4.setCategoria_to_admin(admin);
            categorias.add(categoria4);

            Categoria categoria5 = new Categoria();
            categoria5.setNome("Perfis");
            categoria5.setCategoria_to_admin(admin);
            categorias.add(categoria5);

            Categoria categoria6 = new Categoria();
            categoria6.setNome("Mini");
            categoria6.setCategoria_to_admin(admin);
            categorias.add(categoria6);

            Categoria categoria_principal = new Categoria();
            categoria_principal.setNome("Categoria Principal");
            categoria_principal.setCategoria_to_admin(admin);
            categorias.add(categoria_principal);


            categoriarRepository.saveAll(categorias);
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            List<Imagem> imagens = new ArrayList<Imagem>();

            Imagem imagem = new Imagem();
            imagem.setDescricao("Fotos principais");
            imagem.setTitulo("1");
            imagem.setCaminho("/resources/static/images/1.jpg");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria_principal);
            imagens.add(imagem);

            imagem = new Imagem();
            imagem.setDescricao("2_felipe_araujo");
            imagem.setTitulo("2_felipe_araujo");
            imagem.setCaminho("/resources/static/videos/2_felipe_araujo.mp4");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria_principal);
            imagens.add(imagem);

            imagem = new Imagem();
            imagem.setDescricao("Fotos principais");
            imagem.setTitulo("3");
            imagem.setCaminho("/resources/static/images/3.jpg");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria_principal);
            imagens.add(imagem);

            imagem = new Imagem();
            imagem.setDescricao("4_ze_neto_e_cristiano");
            imagem.setTitulo("4_ze_neto_e_cristiano");
            imagem.setCaminho("/resources/static/videos/4_ze_neto_e_cristiano.mp4");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria_principal);
            imagens.add(imagem);

            imagem = new Imagem();
            imagem.setDescricao("Fotos principais");
            imagem.setTitulo("5");
            imagem.setCaminho("/resources/static/images/5.jpg");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria_principal);
            imagens.add(imagem);

            imagem = new Imagem();
            imagem.setDescricao("6_leo_e_rafael");
            imagem.setTitulo("6_leo_e_rafael");
            imagem.setCaminho("/resources/static/videos/6_leo_e_rafael.mp4");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria_principal);
            imagens.add(imagem);

            imagem = new Imagem();
            imagem.setDescricao("Fotos principais");
            imagem.setTitulo("7");
            imagem.setCaminho("/resources/static/images/7.jpg");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria_principal);
            imagens.add(imagem);

            imagem = new Imagem();
            imagem.setDescricao("8_belluco");
            imagem.setTitulo("8_belluco");
            imagem.setCaminho("/resources/static/videos/8_belluco.mp4");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria_principal);
            imagens.add(imagem);

            imagem = new Imagem();
            imagem.setDescricao("Fotos principais");
            imagem.setTitulo("9");
            imagem.setCaminho("/resources/static/images/9.jpg");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria_principal);
            imagens.add(imagem);

            imagem = new Imagem();
            imagem.setDescricao("10_pedro_paulo");
            imagem.setTitulo("10_pedro_paulo");
            imagem.setCaminho("/resources/static/videos/10_pedro_paulo.mp4");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria_principal);
            imagens.add(imagem);

            imagem = new Imagem();
            imagem.setDescricao("Fotos principais");
            imagem.setTitulo("11");
            imagem.setCaminho("/resources/static/images/11.jpg");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria_principal);
            imagens.add(imagem);

            imagem = new Imagem();
            imagem.setDescricao("12_nadson");
            imagem.setTitulo("12_nadson");
            imagem.setCaminho("/resources/static/videos/12_nadson.mp4");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria_principal);
            imagens.add(imagem);

            imagem = new Imagem();
            imagem.setDescricao("Fotos principais");
            imagem.setTitulo("13");
            imagem.setCaminho("/resources/static/images/13.JPG");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria_principal);
            imagens.add(imagem);

            imagem = new Imagem();
            imagem.setDescricao("Fotos principais");
            imagem.setTitulo("15");
            imagem.setCaminho("/resources/static/images/15.jpg");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria_principal);
            imagens.add(imagem);

            imagem = new Imagem();
            imagem.setDescricao("Fotos principais");
            imagem.setTitulo("16");
            imagem.setCaminho("/resources/static/images/16.jpg");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria_principal);
            imagens.add(imagem);
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            imagem = new Imagem();
            imagem.setDescricao("Capa");
            imagem.setTitulo("Belluco");
            imagem.setCaminho("/resources/static/images/capa.JPG");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria3);
            imagens.add(imagem);

            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            

            imagem = new Imagem();
            imagem.setDescricao("perfil_demasi");
            imagem.setTitulo("perfil_demasi");
            imagem.setCaminho("/resources/static/images/perfil.jpg");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria5);
            imagens.add(imagem);

            imagem = new Imagem();
            imagem.setDescricao("logo");
            imagem.setTitulo("Logotipo");
            imagem.setCaminho("/resources/static/images/Demasifilmes.png");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria5);
            imagens.add(imagem);

            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            // imagem = new Imagem();
            // imagem.setDescricao("teste");
            // imagem.setTitulo("person_2");
            // imagem.setCaminho("/resources/static/images/person_2.jpg");
            // imagem.setImagem_to_admin(admin);
            // imagem.setImagem_to_categoria();
            // imagens.add(imagem);

            // imagem = new Imagem();
            // imagem.setDescricao("teste");
            // imagem.setTitulo("person_3");
            // imagem.setCaminho("/resources/static/images/person_3.jpg");
            // imagem.setImagem_to_admin(admin);
            // imagem.setImagem_to_categoria();
            // imagens.add(imagem);

            // imagem = new Imagem();
            // imagem.setDescricao("teste");
            // imagem.setTitulo("person_4");
            // imagem.setCaminho("/resources/static/images/person_4.jpg");
            // imagem.setImagem_to_admin(admin);
            // imagem.setImagem_to_categoria();
            // imagens.add(imagem);

            // imagem = new Imagem();
            // imagem.setDescricao("teste");
            // imagem.setTitulo("person_5");
            // imagem.setCaminho("/resources/static/images/person_5.jpg");
            // imagem.setImagem_to_admin(admin);
            // imagem.setImagem_to_categoria();
            // imagens.add(imagem);
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


            // imagem = new Imagem();
            // imagem.setDescricao("teste");
            // imagem.setTitulo("blog_1");
            // imagem.setCaminho("/resources/static/images/blog_1.jpg");
            // imagem.setImagem_to_admin(admin);
            // imagem.setImagem_to_categoria();
            // imagens.add(imagem);

            // imagem = new Imagem();
            // imagem.setDescricao("teste");
            // imagem.setTitulo("blog_2");
            // imagem.setCaminho("/resources/static/images/blog_2.jpg");
            // imagem.setImagem_to_admin(admin);
            // imagem.setImagem_to_categoria();
            // imagens.add(imagem);

            // imagem = new Imagem();
            // imagem.setDescricao("teste");
            // imagem.setTitulo("blog_3");
            // imagem.setCaminho("/resources/static/images/blog_3.jpg");
            // imagem.setImagem_to_admin(admin);
            // imagem.setImagem_to_categoria();
            // imagens.add(imagem);

            // imagem = new Imagem();
            // imagem.setDescricao("teste");
            // imagem.setTitulo("blog_4");
            // imagem.setCaminho("/resources/static/images/blog_4.jpg");
            // imagem.setImagem_to_admin(admin);
            // imagem.setImagem_to_categoria();
            // imagens.add(imagem);

            imagem = new Imagem();
            imagem.setDescricao("Miniatunas");
            imagem.setTitulo("1_min");
            imagem.setCaminho("/resources/static/images/1_min.jpg");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria6);
            imagens.add(imagem);

            imagem = new Imagem();
            imagem.setDescricao("Miniatunas");
            imagem.setTitulo("3_min");
            imagem.setCaminho("/resources/static/images/3_min.jpg");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria6);
            imagens.add(imagem);

            imagem = new Imagem();
            imagem.setDescricao("Miniatunas");
            imagem.setTitulo("5_min");
            imagem.setCaminho("/resources/static/images/5_min.jpg");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria6);
            imagens.add(imagem);

            imagem = new Imagem();
            imagem.setDescricao("Miniatunas");
            imagem.setTitulo("7_min");
            imagem.setCaminho("/resources/static/images/7_min.jpg");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria6);
            imagens.add(imagem);

            imagem = new Imagem();
            imagem.setDescricao("Miniatunas");
            imagem.setTitulo("9_min");
            imagem.setCaminho("/resources/static/images/9_min.jpg");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria6);
            imagens.add(imagem);

            imagem = new Imagem();
            imagem.setDescricao("Miniatunas");
            imagem.setTitulo("11_min");
            imagem.setCaminho("/resources/static/images/11_min.jpg");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria6);
            imagens.add(imagem);

            imagem = new Imagem();
            imagem.setDescricao("Miniatunas");
            imagem.setTitulo("13_min");
            imagem.setCaminho("/resources/static/images/13_min.JPG");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria6);
            imagens.add(imagem);

            imagem = new Imagem();
            imagem.setDescricao("Miniatunas");
            imagem.setTitulo("15_min");
            imagem.setCaminho("/resources/static/images/15_min.jpg");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria6);
            imagens.add(imagem);

            imagem = new Imagem();
            imagem.setDescricao("Miniatunas");
            imagem.setTitulo("16_min");
            imagem.setCaminho("/resources/static/images/16_min.jpg");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria6);
            imagens.add(imagem);


            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            imagem = new Imagem();
            imagem.setDescricao("Miniatunas");
            imagem.setTitulo("2_felipe_araujo_min");
            imagem.setCaminho("/resources/static/videos/2_felipe_araujo_min.jpg");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria6);
            imagens.add(imagem);

            imagem = new Imagem();
            imagem.setDescricao("Miniatunas");
            imagem.setTitulo("4_ze_neto_e_cristiano_min");
            imagem.setCaminho("/resources/static/videos/4_ze_neto_e_cristiano_min.jpg");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria6);
            imagens.add(imagem);

            imagem = new Imagem();
            imagem.setDescricao("Miniatunas");
            imagem.setTitulo("6_leo_e_rafael_min");
            imagem.setCaminho("/resources/static/videos/6_leo_e_rafael_min.jpg");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria6);
            imagens.add(imagem);

            imagem = new Imagem();
            imagem.setDescricao("Miniatunas");
            imagem.setTitulo("8_belluco_min");
            imagem.setCaminho("/resources/static/videos/8_belluco_min.jpg");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria6);
            imagens.add(imagem);

            imagem = new Imagem();
            imagem.setDescricao("Miniatunas");
            imagem.setTitulo("10_pedro_paulo_min");
            imagem.setCaminho("/resources/static/videos/10_pedro_paulo_min.jpg");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria6);
            imagens.add(imagem);

            imagem = new Imagem();
            imagem.setDescricao("Miniatunas");
            imagem.setTitulo("12_nadson_min");
            imagem.setCaminho("/resources/static/videos/12_nadson_min.jpg");
            imagem.setImagem_to_admin(admin);
            imagem.setImagem_to_categoria(categoria6);
            imagens.add(imagem);


            imagemrRepository.saveAll(imagens);

        }
    }
}
