package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import uni.eszterhazy.keretrendszer.dao.KutyaDAO;
import uni.eszterhazy.keretrendszer.exceptions.KutyaAlreadyAdded;
import uni.eszterhazy.keretrendszer.exceptions.KutyaNotFound;
import uni.eszterhazy.keretrendszer.model.Kutya;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class KutyaDAOJSON implements KutyaDAO {

    File jsonFile;
    ObjectMapper mapper;
    public KutyaDAOJSON(String jsonFilePath) throws IOException {
        jsonFile= new File(jsonFilePath);
        System.out.println(jsonFile.getAbsolutePath());
        if(!jsonFile.exists()){
            jsonFile.createNewFile();
            FileWriter writer = new FileWriter(jsonFile);
            writer.write("[]");
            writer.flush();
            writer.close();
        }

        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }
    @Override
    public void createKutya(Kutya kutya) throws KutyaAlreadyAdded {

        Collection<Kutya> kutyak = readAllKutya();

        Kutya result = null;
        try {
            result = readKutya(kutya.getId());

        } catch (KutyaNotFound kutyaNotFound) {
            kutyak.add(kutya);

            try {
                mapper.writeValue(jsonFile, kutyak);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if(result!=null) {throw new KutyaAlreadyAdded(kutya.getId());}

    }

    @Override
    public Collection<Kutya> readAllKutya() {
        Collection<Kutya> kutyak = new ArrayList<Kutya>();
        TypeReference type = new TypeReference<ArrayList<Kutya>>() {};
        try {
            kutyak = mapper.readValue(jsonFile,type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return kutyak;
    }


    public Kutya readKutya(String id) throws KutyaNotFound {
        Collection<Kutya> kutyak = readAllKutya();
        for (Kutya k: kutyak){
            if(k.getId().equalsIgnoreCase(id)){
                return k;
            }
        }
        throw new KutyaNotFound(id);
    }

    @Override
   public Collection<Kutya> readAllKutyaByFajta(uni.eszterhazy.keretrendszer.model.Fajta fajta) throws KutyaNotFound {
        Collection<Kutya> kutyak= readAllKutya();
        Collection<Kutya> result = new ArrayList<Kutya>();
        for (Kutya k: kutyak){
            if(k.getFajta().equals(fajta)){
                result.add(k);
            }
        }
        return result;
    }

    @Override
    public void deleteKutya(Kutya kutya) {
        Collection<Kutya> kutyak= readAllKutya();
        kutyak.remove(kutya);
    }

    @Override
    public void updateKutya(Kutya kutya) {

        try {
            Kutya kutyaResult= readKutya(kutya.getId());
            kutyaResult=kutya;
        } catch (KutyaNotFound kutyaNotFound) {
            kutyaNotFound.printStackTrace();
        }

    }
}
