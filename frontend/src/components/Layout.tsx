import { Outlet, useNavigate } from "react-router-dom";
import { AppBar, Toolbar, Typography, Drawer, List, ListItem, ListItemButton, ListItemText } from "@mui/material";

const drawerWidth = 240;

const Layout = () => {
  const navigate = useNavigate();
  const menuItems = [
    { text: "Home", path: "/" },
    { text: "Clientes", path: "/clientes" },
  ];

  return (
    <div style={{ display: "flex" }}>
      <Drawer
        variant="permanent"
        sx={{
          width: drawerWidth,
          flexShrink: 0,
          "& .MuiDrawer-paper": { width: drawerWidth, boxSizing: "border-box" },
        }}
      >
        <Toolbar />
        <List>
          {menuItems.map((item) => (
            <ListItem key={item.text} disablePadding>
              <ListItemButton onClick={() => navigate(item.path)}>
                <ListItemText primary={item.text} />
              </ListItemButton>
            </ListItem>
          ))}
        </List>
      </Drawer>
      <div style={{ flexGrow: 1, padding: "20px", marginLeft: drawerWidth }}>
        <AppBar position="fixed" sx={{ width: `calc(100% - ${drawerWidth}px)`, ml: `${drawerWidth}px` }}>
          <Toolbar>
            <Typography variant="h6" noWrap component="div">
              SICC - Sistema de Cadastro de Cliente
            </Typography>
          </Toolbar>
        </AppBar>
        <div style={{ marginTop: "64px", padding: "20px" }}>
          <Outlet />
        </div>
      </div>
    </div>
  );
};

export default Layout;
