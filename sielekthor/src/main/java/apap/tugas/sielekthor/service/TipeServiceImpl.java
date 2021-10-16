package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.model.TipeModel;
import apap.tugas.sielekthor.repository.TipeDB;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TipeServiceImpl implements TipeService{
    @Autowired
    TipeDB tipeDB;

    @Override
    public List<TipeModel> getListTipe(){
        return tipeDB.findByOrderByIdAsc();
    }

    @Override
    public TipeModel getTipeById(Long id) {
        Optional<TipeModel> tipe = tipeDB.findById(id);
        if (tipe.isPresent()) {
            return tipe.get();
        }
        return null;
    }
}
