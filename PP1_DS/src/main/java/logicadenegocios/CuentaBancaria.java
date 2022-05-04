
package logicadenegocios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;


public class CuentaBancaria {
        protected int numeroCuenta;
    protected LocalDate fechaCreacion; 
    protected double saldo;
    protected boolean activa;
    protected String pin; 
    protected Cliente duenio;
    protected ArrayList<Operacion> operaciones;
    protected static int cantidadOperacionesRetirosDepositos;  
    static protected int sCantidadOperaciones;
    

    public CuentaBancaria(double pSaldo, String pPin, Cliente pDuenio) {
        setFechaCreacion();
        setSaldo(pSaldo);
        setActiva(true);
        setPin(pPin);
        setDuenio(pDuenio);
        setNumeroCuenta();
        setCantidadOperacionesRetirosDepositos(0);
        operaciones = new ArrayList <Operacion>();
    }  
    
    public boolean menorQue(Comparable pOtroObjeto){
        return getSaldo() > ((CuentaBancaria)pOtroObjeto).getSaldo();
    }
    
     //Se debe acceder desde la clase cliente
    public LocalDate obtenerFechaActual(){
        LocalDate fechaActual = LocalDate.now();
        return fechaActual;
    }
    
    //Se debe acceder desde la clase cliente
    public void agregarOperacion (String pTipoOperacion, double pMonto,double pMontoComision){
        Operacion nuevaOperacion = new Operacion (pTipoOperacion, pMonto, pMontoComision);
        operaciones.add(nuevaOperacion);
        setSCantidadOperaciones(getSCantidadOperaciones() + 1 );
    }
    
    public double calcularComision(double pMonto){
        if (getCantidadOperacionesRetirosDepositos() <= 3){
            return pMonto * 0.02;
        }
        else{
            return 0;
        }
    }
    
    public double calcularTipoCambioCompra(double pMonto){
        //Web scraping
        return pMonto * 660; //De momento :)
    }
    
    public double calcularTipoCambioVenta(double pMonto){
        //Web scraping
        return pMonto * 665; //De momento :)
    }
        
    public void setNumeroCuenta() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=7000; i<100000; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        for (int i=0; i<3; i++) {
          numeroCuenta =  list.get(i);
        }
    }

    private void setDuenio (Cliente pDuenio){
        duenio = pDuenio;
    }
    
    public void setFechaCreacion(){
        fechaCreacion = obtenerFechaActual();
    }

    public void setSaldo(double pSaldo) {
        saldo = pSaldo;
    }

    public void setActiva(boolean pActiva) {
        activa = pActiva;
    }

    public void setPin(String pPin) {
        pin = pPin;
    }
    
    public void setCantidadOperacionesRetirosDepositos(int pCantidadOperacionesRetirosDepositos){
        cantidadOperacionesRetirosDepositos = pCantidadOperacionesRetirosDepositos;
    }
    
    public static void setSCantidadOperaciones(int pSCantidadOperaciones){
        sCantidadOperaciones = pSCantidadOperaciones;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isActiva() {
        return activa;
    }

    public String getPin() {
        return pin;
    }
    
    public Cliente getDuenio() {
        return duenio;
    }
    
    public int getCantidadOperacionesRetirosDepositos() {
        return cantidadOperacionesRetirosDepositos;
    }

    public static int getSCantidadOperaciones() {
        return sCantidadOperaciones;
    }
    
    public String toString( ){
        String msg = "";
        msg += "Número Cuenta: " + getNumeroCuenta() + "\n";
        msg += "Fecha De Creación: " + getFechaCreacion() + "\n";
        msg += "Saldo: " + getSaldo() +"\n";
        msg += "Es Activa: " + isActiva() + "\n";
        return msg;
    }
}
