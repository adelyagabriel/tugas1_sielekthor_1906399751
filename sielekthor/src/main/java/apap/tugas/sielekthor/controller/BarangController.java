package apap.tugas.sielekthor.controller;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.service.BarangService;
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
public class BarangController {

    @Qualifier("barangServiceImpl")
    @Autowired
    private BarangService barangService;

    @GetMapping("/barang")
    public String viewAllBarang(Model model) {
        List<BarangModel> listBarang = barangService.getBarangList();
        model.addAttribute ( "listBarang",listBarang);
        return "viewall-barang" ;
    }
}