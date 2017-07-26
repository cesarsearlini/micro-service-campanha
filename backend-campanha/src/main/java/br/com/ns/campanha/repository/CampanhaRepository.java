/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ns.campanha.repository;

import br.com.ns.campanha.model.Campanha;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Cesar Searlini
 */
public interface CampanhaRepository extends JpaRepository<Campanha, Long> {

    @Query(value = " SELECT * "
            + " FROM campanha "
            + " WHERE "
            + " data_inicio BETWEEN :dataInicio AND :dataTermino "
            + " AND data_termino >= :dataTermino "
            + " ORDER BY data_termino DESC", nativeQuery = true)
    public List<Campanha> queryPeriodo(@Param("dataInicio") Date dateInicio, @Param("dataTermino") Date dateTermino);
    
    @Query(value = " SELECT * "
            + " FROM campanha "
            + " WHERE "
            + " data_termino >= :dataTermino", nativeQuery = true)
    public List<Campanha> queryAllAtiva(@Param("dataTermino") Date dateTermino);          

    @Query(value = " SELECT * "
            + " FROM campanha "
            + " WHERE "
            + " id = :id "
            + " AND data_termino >= :dataTermino", nativeQuery = true)
    public Campanha queryById(@Param("id") Long id, @Param("dataTermino") Date dateTermino);   

}
