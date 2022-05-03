
package controlador;

import java.util.ArrayList;
import logicadenegocios.Cliente;
import logicadenegocios.CuentaBancaria;

public class CuentaBancariaCt {
    public ArrayList<CuentaBancaria> cuentas = new ArrayList();
    
    public ArrayList<CuentaBancaria> getCuentas (){
        return cuentas;
    }
    
    public String resgistrarCuentaBancaria (double pSaldo, String pPin,int pIdentificacion){
        if (autenticarCliente(pIdentificacion) != true){
            return "El cliente no existe, inténtelo de nuevo.";
        }
        
        if (validarPin(pPin) != true){
            return "El PIN es incorrecto, inténtelo de nuevo.";
        }
        
        //No sé cual es método para validar el monto
        
        CuentaBancaria cuentaBancariaAux = null;
        Cliente clienteAux = null;
                
        for (int i = 0; i < clientes.size(); i++){
            if ( clientes.get(i).getIdentificacion() == pIdentificacion){
                clienteAux = clientes.get(i);
            }
        }
        cuentaBancariaAux = new CuentaBancaria(pSaldo,pPin,clienteAux);
        cuentas.add(cuentaBancariaAux);
        
        return "Se ha creado una nueva cuenta en el sistema, los datos de la cuenta son: \n" +
        "Número de cuenta:"+ cuentaBancariaAux.getNumeroCuenta() +"\n" +
        "Estatus de la cuenta:"+ cuentaBancariaAux.isActiva() +"\n" +
        "Saldo actual:"+ cuentaBancariaAux.getSaldo() +"\n" +
        "--- \n" +
        "Nombre del dueño de la cuenta:"+ clienteAux.getNombreCompleto() +"\n" +
        "Número de teléfono “asociado” a la cuenta:"+ clienteAux.getNumeroTelefonico() +"\n" +
        "Dirección de correo electrónico “asociada” a la cuenta:"+ clienteAux.getCorreoElectronico();   
    }
}
