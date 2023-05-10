package school.sptech.limpee.service.FormularioServico.dto;

import school.sptech.limpee.domain.FormularioServico.FormularioServico;
import school.sptech.limpee.service.usuario.dto.UsuarioMapper;

public class FormularioServicoMapper {

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
        formularioServico.setUsuario(UsuarioMapper.of(formularioServicoDTO.getUsuarioDto()));

        return formularioServico;
    }
}
