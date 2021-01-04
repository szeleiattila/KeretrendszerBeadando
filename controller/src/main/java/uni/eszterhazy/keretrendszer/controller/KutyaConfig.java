package uni.eszterhazy.keretrendszer.controller;

import json.KutyaDAOJSON;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uni.eszterhazy.keretrendszer.dao.KutyaDAO;
import uni.eszterhazy.keretrendszer.service.impl.KutyaServiceImpl;

import java.io.IOException;

@Configuration
public class KutyaConfig {

    @Bean("kutyaDB")
    public KutyaServiceImpl service() throws IOException {
        KutyaDAO dao = new KutyaDAOJSON("adatok.json");
        return new KutyaServiceImpl(dao);
    }

}
