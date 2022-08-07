import React from "react";
import MenuItem from "@material-ui/core/MenuItem";
import Button from "@material-ui/core/Button";
import Menu from "@material-ui/core/Menu";
  
const App = () => {
  const [anchorEl, setAnchorEl] = React.useState(null);
  
  const handleClose = () => {
    setAnchorEl(null);
  };
  
  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };
  
  return (
    <div
      style={{
        marginLeft: "40%",
      }}
    >
      <h2>TecFlix</h2>
      <form action="App2.js">
            <button>Volver </button>
      </form>
      <Button
        aria-controls="simple-menu"
        aria-haspopup="true"
        onClick={handleClick}
      >
        Modificiones
      </Button>
      <Menu
        keepMounted
        anchorEl={anchorEl}
        onClose={handleClose}
        open={Boolean(anchorEl)}
      >
        <MenuItem onClick={handleClose}>Agregar</MenuItem>
        <MenuItem onClick={handleClose}>Modificar</MenuItem>
        <MenuItem onClick={handleClose}>Eliminar</MenuItem>

      </Menu>
    </div>
  );
};
  
export default App;