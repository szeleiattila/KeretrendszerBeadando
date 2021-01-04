package uni.eszterhazy.keretrendszer.dao;

import uni.eszterhazy.keretrendszer.model.Fajta;
import uni.eszterhazy.keretrendszer.model.Kutya;
import uni.eszterhazy.keretrendszer.exceptions.KutyaAlreadyAdded;
import uni.eszterhazy.keretrendszer.exceptions.KutyaNotFound;

import java.util.Collection;

public interface KutyaDAO {
    void createKutya(Kutya kutya) throws KutyaAlreadyAdded;
    Collection<Kutya>readAllKutya();
    Kutya readKutya(String id) throws KutyaNotFound;
    void updateKutya(Kutya kutya);
    void deleteKutya(Kutya kutya);
    Collection<Kutya> readAllKutyaByFajta(Fajta fajta) throws KutyaNotFound;
}
