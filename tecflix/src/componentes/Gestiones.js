import React, { Component } from "react";
import Buscador from "./Buscador";
class Gestiones extends Component{
  render(){
    return (
      <form>
        <div className = "row">
        
        <hr></hr>

          <div className = "form-group col-md-2">
            <button type = "sumbmit" className = " btn-block" value="Modificar" > Modificar </button>
          </div>
          <div className = "form-group col-md-2">
            <button type = "sumbmit" className = " btn-block" value="Eliminar" >Agregar</button>
          </div>
          <div className = "form-group col-md-4">
            <button type = "sumbmit" className = " btn-block" value="Agregar" >Eliminar</button>
            
          </div>
          
 

          <div className = "form-group col-md-10">
          <Buscador/>
          </div>
          

        </div>
      </form>
      
    );
  }

}
  
export default Gestiones;
