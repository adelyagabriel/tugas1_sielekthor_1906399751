package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.PembelianModel;
import apap.tugas.sielekthor.repository.PembelianDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PembelianServiceImpl implements PembelianService {

    @Autowired
    PembelianDB pembelianDB;

    @Override
    public List<PembelianModel> getPembelianList(){
        return pembelianDB.findAll();
    }

    @Override
    public PembelianModel getPembelianById(Long id) {
        return null;
    }
}
