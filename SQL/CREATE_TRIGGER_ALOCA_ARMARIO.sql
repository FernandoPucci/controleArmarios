﻿--TABELA PARA ALOCAR ARMARIO (ARMARIO.FLG_OCUPADO = 1) QUANDO GERAR LOCACAO
DELIMITER //

CREATE TRIGGER ALOCAR_ARMARIO_TRG
AFTER INSERT
   ON ALUGUEL_ARMARIO FOR EACH ROW

BEGIN

   UPDATE ARMARIO
   SET FLG_OCUPADO = 1
   WHERE ID_ARMARIO = NEW.ID_ARMARIO;

END; //

DELIMITER;