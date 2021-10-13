package apap.tugas.sielekthor.controller;

import apap.tugas.sielekthor.model.MemberModel;
import apap.tugas.sielekthor.service.MemberService;
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
public class MemberController {
    @Qualifier("memberServiceImpl")
    @Autowired
    private MemberService memberService;

    //View Semua Member
    @GetMapping("/member")
    public String viewAllMember(Model model) {
        List<MemberModel> listMember = memberService.getMemberList();
        model.addAttribute ( "listMember",listMember);
        return "viewall-member" ;
    }

    //Menampilkan form tambah barang
    @GetMapping("/member/tambah")
    public String addMemberForm(Model model) {
        model.addAttribute("listMember", memberService.getMemberList());
        model.addAttribute("member", new MemberModel());
        return "form-add-member";
    }

    //Menampilkan pesan bahwa member berhasil ditambahkan
    @PostMapping("/member/tambah")
    public String addMemberSubmit(
            @ModelAttribute MemberModel member,
            Model model
    ){
        memberService.addMember(member);
        model.addAttribute( "namaMember", member.getNamaMember());
        return "yey-add-member";
    }

    //Mengubah detail member
    @GetMapping("/member/ubah/{idMember}")
    public String updateMemberForm(
            @PathVariable Long idMember,
            Model model
    ){
        MemberModel member = memberService.getMemberById(idMember);
        model.addAttribute( "member", member);
        return"form-update-member" ;
    }

    //Post mapping berhasil ubah member
    @PostMapping("/member/ubah")
    public String updateMemberSubmit(
            @ModelAttribute MemberModel member,
            Model model
    ){
        memberService.updateMember(member);
        model.addAttribute( "namaMember", member.getNamaMember());
        return "yey-update-member";
    }
}
