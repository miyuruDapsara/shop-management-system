package bo;

import bo.custom.impl.ApplicationUserBoImpl;
import dao.Daotype;
import dao.custom.impl.ApplicationUserDaoImpl;

import static bo.Botype.APPLICATION_USER;

public class BoFactory {
    private static BoFactory boFactory;

    public  static BoFactory getInstance(){
        if (boFactory == null) {
            boFactory = new BoFactory();
        }
        return boFactory;
    }

    public <T> T getBo(Botype boType){
        switch (boType) {
            case APPLICATION_USER:return(T) new ApplicationUserBoImpl();
            default: return null;
        }
    }
}
