/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ns.torcedor.util;

import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Cesar
 */
public class XResponseEntity {

    private static final String TITULO_RESPOSTA = "Resposta";
    private static final String RETORNO_OK = "00";
    private static final String RETORNO_NOK = "01";

    public static ResponseEntity accepted(String mensagem) {
        return ResponseEntity.accepted()
                .header(TITULO_RESPOSTA, RETORNO_OK, mensagem)
                .build();
    }

    public static ResponseEntity noContent(String mensagem) {
        return ResponseEntity.noContent()
                .header(TITULO_RESPOSTA, RETORNO_NOK, mensagem)
                .build();
    }

    public static ResponseEntity badRequest(String mensagem) {
        return ResponseEntity.badRequest()
                .header(TITULO_RESPOSTA, RETORNO_NOK, mensagem)
                .build();
    }

    public static ResponseEntity ok(Object object) {
        return ResponseEntity.ok(object);
    }

    public static ResponseEntity ok(List<Object> object) {
        return ResponseEntity.ok(object);
    }

}
