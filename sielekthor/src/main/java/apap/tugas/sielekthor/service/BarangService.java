package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.model.TipeModel;

import java.util.List;

public interface BarangService {
    BarangModel getBarangById(Long id);
    void addBarang(BarangModel barang);
    List<BarangModel> getBarangList();
    void updateBarang(BarangModel barang);
    List<BarangModel> findBarang(Long idBarang, boolean stok);
}
