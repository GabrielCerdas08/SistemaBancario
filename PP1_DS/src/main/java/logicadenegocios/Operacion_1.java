
package logicadenegocios;

import java.time.LocalDate;

/**
 *
 * @author fabih
 */
public class Operacion_1 {
        private LocalDate fechaOperacion;
    private String tipoOperacion;
    private double monto;
    private boolean comision;
    private double montoComision;

    public Operacion_1(String pTipoOperacion, double pMonto,double pMontoComision) {
        setTipoOperacion(pTipoOperacion);
        setMonto(pMonto);
        setFechaOperacion();
        setMontoComision(pMontoComision);
        //Faltan los otros dos atributos
    }
    
    private LocalDate obtenerFechaActual(){
        LocalDate fechaActual = LocalDate.now();
        return fechaActual;
    }

    public void setFechaOperacion(){
        fechaOperacion = obtenerFechaActual();
    }

    public void setTipoOperacion(String pTipoOperacion) {
        tipoOperacion = pTipoOperacion;
    }

    public void setMonto(double pMonto) {
        monto = pMonto;
    }

    public void setComision(boolean pComision) {
        comision = pComision;
    }

    public void setMontoComision(double montoComision) {
        this.montoComision = montoComision;
    }

    public LocalDate getFechaOperacion() {
        return fechaOperacion;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public double getMonto() {
        return monto;
    }

    public boolean isComision() {
        return comision;
    }

    public double getMontoComision() {
        return montoComision;
    }
}
