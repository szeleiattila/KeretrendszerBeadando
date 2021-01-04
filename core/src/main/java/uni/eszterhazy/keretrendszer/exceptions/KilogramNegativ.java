package uni.eszterhazy.keretrendszer.exceptions;

public class KilogramNegativ extends Throwable {
    public KilogramNegativ(int kilogramm) {
        super(String.valueOf(kilogramm));
    }
}
