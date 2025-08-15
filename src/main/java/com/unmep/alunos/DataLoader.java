package com.unmep.alunos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unmep.alunos.dto.AlunoDTO;
import com.unmep.alunos.entity.Aluno;
import com.unmep.alunos.repository.AlunoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final AlunoRepository alunoRepository;

    private final ObjectMapper objectMapper; // Trocamos RestTemplate por ObjectMapper

    public DataLoader(AlunoRepository alunoRepository, ObjectMapper objectMapper) {
        this.alunoRepository = alunoRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if (alunoRepository.count() > 0) {
            System.out.println("Dados de alunos já carregados. Ignorando a carga inicial.");
            return;
        }

        System.out.println("Carregando dados iniciais de alunos...");

        InputStream inputStream = new ClassPathResource("alunos.json").getInputStream();

        AlunoDTO[] alunosDTO = objectMapper.readValue(inputStream, AlunoDTO[].class);

        if (alunosDTO == null) {
            System.out.println("Não foi possível buscar os dados dos alunos.");
            return;
        }

        List<Aluno> alunosParaSalvar = new ArrayList<>();

        for(AlunoDTO dto : alunosDTO) {
            Aluno aluno = new Aluno();
            aluno.setPrimeiroNome(dto.getPrimeiroNome());
            aluno.setUltimoNome(dto.getUltimoNome());
            aluno.setNota1(dto.getNota1());
            aluno.setNota2(dto.getNota2());
            aluno.setNota3(dto.getNota3());
            aluno.setNota4(dto.getNota4());
            aluno.setFaltas(dto.getFaltas());
            double media = calcularMedia(dto);
            aluno.setMedia(media);

            alunosParaSalvar.add(aluno);
        }
        alunoRepository.saveAll(alunosParaSalvar);
        System.out.println("Carga de " + alunosParaSalvar.size() + " alunos concluída com sucesso!");

    }

    private double calcularMedia(AlunoDTO dto) {
        List<Double> notas = Arrays.asList(
                dto.getNota1() != null ? dto.getNota1() : 0.0,
                dto.getNota2() != null ? dto.getNota2() : 0.0,
                dto.getNota3() != null ? dto.getNota3() : 0.0,
                dto.getNota4() != null ? dto.getNota4() : 0.0
        );

        if (notas.isEmpty()) {
            return 0.0;
        }

        return notas.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }
}
