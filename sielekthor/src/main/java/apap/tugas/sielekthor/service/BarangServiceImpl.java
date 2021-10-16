package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.model.TipeModel;
import apap.tugas.sielekthor.repository.BarangDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BarangServiceImpl implements BarangService {

    @Autowired
    BarangDB barangDB;

    @Qualifier("tipeServiceImpl")
    @Autowired
    TipeService tipeService;

    @Override
    public void addBarang(BarangModel barang) {
        barangDB.save(barang);
    }

    @Override
    public List<BarangModel> getBarangList() {
        return barangDB.findAll();
    }

    @Override
    public BarangModel getBarangById(Long id) {
        Optional<BarangModel> barang = barangDB.findById(id);
        if (barang.isPresent()) {
            return barang.get();
        }
        return null;
    }

    @Override
    public void updateBarang(BarangModel barang) {
        barangDB.save(barang);
    }

    @Override
    public List<BarangModel> findBarang(Long idTipe, boolean stok) {
        List<BarangModel> listBarang = getBarangList();
        List<BarangModel> result = new ArrayList<>();

        for (int b = 0; b < listBarang.size(); b++){
            if (listBarang.get(b).getTipe().getId().equals(idTipe)){
                if (stok && listBarang.get(b).getStok() > 0){
                    result.add(listBarang.get(b));
                } else if (!stok && listBarang.get(b).getStok() <= 0){
                    result.add(listBarang.get(b));
                }
            }
        }
        return result;
    }
}


