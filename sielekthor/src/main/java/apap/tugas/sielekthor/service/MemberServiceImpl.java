package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.MemberModel;
import apap.tugas.sielekthor.repository.MemberDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberDB memberDB;

    //Get Member by their id
    @Override
    public MemberModel getMemberById(Long id) {
        Optional<MemberModel> member = memberDB.findById(id);
        if (member.isPresent()) {
            return member.get();
        }
        return null;
    }

    //Tambah Member
    @Override
    public void addMember(MemberModel member) {
        memberDB.save(member);
    }

    //Get member list
    @Override
    public List<MemberModel> getMemberList() {
        return memberDB.findAll();
    }

    @Override
    public void updateMember(MemberModel member) {
        memberDB.save(member);
    }
}
