import './App.css';
import Buscador from "./componentes/Buscador";
import Gestiones from "./componentes/Gestiones"
import React, { Component } from "react";

import { Switch ,Route, Link} from 'react-router-dom';


class HomeView extends  Component{
  render(){
    return  <Buscador/>
  }
}
class GestionarView extends  Component{
    render(){
      return <Gestiones/>
    }
}

class App extends Component{
  render(){
    return (
      <div className = " App">

        <div className = "menu">
          <ul>
          <br></br>

            <h1 className = " btn-ligth"> TECFLIX </h1>
            <hr></hr>
            <br></br>
            <form action="/home">
            <button>Inicio</button></form>
            <br></br>
            
            <form action="/gestionar">
            <button>Gestionar Biblioteca</button></form>
            <br></br>

          </ul>
          
        </div>

        <div className = "display">
          <Switch>

            <Route path={'/home'}>
              <HomeView/>
            </Route>

            <Route path={'/gestionar'}>
              <GestionarView/>
            </Route>
            
          </Switch>

        </div>
    
      </div>
    )
  }

}
  
export default App;