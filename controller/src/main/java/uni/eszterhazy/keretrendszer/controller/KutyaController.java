package uni.eszterhazy.keretrendszer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import uni.eszterhazy.keretrendszer.exceptions.KutyaAlreadyAdded;
import uni.eszterhazy.keretrendszer.exceptions.KutyaNotFound;
import uni.eszterhazy.keretrendszer.exceptions.RosszSzuletesiDatum;
import uni.eszterhazy.keretrendszer.model.Fajta;
import uni.eszterhazy.keretrendszer.model.Kutya;
import uni.eszterhazy.keretrendszer.service.impl.KutyaServiceImpl;

@Controller
public class KutyaController {
    @Autowired
    private KutyaServiceImpl kutyaDB;

    @ModelAttribute(value = "kutya")
    public Kutya create() {
        return new Kutya();
    }


    @GetMapping(value = "/kutyak")
    public ModelAndView getKutyak() {
        ModelAndView mav = new ModelAndView("kutyaList.jsp");
        System.out.println(kutyaDB.getAllKutya());
        mav.addObject("kutyak", kutyaDB.getAllKutya());
        return mav;
    }

    @GetMapping(value = "/kutyak/{fajta}")
    public ModelAndView getKutyakbyfajta(@PathVariable String fajta) throws KutyaNotFound {
        ModelAndView mav = new ModelAndView("kutyaList.jsp");
        if (fajta.equals("nemetjuhasz")){
            mav.addObject("kutyak", kutyaDB.readAllKutyaByFajta(Fajta.NEMETJUHASZ));
        } else if (fajta.equals("vizsla")){
            mav.addObject("kutyak", kutyaDB.readAllKutyaByFajta(Fajta.VIZSLA));
        } else if (fajta.equals("tacsko")){
            mav.addObject("kutyak", kutyaDB.readAllKutyaByFajta(Fajta.TACSKO));
        } else if (fajta.equals("bischon")) {
            mav.addObject("kutyak", kutyaDB.readAllKutyaByFajta(Fajta.BISCHON));
        } else if (fajta.equals("puli")){
            mav.addObject("kutyak", kutyaDB.readAllKutyaByFajta(Fajta.PULI));
        }
        return mav;
    }



    @GetMapping(value = "/kutya/{id}")
    public String getKutyaById(@PathVariable String id, Model model) throws KutyaNotFound {
        model.addAttribute("kutya", kutyaDB.getKutyaById(id));
        return "kutyadetails.jsp";
    }

    @GetMapping(value = "addKutya")
    public String addKutyaForm(Model model) {
        model.addAttribute("fajtak", Fajta.values());
        return "kutyaForm.jsp";
    }
    @PostMapping(value = "addKutya")
    public String addKutya(@ModelAttribute("kutya") Kutya kutya, Model model){
        System.out.println(kutya);
        try {
            kutyaDB.addKutya(kutya);
        } catch (KutyaAlreadyAdded kutyaAlreadyAdded) {
            kutyaAlreadyAdded.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Üzenet", kutyaAlreadyAdded);
    }
        return "redirect:kutya/"+kutya.getId();
    }
    @ExceptionHandler(KutyaNotFound.class)
    public String kutyaNotFound(KutyaNotFound e){
        return "Kutya az adott azonositoval nem található: "+ e.getMessage();
    }
    @ExceptionHandler(KutyaAlreadyAdded.class)
    @ResponseStatus(HttpStatus.IM_USED)
    public String usedKutyaId(KutyaAlreadyAdded e){
        return "Kutya az adott azonositoval már létezik: "+ e.getMessage();
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
        public String badrequest (HttpMessageNotReadableException e){
        Throwable c= e.getCause().getCause();
        return c.getClass().getSimpleName()+ ": "+c.getMessage();
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public String methodNotAllowed(HttpRequestMethodNotSupportedException e){
        return "This method is not allowed"+e.getMethod()+", use one of these "+e.getSupportedHttpMethods();
    }


    }


