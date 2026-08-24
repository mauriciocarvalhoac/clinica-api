CREATE UNIQUE INDEX idx_medico_especialidade_principal  ON tb_medico_especialidade (id_medico) WHERE (principal = true);
