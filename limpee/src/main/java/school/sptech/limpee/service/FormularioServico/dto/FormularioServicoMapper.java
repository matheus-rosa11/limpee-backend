package school.sptech.limpee.service.FormularioServico.dto;

import school.sptech.limpee.domain.FormularioServico.FormularioServico;
import school.sptech.limpee.domain.usuario.Usuario;

public class FormularioServicoMapper {

    public static FormularioServico of(FormularioServicoDTO formularioServicoDTO, Usuario cliente, Usuario prestador){
        FormularioServico formularioServico = new FormularioServico();

        formularioServico.setTipoServico(formularioServicoDTO.getTipoServico());
        formularioServico.setLocalServico(formularioServicoDTO.getLocalServico());
        formularioServico.setAreaExterna(formularioServicoDTO.getAreaExterna());
        formularioServico.setArmario(formularioServicoDTO.getArmario());
        formularioServico.setGeladeira(formularioServicoDTO.getGeladeira());
        formularioServico.setJanelas(formularioServicoDTO.getJanelas());
        formularioServico.setLavarRoupa(formularioServicoDTO.getLavarRoupa());
        formularioServico.setPassarRoupa(formularioServicoDTO.getPassarRoupa());
        formularioServico.setOutros(formularioServicoDTO.getOutros());
        formularioServico.setOutrosAdcional(formularioServicoDTO.getOutrosAdcional());
        formularioServico.setQtdComodos(formularioServicoDTO.getQtdComodos());
        formularioServico.setQtdBanheiro(formularioServicoDTO.getQtdBanheiro());
        formularioServico.setCliente(cliente);
        formularioServico.setPrestador(prestador);

        return formularioServico;
    }

    public static FormularioServico of(FormularioServicoDTO formularioServicoDTO){
        FormularioServico formularioServico = new FormularioServico();

        formularioServico.setTipoServico(formularioServicoDTO.getTipoServico());
        formularioServico.setLocalServico(formularioServicoDTO.getLocalServico());
        formularioServico.setAreaExterna(formularioServicoDTO.getAreaExterna());
        formularioServico.setArmario(formularioServicoDTO.getArmario());
        formularioServico.setGeladeira(formularioServicoDTO.getGeladeira());
        formularioServico.setJanelas(formularioServicoDTO.getJanelas());
        formularioServico.setLavarRoupa(formularioServicoDTO.getLavarRoupa());
        formularioServico.setPassarRoupa(formularioServicoDTO.getPassarRoupa());
        formularioServico.setOutros(formularioServicoDTO.getOutros());
        formularioServico.setOutrosAdcional(formularioServicoDTO.getOutrosAdcional());
        formularioServico.setQtdComodos(formularioServicoDTO.getQtdComodos());
        formularioServico.setQtdBanheiro(formularioServicoDTO.getQtdBanheiro());

        return formularioServico;
    }

    public static FormularioServicoDTO of(FormularioServico formularioServico){
        FormularioServicoDTO formularioServicoDTO = new FormularioServicoDTO();

        formularioServicoDTO.setTipoServico(formularioServico.getTipoServico());
        formularioServicoDTO.setLocalServico(formularioServico.getLocalServico());
        formularioServicoDTO.setAreaExterna(formularioServico.getAreaExterna());
        formularioServicoDTO.setArmario(formularioServico.getArmario());
        formularioServicoDTO.setGeladeira(formularioServico.getGeladeira());
        formularioServicoDTO.setJanelas(formularioServico.getJanelas());
        formularioServicoDTO.setLavarRoupa(formularioServico.getLavarRoupa());
        formularioServicoDTO.setPassarRoupa(formularioServico.getPassarRoupa());
        formularioServicoDTO.setOutros(formularioServico.getOutros());
        formularioServicoDTO.setOutrosAdcional(formularioServico.getOutrosAdcional());
        formularioServicoDTO.setQtdComodos(formularioServico.getQtdComodos());
        formularioServicoDTO.setQtdBanheiro(formularioServico.getQtdBanheiro());
        formularioServicoDTO.setCliente(formularioServico.getCliente().getId());
        formularioServicoDTO.setPrestador(formularioServico.getPrestador().getId());

        return formularioServicoDTO;
    }
}
