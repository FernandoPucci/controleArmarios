/*
 * Copyright (C) 2016 fernando-pucci
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.com.etefgarcia.armarios.service;

import br.com.etefgarcia.armarios.dao.AlunoDAO;
import br.com.etefgarcia.armarios.dao.impl.AlunoDAOImpl;
import br.com.etefgarcia.armarios.exceptions.NegocioException;
import br.com.etefgarcia.armarios.exceptions.SistemaException;
import br.com.etefgarcia.armarios.model.Aluno;
import br.com.etefgarcia.armarios.util.threads.ExcelThreads;
import br.com.etefgarcia.armarios.util.ServiceUtils;
import br.com.etefgarcia.armarios.util.TelaUtils;
import br.com.etefgarcia.armarios.util.constantes.Constantes;
import br.com.etefgarcia.armarios.util.constantes.ConstantesExcelHeaders;
import br.com.etefgarcia.armarios.view.aluno.CarregarPlanilhaAlunoView;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author fernando-pucci
 *
 * Classe responsável por leitura, validação e persistencia de dados de arquivo
 * Excel
 *
 */
public class ExcelService {

    private CarregarPlanilhaAlunoView carregarPlanilhaAlunoView = null;

    private String caminhoArquivo = null;

    private Map<String, Integer> mapaColunas;

    private Double totalLinhas = 0.0;
    private Double linhasAtualizadas = 0.0;

    public ExcelService(File arquivo, CarregarPlanilhaAlunoView carregarPlanilhaAlunoView) {

        this.caminhoArquivo = arquivo.getAbsolutePath();
        this.carregarPlanilhaAlunoView = carregarPlanilhaAlunoView;
    }

    public void inicia() {

        try {

            List<Aluno> listaAlunos = null;

            carregarPlanilhaAlunoView.habilitarSalvar(false);

            listaAlunos = carregaListaAlunos(caminhoArquivo, Constantes.LINHA_CABECALHO_PLANILHA);

            totalLinhas = Double.parseDouble(listaAlunos.size() + "");

            AlunoDAO dao = new AlunoDAOImpl();

            Long initTime = System.nanoTime();

            carregarPlanilhaAlunoView.setLogger("AGUARDE...");

            for (Aluno a : listaAlunos) {

                if (a.getTelefone() == null && a.getEmail() == null) {

                    throw new NegocioException("Aluno " + a.getIdAluno() + ", possui dados de contato inválidos");

                }

                if (a.getEmail() != null) {
                    if (!TelaUtils.validaEmail(a.getEmail())) {

                        throw new NegocioException("Aluno " + a.getIdAluno() + ", possui dados de e-mail inválidos");

                    }
                }

                if (a.getTelefone() != null) {
                    if (!TelaUtils.validaTelefone(a.getTelefone())) {

                        throw new NegocioException("Aluno " + a.getIdAluno() + ", possui dados de Telefone inválidos");

                    }

                }

                try {

                    AlunoService service = new AlunoService(dao);

                    service.cadastrarAluno(a);
                    linhasAtualizadas++;

                } catch (NegocioException | SistemaException e) {

                    throw new SistemaException(e.getMessage());

                }
            }

            Long finalTime = System.nanoTime() - initTime;

            BigDecimal tempoSegundos = new BigDecimal((double) finalTime / 1000000000.0);

            tempoSegundos = tempoSegundos.setScale(1, BigDecimal.ROUND_HALF_UP);

            carregarPlanilhaAlunoView.processarSucesso(listaAlunos.size() + " registros em " + (tempoSegundos) + " segundos");
            carregarPlanilhaAlunoView.setInstrucao("Pronto!");

        } catch (SistemaException | NegocioException | IOException ex) {

            ExcelThreads.bp.dispose();
            carregarPlanilhaAlunoView.processarErro(ex.getMessage());

        }

    }

    private List<Aluno> carregaListaAlunos(String arquivo, int linhaInicio) throws NegocioException, IOException {

        List<Aluno> listaAlunos = null;

        try {

            int linhaInicioReal = linhaInicio - 1;

            listaAlunos = new ArrayList<>();
            mapaColunas = new HashMap<>();

            FileInputStream fis = new FileInputStream(arquivo);

            Workbook workbook = null;

            if (arquivo.toLowerCase().endsWith("xlsx")) {

                workbook = new XSSFWorkbook(fis);

            } else if (arquivo.toLowerCase().endsWith("xls")) {

                workbook = new HSSFWorkbook(fis);

            }

            int totalPaginas = workbook.getNumberOfSheets();

            for (int i = 0; i < totalPaginas; i++) {

                //Iterator de planilha
                Sheet sheet = workbook.getSheetAt(i);

                //Iterator de linha
                Iterator<Row> rowIterator = sheet.iterator();
                while (rowIterator.hasNext()) {

                    Row row = rowIterator.next();

                    if (row.getRowNum() < linhaInicio) {

                        continue;

                    }

                    Aluno a = new Aluno();

                    //Iterator de celula
                    Iterator<Cell> cellIterator = row.cellIterator();

                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();

                        if (row.getRowNum() == linhaInicioReal) {

                            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {

                                populaMapaColunas(cell);

                                if (!validaCampos()) {

                                    throw new NegocioException("Arquivo Inválido! - Formatação de Colunas Incorreta.");

                                }

                            } else {
                                throw new NegocioException("Arquivo Inválido!");

                            }

                        }

                        String valor = null;

                        //verifica o tipo do valor da celula
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                valor = cell.getStringCellValue();
                                break;
                            case Cell.CELL_TYPE_NUMERIC:

                                valor = cell.getNumericCellValue() + "";

                                break;
                        }

                        switch (cell.getColumnIndex()) {

                            case ConstantesExcelHeaders.COLUNA_RM:
                                a.setIdAluno(new Long(valor));
                                break;
                            case ConstantesExcelHeaders.COLUNA_NOME:
                                a.setNome(valor);
                                break;
                            case ConstantesExcelHeaders.COLUNA_E_MAIL:
                                a.setEmail(valor);
                                break;
                            case ConstantesExcelHeaders.COLUNA_CELULAR:

                                if (a.getTelefone() == null || !TelaUtils.validaTelefone(a.getTelefone())) {//se nao tiver telefone grava celular
                                    a.setTelefone(ServiceUtils.limpaTelefone(valor));
                                }

                                break;

                            case ConstantesExcelHeaders.COLUNA_TELEFONE:
                                a.setTelefone(ServiceUtils.limpaTelefone(valor));
                                break;
                            case ConstantesExcelHeaders.COLUNA_SEXO:
                                a.setSexo(valor);
                                break;

                        }

                    }

                    listaAlunos.add(a);
                }

            }

            fis.close();

        } catch (IOException e) {
            throw e;
        }

        return listaAlunos;
    }

    private boolean validaCampos() {

        for (Map.Entry m : mapaColunas.entrySet()) {

            switch ((int) m.getValue()) {

                case ConstantesExcelHeaders.COLUNA_RM:
                    if (!m.getKey().equals(ConstantesExcelHeaders.RM)) {
                        return false;
                    }
                    break;
                case ConstantesExcelHeaders.COLUNA_NOME:
                    if (!m.getKey().equals(ConstantesExcelHeaders.NOME)) {
                        return false;
                    }
                    break;
                case ConstantesExcelHeaders.COLUNA_TELEFONE:
                    if (!m.getKey().equals(ConstantesExcelHeaders.TELEFONE)) {
                        return false;
                    }
                    break;
                case ConstantesExcelHeaders.COLUNA_CELULAR:
                    if (!m.getKey().equals(ConstantesExcelHeaders.CELULAR)) {
                        return false;
                    }
                    break;
                case ConstantesExcelHeaders.COLUNA_E_MAIL:
                    if (!m.getKey().equals(ConstantesExcelHeaders.EMAIL)) {
                        return false;
                    }
                    break;
                case ConstantesExcelHeaders.COLUNA_SEXO:
                    if (!m.getKey().equals(ConstantesExcelHeaders.SEXO)) {
                        return false;
                    }
                    break;

            }

        }

        return true;
    }

    private void populaMapaColunas(Cell cell) throws NegocioException {

        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {

            switch (cell.getStringCellValue()) {

                case ConstantesExcelHeaders.RM:
                    mapaColunas.put(ConstantesExcelHeaders.RM, cell.getColumnIndex());
                    break;
                case ConstantesExcelHeaders.NOME:
                    mapaColunas.put(ConstantesExcelHeaders.NOME, cell.getColumnIndex());
                    break;
                case ConstantesExcelHeaders.TELEFONE:
                    mapaColunas.put(ConstantesExcelHeaders.TELEFONE, cell.getColumnIndex());
                    break;
                case ConstantesExcelHeaders.CELULAR:
                    mapaColunas.put(ConstantesExcelHeaders.CELULAR, cell.getColumnIndex());
                    break;
                case ConstantesExcelHeaders.EMAIL:
                    mapaColunas.put(ConstantesExcelHeaders.EMAIL, cell.getColumnIndex());
                    break;
                case ConstantesExcelHeaders.SEXO:
                    mapaColunas.put(ConstantesExcelHeaders.SEXO, cell.getColumnIndex());
                    break;

                default:

                    break;
            }

        } else {
            throw new NegocioException("Arquivo fora do padrão de colunas.");
        }

    }

    public Double getTotalLinhas() {
        return totalLinhas;

    }

    public Double getTotalLinhasAtualizadas() {
        return linhasAtualizadas;

    }

}
