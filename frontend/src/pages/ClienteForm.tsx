import { useForm, Controller } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";
import { TextField, Button, Box, Typography, FormControl, InputLabel, Select, MenuItem, Snackbar, Alert, CircularProgress, Dialog, DialogTitle, DialogContent, DialogActions } from "@mui/material";
import InputMask from "react-input-mask";
import { useClienteService } from "../service/clienteService";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import { useState } from "react";

const schema = yup.object({
  nome: yup.string().required("Nome é obrigatório").min(3, "O nome deve ter pelo menos 3 caracteres"),
  email: yup.string().email("Email inválido").required("Email é obrigatório"),
  observacao: yup.string().optional(),
  cor: yup.string().required("Selecione uma cor"),
  cpf: yup
    .string()
    .required("CPF é obrigatório")
    .matches(/^\d{3}\.\d{3}\.\d{3}-\d{2}$/, "Formato de CPF inválido"),
});

type FormData = {
  id?: number; 
  nome: string;
  cpf: string;
  email: string;
  observacao?: string;
  cor: string;
};

const ClienteForm = () => {
    
    const { id } = useParams();
    const location = useLocation(); 
    const { post, update } = useClienteService()
    const clienteSelecionado = location.state?.cliente; 
    const navigate = useNavigate();
    const [openSnackbar, setOpenSnackbar] = useState(false); 
    const [loading, setLoading] = useState(false);
    const [errorMessage, setErrorMessage] = useState(""); 
    const [openErrorModal, setOpenErrorModal] = useState(false); 

    const {
        control,
        handleSubmit,
        formState: { errors },
        reset
    } = useForm<FormData>({
        resolver: yupResolver(schema),
        defaultValues: clienteSelecionado || {
            nome: "",
            cpf: "",
            email: "",
            cor: "",
            observacao: "",
        }
    });

  const onSubmit = async (data: FormData) => {
    try {
        setLoading(true);
        const promise = !id ? post(data) : update(data, Number(id))
        await promise
        reset();
        setOpenSnackbar(true); 
    }catch(error: any) {
      setErrorMessage(error.response?.data?.message || "Ocorreu um erro inesperado.");
      setOpenErrorModal(true);
    }finally{
        setLoading(false);
    }
    
  };

  return (
    <Box
      component="form"
      onSubmit={handleSubmit(onSubmit)}
      sx={{ display: "flex", flexDirection: "column", gap: 2, maxWidth: 400, margin: "auto", mt: 5 }}
    >
      <Typography variant="h5" textAlign="center">
        Cadastro de Clientes
      </Typography>

      <Controller
        name="nome"
        control={control}
        defaultValue=""
        render={({ field }) => (
          <TextField
            {...field}
            label="Nome"
            variant="outlined"
            error={!!errors.nome}
            helperText={errors.nome?.message}
            fullWidth
          />
        )}
      />

    <Controller
        name="cpf"
        control={control}
        defaultValue=""
        render={({ field }) => (
          <TextField
            {...field}
            label="CPF"
            variant="outlined"
            error={!!errors.cpf}
            helperText={errors.cpf?.message}
            fullWidth
          />
        )}
      />

    <Controller
        name="email"
        control={control}
        defaultValue=""
        render={({ field }) => (
          <TextField
            {...field}
            label="Email"
            variant="outlined"
            error={!!errors.email}
            helperText={errors.email?.message}
            fullWidth
          />
        )}
      />

    <Controller
        name="cor"
        control={control}
        defaultValue=""
        render={({ field }) => (
          <FormControl fullWidth error={!!errors.cor}>
            <InputLabel>Cor</InputLabel>
            <Select {...field} label="Cor">
              <MenuItem value="vermelho">Vermelho</MenuItem>
              <MenuItem value="azul">Azul</MenuItem>
              <MenuItem value="verde">Verde</MenuItem>
            </Select>
            <Typography variant="body2" color="error">
              {errors.cor?.message}
            </Typography>
          </FormControl>
        )}
      />

<Controller
        name="observacao"
        control={control}
        defaultValue=""
        render={({ field }) => (
          <TextField
            {...field}
            label="Observacao"
            variant="outlined"
            error={!!errors.observacao}
            helperText={errors.observacao?.message}
            fullWidth
          />
        )}
      />

      <Button type="submit" variant="contained" color="primary">
      {loading ? <CircularProgress size={24} color="inherit" /> : !id ? 'Cadastrar' : 'Atualizar'}
      </Button>

      <Button type="button" variant="contained" color="primary" onClick={ () => navigate("/clientes")}>
        Voltar
      </Button>

      <Snackbar open={openSnackbar} autoHideDuration={4000} onClose={() => setOpenSnackbar(false)}>
        <Alert onClose={() => setOpenSnackbar(false)} severity="success" sx={{ width: "100%" }}>
        {`Cadastro ${!id ? 'cadastrado' : 'atualizado'} com sucesso`}
        </Alert>
      </Snackbar>

      <Dialog open={openErrorModal} onClose={() => setOpenErrorModal(false)}>
        <DialogTitle>Erro</DialogTitle>
        <DialogContent>
          <Typography color="error">{errorMessage}</Typography>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setOpenErrorModal(false)} color="primary">
            Fechar
          </Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};

export default ClienteForm;
