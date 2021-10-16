package apap.tugas.sielekthor.controller;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.model.MemberModel;
import apap.tugas.sielekthor.model.PembelianBarangModel;
import apap.tugas.sielekthor.model.PembelianModel;
import apap.tugas.sielekthor.repository.PembelianDB;
import apap.tugas.sielekthor.service.MemberService;
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

    @Autowired
    private MemberService memberService;

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

    @PostMapping("/pembelian/hapus/{idPembelian}")
    public String deletePembelianSubmit(
            @PathVariable Long idPembelian,
            Model model
    ) {
        PembelianModel pembelian = pembelianService.getPembelianById(idPembelian);
        System.out.println("masuk pak eko");
        pembelianService.deletePembelian(pembelian);
        model.addAttribute("pembelian", pembelian);
        model.addAttribute("listPembelianBarang", pembelian.getListPembelianBarang());
        return "yey-delete-pembelian";
    }

    @RequestMapping(value="/cari/pembelian", method=RequestMethod.GET)
    public String cariPembelian(
            @RequestParam(required = false, value = "idMember") Long idMember,
            @RequestParam(required = false,value = "isCash") Boolean isCash,
            Model model)
    {
        //List<PembelianModel> listPembelian = pembelianService.getPembelianList();
        List<MemberModel> listMember = memberService.getMemberList();
        List<PembelianModel> listPembelian = new ArrayList<>();

        System.out.println("masuk 1");
        boolean searched = false;

        if (idMember != null && isCash != null) {
            System.out.println("masuk if 1");
            listPembelian = pembelianService.findPembelian(idMember, isCash);
            model.addAttribute("listPembelian", listPembelian);
            model.addAttribute("listMember", listMember);
            searched = true;
            model.addAttribute("searched", searched);
            return "cari-pembelian";

        } if (idMember != null && isCash == null){
            System.out.println("masuk if 2");
            listPembelian = pembelianService.findPembelianByIdMember(idMember);
            model.addAttribute("listPembelian", listPembelian);
            model.addAttribute("listMember", listMember);
            searched = true;
            model.addAttribute("searched", searched);
            return "cari-pembelian";

        } if (idMember == null && isCash != null){
            System.out.println("masuk if 3");
            listPembelian = pembelianService.findPembelianByPaymentMethod(isCash);
            searched = true;
            model.addAttribute("listPembelian", listPembelian);
            model.addAttribute("listMember", listMember);
            model.addAttribute("searched", searched);
            return "cari-pembelian";

        }
        model.addAttribute("listPembelian", listPembelian);
        model.addAttribute("listMember", listMember);
        model.addAttribute("searched", searched);
        return "cari-pembelian";

    }
}

