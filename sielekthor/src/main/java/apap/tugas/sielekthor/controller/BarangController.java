package apap.tugas.sielekthor.controller;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.service.BarangService;
import apap.tugas.sielekthor.service.TipeService;
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

    @Qualifier("tipeServiceImpl")
    @Autowired
    private TipeService tipeService;

    //View Semua Barang
    @GetMapping("/barang")
    public String viewAllBarang(Model model) {
        List<BarangModel> listBarang = barangService.getBarangList();
        model.addAttribute ( "listBarang",listBarang);
        return "viewall-barang" ;
    }

    //Menampilkan form tambah barang
    @GetMapping("/barang/tambah")
    public String addBarangForm(Model model) {
        model.addAttribute("listTipe", tipeService.getListTipe());
        model.addAttribute("barang", new BarangModel());
        return "form-add-barang";
    }

    //Menampilkan pesan bahwa barang berhasil ditambahkan
    @PostMapping("/barang/tambah")
    public String addBarangSubmit(
            @ModelAttribute BarangModel barang,
            Model model
    ){
        barangService.addBarang(barang);
        model.addAttribute( "kodeBarang", barang.getKodeBarang());
        return "yey-add-barang";
    }

    //Menampilkan form tambah barang
    @GetMapping("/barang/")
    public String viewDetailBarang(
            @RequestParam(value = "id") Long id,
            Model model) {

        BarangModel barang = barangService.getBarangById(id);
        if(!barangService.getBarangList().contains(barang)){
            model.addAttribute("kodeBarang", barang.getKodeBarang());
            return "error-view-barang";
        }
        model.addAttribute( "barang", barang);
        return "view-barang";
    }

    //Mengubah detail barang
    @GetMapping("/barang/ubah/{idBarang}")
    public String updateBarangForm(
            @PathVariable Long idBarang,
            Model model
    ){
        BarangModel barang = barangService.getBarangById(idBarang);
        model.addAttribute( "barang", barang);
        return"form-update-barang" ;
    }

    @PostMapping("/barang/ubah")
    public String updateBarangSubmit(
            @ModelAttribute BarangModel barang,
            Model model
    ){
        barangService.updateBarang(barang);
        model.addAttribute( "kodeBarang", barang.getKodeBarang());
        return "yey-update-barang";
    }

}
