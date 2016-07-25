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
package testers;

import br.com.etefgarcia.armarios.dao.AlunoDAO;
import br.com.etefgarcia.armarios.dao.impl.AlunoDAOImpl;
import br.com.etefgarcia.armarios.exceptions.NegocioException;
import br.com.etefgarcia.armarios.exceptions.SistemaException;
import br.com.etefgarcia.armarios.model.Aluno;
import br.com.etefgarcia.armarios.service.AlunoService;
import br.com.etefgarcia.armarios.util.ServiceUtils;
import br.com.etefgarcia.armarios.util.TelaRenderUtil;
import br.com.etefgarcia.armarios.util.TelaUtils;
import br.com.etefgarcia.armarios.util.constantes.ConstantesExcelHeaders;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author fernando-pucci
 */
public class TesterApachePOI {
    
    private static final String CAMINHO_ARQUIVO = "/home/fernando-pucci/Projetos/ETEC/Diversos/base_xls/banco_alunos.xlsx";
    
    private static Map<String, Integer> mapaColunas;
    
    public static void main(String[] args) {
        
        try {
            System.out.println("TESTE APACHE POI");
            
            List<Aluno> listaAlunos = null;
            
            listaAlunos = carregaListaAlunos(CAMINHO_ARQUIVO, 4);

            //TESTE - mostra mapa de colunas
//            for (Map.Entry m : mapaColunas.entrySet()) {
//
//                System.out.println(m.getKey() + " - " + m.getValue());
//                
//            }
            AlunoDAO dao = new AlunoDAOImpl();
            
            for (Aluno a : listaAlunos) {
                
                System.out.println(a);
                
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
                    
                } catch (Exception e) {
                    
                    throw new SistemaException(e.getMessage());
                    
                }
            }
            
            System.out.println(listaAlunos.size() + "registros.");
            
        } catch (SistemaException | NegocioException | IOException ex) {
            
            System.out.println(ex.getMessage());
            
        } finally {
            
            System.exit(0);
            
        }
        
    }
    
    private static List<Aluno> carregaListaAlunos(String arquivo, int linhaInicio) throws NegocioException, IOException {
        
        List<Aluno> listaAlunos = null;
        
        try {
            
            int linhaInicioReal = linhaInicio - 1;
            
            listaAlunos = new ArrayList<>();
            mapaColunas = new HashMap<>();

            //Create the input stream from the xlsx/xls file
            FileInputStream fis = new FileInputStream(arquivo);

            //Create Workbook instance for xlsx/xls file input stream
            Workbook workbook = null;
            if (arquivo.toLowerCase().endsWith("xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else if (arquivo.toLowerCase().endsWith("xls")) {
                workbook = new HSSFWorkbook(fis);
            }

            //Get the number of sheets in the xlsx file
            int numberOfSheets = workbook.getNumberOfSheets();

            //loop through each of the sheets
            for (int i = 0; i < numberOfSheets; i++) {

                //Get the nth sheet from the workbook
                Sheet sheet = workbook.getSheetAt(i);

                //every sheet has rows, iterate over them
                Iterator<Row> rowIterator = sheet.iterator();
                while (rowIterator.hasNext()) {

                    //Get the row object
                    Row row = rowIterator.next();
                    
                    if (row.getRowNum() < linhaInicio) {
                        
                        continue;
                        
                    }
                    
                    Aluno a = new Aluno();

                    //Every row has columns, get the column iterator and iterate over them
                    Iterator<Cell> cellIterator = row.cellIterator();
                    
                    while (cellIterator.hasNext()) {
                        //Get the Cell object
                        Cell cell = cellIterator.next();

                        //System.out.println(row.getRowNum());
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

                        //check the cell type and process accordingly
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
                        
                    } //end of cell iterator

                    listaAlunos.add(a);
                } //end of rows iterator

            } //end of sheets for loop

            //close file input stream
            fis.close();
            
        } catch (IOException e) {
            throw e;
        }
        
        return listaAlunos;
    }
    
    private static boolean validaCampos() {
        
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
    
    private static void populaMapaColunas(Cell cell) throws NegocioException {
        
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
    
}
