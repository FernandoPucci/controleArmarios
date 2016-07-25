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

import br.com.etefgarcia.armarios.view.BarraProgresso;

/**
 *
 * @author fernando-pucci
 */
public class TesterProgresso {

    private TesterApachePOI tpoe = null;

    public TesterProgresso() {

        tpoe = new TesterApachePOI();
        controlaBarraProgresso(tpoe);
        tpoe.inicia();

    }

    public static void main(String[] args) {
        TesterProgresso este = new TesterProgresso();
    }

    private static void controlaBarraProgresso(final Object obj) {

        final BarraProgresso bp = new BarraProgresso();

        bp.setVisible(true);

        Thread t = new Thread() {
            public void run() {

                TesterApachePOI tp = (TesterApachePOI) obj;

                do {

                    if (tp.getTotalLinhas() > 0) {
                        
                        bp.setValorTotal(tp.getTotalLinhas());
                        
                        bp.setValor(((tp.getTotalLinhasAtualizadas() * 100) / tp.getTotalLinhas()), tp.getTotalLinhasAtualizadas().intValue());
                        
                    } else {
                        bp.setValor(0.0,0);
                        bp.setValorTotal(0.0);
                    }

                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException ex) {

                    }

                } while (tp.getTotalLinhasAtualizadas() <= tp.getTotalLinhas());
            }
        };

        t.start();

    }

}
