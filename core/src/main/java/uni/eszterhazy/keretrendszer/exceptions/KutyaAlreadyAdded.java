package uni.eszterhazy.keretrendszer.exceptions;

public class KutyaAlreadyAdded extends Throwable {
    public KutyaAlreadyAdded(String id) {
        super(id);
    }
}
