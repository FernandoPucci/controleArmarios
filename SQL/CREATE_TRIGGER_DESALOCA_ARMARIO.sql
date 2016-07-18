--TRIGGER PARA DESALOCAR ARMARIO, AO ATUALIZAR TABELA DE ALUGUEL, COM DATA DEVOLUCAO
DELIMITER //
CREATE TRIGGER DESALOCAR_ARMARIO_TRG
BEFORE UPDATE
   ON ALUGUEL_ARMARIO FOR EACH ROW

BEGIN

  DECLARE  DIAS_DURACAO   INTEGER;


    SELECT DATEDIFF(NEW.DATA_DEVOLUCAO,DATA_ALUGUEL)
      INTO DIAS_DURACAO
      FROM ALUGUEL_ARMARIO
    WHERE ID_ALUGUEL_ARMARIO =NEW.ID_ALUGUEL_ARMARIO;



IF(NEW.FLG_DEVOLVIDO = 1) THEN

   UPDATE ARMARIO
   SET FLG_OCUPADO = 0
   WHERE ID_ARMARIO = NEW.ID_ARMARIO;

  SET NEW.DURACAO = DIAS_DURACAO;

END IF;
END; //

DELIMITER;