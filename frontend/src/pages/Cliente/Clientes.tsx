import { useEffect, useState } from 'react';
import Paper from '@mui/material/Paper';
import { Alert, Button, Snackbar, Typography } from '@mui/material';
import { Cliente, useClienteService } from '../../service/clienteService';
import { DataGrid, GridColDef } from '@mui/x-data-grid';
import { useNavigate } from 'react-router-dom';


const paginationModel = { page: 0, pageSize: 5 };

const headers: GridColDef[] = [
    { field: 'id', headerName: 'ID', width: 70 },
    { field: 'nome', headerName: 'Nome', width: 130 },
    {
      field: 'cpf',
      headerName: 'CPF',
      width: 90,
    },
    {
        field: 'email',
        headerName: 'Email',
        width: 190,
    },
    {
        field: 'cor',
        headerName: 'Cor',
        width: 90,
    }
  ];

const Clientes = () => {

    const [clientes, setClientes] = useState<Cliente[]>([]);
    const [selectedCliente, setSelectedCliente] = useState<Cliente | null>(null);
    const [openSnackbar, setOpenSnackbar] = useState(false); 

    const { list, deleteById } = useClienteService()
    const navigate = useNavigate();

    useEffect( () => {
        async function init() {
            try {
                const { data: { content } } = await list();
                setClientes(content)    
            } catch (error) {
                console.log(error)
            }
        }
        
        init();
    
    }, [list])

    const onClickNavigateToFormCadastro = () => {
        navigate("/cadastro")
    }

    const onEditCliente = () => {
        navigate(`/cadastro/${selectedCliente?.id}`, { state: { cliente: selectedCliente } })
    }

    const onDeleteCliente =  async (id: number) => {
        try {
            await deleteById(id);
            const novaLista = clientes.filter( cliente => cliente.id !== id)
            setClientes(novaLista)
            setOpenSnackbar(true);    
        } catch (error) {
            console.log(error)
        }
    }

  return (
    <>
    <Typography variant="h4">Gestão de Clientes</Typography>
    <Button variant="contained" color="primary" onClick={onClickNavigateToFormCadastro} sx={{ mt: 2, mb: 2 }}>
        Novo Cliente
    </Button>
    <Button
        variant="contained"
        color="secondary"
        onClick={onEditCliente}
        disabled={!selectedCliente}
        sx={{ mt: 2, mb: 2, ml: 2 }}
      >
        Editar Cliente
    </Button>
    <Button
        variant="contained"
        color="error"
        onClick={ () => onDeleteCliente( Number(selectedCliente?.id))}
        disabled={!selectedCliente}
    sx={{ mt: 2, mb: 2, ml: 2 }}
    >
    Deletar Cliente
    </Button>
    <Paper sx={{ height: 400, width: '100%' }}>
        <DataGrid
            rows={clientes}
            columns={headers}
            initialState={{ pagination: { paginationModel } }}
            pageSizeOptions={[5, 10]}
            checkboxSelection={false}
            sx={{ border: 0 }}
            onRowSelectionModelChange={(ids) => {
                const clienteSelecionado = clientes.find((cliente) => cliente.id === ids[0]);
                setSelectedCliente(clienteSelecionado || null);
              }}
        />
        <Snackbar open={openSnackbar} autoHideDuration={4000} onClose={() => setOpenSnackbar(false)}>
            <Alert onClose={() => setOpenSnackbar(false)} severity="success" sx={{ width: "100%" }}>
            Cadastro removido com sucesso!
            </Alert>
        </Snackbar>
    </Paper>
    </>
  );
};

export default Clientes;
