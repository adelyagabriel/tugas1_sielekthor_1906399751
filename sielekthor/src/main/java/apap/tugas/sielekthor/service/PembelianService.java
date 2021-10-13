package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.PembelianModel;
import java.util.List;

public interface PembelianService {
    List<PembelianModel> getPembelianList();
    PembelianModel getPembelianById(Long id);
}
