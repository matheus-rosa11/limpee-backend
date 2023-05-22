package school.sptech.limpee.service.FormularioServico.dto;

import school.sptech.limpee.domain.FormularioServico.FormularioServico;
import school.sptech.limpee.domain.usuario.Usuario;
import school.sptech.limpee.service.notificacao.NotificacaoService;

import java.util.List;

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
        formularioServico.setValorServico(formularioServicoDTO.getValorServico());
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
        formularioServico.setValorServico(formularioServicoDTO.getValorServico());


        return formularioServico;
    }

    public static FormularioServicoDTO of(FormularioServico formularioServico){
        FormularioServicoDTO formularioServicoDTO = new FormularioServicoDTO();

        formularioServicoDTO.setTipoServico(formularioServico.getTipoServico());
        formularioServicoDTO.setLocalServico(formularioServico.getLocalServico());
        formularioServicoDTO.setAreaExterna(formularioServico.hasAreaExterna());
        formularioServicoDTO.setArmario(formularioServico.hasArmario());
        formularioServicoDTO.setGeladeira(formularioServico.hasGeladeira());
        formularioServicoDTO.setJanelas(formularioServico.hasJanelas());
        formularioServicoDTO.setLavarRoupa(formularioServico.getLavarRoupa());
        formularioServicoDTO.setPassarRoupa(formularioServico.getPassarRoupa());
        formularioServicoDTO.setOutros(formularioServico.getOutros());
        formularioServicoDTO.setOutrosAdcional(formularioServico.getOutrosAdcional());
        formularioServicoDTO.setQtdComodos(formularioServico.getQtdComodos());
        formularioServicoDTO.setQtdBanheiro(formularioServico.getQtdBanheiro());
        formularioServicoDTO.setValorServico(formularioServico.getValorServico());
        formularioServicoDTO.setCliente(formularioServico.getCliente().getId());
        formularioServicoDTO.setPrestador(formularioServico.getPrestador().getId());

        return formularioServicoDTO;
    }


    public static FormularioNotificacaoDto mapToFormNotificacaoDto(FormularioServico formulario) {
        FormularioNotificacaoDto f = new FormularioNotificacaoDto();

        f.setId(formulario.getId());
        f.setIdCliente(formulario.getCliente().getId());
        f.setIdPrestador(formulario.getPrestador().getId());
        f.setLocalServico(formulario.getLocalServico());
        f.setTipoServico(formulario.getTipoServico());
        f.setQtdComodos(formulario.getQtdComodos());
        f.setQtdBanheiros(formulario.getQtdBanheiro());

        List<String> camposTrue = NotificacaoService.getAllTrue(formulario);

        f.setServicos(camposTrue);

        return f;
    }

}
