import { useCallback } from "react";
import api from "./api";

export interface Cliente {
    id?: number; 
    nome: string;
    cpf: string;
    email: string;
    observacao?: string;
    cor: string;
  }
  
const path = "/clientes"

export const useClienteService = () => {
    
    const list = useCallback(async () => {
        return api.get(path);
    }, []);

    const post = useCallback(async ( data: Cliente) => {
        return api.post(`${path}`, { ...data });
    }, []);

    const update = useCallback(async ( data: Cliente, id: number) => {
        return api.put(`${path}/${id}`, { ...data });
    }, []);

    const deleteById = useCallback(async ( id: number) => {
        return api.delete(`${path}/${id}`);
    }, []);

    return {
        list,post,update, deleteById
    }
}

  