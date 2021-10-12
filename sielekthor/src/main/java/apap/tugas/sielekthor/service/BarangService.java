package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.BarangModel;
import java.util.List;

public interface BarangService {
    BarangModel getBarangById(Long id);
    void addBarang(BarangModel barang);
    List<BarangModel> getBarangList();
}
