/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ns.torcedor.repository;

import br.com.ns.torcedor.model.Torcedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Cesar Searlini
 */
public interface TorcedorRepository extends JpaRepository<Torcedor, Long> {

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Torcedor t WHERE t.email = :email")
    boolean isEmailCadastrado(@Param("email") String emails);
    
    
    Torcedor findById(@Param("id") Long id);

}
