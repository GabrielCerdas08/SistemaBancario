
package controlador;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import logicadenegocios.Cliente;
import logicadenegocios.CuentaBancaria;
import static seguridad.ClienteSc.autenticarCliente;
import static validaciones.ValidacionCliente.*;
import static validaciones.ValidacionFormato.*;
import static validaciones.ValidacionCuenta.validarSaldoCuenta;
import static seguridad.CuentaBancariaSc.autenticarCuenta;
import static seguridad.CuentaBancariaSc.autenticarNumCuenta;
import static seguridad.CuentaBancariaSc.inactivarCuenta;
import static util.PalabraAleatoria.generarPalabra;
import static util.MensajeSMS.enviarMensaje;

public class ClienteCt {
    static int numCliente = 0;
    static ArrayList<Cliente> clientes = new ArrayList<>();
  
    
    public static String registrarCliente (String pPrimerApellido, String pSegundoApellido, String pNombre, String pIdentificacion, String pDia, String pMes, String pAnio, String pNumeroTelefonico, String pCorreoElectronico) throws ParseException {
        if(isNumeric(pIdentificacion)==false){
            return "Se encuentran caracteres donde solamente se permiten números";
        }
        if(isNumeric(pNumeroTelefonico)==false){
            return "Se encuentran caracteres donde solamente se permiten números";
        }
        if (validarNumTelefonico(Integer.parseInt(pNumeroTelefonico)) == false){
            return "Numero telefónico invalido, inténtelo de nuevo.";
        }
        
        if (validarCorreoElectronico(pCorreoElectronico) == false){
            return "Correo electrónico invalido, inténtelo de nuevo.";
        }
        
        if (validarFecha(pDia, pMes, pAnio) == false){
            return "Fecha de nacimiento invalida, inténtelo de nuevo.";
        }
        if(autenticarCliente(Integer.parseInt(pIdentificacion), clientes)==true){
            return "El cliente ya se encuentra registrado";
        }
        Date pFechaNacimiento = crearFecha(pDia,pMes, pAnio);
        Cliente clienteAux = new Cliente(pPrimerApellido,pSegundoApellido,pNombre,Integer.parseInt(pIdentificacion),pFechaNacimiento,Integer.parseInt(pNumeroTelefonico),pCorreoElectronico);
        clienteAux.setCodigoCliente(numCliente++);
        clientes.add(clienteAux);
        
        return "Se ha creado un nuevo cliente en el sistema, los datos que fueron almacenados  son: \n" +
        "Código: "+ clienteAux.getCodigoCliente() +"\n" +
        "Nombre: "+ clienteAux.getNombreCompleto() +"\n" +
        "Identificación: "+ clienteAux.getIdentificacion() +"\n" +
        "Fecha de Nacimiento: "+ clienteAux.getFechaNacimiento() +"\n" +
        "Número telefónico:"+ clienteAux.getNumeroTelefonico();
    }
    
    //Preguntar sobre si deben ser por separado
    public static String cambiarPin (String pNumCuenta, String pPinActual, String pPinNuevo){
        if(isNumeric(pNumCuenta)==false){
            return "Se encuentran caracteres donde solamente se permiten números";
        }
        int intentos = 0;
        while (intentos < 2){
            if (autenticarCuenta(Integer.parseInt(pNumCuenta),pPinActual,clientes) == false){
                intentos++;
            }else{
                for (int i = 0; i < clientes.size(); i++){
                    for (int j=0; j < clientes.get(i).getMisCuentas().size(); j++){
                        if (clientes.get(i).getMisCuentas().get(j).getNumeroCuenta() == Integer.parseInt(pNumCuenta)){
                            clientes.get(i).cambiarPin(clientes.get(i).getMisCuentas().get(j), pPinNuevo);
                        }
                    }
                }
                return "Estimado usuario, se ha cambiado satisfactoriamente el PIN de su cuenta" + pNumCuenta;
            }
        }
      
       inactivarCuenta(Integer.parseInt(pNumCuenta),clientes);
       return "Estimado usuario, su cuenta está inactiva";
   }
    
    public static String realizarDepositoColones (String pNumCuenta, String pMonto){
        String msg ="";
        if(isNumeric(pNumCuenta)==false){
            return "Se encuentran caracteres donde solamente se permiten números";
        }
        if (isNumeric(pMonto) == false){
            return "Monto invalido, inténtelo de nuevo";
        }
        for (int i = 0; i < clientes.size(); i++){
            for (int j=0; j < clientes.get(i).getMisCuentas().size(); j++){
                if (clientes.get(i).getMisCuentas().get(j).getNumeroCuenta() == Integer.parseInt(pNumCuenta)){
                    
                    msg = clientes.get(i).depositarColones(clientes.get(i).getMisCuentas().get(j), Integer.parseInt(pMonto));
                }
            }
        }
        return msg;
    }
    
    public static String realizarDepositoDolares(String pNumCuenta, String pMonto){
        String msg ="";
        if(isNumeric(pNumCuenta)==false){
            return "Se encuentran caracteres donde solamente se permiten números";
        }  
        if (isNumeric(pMonto) == false){
            return "Monto invalido, inténtelo de nuevo";
        }        
        if (autenticarNumCuenta(Integer.parseInt(pNumCuenta), clientes) != true){
            return "Cuenta invalida, inténtelo de nuevo";
        }
        
        if (isNumeric(String.valueOf(pMonto)) != true){
            return "Monto invalido, inténtelo de nuevo";
        }
        
        for (int i = 0; i < clientes.size(); i++){
            for (int j=0; j < clientes.get(i).getMisCuentas().size(); j++){
                if (clientes.get(i).getMisCuentas().get(j).getNumeroCuenta() == Integer.parseInt(pNumCuenta)){
                    msg = clientes.get(i).depositarDolares(clientes.get(i).getMisCuentas().get(j), Integer.parseInt(pMonto));
                }
            }
        }
        return msg;
    }

    static String pPalabra;
    
    public static String realizarRetiroColonesAux (String pNumCuenta, String pPin){       
        int intentos = 0;
        while (intentos < 2){
            if (autenticarCuenta(Integer.parseInt(pNumCuenta),pPin,clientes) == false){
                intentos++;
            }
            else{
                for (int i=0; i < clientes.size(); i++){
                    for (int j=0; j < clientes.get(i).getMisCuentas().size(); j++){
                        if (clientes.get(i).getMisCuentas().get(j).getNumeroCuenta() == Integer.parseInt(pNumCuenta)){
                            pPalabra = generarPalabra();
                            enviarMensaje(Integer.toString(clientes.get(i).getNumeroTelefonico()),pPalabra);
                        }
                    }
                }
                return pPalabra;
            }
        }
       inactivarCuenta(Integer.parseInt(pNumCuenta),clientes);
       return "Estimado usuario, su cuenta está inactiva";       
    }
        
    
    public static  String realizarRetiroColones (int pNumCuenta, String pMonto, String pPalabraIngresada, String pPalabra){
        int intentos =0;
        String msg ="";
        if (isNumeric(pMonto) == false){
            return "Monto invalido, inténtelo de nuevo";
        }
        while (intentos < 2){
            if (pPalabraIngresada.equals(pPalabra)){
                for (int i = 0; i < clientes.size(); i++){
                for (int j=0; j < clientes.get(i).getMisCuentas().size(); j++){
                    if (clientes.get(i).getMisCuentas().get(j).getNumeroCuenta() == pNumCuenta){
                        if (validarSaldoCuenta(clientes.get(i).getMisCuentas().get(j),Integer.parseInt(pMonto),clientes.get(i).getMisCuentas().get(j).calcularComision(Integer.parseInt(pMonto))) == false){
                            return "Monto insuficiente, ingrese un monto menor e inténtelo de nuevo";
                        }
                        msg = clientes.get(i).depositarColones(clientes.get(i).getMisCuentas().get(j),Integer.parseInt(pMonto));
                        return msg; 
                    }
                }
            }
               
            }else{
               intentos++; 
            }
        }
        inactivarCuenta(pNumCuenta,clientes);
       return "Estimado usuario, su cuenta está inactiva";
    }
    
    public static String realizarRetiroDolaresAux (String pNumCuenta, String pPin){
        if (autenticarCuenta(Integer.parseInt(pNumCuenta),pPin,clientes) != true){
            return "Cuenta invalida, inténtelo de nuevo";
        }
        
        for (int i=0; i < clientes.size(); i++){
            for (int j=0; j < clientes.get(i).getMisCuentas().size(); j++){
                if (clientes.get(i).getMisCuentas().get(j).getNumeroCuenta() == Integer.parseInt(pNumCuenta)){
                    pPalabra = generarPalabra();
                    enviarMensaje(Integer.toString(clientes.get(i).getNumeroTelefonico()),pPalabra);
                }
            }
        }
        
        return "Se le ha enviado un codigo a su celular, por favor ingrese los datos requeridos";
    }
    
    public static String realizarRetiroDolares (int pNumCuenta, String pMonto, String pPin,String pPalabraIngresada){
        String msg ="";
        //De momento, porque no sé donde va la validacion de la palabra
        if (pPalabraIngresada != pPalabra){
            return "Palabra incorrecta, por favor ingrese de nuevo la palabra";
        }
        
        if (isNumeric(String.valueOf(pMonto)) != true){
            return "Monto invalido, inténtelo de nuevo";
        }
        
        for (int i = 0; i < clientes.size(); i++){
            for (int j=0; j < clientes.get(i).getMisCuentas().size(); j++){
                if (autenticarNumCuenta(pNumCuenta, clientes) != true){
                    return "Cuenta de deposito invalida, inténtelo de nuevo";
                }
            }
        }
        
        for (int i = 0; i < clientes.size(); i++){
            for (int j=0; j < clientes.get(i).getMisCuentas().size(); j++){
                if (clientes.get(i).getMisCuentas().get(j).getNumeroCuenta() == pNumCuenta){
                    if (validarSaldoCuenta(clientes.get(i).getMisCuentas().get(j),clientes.get(i).getMisCuentas().get(j).calcularTipoCambioVenta(Integer.parseInt(pMonto)),clientes.get(i).getMisCuentas().get(j).calcularComision(Integer.parseInt(pMonto))) != true){
                        return "Monto insuficiente, ingrese un monto menor e inténtelo de nuevo";
                    }
                    msg = clientes.get(i).transferirMonto(clientes.get(i).getMisCuentas().get(j), Integer.parseInt(pMonto));
                }
            }
        }
        
        for (int i = 0; i < clientes.size(); i++){
            for (int j=0; j < clientes.get(i).getMisCuentas().size(); j++){
                if (clientes.get(i).getMisCuentas().get(j).getNumeroCuenta() == pNumCuenta){
                    clientes.get(i).depositarColones(clientes.get(i).getMisCuentas().get(j), Integer.parseInt(pMonto));
                }
            }
        }
        
        return msg;   
    }
    
    public static String realizarTransferenciaAux (String pNumCuentaOrigen, String pPin){
        if (autenticarCuenta(Integer.parseInt(pNumCuentaOrigen),pPin,clientes) != true){
            return "Cuenta invalida, inténtelo de nuevo";
        }
        
        for (int i=0; i < clientes.size(); i++){
            for (int j=0; j < clientes.get(i).getMisCuentas().size(); j++){
                if (clientes.get(i).getMisCuentas().get(j).getNumeroCuenta() == Integer.parseInt(pNumCuentaOrigen)){
                    pPalabra = generarPalabra();
                    enviarMensaje(Integer.toString(clientes.get(i).getNumeroTelefonico()),pPalabra);
                }
            }
        }
        
        return "Se le ha enviado un codigo a su celular, por favor ingrese los datos requeridos";
    }
    
    public static String realizarTransferencia (int pNumCuentaOrigen, int pNumCuentam, double pMonto, String pPin,String pPalabraIngresada){
        String msg ="";
        //De momento, porque no sé donde va la validacion de la palabra
        if (pPalabraIngresada != pPalabra){
            return "Palabra incorrecta, por favor ingrese de nuevo la palabra";
        }
        
        if (isNumeric(String.valueOf(pMonto)) != true){
            return "Monto invalido, inténtelo de nuevo";
        }
        
        for (int i = 0; i < clientes.size(); i++){
            for (int j=0; j < clientes.get(i).getMisCuentas().size(); j++){
                if (clientes.get(i).getMisCuentas().get(j).getNumeroCuenta() == pNumCuentaOrigen){
                    if (validarSaldoCuenta(clientes.get(i).getMisCuentas().get(j),pMonto,clientes.get(i).getMisCuentas().get(j).calcularComision(pMonto)) != true){
                        return "Monto insuficiente, ingrese un monto menor e inténtelo de nuevo";
                    }
                }
            }
        }
        
        for (int i = 0; i < clientes.size(); i++){
            for (int j=0; j < clientes.get(i).getMisCuentas().size(); j++){
                if (clientes.get(i).getMisCuentas().get(j).getNumeroCuenta() == pNumCuentaOrigen){
                    
                }
            }
        }
        
        
        return msg;   
    }
    
     public ArrayList<Integer> cedulaClientes (){
         ArrayList<Integer> cedulas = new ArrayList<Integer>();
       for (int i = 0; i < clientes.size(); i++){
           cedulas.add(clientes.get(i).getIdentificacion());
       }
       return cedulas;
    }
     
}
