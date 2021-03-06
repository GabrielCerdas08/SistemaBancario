
package controlador;

import DAO.CuentaBancariaDAO;
import static controlador.ClienteCt.clientes;
import java.time.LocalDate;
import java.util.ArrayList;
import logicadenegocios.Cliente;
import logicadenegocios.CuentaBancaria;
import logicadenegocios.Operacion;
import static seguridad.ClienteSc.autenticarCliente;
import static seguridad.CuentaBancariaSc.autenticarNumCuenta;
import util.Ordenacion;
import static validaciones.ValidacionCuenta.*;
import static validaciones.ValidacionFormato.isNumeric;

public class CuentaBancariaCt {
    
    static ClienteCt clienteCt = new ClienteCt();
    static CuentaBancariaDAO cuentaDAO = new CuentaBancariaDAO();
    
    public static String resgistrarCuentaBancaria (String pSaldo, String pPin,String pIdentificacion){
        if (validarPin(pPin)== false){
            return "El PIN es incorrecto, inténtelo de nuevo.";
        }
        
        if (isNumeric(pSaldo) == false){
            return "Saldo invalido, inténtelo de nuevo";
        }

        CuentaBancaria cuentaBancariaAux = null;
        for (int i = 0; i < clienteCt.clientes.size(); i++){
            if (clienteCt.clientes.get(i).getIdentificacion() == Integer.parseInt(pIdentificacion)){
              
                cuentaBancariaAux = new CuentaBancaria(Integer.parseInt(pSaldo),pPin,clienteCt.clientes.get(i));
                clienteCt.clientes.get(i).agregarCuentaBancaria(cuentaBancariaAux);
                cuentaDAO.registrarCuentaDAO(cuentaBancariaAux.getNumeroCuenta(),cuentaBancariaAux.getFechaCreacion(),cuentaBancariaAux.getSaldo(),cuentaBancariaAux.getactiva(),cuentaBancariaAux.getPin(),Integer.parseInt(pIdentificacion),cuentaBancariaAux.getCantidadOperacionesRetirosDepositos());
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

    public ArrayList<String> listarCuentas(){
        ArrayList <String> datosCuentas = new ArrayList();
        int cantidadCuentas = 0;
        
        for (int i=0; i < clientes.size(); i++){
           for (int j=0; j < clientes.get(i).getMisCuentas().size(); j++){
               cantidadCuentas+=1;
           }
        } 
        CuentaBancaria [] cuentasBancariasOrdenadas = new CuentaBancaria [cantidadCuentas];
        
        cantidadCuentas = 0;
        for (int i=0; i < clientes.size(); i++){
           for (int j=0; j < clientes.get(i).getMisCuentas().size(); j++){
               cuentasBancariasOrdenadas[cantidadCuentas] = clientes.get(i).getMisCuentas().get(j);
               cantidadCuentas+=1;
           }    
        } 
        
        Ordenacion.insercion(cuentasBancariasOrdenadas);
        for (int i = 0; i < cuentasBancariasOrdenadas.length; i++){
            datosCuentas.add("Cuenta: " + cuentasBancariasOrdenadas[i].getNumeroCuenta() + "   "  + cuentasBancariasOrdenadas[i].isActiva() + "   "  + cuentasBancariasOrdenadas[i].getSaldo() + "\n" + "Dueño: " + cuentasBancariasOrdenadas[i].getDuenio().getNombreCompleto() + "   "  + cuentasBancariasOrdenadas[i].getDuenio().getIdentificacion());
        }
        return datosCuentas;
    }
    
    public  String consultarCuenta(String pNumCuenta){
        String msg="";
        if (isNumeric(pNumCuenta) != true){
            return "Numero de cuenta invalido, debe ingresar un valor numerico";
        }
        
        if (autenticarNumCuenta(Integer.parseInt(pNumCuenta),clientes) != true){
            return "Numero de cuenta invalido, inténtelo de nuevo";
        }
        for (int i = 0; i < clientes.size(); i++){
            for (int j=0; j < clientes.get(i).getMisCuentas().size(); j++){
                if (clientes.get(i).getMisCuentas().get(j).getNumeroCuenta() == Integer.parseInt(pNumCuenta)){
                    msg = clientes.get(i).getMisCuentas().get(j).toString();
                }
            }
        }
        return msg;
    }
    
    public String consultarGananciasTotalesCuenta(String pNumCuenta){
        double gananciaCuenta = 0;
        if(isNumeric(pNumCuenta)==false){
            return "Se encuentran caracteres donde solamente se permiten números";
        }
        
        if (autenticarNumCuenta(Integer.parseInt(pNumCuenta), clienteCt.clientes) != true){
            return "Cuenta invalida, inténtelo de nuevo";
        }
        
        for (int i = 0; i < clienteCt.clientes.size(); i++){
            for (int j=0; j < clienteCt.clientes.get(i).getMisCuentas().size(); j++){
                if (clienteCt.clientes.get(i).getMisCuentas().get(j).getNumeroCuenta() == Integer.parseInt(pNumCuenta)){
                    for (int k=0; k < clienteCt.clientes.get(i).getMisCuentas().get(j).getMisOperacioes().size(); k++){
                        if (clienteCt.clientes.get(i).getMisCuentas().get(j).getMisOperacioes().get(k).getTipoOperacion().equals("Deposito") || clienteCt.clientes.get(i).getMisCuentas().get(j).getMisOperacioes().get(k).getTipoOperacion().equals("Retiro")){
                            gananciaCuenta += clienteCt.clientes.get(i).getMisCuentas().get(j).getMisOperacioes().get(k).getMontoComision();
                        }
                    }
                }
            }
        }
        return Double.toString(gananciaCuenta);
    }
    
    public String consultarGananciasDepositosCuenta(String pNumCuenta){
        double gananciaCuenta = 0;
        if(isNumeric(pNumCuenta)==false){
            return "Se encuentran caracteres donde solamente se permiten números";
        }
        
        if (autenticarNumCuenta(Integer.parseInt(pNumCuenta), clienteCt.clientes) != true){
            return "Cuenta invalida, inténtelo de nuevo";
        }
        
        for (int i = 0; i < clienteCt.clientes.size(); i++){
            for (int j=0; j < clienteCt.clientes.get(i).getMisCuentas().size(); j++){
                if (clienteCt.clientes.get(i).getMisCuentas().get(j).getNumeroCuenta() == Integer.parseInt(pNumCuenta)){
                    for (int k=0; k < clienteCt.clientes.get(i).getMisCuentas().get(j).getMisOperacioes().size(); k++){
                        if (clienteCt.clientes.get(i).getMisCuentas().get(j).getMisOperacioes().get(k).getTipoOperacion().equals("Deposito")){
                            gananciaCuenta += clienteCt.clientes.get(i).getMisCuentas().get(j).getMisOperacioes().get(k).getMontoComision();
                        }
                    }
                }
            }
        }
        return Double.toString(gananciaCuenta);
    }
    
    public String consultarGananciasRetirosCuenta(String pNumCuenta){
        double gananciaCuenta = 0;
        if(isNumeric(pNumCuenta)==false){
            return "Se encuentran caracteres donde solamente se permiten números";
        }
        
        if (autenticarNumCuenta(Integer.parseInt(pNumCuenta), clienteCt.clientes) != true){
            return "Cuenta invalida, inténtelo de nuevo";
        }
        
        for (int i = 0; i < clienteCt.clientes.size(); i++){
            for (int j=0; j < clienteCt.clientes.get(i).getMisCuentas().size(); j++){
                if (clienteCt.clientes.get(i).getMisCuentas().get(j).getNumeroCuenta() == Integer.parseInt(pNumCuenta)){
                    for (int k=0; k < clienteCt.clientes.get(i).getMisCuentas().get(j).getMisOperacioes().size(); k++){
                        if (clienteCt.clientes.get(i).getMisCuentas().get(j).getMisOperacioes().get(k).getTipoOperacion().equals("Retiro")){
                            gananciaCuenta += clienteCt.clientes.get(i).getMisCuentas().get(j).getMisOperacioes().get(k).getMontoComision();
                        }
                    }
                }
            }
        }
        return Double.toString(gananciaCuenta);
    }
    
    public static double consultarGananciasTotalesBanco(){
        double gananciaBanco = 0;
        for (int i = 0; i < clienteCt.clientes.size(); i++){
            for (int j=0; j < clienteCt.clientes.get(i).getMisCuentas().size(); j++){
                for (int k=0; k < clienteCt.clientes.get(i).getMisCuentas().get(j).getMisOperacioes().size(); k++){
                    if (clienteCt.clientes.get(i).getMisCuentas().get(j).getMisOperacioes().get(k).getTipoOperacion().equals("Deposito") || clienteCt.clientes.get(i).getMisCuentas().get(j).getMisOperacioes().get(k).getTipoOperacion().equals("Retiro")){
                        gananciaBanco += clienteCt.clientes.get(i).getMisCuentas().get(j).getMisOperacioes().get(k).getMontoComision();
                    }
                }
            }
        }
        return gananciaBanco;
    }
    
    public static double consultarGananciasDepositosBanco(){
        double gananciaBanco = 0;
        for (int i = 0; i < clienteCt.clientes.size(); i++){
            for (int j=0; j < clienteCt.clientes.get(i).getMisCuentas().size(); j++){
                for (int k=0; k < clienteCt.clientes.get(i).getMisCuentas().get(j).getMisOperacioes().size(); k++){
                    if (clienteCt.clientes.get(i).getMisCuentas().get(j).getMisOperacioes().get(k).getTipoOperacion().equals("Deposito")){
                        gananciaBanco += clienteCt.clientes.get(i).getMisCuentas().get(j).getMisOperacioes().get(k).getMontoComision();
                    }
                }
            }
        }
        return gananciaBanco;
    }
    
    public static double consultarGananciasRetirosBanco(){
        double gananciaBanco = 0;
        for (int i = 0; i < clienteCt.clientes.size(); i++){
            for (int j=0; j < clienteCt.clientes.get(i).getMisCuentas().size(); j++){
                for (int k=0; k < clienteCt.clientes.get(i).getMisCuentas().get(j).getMisOperacioes().size(); k++){
                    if (clienteCt.clientes.get(i).getMisCuentas().get(j).getMisOperacioes().get(k).getTipoOperacion().equals("Retiro")){
                        gananciaBanco += clienteCt.clientes.get(i).getMisCuentas().get(j).getMisOperacioes().get(k).getMontoComision();
                    }
                }
            }
        }
        return gananciaBanco;
    }
    
    //-------------------CONTROLADOR - DAO----------------//
    public static void cargarCuentasCt(int pNumCuenta,LocalDate pFechaCreacion,double pSaldo, boolean pIsActiva,int pDuenio,int pCantOperaciones, String pPin){
        ArrayList<Operacion> operaciones = new ArrayList<>();
        for (int i = 0; i < clienteCt.clientes.size(); i++){
             if(pDuenio ==  clienteCt.clientes.get(i).getIdentificacion()){
                 CuentaBancaria cuenta = new CuentaBancaria();
                 cuenta.setNumCuenta(pNumCuenta);
                 cuenta.setDuenio(clienteCt.clientes.get(i));
                 cuenta.setFechaCreacion(pFechaCreacion);
                 cuenta.setSaldo(pSaldo);
                 cuenta.setActiva(pIsActiva);
                 cuenta.setPin(pPin);
                 cuenta.setCantidadOperacionesRetirosDepositos(pCantOperaciones);
                 cuenta.setOperaciones(operaciones);
                 clienteCt.clientes.get(i).agregarCuentaBancaria(cuenta); 
             }
         }    
    }
    
     public static void cargarOperacionCuentaCt(int pNumCuenta,LocalDate pFechaOperacion, String pTipoOperacion, double pMonto, boolean pIsComision, double pMontoComison){
        for (int i = 0; i < clienteCt.clientes.size(); i++){
             for( int j = 0; j < clienteCt.clientes.get(i).getMisCuentas().size(); j++){
                 if(pNumCuenta == clienteCt.clientes.get(i).getMisCuentas().get(j).getNumeroCuenta()){
                     clienteCt.clientes.get(i).getMisCuentas().get(j).agregarOperacion(pTipoOperacion, pMonto, pMontoComison, pFechaOperacion, pIsComision);
                 }  
             }
         }    
    } 
}
