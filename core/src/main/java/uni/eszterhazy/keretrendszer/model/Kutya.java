package uni.eszterhazy.keretrendszer.model;

import com.sun.istack.internal.NotNull;
import org.apache.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import uni.eszterhazy.keretrendszer.exceptions.KilogramNegativ;
import uni.eszterhazy.keretrendszer.exceptions.NevNemLehetUres;
import uni.eszterhazy.keretrendszer.exceptions.RosszSzuletesiDatum;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;


public class Kutya {
    private String nev;
    private String id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate szuletesIdeje;
    private Fajta fajta;
    private int kilogramm;
    Logger logger = Logger.getLogger(this.getClass());



    public Kutya() {
        this.id = UUID.randomUUID().toString();
        logger.info("Uj kutya lett hozzáadva a "+ this.id +"azonositoval");
    }

    public Fajta getFajta() {
        return fajta;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(@NotNull String nev) throws NevNemLehetUres {
        if(nev.length()==0)
        {
            throw new NevNemLehetUres();
        }
        this.nev = nev;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getSzuletesIdeje() {
        return szuletesIdeje;
    }

    public void setSzuletesIdeje( @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate szuletesIdeje) throws RosszSzuletesiDatum {
        if(szuletesIdeje.isAfter(LocalDate.now())){
            throw new RosszSzuletesiDatum("Még meg sem született");
        }
        if(szuletesIdeje.isBefore(LocalDate.now().minusYears(40)))
        {
            throw new RosszSzuletesiDatum("40 évnél öregebb kutya nem létezik: "+ szuletesIdeje);
        }
        this.szuletesIdeje = szuletesIdeje;
    }




    public void setFajta(Fajta fajta) {
        this.fajta = fajta;
    }

    public int getKilogramm() {
        return kilogramm;
    }




    public void setKilogramm(int kilogramm) throws KilogramNegativ {
        if (kilogramm<0){
            throw new KilogramNegativ(kilogramm);
        }
        this.kilogramm = kilogramm;

    }

    @Override
    public String toString() {
        return "Kutya{" +
                "nev='" + nev + '\'' +
                ", id='" + id + '\'' +
                ", szuletesIdeje=" + szuletesIdeje +
                ", fajta=" + fajta +
                ", kilogramm=" + kilogramm +

                '}';
    }

}
