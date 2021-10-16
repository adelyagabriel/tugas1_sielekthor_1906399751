package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.PembelianModel;
import java.util.List;

public interface PembelianService {
    List<PembelianModel> getPembelianList();
    PembelianModel getPembelianById(Long id);
    void deletePembelian(PembelianModel pembelian);
    List<PembelianModel> findPembelian(Long idMember, Boolean isCash);
    List<PembelianModel> findPembelianByIdMember(Long idMember);
    List<PembelianModel> findPembelianByPaymentMethod (Boolean isCash);
}
