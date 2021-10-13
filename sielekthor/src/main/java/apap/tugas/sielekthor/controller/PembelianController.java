package apap.tugas.sielekthor.controller;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.model.PembelianBarangModel;
import apap.tugas.sielekthor.model.PembelianModel;
import apap.tugas.sielekthor.repository.PembelianDB;
import apap.tugas.sielekthor.service.PembelianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util. ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class PembelianController {

    @Qualifier("pembelianServiceImpl")
    @Autowired
    private PembelianService pembelianService;

    //View Semua Pembelian
    @GetMapping("/pembelian")
    public String viewAllPembelian(Model model) {
        List<PembelianModel> listPembelian = pembelianService.getPembelianList();
        model.addAttribute ( "listPembelian",listPembelian);
        return "viewall-pembelian" ;
    }

    //Menampilkan detail pembelian
    @GetMapping("/pembelian/{idPembelian}")
    public String viewDetailPembelian(
            @PathVariable Long idPembelian,
            Model model
    ) {
        PembelianModel pembelian = pembelianService.getPembelianById(idPembelian);
        List<PembelianBarangModel> listPembelianBarang = pembelian.getListPembelianBarang();

        if(pembelian == null){
            model.addAttribute("pembelian", pembelian);
            return "error-view-pembelian";
        }
        model.addAttribute( "pembelian", pembelian);
        model.addAttribute( "listPembelianBarang", listPembelianBarang);
        return "view-pembelian";
    }
}

