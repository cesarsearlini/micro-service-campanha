/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ns.torcedor.repository;

import br.com.ns.torcedor.model.TorcedorHasCampanhas;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Cesar Searlini
 */
public interface TorcedorHasCampanhaRepository extends JpaRepository<TorcedorHasCampanhas, Long> {   

        
    List<TorcedorHasCampanhas> findByIdTorcedor(@Param("idTorcedor") Long idTorcedor);

    
}
