package com.mindtech.newsletter.controller;

import com.mindtech.newsletter.dto.InscricaoDto;
import com.mindtech.newsletter.service.InscricaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mindtech-newsletter")
public class InscricaoController extends BaseController {

    @Autowired
    private InscricaoService inscricaoService;

    @PostMapping("/inscricao")
    public ResponseEntity<?> inscricaoNewsletter(@RequestBody InscricaoDto inscricaoDto) {
        return createObjectReturn(inscricaoService.inscricao(inscricaoDto));
    }

    @DeleteMapping("/cancelar-inscricao")
    public ResponseEntity<?> cancelarInscricao(@RequestParam(name = "email") String email) {
        inscricaoService.cancelarInscricao(email);
        return successObjectReturn("Inscrição cancelada com sucesso!");
    }

}
