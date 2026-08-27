package com.mrc.clinic.clinic_api.service.impl;

import com.mrc.clinic.clinic_api.entity.Endereco;
import com.mrc.clinic.clinic_api.entity.Funcionario;
import com.mrc.clinic.clinic_api.entity.dto.FuncionarioDTO;
import com.mrc.clinic.clinic_api.entity.rec.FuncionarioRec;

public class AbstractServiceImpl {

    public void to(FuncionarioDTO dto, Funcionario obj) {
        obj.setId(dto.getId());
        obj.setNome(dto.getNome());
        obj.setCpf(dto.getCpf());
        obj.setEmail(dto.getEmail());
        obj.setTelefone(dto.getTelefone());
        obj.setCelular(dto.getCelular());
        obj.setPaisOrigem(dto.getPaisOrigem());
        obj.setDataNascimento(dto.getDataNascimento());
        obj.setGenero(dto.getGenero());
        obj.setRg(dto.getRg());
        obj.getEndereco().setCep(dto.getEndereco().getCep());
        obj.getEndereco().setLogradouro(dto.getEndereco().getLogradouro());
        obj.getEndereco().setNumero(dto.getEndereco().getNumero());
        obj.getEndereco().setBairro(dto.getEndereco().getBairro());
        obj.getEndereco().setCidade(dto.getEndereco().getCidade());
        obj.getEndereco().setEstado(dto.getEndereco().getEstado());

        obj.setFuncao(dto.getFuncao());
        obj.setMatricula(dto.getMatricula());
        obj.setDepartamento(dto.getDepartamento());

//        obj.setCrm(dto.getCrm());
//        obj.setCrmEstado(dto.getCrmEstado());
//        obj.setInstituicaoGraduacao(dto.getInstituicaoGraduacao());
//        obj.setStatusPos(dto.getStatusPos());
//        obj.setInstituicaoPos(dto.getInstituicaoPos());
//        obj.setStatusMestrado(dto.getStatusMestrado());
//        obj.setInstituicaoMestrado(dto.getInstituicaoMestrado());
//        obj.setStatusDoutorado(dto.getStatusDoutorado());
//        obj.setInstituicaoDoutorado(dto.getInstituicaoDoutorado());

    }

    public FuncionarioDTO to(FuncionarioRec rec) {
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setId(rec.id());
        dto.setNome(rec.nome());
        dto.setCpf(rec.cpf());
        dto.setEmail(rec.email());
        dto.setFuncao(rec.funcao());
        return dto;
    }

    public FuncionarioDTO to(Funcionario obj) {
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setId(obj.getId());
        dto.setNome(obj.getNome());
        dto.setCpf(obj.getCpf());
        dto.setEmail(obj.getEmail());
        dto.setTelefone(obj.getTelefone());
        dto.setCelular(obj.getCelular());
        dto.setDataNascimento(obj.getDataNascimento());
        dto.setPaisOrigem(obj.getPaisOrigem());
        dto.setGenero(obj.getGenero());
        dto.setRg(obj.getRg());
        obj.setEndereco((obj.getEndereco() != null) ? obj.getEndereco() : new Endereco());
        dto.getEndereco().setCep(obj.getEndereco().getCep());
        dto.getEndereco().setLogradouro(obj.getEndereco().getLogradouro());
        dto.getEndereco().setNumero(obj.getEndereco().getNumero());
        dto.getEndereco().setBairro(obj.getEndereco().getBairro());
        dto.getEndereco().setCidade(obj.getEndereco().getCidade());
        dto.getEndereco().setEstado(obj.getEndereco().getEstado());

        dto.setFuncao(obj.getFuncao());
        dto.setMatricula(obj.getMatricula());
        dto.setDepartamento(obj.getDepartamento());

//        dto.setCrm(obj.getCrm());
//        dto.setCrmEstado(obj.getCrmEstado());
//        dto.setInstituicaoGraduacao(obj.getInstituicaoGraduacao());
//        dto.setStatusPos(obj.getStatusPos());
//        dto.setInstituicaoPos(obj.getInstituicaoPos());
//        dto.setStatusMestrado(obj.getStatusMestrado());
//        dto.setInstituicaoMestrado(obj.getInstituicaoMestrado());
//        dto.setStatusDoutorado(obj.getStatusDoutorado());
//        dto.setInstituicaoDoutorado(obj.getInstituicaoDoutorado());

//        for (FuncionarioEspecialidade medEsp : obj.getFuncionarioEspecialidades()) {
//            FuncionarioEspecialidadeDTO medespDTO = new FuncionarioEspecialidadeDTO();
//            medespDTO.setEspecialidade(new EspecialidadeDTO());
//            medespDTO.getEspecialidade().setId(medEsp.getEspecialidade().getId());
//            medespDTO.getEspecialidade().setDescricao(medEsp.getEspecialidade().getDescricao());
//            medespDTO.getEspecialidade().setCbo(medEsp.getEspecialidade().getCbo());
//            medespDTO.getEspecialidade().setTiss(medEsp.getEspecialidade().getTiss());
//            medespDTO.setPrincipal(medEsp.getPrincipal());
//            medespDTO.setSituacao(medEsp.getSituacao());
//            FuncionarioDTO FuncionarioDTO = new FuncionarioDTO();
//            medespDTO.setId(medEsp.getId());
//            medespDTO.setFuncionario(FuncionarioDTO);
//            dto.getFuncionarioEspecialidades().add(medespDTO);
//        }

        return dto;
    }

    public Funcionario to(FuncionarioDTO dto) {
        Funcionario obj = new Funcionario();
        obj.setId(dto.getId());
        obj.setNome(dto.getNome());
        obj.setCpf(dto.getCpf());
        obj.setEmail(dto.getEmail());
        obj.setTelefone(dto.getTelefone());
        obj.setCelular(dto.getCelular());
        obj.setPaisOrigem(dto.getPaisOrigem());
        obj.setDataNascimento(dto.getDataNascimento());
        obj.setGenero(dto.getGenero());
        obj.setRg(dto.getRg());
        obj.getEndereco().setCep(dto.getEndereco().getCep());
        obj.getEndereco().setLogradouro(dto.getEndereco().getLogradouro());
        obj.getEndereco().setNumero(dto.getEndereco().getNumero());
        obj.getEndereco().setBairro(dto.getEndereco().getBairro());
        obj.getEndereco().setCidade(dto.getEndereco().getCidade());
        obj.getEndereco().setEstado(dto.getEndereco().getEstado());

        obj.setFuncao(dto.getFuncao());
        obj.setMatricula(dto.getMatricula());
        obj.setDepartamento(dto.getDepartamento());

//        obj.setCrm(dto.getCrm());
//        obj.setCrmEstado(dto.getCrmEstado());
//        obj.setInstituicaoGraduacao(dto.getInstituicaoGraduacao());
//        obj.setStatusPos(dto.getStatusPos());
//        obj.setInstituicaoPos(dto.getInstituicaoPos());
//        obj.setStatusMestrado(dto.getStatusMestrado());
//        obj.setInstituicaoMestrado(dto.getInstituicaoMestrado());
//        obj.setStatusDoutorado(dto.getStatusDoutorado());
//        obj.setInstituicaoDoutorado(dto.getInstituicaoDoutorado());

//        for (FuncionarioEspecialidadeDTO medEspDTO : dto.getFuncionarioEspecialidades()) {
//            FuncionarioEspecialidade medesp = new FuncionarioEspecialidade();
//            medesp.setEspecialidade(new Especialidade());
//            medesp.getEspecialidade().setId(medEspDTO.getEspecialidade().getId());
//            medesp.getEspecialidade().setDescricao(medEspDTO.getEspecialidade().getDescricao());
//            medesp.getEspecialidade().setCbo(medEspDTO.getEspecialidade().getCbo());
//            medesp.getEspecialidade().setTiss(medEspDTO.getEspecialidade().getTiss());
//            medesp.setPrincipal(medEspDTO.getPrincipal());
//            medesp.setSituacao(medEspDTO.getSituacao());
//            medesp.setFuncionario(obj);
//            obj.getFuncionarioEspecialidades().add(medesp);
//        }

        return obj;
    }
}
