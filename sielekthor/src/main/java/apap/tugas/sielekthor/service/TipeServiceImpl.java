package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.TipeModel;
import apap.tugas.sielekthor.repository.TipeDB;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TipeServiceImpl implements TipeService{
    @Autowired
    TipeDB tipeDB;

    @Override
    public List<TipeModel> getListTipe(){
        return tipeDB.findByOrderByIdAsc();
    }

}