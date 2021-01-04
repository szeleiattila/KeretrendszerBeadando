package uni.eszterhazy.keretrendszer.service.impl;

import uni.eszterhazy.keretrendszer.exceptions.KutyaAlreadyAdded;
import uni.eszterhazy.keretrendszer.exceptions.KutyaNotFound;
import uni.eszterhazy.keretrendszer.model.Fajta;
import uni.eszterhazy.keretrendszer.model.Kutya;

import java.util.Collection;

public interface KutyaService {
    void addKutya(Kutya kutya) throws KutyaAlreadyAdded;
    Collection<Kutya> getAllKutya();
    Kutya getKutyaById(String id) throws KutyaNotFound;
    void updateKutya(Kutya kutya);
    void removeKutya(Kutya kutya);
    Collection<Kutya> readAllKutyaByFajta(Fajta fajta) throws KutyaNotFound;
    //double atlagKilogramm();

}
