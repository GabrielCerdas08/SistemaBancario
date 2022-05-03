
package controlador;

import java.util.ArrayList;
import java.util.Date;
import logicadenegocios.Cliente;
import logicadenegocios.CuentaBancaria;
import static validaciones.ValidacionCliente.validarCorreoElectronico;
import static validaciones.ValidacionCliente.validarNumTelefonico;
import static validaciones.ValidacionFormato.isNumeric;
import static validaciones.ValidacionFormato.validarFecha;

public class ClienteCt {
    protected ArrayList<Cliente> clientes = new ArrayList<>();
    
    public ArrayList<Cliente> getClientes (){
        return clientes;
    }
    
    public String registrarCliente (String pPrimerApellido, String pSegundoApellido, String pNombre, int pIdentificacion, Date pFechaNacimiento, int pNumeroTelefonico, String pCorreoElectronico) {
        if (isNumeric(Integer.toString(pIdentificacion)) !=true){
            return "Identificacion invalida, inténtelo de nuevo.";
        }
        
        if (validarNumTelefonico(pNumeroTelefonico) != true){
            return "Numero telefónico invalido, inténtelo de nuevo.";
        }
        
        if (validarCorreoElectronico(pCorreoElectronico) != true){
            return "Correo electrónico invalido, inténtelo de nuevo.";
        }
        
        if (validarFecha(Integer.toString(pFechaNacimiento.getDate()), Integer.toString(pFechaNacimiento.getMonth()), Integer.toString(pFechaNacimiento.getYear())) != true){
            return "Fecha de nacimiento invalida, inténtelo de nuevo.";
        }
        
        Cliente clienteAux = new Cliente(pPrimerApellido,pSegundoApellido,pNombre,pIdentificacion,pFechaNacimiento,pNumeroTelefonico,pCorreoElectronico);
        
        clientes.add(clienteAux);
        
        return "Se ha creado un nuevo cliente en el sistema, los datos que fueron almacenados  son: \n" +
        "Código:"+ clienteAux.getCodigoCliente() +"\n" +
        "Nombre:"+ clienteAux.getNombreCompleto() +"\n" +
        "Identificación:"+ clienteAux.getIdentificacion() +"\n" +
        "Fecha de Nacimiento:"+ clienteAux.getFechaNacimiento() +"\n" +
        "Número telefónico:"+ clienteAux.getNumeroTelefonico();
    }
    
    //Preguntar sobre si deben ser por separado
    public String cambiarPin (int pNumCuenta, String pPinActual, String pPinNuevo){
        if (autenticarCuenta(pNumCuenta,pPinActual,cuentas) != true){
            return "Cuenta invalida, inténtelo de nuevo";
        }
        
        CuentaBancaria cuentaBancariaAux = null;
        Cliente clienteAux = null;
                
        for (int i = 0; i < clientes.size(); i++){
            for (int j=0; j < clientes.get(i).getMisCuentas().size(); j++){
                if (clientes.get(i).getMisCuentas().get(j).getNumeroCuenta() == pNumCuenta){
                    clientes.get(i).cambiarPin(clientes.get(i).getMisCuentas().get(j), pPinNuevo);
                }
            }
        }
        return "Estimado usuario, se ha cambiado satisfactoriamente el PIN de su cuenta" + pNumCuenta;
    }
    
    public String RealizarDepositoColones (int pNumCuenta, double pMonto){
        //if ()
        
        //No sé cual es la función de validar el monto
        
        for (int i = 0; i < clientes.size(); i++){
            for (int j=0; j < clientes.get(i).getMisCuentas().size(); j++){
                if (clientes.get(i).getMisCuentas().get(j).getNumeroCuenta() == pNumCuenta){
                    clientes.get(i).cambiarPin(clientes.get(i).getMisCuentas().get(j), pPinNuevo);
                }
            }
        }
    }
    
}
