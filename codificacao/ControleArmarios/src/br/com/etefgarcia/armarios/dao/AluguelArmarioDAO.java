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
package br.com.etefgarcia.armarios.dao;

import br.com.etefgarcia.armarios.model.AluguelArmario;
import java.util.Date;
import java.util.List;

/**
 *
 * @author fernando-pucci
 */
public interface AluguelArmarioDAO extends BaseDAO<AluguelArmario, Long> {

    public List<AluguelArmario> getAllAluguelArmarioEmAberto() throws Exception;

    public List<AluguelArmario> getAllAluguelArmarioEmAberto(Date dataInicial, Date DataFinal) throws Exception;

    public List<AluguelArmario> getAllAluguelArmarioFechadas() throws Exception;

    public List<AluguelArmario> getAllAluguelArmarioFechadas(Date dataInicial, Date DataFinal) throws Exception;

}
