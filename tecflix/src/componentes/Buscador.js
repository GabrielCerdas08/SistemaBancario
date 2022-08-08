import React, { Component } from "react";
class Buscador extends Component{
  render(){
    return (
      <form>
        <hr></hr>
        <div className = "row">
          <div className = "form-group col-md-10">
            <input type = "text" className = "form-control form-control-lg" placeholder="Busca videos en tu galeria" />
          </div>

          <div className = "form-group col-md-1">
            <button type = "sumbmit" className = "btn btn-lg btn-danger btn-block" value="Buscar" > Buscar</button>
          </div>
        </div>
      </form>
    );
  }

}
  
export default Buscador;
