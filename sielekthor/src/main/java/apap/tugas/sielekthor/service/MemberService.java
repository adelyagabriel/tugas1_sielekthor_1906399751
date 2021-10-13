package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.MemberModel;

import java.util.List;

public interface MemberService {
    MemberModel getMemberById(Long id);
    void addMember(MemberModel member);
    List<MemberModel> getMemberList();
}
