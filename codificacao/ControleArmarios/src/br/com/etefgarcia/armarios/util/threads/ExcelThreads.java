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
package br.com.etefgarcia.armarios.util.threads;

import br.com.etefgarcia.armarios.service.ExcelService;
import br.com.etefgarcia.armarios.view.BarraProgresso;
import br.com.etefgarcia.armarios.view.aluno.CarregarPlanilhaAlunoView;
import java.io.File;

/**
 *
 * @author fernando-pucci
 */
public class ExcelThreads {

    private ExcelService excelService = null;
    private CarregarPlanilhaAlunoView carregarPlanilhaAlunoView = null;
   
    public final static BarraProgresso bp = new BarraProgresso();

    public ExcelThreads(File arquivo, CarregarPlanilhaAlunoView carregarPlanilhaAlunoView) {

        this.carregarPlanilhaAlunoView = carregarPlanilhaAlunoView;

        if (arquivo != null) {

            excelService = new ExcelService(arquivo, carregarPlanilhaAlunoView);
            controlaBarraProgresso(excelService);
            excelService.inicia();

        } else {

            this.carregarPlanilhaAlunoView.processarErro("Problemas ao iniciar a Thread de leitura.");
        }

    }

    private static Thread getThreadBarraProgresso(final Object obj, final BarraProgresso bp) {

        return new Thread() {
            public void run() {

                ExcelService es = (ExcelService) obj;

                do {

                    if (es.getTotalLinhas() > 0) {

                        bp.setValorTotal(es.getTotalLinhas());

                        bp.setValor(((es.getTotalLinhasAtualizadas() * 100) / es.getTotalLinhas()), es.getTotalLinhasAtualizadas().intValue());

                    } else {
                        bp.setValor(0.0, 0);
                        bp.setValorTotal(0.0);
                    }

                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException ex) {

                    }

                    if (((es.getTotalLinhasAtualizadas()) == es.getTotalLinhas().doubleValue()) && (es.getTotalLinhasAtualizadas() > 0.0)) {

                        break;

                    }

                } while (es.getTotalLinhasAtualizadas() <= es.getTotalLinhas());

                bp.dispose();

            }
        };
    }

    private static void controlaBarraProgresso(final Object obj) {

        bp.setVisible(true);

        Thread t;

        t = getThreadBarraProgresso(obj, bp);
        t.setName("BARRA_PROGRESSO_THREAD");
        t.start();

    }

}
