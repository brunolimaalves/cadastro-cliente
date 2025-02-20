
import * as yup from "yup";

export const schema = yup.object({
    nome: yup.string().required("Nome é obrigatório").min(3, "O nome deve ter pelo menos 3 caracteres"),
    email: yup.string().email("Email inválido").required("Email é obrigatório"),
    observacao: yup.string().optional(),
    cor: yup.string().required("Selecione uma cor"),
    cpf: yup
      .string()
      .required("CPF é obrigatório")
      .matches(/^\d{3}\.\d{3}\.\d{3}-\d{2}$/, "Formato de CPF inválido"),
  });

  export default schema;