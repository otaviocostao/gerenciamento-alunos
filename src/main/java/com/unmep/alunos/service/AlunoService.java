package com.unmep.alunos.service;

import com.unmep.alunos.entity.Aluno;
import com.unmep.alunos.repository.AlunoRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {

        private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    public List<Aluno> findAprovados() {
        Specification<Aluno> spec = mediaMaiorOuIgualA(7.0)
                .and(faltasMenorQue(7));
        return alunoRepository.findAll(spec);
    }

    public List<Aluno> findReprovados() {
        Specification<Aluno> spec = mediaMenorQue(7.0)
                .or(faltasMaiorOuIgualA(7));
        return alunoRepository.findAll(spec);
    }

    // Filtros dinamicos para retornar alunos com base em parametros
    public List<Aluno> findWithFilters(String nome, Double mediaMinima, Integer faltas) {

        List<Specification<Aluno>> specs = new ArrayList<>();


        if (StringUtils.hasText(nome)) {
            specs.add(nomeContem(nome));
        }
        if (mediaMinima != null) {
            specs.add(mediaMaiorOuIgualA(mediaMinima));
        }
        if (faltas != null) {
            specs.add(faltasMenorQue(faltas+1));
        }

        if (specs.isEmpty()) {
            return alunoRepository.findAll();
        }

        Specification<Aluno> resultSpec = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            resultSpec = resultSpec.and(specs.get(i));
        }

        return alunoRepository.findAll(resultSpec);
    }


    // Retorna alunos pelo nome
    private Specification<Aluno> nomeContem(String nome) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("primeiroNome")), "%" + nome.toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("ultimoNome")), "%" + nome.toLowerCase() + "%")
                );
    }

    // Retorna alunos com média maior ou igual a um valor
    private Specification<Aluno> mediaMaiorOuIgualA(double media) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("media"), media);
    }

    // Retorna alunos com média menor que um valor
    private Specification<Aluno> mediaMenorQue(double media) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThan(root.get("media"), media);
    }


    // Retorna alunos com faltas menor que um valor
    private Specification<Aluno> faltasMenorQue(int faltas) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThan(root.get("faltas"), faltas);
    }

    // Retorna alunos com faltas maior ou igual a um valor
    private Specification<Aluno> faltasMaiorOuIgualA(int faltas) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("faltas"), faltas);
    }
}