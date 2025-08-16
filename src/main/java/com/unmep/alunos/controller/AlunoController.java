package com.unmep.alunos.controller;

import com.unmep.alunos.entity.Aluno;
import com.unmep.alunos.service.AlunoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public String listarAlunos(Model model) {
        List<Aluno> alunos = alunoService.findAll();
        model.addAttribute("alunos", alunos);
        return "lista-alunos";
    }

    @GetMapping("/aprovados")
    public String listarAprovados(Model model) {
        List<Aluno> alunos = alunoService.findAprovados();
        model.addAttribute("alunos", alunos);
        return "lista-alunos";
    }

    @GetMapping("/reprovados")
    public String listarReprovados(Model model) {
        List<Aluno> alunos = alunoService.findReprovados();
        model.addAttribute("alunos", alunos);
        return "lista-alunos";
    }

    @GetMapping("/filtros")
    public String listarComFiltros(Model model, String nome, Double mediaMinima, Integer faltas) {
        List<Aluno> alunos = alunoService.findWithFilters(nome, mediaMinima, faltas);
        model.addAttribute("alunos", alunos);
        model.addAttribute("nome", nome);
        model.addAttribute("mediaMinima", mediaMinima);
        model.addAttribute("faltas", faltas);
        return "lista-alunos";
    }
}
