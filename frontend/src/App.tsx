import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Layout from "./components/Layout";
import Home from "./pages/Home";
import Clientes from "./pages/Cliente/Clientes";
import ClienteForm from "./pages/Cliente/ClienteForm";

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="clientes" element={<Clientes />} />
          <Route path="cadastro" element={<ClienteForm />} />
          <Route path="cadastro/:id" element={<ClienteForm />} />
        </Route>
      </Routes>
    </Router>
  );
};

export default App;
