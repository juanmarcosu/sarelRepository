package com.sarel.web.dao;

import java.util.List;

import com.sarel.web.model.ResultadoLaboratorioVO;

public interface ResultadoLaboratorioVODao {

	List<ResultadoLaboratorioVO> findByIdExpediente(Integer idExpediente);
}
