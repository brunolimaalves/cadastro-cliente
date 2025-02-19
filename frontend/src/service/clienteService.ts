import { useCallback } from "react";
import api from "./api";

export interface Cliente {
    id: number;
    nome: string;
    email: string;
    cpf: string;
    observacao: string;
    cor: string;
  }
  
const path = "/clientes"

export const useClienteService = () => {
    
    const list = useCallback(async ({ params }: any) => {
        return api.get(path, params );
    }, [api]);

    const post = useCallback(async ( data: any) => {
        return api.post(`${path}`, { ...data });
    }, [api]);

    const update = useCallback(async ( data: any, id: number) => {
        return api.put(`${path}/${id}`, { ...data });
    }, [api]);

    const deleteById = useCallback(async ( id: number) => {
        return api.delete(`${path}/${id}`);
    }, [api]);

    return {
        list,post,update, deleteById
    }
}

  