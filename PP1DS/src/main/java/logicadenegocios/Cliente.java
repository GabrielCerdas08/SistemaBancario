
package logicadenegocios;

import java.util.ArrayList;
import java.util.Date;


public class Cliente {
    protected String primerApellido;
    protected String segundoApellido;
    protected String nombre;
    protected int identificacion;
    protected Date fechaNacimiento;
    protected int numeroTelefonico;
    protected String correoElectronico;
    protected String codigoCliente;
    protected ArrayList<CuentaBancaria> misCuentas;

    public Cliente(String pPrimerApellido, String pSegundoApellido, String pNombre, int pIdentificacion, Date pFechaNacimiento, int pNumeroTelefonico, String pCorreoElectronico) {
        setPrimerApellido(pPrimerApellido);
        setSegundoApellido(pSegundoApellido);
        setNombre(pNombre);
        setIdentificacion(pIdentificacion);
        setFechaNacimiento(pFechaNacimiento);
        setNumeroTelefonico(pNumeroTelefonico);
        setCorreoElectronico(pCorreoElectronico);
        setCodigoCliente();
    }
    
    public void agregarCuentaBancaria (CuentaBancaria pCuentaBancaria){
        misCuentas.add(pCuentaBancaria);
    }
    
    public boolean menorQue(Comparable pOtroObjeto){
        return (getPrimerApellido().compareTo(((Cliente)pOtroObjeto).getPrimerApellido())<0 ? true:false);
    }

    public void cambiarPin(CuentaBancaria pCuentaBancaria, String pPin){
        pCuentaBancaria.setPin(pPin);
        pCuentaBancaria.agregarOperacion("Cambio de pin",0,0);
    }
    
    public String depositarColones(CuentaBancaria pCuentaBancaria, double pMontoDeposito){
        double montoComision = pCuentaBancaria.calcularComision(pMontoDeposito);
        pCuentaBancaria.setSaldo(pCuentaBancaria.getSaldo() + (pMontoDeposito - montoComision));
        pCuentaBancaria.setCantidadOperacionesRetirosDepositos(pCuentaBancaria.getCantidadOperacionesRetirosDepositos() + 1);
        pCuentaBancaria.agregarOperacion("Deposito",pMontoDeposito,montoComision);
        return "Estimado usuario, se han depositado correctamente" + pMontoDeposito +" colones" + "[El monto real depositado a su cuenta " + pCuentaBancaria.getNumeroCuenta() + " es de " + pMontoDeposito + "colones]" + "[El monto cobrado por concepto de comisión fue de " + montoComision + "colones, que  fueron rebajados automáticamente de su saldo actual]";
    }
    
    public String depositarDolares(CuentaBancaria pCuentaBancaria, double pMontoDeposito){
        double montoDepositoColones = pCuentaBancaria.calcularTipoCambioCompra(pMontoDeposito);
        double montoComision = pCuentaBancaria.calcularComision(montoDepositoColones);
        pCuentaBancaria.setSaldo(pCuentaBancaria.getSaldo() + (montoDepositoColones - montoComision));
        pCuentaBancaria.agregarOperacion("Deposito",montoDepositoColones,montoComision);
        return "Estimado usuario, se han recibido correctamente" + pMontoDeposito + " dolares" + "[Según el BCCR, el tipo de cambio de compra del dólar del " + pCuentaBancaria.obtenerFechaActual() + " es: DDD.DD]" + "[El monto equivalente en colones es " + montoDepositoColones + "[El monto real depositado a su cuenta " + pCuentaBancaria.getNumeroCuenta() + " es de " + pMontoDeposito + "colones]" + "[El monto cobrado por concepto de comisión fue de " + montoComision + "colones, que  fueron rebajados automáticamente de su saldo actual]";
    }
    
    public String retirarColones(CuentaBancaria pCuentaBancaria, double pMontoRetiro){
        double montoComision = pCuentaBancaria.calcularComision(pMontoRetiro);
        pCuentaBancaria.setSaldo(pCuentaBancaria.getSaldo() - (pMontoRetiro + montoComision));
        pCuentaBancaria.agregarOperacion("Retiro",pMontoRetiro,montoComision);
        return "Estimado usuario, el monto de este retiro es " + pMontoRetiro + "colones." + "[El monto cobrado por concepto de comisión fue de " + montoComision + "colones, que  fueron rebajados automáticamente de su saldo actual]";
    }
    
    public String retirarDolares(CuentaBancaria pCuentaBancaria, double pMontoRetiro){
        double montoRetiroColones = pCuentaBancaria.calcularTipoCambioVenta(pMontoRetiro);
        double montoComision = pCuentaBancaria.calcularComision(montoRetiroColones);
        pCuentaBancaria.setSaldo(pCuentaBancaria.getSaldo() - (montoRetiroColones + montoComision));
        pCuentaBancaria.setCantidadOperacionesRetirosDepositos(pCuentaBancaria.getCantidadOperacionesRetirosDepositos() + 1);
        pCuentaBancaria.agregarOperacion("Retiro",montoRetiroColones,montoComision);
        return "Estimado usuario, el monto de este retiro es " + pMontoRetiro + "dólares." + "[Según el BCCR, el tipo de cambio de venta del dólar de hoy es:" + pCuentaBancaria.calcularTipoCambioVenta(1) + "[El monto equivalente de su retiro es " + montoRetiroColones + "colones] [El monto cobrado por concepto de comisión fue de " + montoComision + "colones, que fueron rebajados automáticamente de su saldo actual]";
    }
    
    public String transferirMonto (CuentaBancaria pCuentaBancaria, double pMonto){
        double montoComision = pCuentaBancaria.calcularComision(pMonto);
        pCuentaBancaria.setSaldo(pCuentaBancaria.getSaldo() - (pMonto + montoComision));
        pCuentaBancaria.agregarOperacion("Transferencia",pMonto,montoComision);
        return "Estimado usuario, la transferencia de fondos se ejecutó satisfactoriamente. El monto retirado de la cuenta origen y depositado en la cuenta destino es " + pMonto + "colones. \n" + "[El monto cobrado por concepto de comisión a la cuenta origen fue de " + montoComision + "colones, que fueron rebajados automáticamente de su saldo actual]";
    }
    
    public void depositarTransferencia(CuentaBancaria pCuentaBancaria,double pMonto){
        pCuentaBancaria.setSaldo(pCuentaBancaria.getSaldo() + pMonto);
        pCuentaBancaria.agregarOperacion("Deposito",pMonto,0); //No sé si es el tipo de Operación
    }
    
    public String consultarSaldoCuenta(CuentaBancaria pCuentaBancaria){
        pCuentaBancaria.agregarOperacion("Consulta",0,0);
        return "Estimado usuario el saldo actual de su cuenta es " + pCuentaBancaria.getSaldo() + "colones. ";
    }
    
    public String consultarSaldoCuentaDolares(CuentaBancaria pCuentaBancaria){
        pCuentaBancaria.agregarOperacion("Consulta",0,0);
        return "Estimado usuario el saldo actual de su cuenta es " + pCuentaBancaria.calcularTipoCambioCompra(pCuentaBancaria.getSaldo()) + " dólares. Para esta conversión se utilizó el tipo de cambio del dólar, precio de compra. [Según el BCCR, el tipo de cambio de compra del dólar de hoy es:" + pCuentaBancaria.calcularTipoCambioCompra(1) + "]"; 
    }
    
    public String consultarEstadoCuenta(CuentaBancaria pCuentaBancaria){
        pCuentaBancaria.agregarOperacion("Consulta",0,0);
        return "Duenio: " + pCuentaBancaria.duenio.getNombre() + pCuentaBancaria.duenio.getPrimerApellido() + "\nFecha creacion: " + pCuentaBancaria.getFechaCreacion() + "\nNumero de Cuenta: " + pCuentaBancaria.getNumeroCuenta() + "\nSaldo en colones: " + pCuentaBancaria.getSaldo();
    }
    
    public String consultarEstadoCuentaDolares(CuentaBancaria pCuentaBancaria){
        pCuentaBancaria.agregarOperacion("Consulta",0,0);
        return "Duenio: " + pCuentaBancaria.duenio.getNombre() + pCuentaBancaria.duenio.getPrimerApellido() + "\nFecha creacion: " + pCuentaBancaria.getFechaCreacion() + "\nNumero de Cuenta: " + pCuentaBancaria.getNumeroCuenta() + "\nSaldo en dolares: " + pCuentaBancaria.calcularTipoCambioVenta(pCuentaBancaria.getSaldo());
    }

    public String consultarStatusCuenta(CuentaBancaria pCuentaBancaria){
        pCuentaBancaria.agregarOperacion("Consulta",0,0);
        return "“La cuenta número " + pCuentaBancaria.getNumeroCuenta() + "Esta Activa: " + pCuentaBancaria.isActiva();
    }
        
    public void setPrimerApellido(String pPrimerApellido) {
        primerApellido = pPrimerApellido;
    }

    public void setSegundoApellido(String pSegundoApellido) {
        segundoApellido = pSegundoApellido;
    }

    public void setNombre(String pNombre) {
        nombre = pNombre;
    }

    public void setIdentificacion(int pIdentificacion) {
        identificacion = pIdentificacion;
    }

    public void setFechaNacimiento(Date pFechaNacimiento) {
        fechaNacimiento = pFechaNacimiento;
    }

    public void setNumeroTelefonico(int pNumeroTelefonico) {
        numeroTelefonico = pNumeroTelefonico;
    }

    public void setCorreoElectronico(String pCorreoElectronico) {
        correoElectronico = pCorreoElectronico;
    }

    public void setCodigoCliente() {
        //Falta poner la parte del codigo
        codigoCliente = "codigo";
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public int getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }
    
    public String toString( ){
        String msg = "";
        msg += "Código Cliente: " + getCodigoCliente() + "\n";
        msg += "Identificacion: " + getIdentificacion() + "\n";
        msg += "Nombre: " + getNombre() + " " + getPrimerApellido() + " " + getSegundoApellido() +"\n";
        msg += "Fecha Nacimiento: " + getFechaNacimiento() + "\n";
        msg += "Numero Telefonico: " + getNumeroTelefonico() + "\n";
        msg += "Correo Electronico: " + getCorreoElectronico() + "\n";
        return msg;
    }
    
}
