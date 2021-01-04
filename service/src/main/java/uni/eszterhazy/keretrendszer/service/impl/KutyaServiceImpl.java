package uni.eszterhazy.keretrendszer.service.impl;

import org.apache.log4j.Logger;
import uni.eszterhazy.keretrendszer.dao.KutyaDAO;
import uni.eszterhazy.keretrendszer.exceptions.KutyaNotFound;
import uni.eszterhazy.keretrendszer.model.Fajta;
import uni.eszterhazy.keretrendszer.model.Kutya;
import uni.eszterhazy.keretrendszer.exceptions.KutyaAlreadyAdded;

import java.util.Collection;

public class KutyaServiceImpl implements KutyaService{
    Logger logger = Logger.getLogger(this.getClass());

    private KutyaDAO dao;

    public KutyaServiceImpl(KutyaDAO dao) {
        this.dao = dao;
    }
    @Override
    public void addKutya(Kutya kutya) throws KutyaAlreadyAdded {
        dao.createKutya(kutya);
    }
    @Override
    public Collection<Kutya> getAllKutya() {
    Collection<Kutya> result = dao.readAllKutya();
    logger.info("Az adatbázisban "+ result.size()+" kutya van felvéve.");
        return result;
    }
    @Override
    public Kutya getKutyaById(String id) throws KutyaNotFound{
        return dao.readKutya(id);
    }

    @Override
    public void updateKutya(Kutya kutya) {
    dao.updateKutya(kutya);
    }
    @Override
    public void removeKutya(Kutya kutya) {
        Collection<Kutya> kutyak= getAllKutya();
        kutyak.remove(kutya);
    }

    @Override
    public Collection<Kutya> readAllKutyaByFajta(Fajta fajta) throws KutyaNotFound {
        return dao.readAllKutyaByFajta(fajta);
    }
        /*@Override
    public double atlagKilogramm() {
        Collection<Kutya> kutyak = getAllKutya();
        double sum =0;
        for ( Kutya k: kutyak ){
            sum += k.getKilogramm();
        }
        return sum / kutyak.size();
    }*/
}
