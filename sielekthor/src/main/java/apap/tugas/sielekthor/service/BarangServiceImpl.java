package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.BarangModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BarangServiceImpl implements BarangService {

    @Override
    public void addBarang(BarangModel barang) {

    }
}
