
package seguridad;

import java.util.ArrayList;
import logicadenegocios.CuentaBancaria;
public class CuentaBancariaSc {
    
    //autenticación para iniciar operaciones en cuentas
    public boolean autenticarCuenta( int pNumCuenta,String pPin,ArrayList<CuentaBancaria>cuentas){
        if(autenticarNumCuenta(pNumCuenta,cuentas) == true && autenticarPin(pPin,cuentas) == true){
            return true;
        }
        return false;
    }
    
    //Método para inactivar una cuenta en caso de anomalías (pin y mensaje)
    public static void inactivarCuenta(CuentaBancaria cuenta){
        cuenta.setActiva(false);
    }
         
    
    private boolean autenticarNumCuenta(int pNumCuenta,ArrayList<CuentaBancaria>cuentas){
          for (int i = 0; i < cuentas.size(); i++){
            if ( cuentas.get(i).getNumeroCuenta() == pNumCuenta && cuentas.get(i).isActiva() == true){
                return true;
            }
        }
      return false; 
    }
    private boolean autenticarPin(String pPin,ArrayList<CuentaBancaria>cuentas){
          for (int i = 0; i < cuentas.size(); i++){
            if (cuentas.get(i).getPin().equals(pPin)){
                return true;
            }
        }
      return false; 
    }
      
  }
    

