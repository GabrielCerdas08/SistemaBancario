
package controlador;

import java.util.ArrayList;
import logicadenegocios.Cliente;
import logicadenegocios.CuentaBancaria;
import controlador.ClienteCt;
import static seguridad.ClienteSc.autenticarCliente;
import static validaciones.ValidacionCuenta.*;
import static validaciones.ValidacionFormato.isNumeric;

public class CuentaBancariaCt {
    static public ArrayList<CuentaBancaria> cuentas = new ArrayList();
    static ClienteCt cliente = new ClienteCt();
    
    public static String resgistrarCuentaBancaria (String pSaldo, String pPin,String pIdentificacion){
        if (validarPin(pPin)== false){
            return "El PIN es incorrecto, inténtelo de nuevo.";
        }
        
        if (isNumeric(pSaldo) == false){
            return "Saldo invalido, inténtelo de nuevo";
        }

         CuentaBancaria cuentaBancariaAux = null;
        for (int i = 0; i < cliente.clientes.size(); i++){
            if (cliente.clientes.get(i).getIdentificacion() == Integer.parseInt(pIdentificacion)){
              
                cuentaBancariaAux = new CuentaBancaria(Integer.parseInt(pSaldo),pPin,cliente.clientes.get(i));
                cuentas.add(cuentaBancariaAux);
                
                cliente.clientes.get(i).agregarCuentaBancaria(cuentaBancariaAux);
            }
        }
        
        return "Se ha creado una nueva cuenta en el sistema, los datos de la cuenta son: \n" + 
        "Número de cuenta:"+ cuentaBancariaAux.getNumeroCuenta() +"\n" +
        "Estatus de la cuenta:"+ cuentaBancariaAux.isActiva() +"\n" +
        "Saldo actual:"+ cuentaBancariaAux.getSaldo() +"\n" +
        "--- \n" +
        "Nombre del dueño de la cuenta:"+ cuentaBancariaAux.getDuenio().getNombreCompleto() +"\n" +
        "Número de teléfono “asociado” a la cuenta:"+ cuentaBancariaAux.getDuenio().getNumeroTelefonico() +"\n" +
        "Dirección de correo electrónico “asociada” a la cuenta:"+ cuentaBancariaAux.getDuenio().getCorreoElectronico();
          
    }
    
    
    public ArrayList<Integer> numCuentas (){
         ArrayList<Integer> numCuentas = new ArrayList<Integer>();
       for (int i = 0; i < cuentas.size(); i++){
           numCuentas.add(cuentas.get(i).getNumeroCuenta());
       }
       return numCuentas;
    }
    
    
}
