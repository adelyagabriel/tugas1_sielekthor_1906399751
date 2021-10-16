package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.model.PembelianBarangModel;
import apap.tugas.sielekthor.model.PembelianModel;
import apap.tugas.sielekthor.repository.PembelianDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
        Optional<PembelianModel> pembelian = pembelianDB.findById(id);
        if (pembelian.isPresent()) {
            return pembelian.get();
        }
        return null;
    }

    @Override
    public void deletePembelian(PembelianModel pembelian) {
        List<PembelianBarangModel> listPembelianBarang = pembelian.getListPembelianBarang();
        for (int p = 0; p < listPembelianBarang.size(); p++){
            System.out.println("service dalam for");
            BarangModel barang = listPembelianBarang.get(p).getBarang();
            barang.setStok(barang.getStok() + listPembelianBarang.get(p).getQuantity());
        }
        System.out.println("service luar");
        pembelianDB.delete(pembelian);
    }

    @Override
    public List<PembelianModel> findPembelian(Long idMember, Boolean isCash) {
        List<PembelianModel> listPembelian = getPembelianList();
        List<PembelianModel> result = new ArrayList<>();
        for (int p = 0; p < listPembelian.size(); p++){
            if (listPembelian.get(p).getMember().getId().equals(idMember)){
                if (listPembelian.get(p).getIsCash().equals(isCash)){
                    result.add(listPembelian.get(p));
                }
            }
        }
        return result;
    }

    @Override
    public List<PembelianModel> findPembelianByIdMember (Long idMember){
        List<PembelianModel> result = new ArrayList<>();
        List<PembelianModel> listPembelian = getPembelianList();
        for (int p = 0; p < listPembelian.size(); p++) {
            if (listPembelian.get(p).getMember().getId().equals(idMember)) {
                result.add(listPembelian.get(p));
            }
        }
        return result;
    }

    @Override
    public List<PembelianModel> findPembelianByPaymentMethod (Boolean isCash){
        List<PembelianModel> result = new ArrayList<>();
        List<PembelianModel> listPembelian = getPembelianList();
        for (int p = 0; p < listPembelian.size(); p++) {
            if (listPembelian.get(p).getIsCash().equals(isCash)){
                result.add(listPembelian.get(p));
            }
        }
        return result;
    }



}
