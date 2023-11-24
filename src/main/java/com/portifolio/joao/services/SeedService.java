package com.portifolio.joao.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.portifolio.joao.models.Cidade;
import com.portifolio.joao.models.Estado;
import com.portifolio.joao.models.Pais;
import com.portifolio.joao.repositories.CidadeRepository;
import com.portifolio.joao.repositories.EstadoRepository;
import com.portifolio.joao.repositories.PaisRepository;

import jakarta.annotation.PostConstruct;

@Service
public class SeedService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private PaisRepository paisRepository;

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
        }
    }
}
